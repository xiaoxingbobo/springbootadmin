package com.xxbb.springbootapi.utils.delayqueue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@Slf4j
@Component
public class DelayQueueService {

    // 延时队列
    private final DelayQueue<DelayTask<?>> delayQueue = new DelayQueue<>();

    // 任务处理器映射
    private final Map<String, Consumer<?>> taskHandlers = new ConcurrentHashMap<>();

    // 任务映射表 - 用于快速查找和防止重复
    private final Map<String, DelayTask<?>> taskMap = new ConcurrentHashMap<>();

    // 消费线程
    private Thread consumerThread;

    // 是否运行
    private volatile boolean running = true;

    /**
     * 添加延时任务
     *
     * @param taskId    任务ID，用于防止重复添加
     * @param data      任务数据
     * @param type      任务类型
     * @param delayTime 延迟时间
     * @param timeUnit  时间单位
     * @return 是否成功添加（如果任务已存在则返回false）
     */
    public <T> boolean addTask(String taskId, T data, String type, long delayTime, TimeUnit timeUnit) {
        // 检查任务ID是否为空
        if (taskId == null || taskId.trim().isEmpty()) {
            throw new IllegalArgumentException("任务ID不能为空");
        }

        // 检查任务是否已存在
        if (taskMap.containsKey(taskId)) {
            log.warn("任务已存在，不重复添加: {}", taskId);
            return false;
        }

        // 创建任务
        DelayTask<T> task = new DelayTask<>(taskId, data, type, delayTime, timeUnit);

        // 添加到队列和映射表
        delayQueue.put(task);
        taskMap.put(taskId, task);

        log.info("添加延时任务: {}, 类型: {}, 延时: {}ms", taskId, type, timeUnit.toMillis(delayTime));
        return true;
    }

    /**
     * 添加延时任务（自动生成任务ID）
     */
    public <T> String addTask(T data, String type, long delayTime, TimeUnit timeUnit) {
        // 生成任务ID
        String taskId = generateTaskId(type, data);

        // 添加任务
        addTask(taskId, data, type, delayTime, timeUnit);

        return taskId;
    }

    /**
     * 生成任务ID
     */
    private <T> String generateTaskId(String type, T data) {
        return type + "_" + System.currentTimeMillis() + "_" + Math.abs(Objects.hashCode(data));
    }

    /**
     * 检查任务是否存在
     *
     * @param taskId 任务ID
     * @return 是否存在
     */
    public boolean isTaskExists(String taskId) {
        return taskMap.containsKey(taskId);
    }

    /**
     * 取消任务
     *
     * @param taskId 任务ID
     * @return 是否成功取消
     */
    public boolean cancelTask(String taskId) {
        DelayTask<?> task = taskMap.remove(taskId);
        if (task != null) {
            boolean removed = delayQueue.remove(task);
            log.info("取消任务: {}, 结果: {}", taskId, removed);
            return removed;
        }
        return false;
    }

    /**
     * 获取所有任务ID
     *
     * @return 任务ID列表
     */
    public List<String> getAllTaskIds() {
        return new ArrayList<>(taskMap.keySet());
    }

    /**
     * 获取指定类型的所有任务ID
     *
     * @param type 任务类型
     * @return 任务ID列表
     */
    public List<String> getTaskIdsByType(String type) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, DelayTask<?>> entry : taskMap.entrySet()) {
            if (entry.getValue().getType().equals(type)) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    /**
     * 清空所有任务
     */
    public void clearAllTasks() {
        delayQueue.clear();
        taskMap.clear();
        log.info("清空所有任务");
    }

    /**
     * 注册任务处理器
     *
     * @param type    任务类型
     * @param handler 处理器
     */
    public <T> void registerHandler(String type, Consumer<T> handler) {
        taskHandlers.put(type, handler);
        log.info("注册任务处理器: {}", type);
    }

    /**
     * 启动任务消费线程
     */
    @PostConstruct
    public void start() {
        consumerThread = new Thread(() -> {
            log.info("延时队列消费线程启动");
            while (running) {
                try {
                    DelayTask<?> task = delayQueue.take();
                    log.info("处理延时任务: {}", task.getTaskId());
                    processTask(task);
                } catch (InterruptedException e) {
                    if (running) {
                        log.error("延时队列消费线程被中断", e);
                    } else {
                        log.info("延时队列消费线程停止");
                        break;
                    }
                } catch (Exception e) {
                    log.error("处理延时任务异常", e);
                }
            }
        }, "delay-queue-consumer");

        consumerThread.setDaemon(true);
        consumerThread.start();
    }

    /**
     * 处理任务
     */
    @SuppressWarnings("unchecked")
    private <T> void processTask(DelayTask<T> task) {
        // 从映射表中移除任务
        taskMap.remove(task.getTaskId());

        Consumer<T> handler = (Consumer<T>) taskHandlers.get(task.getType());
        if (handler != null) {
            try {
                handler.accept(task.getData());
                log.info("任务处理成功: {}", task.getTaskId());
            } catch (Exception e) {
                log.error("任务处理失败: {}, 异常: {}", task.getTaskId(), e.getMessage(), e);
            }
        } else {
            log.warn("未找到任务处理器: {}, 任务ID: {}", task.getType(), task.getTaskId());
        }
    }

    /**
     * 停止延时队列
     */
    @PreDestroy
    public void stop() {
        running = false;
        if (consumerThread != null) {
            consumerThread.interrupt();
            try {
                consumerThread.join(3000); // 等待最多3秒
            } catch (InterruptedException e) {
                log.warn("等待消费线程停止被中断", e);
            }
        }
        log.info("延时队列服务已停止");
    }

    /**
     * 获取队列大小
     */
    public int getQueueSize() {
        return delayQueue.size();
    }

    /**
     * 获取任务信息
     *
     * @param taskId 任务ID
     * @return 任务信息
     */
    public DelayTaskInfo getTaskInfo(String taskId) {
        DelayTask<?> task = taskMap.get(taskId);
        if (task == null) {
            return null;
        }

        return new DelayTaskInfo(
                task.getTaskId(),
                task.getType(),
                task.getExecuteTime(),
                System.currentTimeMillis(),
                task.getDelay(TimeUnit.MILLISECONDS)
        );
    }

    /**
     * 任务信息类（用于向外部返回任务状态信息）
     */
    public static class DelayTaskInfo {
        private final String taskId;
        private final String type;
        private final long executeTime;
        private final long currentTime;
        private final long remainingTime;

        public DelayTaskInfo(String taskId, String type, long executeTime, long currentTime, long remainingTime) {
            this.taskId = taskId;
            this.type = type;
            this.executeTime = executeTime;
            this.currentTime = currentTime;
            this.remainingTime = remainingTime;
        }

        public String getTaskId() {
            return taskId;
        }

        public String getType() {
            return type;
        }

        public long getExecuteTime() {
            return executeTime;
        }

        public long getCurrentTime() {
            return currentTime;
        }

        public long getRemainingTime() {
            return remainingTime;
        }

        @Override
        public String toString() {
            return "DelayTaskInfo{" +
                    "taskId='" + taskId + '\'' +
                    ", type='" + type + '\'' +
                    ", executeTime=" + executeTime +
                    ", currentTime=" + currentTime +
                    ", remainingTime=" + remainingTime +
                    '}';
        }
    }
}
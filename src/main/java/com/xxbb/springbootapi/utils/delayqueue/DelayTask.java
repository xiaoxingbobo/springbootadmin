package com.xxbb.springbootapi.utils.delayqueue;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Getter
@ToString
public class DelayTask<T> implements Delayed {

    // 任务ID
    private final String taskId;

    // 任务数据
    private final T data;

    // 任务类型
    private final String type;

    // 执行时间点（毫秒时间戳）
    private final long executeTime;

    // 创建时间
    private final long createTime;

    /**
     * 创建延时任务
     *
     * @param taskId    任务ID
     * @param data      任务数据
     * @param type      任务类型
     * @param delayTime 延迟时间
     * @param timeUnit  时间单位
     */
    public DelayTask(String taskId, T data, String type, long delayTime, TimeUnit timeUnit) {
        this.taskId = taskId;
        this.data = data;
        this.type = type;
        this.createTime = System.currentTimeMillis();
        this.executeTime = this.createTime + timeUnit.toMillis(delayTime);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(executeTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(this.executeTime, ((DelayTask<?>) o).executeTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DelayTask<?> that = (DelayTask<?>) o;
        return Objects.equals(taskId, that.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId);
    }
}
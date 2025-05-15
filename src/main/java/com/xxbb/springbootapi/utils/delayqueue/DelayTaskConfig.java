package com.xxbb.springbootapi.utils.delayqueue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@RequiredArgsConstructor
public class DelayTaskConfig {


    // 任务类型常量
    public static final String TASK_PAY_TIMEOUT_CANCEL = "TASK_PAY_TIMEOUT_CANCEL";
    public static final String TASK_IN_PROGRESS = "TASK_IN_PROGRESS";


    private final DelayQueueService delayQueueService;

    @PostConstruct
    public void init() {
        log.info("延时任务配置初始化");

        // 支付超时自动取消
        delayQueueService.registerHandler(TASK_PAY_TIMEOUT_CANCEL, (Integer billId) -> {
            log.info("支付超时自动取消: {}", billId);
            // 调用支付超时取消订单
        });


        // 修改预约中为进行中
        delayQueueService.registerHandler(TASK_IN_PROGRESS, (Integer billId) -> {
            log.info("修改预约中为进行中: {}", billId);
            // 调用支付超时取消订单
        });
    }
    //使用示例
    // Integer billId = 123;
    // delayQueueService.addTask(DelayTaskConfig.TASK_PAY_TIMEOUT_CANCEL+"111", billId, DelayTaskConfig.TASK_PAY_TIMEOUT_CANCEL, 10, TimeUnit.SECONDS);

}
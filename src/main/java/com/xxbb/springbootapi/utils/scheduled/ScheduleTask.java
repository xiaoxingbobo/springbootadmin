package com.xxbb.springbootapi.utils.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@EnableScheduling    //开启定时任务
@Slf4j
public class ScheduleTask {


    //定时器，每5分钟执行一次
    @Scheduled(fixedRate = 300000)
    private void updateOrder() {
        log.info("当前时间：{}，定时任务执行", new Date());
        //获取当前时间
        Date nowDate = new Date();
        //lt:小于，gt：大于，eq：等于，ne：不等于，le：小于等于，ge：大于等于

    }
}
package com.kaishengit;

import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;

public class TaskTest {

    @Test
    public void test() throws SchedulerException, IOException {
        //定义任务
        JobDetail jobDetail = JobBuilder.newJob(Task.class).build();

        //定义触发形式
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
        simpleScheduleBuilder.withIntervalInSeconds(5);
        simpleScheduleBuilder.repeatForever();
        //根据除发行时定义触发器
        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(simpleScheduleBuilder).build();
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();
        System.in.read();

    }

}

package com.kaishengit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-quartz.xml")
public class MyJobTest {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Test
    public void hello() throws IOException {
        System.out.println("测试开始");
        System.in.read();
    }


    @Test
    public void addJob() throws SchedulerException, IOException {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("test","jiang");
        JobDetail jobDetail =  JobBuilder.newJob(MyJob.class).withIdentity("gfhgfd","1.0").setJobData(jobDataMap).build();
        String cron = "0/5 * * * * ? *";
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(cronScheduleBuilder).build();

        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();
        System.in.read();
    }
}

package com.alibaba.monitor.quartz.pojos;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/08/29
 */
public class CommonJob implements Job {

    Logger logger = LoggerFactory.getLogger(CommonJob.class);

    /**
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("定时任务执行 ： " + new Date());
        logger.error("定时任务执行 ： " + String.valueOf(new Date()));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("定时任务执行完成 ： " + new Date());
        logger.error("定时任务执行完成 ： " + String.valueOf(new Date()));
    }
}

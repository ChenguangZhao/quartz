package com.alibaba.monitor.quartz.service;

import org.quartz.SchedulerException;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/08/29
 */
public interface JobService {
    /**
     * @param name
     * @param group
     */
    void addJob(String name, String group) throws SchedulerException;

    /**
     * @param name
     * @param group
     */
    boolean triggerJob(String name, String group) throws SchedulerException;

    /**
     * @param name
     * @param group
     * @return
     */
    boolean pauseJob(String name, String group) throws SchedulerException;

    /**
     * @param name
     * @param group
     * @return
     */
    boolean resumeJob(String name, String group) throws SchedulerException;

    /**
     * @param name
     * @param group
     * @param cronExpression
     * @return
     */
    boolean rescheduleJob(String name, String group, String cronExpression) throws SchedulerException;
}

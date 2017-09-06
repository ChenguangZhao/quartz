package com.alibaba.monitor.quartz.service;

import com.alibaba.monitor.quartz.pojos.vo.JobInfoVO;

import org.quartz.SchedulerException;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/08/29
 */
public interface SchedulerService {
    /**
     * @param jobInfoVO
     * @throws SchedulerException
     */
    void addJob(JobInfoVO jobInfoVO) throws SchedulerException;

    /**
     * @param name
     * @param group
     */
    boolean triggerJob(String name, String group) throws SchedulerException;

    /**
     * @param jobInfoVO
     * @return
     */
    boolean pauseJob(JobInfoVO jobInfoVO) throws Exception;

    /**
     * @param jobInfoVO
     * @return
     */
    boolean resumeJob(JobInfoVO jobInfoVO) throws Exception;

    /**
     * @param jobInfoVO
     * @return
     */
    boolean rescheduleJob(JobInfoVO jobInfoVO) throws Exception;
}

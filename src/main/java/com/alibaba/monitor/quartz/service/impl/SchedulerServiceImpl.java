package com.alibaba.monitor.quartz.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import com.alibaba.monitor.quartz.pojos.CommonJob;
import com.alibaba.monitor.quartz.pojos.vo.JobInfoVO;
import com.alibaba.monitor.quartz.service.SchedulerService;

import jdk.nashorn.internal.scripts.JO;
import org.apache.commons.lang.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/08/29
 */
@Service
public class SchedulerServiceImpl implements SchedulerService {

    private final static String JOB_GROUP = "DEFAULT_GROUP";

    Logger logger = LoggerFactory.getLogger(SchedulerServiceImpl.class);

    @Resource(name = "quartzScheduler")
    Scheduler quartzScheduler;

    /**
     * @param jobInfoVO
     * @throws SchedulerException
     */
    @Override
    public void addJob(JobInfoVO jobInfoVO) throws SchedulerException {
        // TriggerKey : name + group
        String jobName = jobInfoVO.getName();
        String cronExpression = jobInfoVO.getCronExpression();

        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, JOB_GROUP);
        JobKey jobKey = new JobKey(jobName, JOB_GROUP);

        // TriggerKey valid if_exists
        if (checkExists(jobName, JOB_GROUP)) {
            logger.info(">>>>>>>>> addJob fail, job already exist, jobGroup:{}, jobName:{}", JOB_GROUP, jobName);
            return;
        }

        // CronTrigger : TriggerKey + cronExpression	// withMisfireHandlingInstructionDoNothing 忽略掉调度终止过程中忽略的调度
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression)
            .withMisfireHandlingInstructionDoNothing();
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(cronScheduleBuilder)
            .build();

        // JobDetail : jobClass
        Class<? extends Job> jobClass_ = CommonJob.class;   // Class.forName(jobInfo.getJobClass());

        JobDetail jobDetail = JobBuilder.newJob(jobClass_).withIdentity(jobKey).build();

        Date date = quartzScheduler.scheduleJob(jobDetail, cronTrigger);
        System.out.println(date);
    }

    /**
     * @param jobName
     * @param jobGroup
     */
    @Override
    public boolean triggerJob(String jobName, String jobGroup) throws SchedulerException {
        JobKey jobKey = new JobKey(jobName, jobGroup);

        boolean result = false;
        if (checkExists(jobName, jobGroup)) {
            quartzScheduler.triggerJob(jobKey);
            result = true;
            logger.info(">>>>>>>>>>> runJob success, jobKey:{}", jobKey);
        } else {
            logger.info(">>>>>>>>>>> runJob fail, jobKey:{}", jobKey);
        }
        return result;
    }

    /**
     * 暂停
     *
     * @param jobInfoVO
     * @return
     */
    @Override
    public boolean pauseJob(JobInfoVO jobInfoVO) throws Exception {
        if (jobInfoVO == null || StringUtils.isEmpty(jobInfoVO.getName())) {
            throw new Exception("job info vo or name is null");
        }
        // TriggerKey : name + group
        TriggerKey triggerKey = TriggerKey.triggerKey(jobInfoVO.getName(), JOB_GROUP);

        boolean result = false;
        if (checkExists(jobInfoVO.getName(), JOB_GROUP)) {
            quartzScheduler.pauseTrigger(triggerKey);
            result = true;
            logger.info(">>>>>>>>>>> pauseJob success, triggerKey:{}", triggerKey);
        } else {
            logger.info(">>>>>>>>>>> pauseJob fail, triggerKey:{}", triggerKey);
        }
        return result;
    }

    /**
     * @param jobInfoVO
     * @return
     */
    @Override
    public boolean resumeJob(JobInfoVO jobInfoVO) throws Exception {
        if (jobInfoVO == null || StringUtils.isEmpty(jobInfoVO.getName())) {
            throw new Exception("job info vo or name is null");
        }
        // TriggerKey : name + group
        TriggerKey triggerKey = TriggerKey.triggerKey(jobInfoVO.getName(), JOB_GROUP);

        boolean result = false;
        if (checkExists(jobInfoVO.getName(), JOB_GROUP)) {
            quartzScheduler.resumeTrigger(triggerKey);
            result = true;
            logger.info(">>>>>>>>>>> resumeJob success, triggerKey:{}", triggerKey);
        } else {
            logger.info(">>>>>>>>>>> resumeJob fail, triggerKey:{}", triggerKey);
        }
        return result;
    }

    /**
     * @param jobInfoVO
     * @return
     * @throws SchedulerException
     */
    @Override
    public boolean rescheduleJob(JobInfoVO jobInfoVO) throws Exception {

        if (jobInfoVO == null || StringUtils.isEmpty(jobInfoVO.getName())) {
            throw new Exception("job info vo or name is null");
        }

        if (!checkExists(jobInfoVO.getName(), JOB_GROUP)) {
            logger.info(">>>>>>>>>>> rescheduleJob fail, job not exists, JobGroup:{}, JobName:{}", jobInfoVO.getName(), JOB_GROUP);
            return false;
        }

        TriggerKey triggerKey = TriggerKey.triggerKey(jobInfoVO.getName(), JOB_GROUP);
        CronTrigger oldTrigger = (CronTrigger)quartzScheduler.getTrigger(triggerKey);
        if (oldTrigger == null) {
            logger.info(">>>>>>>>>>> rescheduleJob fail, job not exists, JobName:{}, JobGroup:{}", jobInfoVO.getName(), JOB_GROUP);
            return false;
        }

        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(jobInfoVO.getCronExpression())
            .withMisfireHandlingInstructionDoNothing();
        oldTrigger = oldTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();

        // rescheduleJob
        quartzScheduler.rescheduleJob(triggerKey, oldTrigger);
        logger.info(">>>>>>>>>>> rescheduleJob success, JobName:{}, JobGroup:{}", jobInfoVO.getName(), JOB_GROUP);
        return true;
    }

    /**
     * check exists
     *
     * @param jobName
     * @param jobGroup
     * @return
     * @throws SchedulerException
     */
    public boolean checkExists(String jobName, String jobGroup) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        return quartzScheduler.checkExists(triggerKey);
    }
}

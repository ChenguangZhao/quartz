package com.alibaba.monitor.quartz.controller;

import java.util.List;

import com.alibaba.monitor.quartz.pojos.AjaxResult;
import com.alibaba.monitor.quartz.pojos.vo.JobInfoVO;
import com.alibaba.monitor.quartz.service.JobService;
import com.alibaba.monitor.quartz.service.SchedulerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/08/25
 */
@Controller
@RequestMapping(value = "/job/")
public class JobController {

    @Autowired
    SchedulerService schedulerService;

    @Autowired
    JobService jobService;

    /**
     * 新增job
     *
     * @param callback
     * @return
     */
    @RequestMapping(value = "/addJob.do")
    @ResponseBody
    public AjaxResult addJob(String callback, JobInfoVO jobInfoVO) {
        try {
            schedulerService.addJob(jobInfoVO);
            jobService.addJob(jobInfoVO);
            return AjaxResult.succResult(callback);

        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.errResult(callback, e.getMessage());
        }
    }

    /**
     * 即时触发
     *
     * @param callback
     * @return
     */
    @RequestMapping(value = "/triggerJob.do")
    @ResponseBody
    public AjaxResult triggerJob(String callback, @RequestParam("name") String name,
                                 @RequestParam("group") String group) {
        try {
            if (schedulerService.triggerJob(name, group)) {
                return AjaxResult.succResult(callback);
            } else {
                return AjaxResult.errResult(callback, "执行失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.errResult(callback, e.getMessage());
        }
    }

    /**
     * @param callback
     * @param jobInfoVO
     * @return
     */
    @RequestMapping(value = "/pauseJob.do")
    @ResponseBody
    public AjaxResult pauseJob(String callback, JobInfoVO jobInfoVO) {
        try {
            if (schedulerService.pauseJob(jobInfoVO)) {
                return AjaxResult.succResult(callback);
            } else {
                return AjaxResult.errResult(callback, "执行失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.errResult(callback, e.getMessage());
        }
    }

    /**
     * resume Job
     *
     * @param callback
     * @param jobInfoVO
     * @return
     */
    @RequestMapping(value = "/resumeJob.do")
    @ResponseBody
    public AjaxResult resumeJob(String callback, JobInfoVO jobInfoVO) {
        try {
            if (schedulerService.resumeJob(jobInfoVO)) {
                return AjaxResult.succResult(callback);
            } else {
                return AjaxResult.errResult(callback, "执行失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.errResult(callback, e.getMessage());
        }
    }

    /**
     * 修改 job cronExpression
     *
     * @param callback
     * @param jobInfoVO
     * @return
     */
    @RequestMapping(value = "/rescheduleJob.do")
    @ResponseBody
    public AjaxResult rescheduleJob(String callback,JobInfoVO jobInfoVO) {
        try {
            if (schedulerService.rescheduleJob(jobInfoVO)) {
                jobService.editJob(jobInfoVO);
                return AjaxResult.succResult(callback);
            } else {
                return AjaxResult.errResult(callback, "执行失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.errResult(callback, e.getMessage());
        }
    }

    /**
     * @param callback
     * @return
     */
    @RequestMapping(value = "/queryJob.do")
    @ResponseBody
    public AjaxResult queryJob(String callback) {
        try {
            List<JobInfoVO> jobInfoVOList = jobService.queryJob();
            return AjaxResult.succResult(callback, jobInfoVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.errResult(callback, e.getMessage());
        }
    }
}

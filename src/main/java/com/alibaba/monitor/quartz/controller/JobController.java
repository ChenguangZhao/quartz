package com.alibaba.monitor.quartz.controller;

import com.alibaba.monitor.quartz.pojos.AjaxResult;
import com.alibaba.monitor.quartz.service.JobService;

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
public class JobController {

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
    public AjaxResult addJob(String callback, @RequestParam("name") String name,
                             @RequestParam("group") String group) {
        try {
            jobService.addJob(name, group);
            return AjaxResult.succResult(callback);

        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.errResult(callback, e.getMessage());
        }
    }

    /**
     * 新增job
     *
     * @param callback
     * @return
     */
    @RequestMapping(value = "/triggerJob.do")
    @ResponseBody
    public AjaxResult triggerJob(String callback, @RequestParam("name") String name,
                                 @RequestParam("group") String group) {
        try {
            if (jobService.triggerJob(name, group)) {
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
     * @param name
     * @param group
     * @return
     */
    @RequestMapping(value = "/pauseJob.do")
    @ResponseBody
    public AjaxResult pauseJob(String callback, @RequestParam("name") String name,
                               @RequestParam("group") String group) {
        try {
            if (jobService.pauseJob(name, group)) {
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
     * resumeJob
     *
     * @param callback
     * @param name
     * @param group
     * @return
     */
    @RequestMapping(value = "/resumeJob.do")
    @ResponseBody
    public AjaxResult resumeJob(String callback, @RequestParam("name") String name,
                                @RequestParam("group") String group) {
        try {
            if (jobService.resumeJob(name, group)) {
                return AjaxResult.succResult(callback);
            } else {
                return AjaxResult.errResult(callback, "执行失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.errResult(callback, e.getMessage());
        }
    }


    @RequestMapping(value = "/rescheduleJob.do")
    @ResponseBody
    public AjaxResult rescheduleJob(String callback, @RequestParam("name") String name,
                                @RequestParam("group") String group) {
        try {
            String cronExpression = "0/20 * * * * ?";

            if (jobService.rescheduleJob(name, group,cronExpression)) {
                return AjaxResult.succResult(callback);
            } else {
                return AjaxResult.errResult(callback, "执行失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.errResult(callback, e.getMessage());
        }
    }
}

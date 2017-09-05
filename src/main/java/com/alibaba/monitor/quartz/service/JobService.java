package com.alibaba.monitor.quartz.service;

import java.util.List;

import com.alibaba.monitor.quartz.pojos.vo.JobInfoVO;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/09/01
 */
public interface JobService {
    /**
     * @param jobInfoVO
     */
    void addJob(JobInfoVO jobInfoVO) throws Exception;

    /**
     * @return
     */
    List<JobInfoVO> queryJob();

    /**
     * @param jobInfoVO
     */
    void editJob(JobInfoVO jobInfoVO) throws Exception;
}

package com.alibaba.monitor.quartz.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.monitor.quartz.dao.JobInfoDao;
import com.alibaba.monitor.quartz.pojos.dataobject.JobInfoDO;
import com.alibaba.monitor.quartz.pojos.vo.JobInfoVO;
import com.alibaba.monitor.quartz.service.JobService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/09/01
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobInfoDao jobInfoDao;

    /**
     * @param jobInfoVO
     */
    @Override
    public void addJob(JobInfoVO jobInfoVO) throws Exception {
        if (jobInfoVO == null) {
            throw new Exception("job info vo is null");
        }

        JobInfoDO jobInfoDO = new JobInfoDO();
        BeanUtils.copyProperties(jobInfoVO, jobInfoDO);
        jobInfoDao.insert(jobInfoDO);
    }

    /**
     * @return
     */
    @Override
    public List<JobInfoVO> queryJob() {
        List<JobInfoDO> jobInfoDOList = jobInfoDao.selectList();
        List<JobInfoVO> jobInfoVOList = new ArrayList<JobInfoVO>();
        for (JobInfoDO jobInfoDO : jobInfoDOList) {
            JobInfoVO jobInfoVO = new JobInfoVO();
            BeanUtils.copyProperties(jobInfoDO, jobInfoVO);
            jobInfoVOList.add(jobInfoVO);
        }
        return jobInfoVOList;
    }

    /**
     *
     * @param jobInfoVO
     */
    @Override
    public void editJob(JobInfoVO jobInfoVO) throws Exception {
        if (jobInfoVO == null) {
            throw new Exception("job info vo is null");
        }
        JobInfoDO jobInfoDO = new JobInfoDO();
        BeanUtils.copyProperties(jobInfoVO,jobInfoDO);
        jobInfoDao.update(jobInfoDO);
    }
}

package com.alibaba.monitor.quartz.dao;

import java.util.List;

import com.alibaba.monitor.quartz.pojos.dataobject.JobInfoDO;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/09/01
 */
public interface JobInfoDao {

    /**
     * @param jobInfoDO
     */
    void insert(JobInfoDO jobInfoDO);

    /**
     * @return
     */
    List<JobInfoDO> selectList();

    /**
     * @param jobInfoDO
     */
    void update(JobInfoDO jobInfoDO);
}

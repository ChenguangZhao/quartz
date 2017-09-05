package com.alibaba.monitor.quartz.pojos.dataobject;

import java.util.Date;

import com.alibaba.monitor.quartz.pojos.vo.JobInfoVO;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/09/01
 */
public class JobInfoDO extends JobInfoVO {

    private Integer isDeleted;

    private Date gmtCreate;

    private Date gmtModified;

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "JobInfoDO{" +
            "isDeleted=" + isDeleted +
            ", gmtCreate=" + gmtCreate +
            ", gmtModified=" + gmtModified +
            '}';
    }
}

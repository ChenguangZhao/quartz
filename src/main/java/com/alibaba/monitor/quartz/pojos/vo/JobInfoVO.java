package com.alibaba.monitor.quartz.pojos.vo;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/09/01
 */
public class JobInfoVO {

    private Long id;

    private String name;

    private String description;

    private String cronExpression;

    private Long nextFireTime;

    private Long prevFireTime;

    private String triggerState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Long getNextFireTime() {
        return nextFireTime;
    }

    public void setNextFireTime(Long nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    public Long getPrevFireTime() {
        return prevFireTime;
    }

    public void setPrevFireTime(Long prevFireTime) {
        this.prevFireTime = prevFireTime;
    }

    public String getTriggerState() {
        return triggerState;
    }

    public void setTriggerState(String triggerState) {
        this.triggerState = triggerState;
    }

    @Override
    public String toString() {
        return "JobInfoVO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", cronExpression='" + cronExpression + '\'' +
            ", nextFireTime=" + nextFireTime +
            ", prevFireTime=" + prevFireTime +
            ", triggerState='" + triggerState + '\'' +
            '}';
    }
}

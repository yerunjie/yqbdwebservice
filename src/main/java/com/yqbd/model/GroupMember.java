package com.yqbd.model;

import java.util.Date;

public class GroupMember extends GroupMemberKey {
    private Date participateTime;

    private Integer status;

    public Date getParticipateTime() {
        return participateTime;
    }

    public void setParticipateTime(Date participateTime) {
        this.participateTime = participateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
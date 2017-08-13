package com.yqbd.model;

public class CompanyTake {
    private Integer taskId;

    private Integer companyId;

    private Integer status;

    private Integer userCommentId;

    private Integer companyCommentId;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserCommentId() {
        return userCommentId;
    }

    public void setUserCommentId(Integer userCommentId) {
        this.userCommentId = userCommentId;
    }

    public Integer getCompanyCommentId() {
        return companyCommentId;
    }

    public void setCompanyCommentId(Integer companyCommentId) {
        this.companyCommentId = companyCommentId;
    }
}
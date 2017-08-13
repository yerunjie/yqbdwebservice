package com.yqbd.model;

import java.util.Date;

public class Task {
    private Integer taskId;

    private Integer companyId;

    private Integer userId;

    private String classification;

    private String taskTitle;

    private Integer taskStatus;

    private Double pay;

    private Date publishTime;

    private Date deadline;

    private Date startTime;

    private Date completeTime;

    private Integer signUpPeopleNumber;

    private Integer currentPeopleNumber;

    private Integer maxPeopleNumber;

    private String simpleDrawingAddress;

    private Integer groupId;

    private String province;

    private String city;

    private String district;

    private String taskAddress;

    private String taskDescription;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification == null ? null : classification.trim();
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle == null ? null : taskTitle.trim();
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Double getPay() {
        return pay;
    }

    public void setPay(Double pay) {
        this.pay = pay;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public Integer getSignUpPeopleNumber() {
        return signUpPeopleNumber;
    }

    public void setSignUpPeopleNumber(Integer signUpPeopleNumber) {
        this.signUpPeopleNumber = signUpPeopleNumber;
    }

    public Integer getCurrentPeopleNumber() {
        return currentPeopleNumber;
    }

    public void setCurrentPeopleNumber(Integer currentPeopleNumber) {
        this.currentPeopleNumber = currentPeopleNumber;
    }

    public Integer getMaxPeopleNumber() {
        return maxPeopleNumber;
    }

    public void setMaxPeopleNumber(Integer maxPeopleNumber) {
        this.maxPeopleNumber = maxPeopleNumber;
    }

    public String getSimpleDrawingAddress() {
        return simpleDrawingAddress;
    }

    public void setSimpleDrawingAddress(String simpleDrawingAddress) {
        this.simpleDrawingAddress = simpleDrawingAddress == null ? null : simpleDrawingAddress.trim();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getTaskAddress() {
        return taskAddress;
    }

    public void setTaskAddress(String taskAddress) {
        this.taskAddress = taskAddress == null ? null : taskAddress.trim();
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription == null ? null : taskDescription.trim();
    }
}
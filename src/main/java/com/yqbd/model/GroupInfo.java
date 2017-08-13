package com.yqbd.model;

public class GroupInfo {
    private Integer groupId;

    private Integer companyId;

    private String groupTitle;

    private Integer currentPeopleNumber;

    private Integer maxPeopleNumber;

    private String groupDescription;

    public GroupInfo() {
    }

    public GroupInfo(Integer companyId, String groupTitle, Integer currentPeopleNumber, Integer maxPeopleNumber, String groupDescription) {
        this.companyId = companyId;
        this.groupTitle = groupTitle;
        this.currentPeopleNumber = currentPeopleNumber;
        this.maxPeopleNumber = maxPeopleNumber;
        this.groupDescription = groupDescription;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle == null ? null : groupTitle.trim();
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

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription == null ? null : groupDescription.trim();
    }
}
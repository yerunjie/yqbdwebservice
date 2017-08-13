package com.yqbd.beans;


import com.yqbd.model.GroupInfo;
import com.yqbd.model.GroupMember;
import com.yqbd.model.UserInfo;

/**
 * Created by joy12 on 2017/7/26.
 */
public class ApplicationBean{
    //group
    private Integer groupId;
    private Integer companyId;
    private String groupTitle;
    private String groupDescription;
    private Integer currentPeopleNumber;
    private Integer maxPeopleNumber;
    //candidate
    private Integer userId;
    private String accountNumber;
    private String sex;
    private String realName;
    private String nickName;
    private String headPortrait;
    private Integer professionalLevel;
    private Integer creditLevel;
    //status
    private Integer status;

    public ApplicationBean(){}

    public ApplicationBean(GroupInfo group, UserInfo candidate, GroupMember status) {
        this.groupId = group.getGroupId();
        this.companyId = group.getCompanyId();
        this.groupTitle = group.getGroupTitle();
        this.groupDescription = group.getGroupDescription();
        this.currentPeopleNumber = group.getCurrentPeopleNumber();
        this.maxPeopleNumber = group.getMaxPeopleNumber();
        this.userId = candidate.getUserId();
        this.accountNumber = candidate.getAccountNumber();
        this.sex = candidate.getSex();
        this.realName = candidate.getRealName();
        this.nickName = candidate.getNickName();
        this.headPortrait = candidate.getHeadPortrait();
        this.professionalLevel = candidate.getProfessionalLevel();
        this.creditLevel = candidate.getCreditLevel();
        this.status = status.getStatus();
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
        this.groupTitle = groupTitle;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public Integer getProfessionalLevel() {
        return professionalLevel;
    }

    public void setProfessionalLevel(Integer professionalLevel) {
        this.professionalLevel = professionalLevel;
    }

    public Integer getCreditLevel() {
        return creditLevel;
    }

    public void setCreditLevel(Integer creditLevel) {
        this.creditLevel = creditLevel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}



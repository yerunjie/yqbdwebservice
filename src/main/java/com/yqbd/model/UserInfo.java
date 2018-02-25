package com.yqbd.model;

import com.yqbd.beans.UserInfoBean;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfo {
    private Integer userId;

    private String accountNumber;

    private String password;

    private String sex;

    private String realName;

    private String nickName;

    private String headPortrait;

    private Integer professionalLevel;

    private Integer creditLevel;

    private String telephone;

    private String school;

    private String occupation;

    private String companyName;

    public UserInfo(UserInfoBean userInfoBean){
        this.userId = userInfoBean.getUserId();
        this.accountNumber = userInfoBean.getAccountNumber();
        this.password = userInfoBean.getPassword();
        this.sex = userInfoBean.getSex();
        this.realName = userInfoBean.getRealName();
        this.nickName = userInfoBean.getNickName();
        this.headPortrait = userInfoBean.getHeadPortrait();
        this.professionalLevel = userInfoBean.getProfessionalLevel();
        this.creditLevel = userInfoBean.getCreditLevel();
        this.telephone = userInfoBean.getTelephone();
        this.school = userInfoBean.getSchool();
        this.occupation = userInfoBean.getOccupation();
        this.companyName = userInfoBean.getCompanyName();
    }

}
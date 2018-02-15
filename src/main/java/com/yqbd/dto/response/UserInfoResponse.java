package com.yqbd.dto.response;

import lombok.Data;

/**
 * Created by yerunjie on 2018/2/15
 *
 * @author yerunjie
 */
@Data
public class UserInfoResponse extends BaseJsonResponse {
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

}

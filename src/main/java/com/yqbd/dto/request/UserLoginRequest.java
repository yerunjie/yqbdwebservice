package com.yqbd.dto.request;

import lombok.Data;

/**
 * Created by yerunjie on 2018/2/15
 *
 * @author yerunjie
 */
@Data
public class UserLoginRequest extends YQBDBaseRequest {
    private String accountNumber;
    private String userPassword;
}

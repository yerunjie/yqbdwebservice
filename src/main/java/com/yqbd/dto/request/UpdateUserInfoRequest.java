package com.yqbd.dto.request;

import com.yqbd.beans.UserInfoBean;
import lombok.Data;

@Data
public class UpdateUserInfoRequest extends YQBDBaseRequest {
    private UserInfoBean userInfoBean;
}

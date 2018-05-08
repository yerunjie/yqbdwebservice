package com.yqbd.controller.api;


import com.google.common.collect.Lists;
import com.yqbd.annotation.Authentication;
import com.yqbd.beans.BaseBean;
import com.yqbd.beans.UserInfoBean;
import com.yqbd.constants.CommonConstants;
import com.yqbd.controller.BaseController;
import com.yqbd.dto.Role;
import com.yqbd.dto.request.UpdateUserInfoRequest;
import com.yqbd.dto.request.UserLoginRequest;
import com.yqbd.dto.response.BaseJsonResponse;
import com.yqbd.mapper.TaskMapper;
import com.yqbd.mapper.UserInfoMapper;
import com.yqbd.model.Task;
import com.yqbd.model.UserInfo;
import com.yqbd.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 11022 on 2017/7/20.
 */
@RestController
@RequestMapping("/v1/api/user")
public class UserController extends BaseController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserInfoMapper userInfoMapper;

    //used
    @RequestMapping(value = "/getUserInfoByUserID")
    @Authentication(Role.User)
    public BaseJsonResponse getUserInfoByUserID() {
        int userID = getToken().getId();
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userID);
        baseJsonResponse.setObj(userInfo);
        baseJsonResponse.setErrorMessage("");
        baseJsonResponse.setReturnCode("");
        return baseJsonResponse;
    }

    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    @Authentication(Role.User)
    public BaseJsonResponse updateUserInfo(@RequestBody UpdateUserInfoRequest request) {
        if (request != null && request.getUserInfoBean() != null){
            UserInfoBean userInfoBean = request.getUserInfoBean();
            userInfoBean.setUserId(getToken().getId());
            userInfoMapper.updateByPrimaryKey(new UserInfo(userInfoBean));
        }
        return new BaseJsonResponse();
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseJsonResponse login(@RequestBody UserLoginRequest request) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        UserInfo userInfo = userInfoMapper.selectByAccountNumber(request.getAccountNumber());
        BaseBean baseBean = new BaseBean();
        int result;
        if (userInfo == null) {
            result = -1;
        } else if (userInfo.getPassword().equals(request.getUserPassword())) {
            result = userInfo.getUserId();
            String token = tokenService.generateToken(Role.User, result);
            baseBean.setSingleResult(token);
            baseJsonResponse.setObj(baseBean);
            addCookie(CommonConstants.TOKEN_KEY, token);
        } else {
            result = 0;
            baseBean.setSingleResult(String.valueOf(result));
            baseJsonResponse.setObj(baseBean);
        }
        switch (result) {
            case -1://对应异常  2.0.E.1
                baseJsonResponse.setReturnCode("2.0.E.1");
                baseJsonResponse.setErrorMessage("学号未被注册");
                break;
            case 0://对应异常  2.0.E.2
                baseJsonResponse.setReturnCode("2.0.E.2");
                baseJsonResponse.setErrorMessage("学号和密码不匹配");
                break;
            default://对应正确用例
                baseJsonResponse.setReturnCode("2.0");
                baseJsonResponse.setErrorMessage("成功");
                break;
        }
        return baseJsonResponse;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public BaseJsonResponse register(@RequestParam("accountNumber") String accountNumber, @RequestParam("userPassword") String userPassword){
        BaseJsonResponse baseJson = new BaseJsonResponse();
        UserInfo userInfo = new UserInfo();
        userInfo.setAccountNumber(accountNumber);
        userInfo.setPassword(userPassword);
        userInfoMapper.insertSelective(userInfo);
        BaseBean baseBean = new BaseBean();
        int result;
        result = userInfo.getUserId();
        baseBean.setSingleResult(String.valueOf(result));
        baseJson.setObj(baseBean);
        baseJson.setReturnCode("2.0");
        baseJson.setErrorMessage("成功");
        return baseJson;
    }

}

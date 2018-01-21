package com.yqbd.controller.api;


import com.yqbd.beans.BaseBean;
import com.yqbd.beans.BaseJson;
import com.yqbd.mapper.UserInfoMapper;
import com.yqbd.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 11022 on 2017/7/20.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserInfoMapper userInfoMapper;


    @RequestMapping(value = "/getUserInfoByUserID")
    public BaseJson getUserInfoByUserID(@RequestParam("userID") int userID) {
        BaseJson baseJson = new BaseJson();
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userID);
        baseJson.setObj(userInfo);
        baseJson.setErrorMessage("");
        baseJson.setReturnCode("");
        return baseJson;
    }

    @RequestMapping(value = "/login")
    public BaseJson login(@RequestParam("accountNumber") String accountNumber, @RequestParam("userPassword") String userPassword) {
        BaseJson baseJson = new BaseJson();
        UserInfo userInfo = userInfoMapper.selectByAccountNumber(accountNumber);
        BaseBean baseBean = new BaseBean();
        int result;
        if (userInfo == null) {
            result = -1;
        } else if (userInfo.getPassword().equals(userPassword)) {
            result = userInfo.getUserId();
        } else {
            result = 0;
        }
        baseBean.setSingleResult(String.valueOf(result));
        baseJson.setObj(baseBean);
        switch (result) {
            case -1://对应异常  2.0.E.1
                baseJson.setReturnCode("2.0.E.1");
                baseJson.setErrorMessage("学号未被注册");
                break;
            case 0://对应异常  2.0.E.2
                baseJson.setReturnCode("2.0.E.2");
                baseJson.setErrorMessage("学号和密码不匹配");
                break;
            default://对应正确用例
                baseJson.setReturnCode("2.0");
                baseJson.setErrorMessage("成功");
                break;
        }
        return baseJson;
    }
    @RequestMapping(value = "/register")
    public BaseJson register(@RequestParam("accountNumber") String accountNumber, @RequestParam("userPassword") String userPassword,
                          @RequestParam("realName") String realName){
        BaseJson baseJson = new BaseJson();
        UserInfo userInfo = new UserInfo();
        userInfo.setAccountNumber(accountNumber);
        userInfo.setPassword(userPassword);
        userInfo.setRealName(realName);
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

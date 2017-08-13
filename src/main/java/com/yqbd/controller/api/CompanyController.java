package com.yqbd.controller.api;


import com.yqbd.beans.BaseBean;
import com.yqbd.beans.BaseJson;
import com.yqbd.mapper.CompanyInfoMapper;
import com.yqbd.mapper.GroupInfoMapper;
import com.yqbd.model.CompanyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by joy12 on 2017/7/22.
 */


@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyInfoMapper companyInfoMapper;

    @Autowired
    private GroupInfoMapper groupInfoMapper;

    @RequestMapping(value = "/getCompanyInfoByCompanyId", method = RequestMethod.POST)
    public BaseJson getCompanyInfoByCompanyId(@RequestParam("companyId") int companyId) {
        BaseJson baseJson = new BaseJson();
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(companyId);
        baseJson.setObj(companyInfo);
        return baseJson;
    }


    @RequestMapping(value = "/companyLogin", method = RequestMethod.POST)
    public BaseJson companyLogin(@RequestParam("companyAccount") String companyAccount, @RequestParam("companyPassword") String companyPassword) {
        BaseJson baseJson = new BaseJson();
        CompanyInfo companyInfo = companyInfoMapper.selectByCompanyAccount(companyAccount);
        BaseBean baseBean = new BaseBean();
        int result;
        if (companyInfo == null) {
            result = -1;
        } else if (companyInfo.getPassword().equals(companyPassword)) {
            result = companyInfo.getCompanyId();
            System.out.println("登录成功: companyId=" + result);
        } else {
            result = 0;
        }
        baseBean.setSingleResult(String.valueOf(result));
        baseJson.setObj(baseBean);
        switch (result) {
            case -1://对应异常  3.0.E.1
                baseJson.setReturnCode("3.0.E.1");
                baseJson.setErrorMessage("企业账号未被注册");
                break;
            case 0://对应异常  2.0.E.2
                baseJson.setReturnCode("3.0.E.2");
                baseJson.setErrorMessage("企业账号和密码不匹配");
                break;
            default://对应正确用例
                baseJson.setReturnCode("3.0");
                baseJson.setErrorMessage("成功");
                break;
        }
        return baseJson;
    }
}

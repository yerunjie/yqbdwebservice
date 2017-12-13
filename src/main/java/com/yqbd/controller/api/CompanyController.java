package com.yqbd.controller.api;


import com.yqbd.beans.BaseBean;
import com.yqbd.beans.BaseJson;
import com.yqbd.beans.CompanyInfoBean;
import com.yqbd.controller.BaseController;
import com.yqbd.mapper.CompanyInfoMapper;
import com.yqbd.mapper.GroupInfoMapper;
import com.yqbd.model.CompanyInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by joy12 on 2017/7/22.
 */


@RestController
@RequestMapping("/company")
public class CompanyController extends BaseController {

    @Autowired
    private CompanyInfoMapper companyInfoMapper;

    @Autowired
    private GroupInfoMapper groupInfoMapper;

    @RequestMapping(value = "/getCompanyInfoByCompanyId")
    public BaseJson getCompanyInfoByCompanyId(@RequestParam("companyId") int companyId) {
        BaseJson baseJson = new BaseJson();
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(companyId);
        baseJson.setObj(parse(companyInfo));
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
                System.out.println(5);
                baseJson.setReturnCode("3.0");
                System.out.println(6);
                baseJson.setErrorMessage("成功");
                System.out.println(7);
                HttpSession session = request.getSession();
                System.out.println(8);
                session.setAttribute("companyId", result);
                System.out.println(9);
                break;
        }
        System.out.println(result);
        return baseJson;
    }

    @RequestMapping(value = "/getAllCompanies")
    public BaseJson getAllCompanies() {
        BaseJson baseJson = new BaseJson();
        List<CompanyInfo> companyInfoList = companyInfoMapper.getAllCompanies();
        baseJson.setObj(companyInfoList.stream().map(this::parse).collect(Collectors.toList()));
        return baseJson;
    }

    private CompanyInfoBean parse(CompanyInfo companyInfo) {
        CompanyInfoBean result = new CompanyInfoBean();
        BeanUtils.copyProperties(companyInfo, result);
        return result;
    }
}

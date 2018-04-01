package com.yqbd.controller.api;


import com.yqbd.beans.BaseBean;
import com.yqbd.beans.CompanyInfoBean;
import com.yqbd.controller.BaseController;
import com.yqbd.dto.response.BaseJsonResponse;
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
    public BaseJsonResponse getCompanyInfoByCompanyId(@RequestParam("companyId") int companyId) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(companyId);
        baseJsonResponse.setObj(parse(companyInfo));
        return baseJsonResponse;
    }


    @RequestMapping(value = "/companyLogin", method = RequestMethod.POST)
    public BaseJsonResponse companyLogin(@RequestParam("companyAccount") String companyAccount, @RequestParam("companyPassword") String companyPassword) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
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
        baseJsonResponse.setObj(baseBean);
        switch (result) {
            case -1://对应异常  3.0.E.1
                baseJsonResponse.setReturnCode("3.0.E.1");
                baseJsonResponse.setErrorMessage("企业账号未被注册");
                break;
            case 0://对应异常  2.0.E.2
                baseJsonResponse.setReturnCode("3.0.E.2");
                baseJsonResponse.setErrorMessage("企业账号和密码不匹配");
                break;
            default://对应正确用例
                baseJsonResponse.setReturnCode("3.0");
                baseJsonResponse.setErrorMessage("成功");
                HttpSession session = servletRequest.getSession();
                session.setAttribute("companyId", result);
                break;
        }
        return baseJsonResponse;
    }

    @RequestMapping(value = "/getAllCompanies")
    public BaseJsonResponse getAllCompanies() {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        List<CompanyInfo> companyInfoList = companyInfoMapper.getAllCompanies();
        baseJsonResponse.setObj(companyInfoList.stream().map(this::parse).collect(Collectors.toList()));
        return baseJsonResponse;
    }

    private CompanyInfoBean parse(CompanyInfo companyInfo) {
        CompanyInfoBean result = new CompanyInfoBean();
        BeanUtils.copyProperties(companyInfo, result);
        return result;
    }
}

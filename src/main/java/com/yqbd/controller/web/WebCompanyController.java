package com.yqbd.controller.web;

import com.yqbd.controller.BaseController;
import com.yqbd.mapper.CompanyInfoMapper;
import com.yqbd.mapper.TaskMapper;
import com.yqbd.model.CompanyInfo;
import com.yqbd.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/webCompany")
public class WebCompanyController extends BaseController {

    @Autowired
    private CompanyInfoMapper companyInfoMapper;

    @Autowired
    protected TaskMapper taskMapper;

    @RequestMapping(value = "/login")
    public String login(Map<String, Object> model) {
        return "login";
    }

    @RequestMapping(value = "/chart_columnar")
    public String chartColumnar(Map<String, Object> model) {
        int companyId = getCurrentCompanyId();
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(companyId);
        String companyName = companyInfo.getCompanyName();
        model.put("company_name", companyName);
        return "chart_columnar";
    }

    @RequestMapping(value = "/chart_line")
    public String chartLine(Map<String, Object> model) {

        int companyId = getCurrentCompanyId();
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(companyId);
        String companyName = companyInfo.getCompanyName();
        model.put("company_name", companyName);
        return "chart_line";
    }

    @RequestMapping(value = "/chart_pie")
    public String chartPie(Map<String, Object> model) {
        int companyId = getCurrentCompanyId();
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(companyId);
        String companyName = companyInfo.getCompanyName();
        model.put("company_name", companyName);
        return "chart_pie";
    }

    @RequestMapping(value = "/form_basic")
    public String formBasic(Map<String, Object> model) {

        int companyId = getCurrentCompanyId();
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(companyId);
        String companyName = companyInfo.getCompanyName();
        model.put("company_name", companyName);
        return "form_basic";
    }

    @RequestMapping(value = "/form_validate")
    public String formValidate(Map<String, Object> model) {

        int companyId = getCurrentCompanyId();
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(companyId);
        String companyName = companyInfo.getCompanyName();
        model.put("company_name", companyName);
        return "form_validate";
    }

    @RequestMapping(value = "/table_basic")
    public String tableBasic(Map<String, Object> model) {
        int companyId = getCurrentCompanyId();
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(companyId);
        String companyName = companyInfo.getCompanyName();
        model.put("company_name", companyName);
        return "table_basic";
    }

    @RequestMapping(value = "/table_complete")
    public String tableComplete(Map<String, Object> model) {
        int companyId = getCurrentCompanyId();
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(companyId);
        String companyName = companyInfo.getCompanyName();
        model.put("company_name", companyName);
        List<Task> taskList;
        taskList = taskMapper.getPublishedTasksByCompanyId(companyId);
        model.put("taskList", taskList);
        return "table_complete";
    }

    @RequestMapping(value = "/typography")
    public String typography(Map<String, Object> model) {
        int companyId = getCurrentCompanyId();
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(companyId);
        String companyName = companyInfo.getCompanyName();
        model.put("company_name", companyName);
        return "typography";
    }

    @RequestMapping(value = "/index")
    public String index(Map<String, Object> model) {
        int companyId = getCurrentCompanyId();
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(companyId);
        String companyName = companyInfo.getCompanyName();
        model.put("company_name", companyName);
        return "index";
    }


    @RequestMapping(value = "/singleTask")
    public String singleTask(Map<String, Object> model) {
        int companyId = getCurrentCompanyId();
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(companyId);
        String companyName = companyInfo.getCompanyName();
        model.put("company_name", companyName);
        Integer taskId = Integer.valueOf((String) request.getSession().getAttribute("taskId"));
        Task task = taskMapper.selectByPrimaryKey(taskId);
        model.put("task", task);
        return "single_task";
    }

    @RequestMapping(value = "/editTask")
    public String editTask(Map<String, Object> model) {
        int companyId = getCurrentCompanyId();
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(companyId);
        String companyName = companyInfo.getCompanyName();
        model.put("company_name", companyName);
        Integer taskId = Integer.valueOf((String) request.getSession().getAttribute("taskId"));
        Task task = taskMapper.selectByPrimaryKey(taskId);
        model.put("task", task);
        return "edit_task";
    }

    @RequestMapping(value = "/post_task")
    public String postTask(Map<String, Object> model) {
        int companyId = getCurrentCompanyId();
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(companyId);
        String companyName = companyInfo.getCompanyName();
        model.put("company_name", companyName);
        return "post_task";
    }
}

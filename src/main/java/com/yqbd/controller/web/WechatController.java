package com.yqbd.controller.web;

import com.google.common.collect.Lists;
import com.yqbd.controller.BaseController;
import com.yqbd.beans.*;
import com.yqbd.dto.response.BaseJsonResponse;
import com.yqbd.mapper.CompanyInfoMapper;
import com.yqbd.mapper.TaskMapper;
import com.yqbd.mapper.TypeMapper;
import com.yqbd.mapper.UserInfoMapper;
import com.yqbd.model.CompanyInfo;
import com.yqbd.model.Task;
import com.yqbd.model.Type;
import com.yqbd.model.UserInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by yerunjie on 2018/1/21
 *
 * @author yerunjie
 */
@Controller
@RequestMapping("/wechat")
public class WechatController extends BaseController {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private CompanyInfoMapper companyInfoMapper;

    @RequestMapping(value = "/list")
    public String list(Map<String, Object> model) {
        model.put("module", "wechat_list");
        List<Task> tasks = taskMapper.selectAllTasks();
        Collections.shuffle(tasks);
        model.put("taskList", Lists.transform(tasks, this::parse));
        return "wechat_list";
    }

    @RequestMapping(value = "/index")
    public String index(Map<String, Object> model) {
        model.put("module", "wechat_index");
        List<Task> tasks = taskMapper.selectAllTasks();
        Collections.shuffle(tasks);
        model.put("taskList", Lists.transform(tasks, this::parse));

        List<CompanyInfo> companyInfoList = companyInfoMapper.getAllCompanies();
        model.put("companyList", companyInfoList.stream().map(this::parse).collect(Collectors.toList()));
        return "wechat_index";
    }

    @RequestMapping(value = "/login")
    public String login(Map<String, Object> model) {
        model.put("module", "wechat_index");
        return "wechat_login";
    }

    private TaskBean parse(Task task) {
        TaskBean taskBean = new TaskBean();
        BeanUtils.copyProperties(task, taskBean);
        taskBean.setPublishTime(task.getPublishTime().getTime());
        taskBean.setCompleteTime(task.getCompleteTime().getTime());
        taskBean.setStartTime(task.getStartTime().getTime());
        taskBean.setDeadline(task.getDeadline().getTime());
        taskBean.setTypeBeans(Lists.transform(typeMapper.selectTypesByTaskId(taskBean.getTaskId()), this::parse));
        return taskBean;
    }

    private TypeBean parse(Type type) {
        TypeBean result = new TypeBean();
        BeanUtils.copyProperties(type, result);
        return result;
    }

    private CompanyInfoBean parse(CompanyInfo companyInfo) {
        CompanyInfoBean result = new CompanyInfoBean();
        BeanUtils.copyProperties(companyInfo, result);
        return result;
    }
    @RequestMapping(value = "/register")
    public String register(Map<String, Object> model) {
        model.put("module", "wechat_index");
        return "wechat_register";
    }
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    @ResponseBody
    public BaseJsonResponse userLogin(@RequestParam("userAccount") String userAccount, @RequestParam("userPassword") String userPassword) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        UserInfo userInfo = userInfoMapper.selectByAccountNumber(userAccount);
        BaseBean baseBean = new BaseBean();
        int result;
        if (userInfo == null) {
            result = -1;
        } else if (userInfo.getPassword().equals(userPassword)) {
            result = userInfo.getUserId();
            System.out.println("登录成功: userId=" + result);
        } else {
            result = 0;
        }
        baseBean.setSingleResult(String.valueOf(result));
        baseJsonResponse.setObj(baseBean);
        switch (result) {
            case -1://对应异常  3.0.E.1
                baseJsonResponse.setReturnCode("3.0.E.1");
                baseJsonResponse.setErrorMessage("用户账号未被注册");
                break;
            case 0://对应异常  2.0.E.2
                baseJsonResponse.setReturnCode("3.0.E.2");
                baseJsonResponse.setErrorMessage("用户账号和密码不匹配");
                break;
            default://对应正确用例
                baseJsonResponse.setReturnCode("3.0");
                baseJsonResponse.setErrorMessage("成功");
                HttpSession session = servletRequest.getSession();
                session.setAttribute("userId", result);
                break;
        }
        return baseJsonResponse;
    }

    @RequestMapping(value = "/personal")
    public String mine(Map<String, Object> model) {
        HttpSession session = servletRequest.getSession();
        model.put("module", "wechat_mine");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey((int) session.getAttribute("userId"));
        model.put("userInfo", userInfo);
        return "wechat_mine";
    }

    @RequestMapping(value = "/my_task")
    public String myTask(Map<String, Object> model) {
        HttpSession session = servletRequest.getSession();
        model.put("module", "wechat_my_task");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey((int) session.getAttribute("userId"));
        model.put("userInfo", userInfo);
        List<Task> list = taskMapper.getTakenTasksByUserId(userInfo.getUserId());
        session.setAttribute("taskList", list);
        return "wechat_my_task";
    }

    @RequestMapping(value = "/single_task")
    public String singleTask(Map<String, Object> model) {
        model.put("module", "wechat_single_task");
        HttpSession session = servletRequest.getSession();
        int taskId = (int)session.getAttribute("taskId");
        Task task = taskMapper.selectByPrimaryKey(taskId);
        model.put("task",task);
        return "wechat_single_task";
    }

    @RequestMapping(value = "/single_task_search")
    @ResponseBody
    public BaseJsonResponse singleTaskSearch(@RequestParam("taskId") String taskId) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        HttpSession session = servletRequest.getSession();
        session.setAttribute("taskId",Integer.valueOf(taskId));
        baseJsonResponse.setErrorMessage("成功");
        return baseJsonResponse;
    }
}



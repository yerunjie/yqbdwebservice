package com.yqbd.controller.web;

import com.google.common.collect.Lists;
import com.yqbd.beans.CompanyInfoBean;
import com.yqbd.beans.TaskBean;
import com.yqbd.beans.TypeBean;
import com.yqbd.controller.BaseController;
import com.yqbd.mapper.CompanyInfoMapper;
import com.yqbd.mapper.TaskMapper;
import com.yqbd.mapper.TypeMapper;
import com.yqbd.model.CompanyInfo;
import com.yqbd.model.Task;
import com.yqbd.model.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}

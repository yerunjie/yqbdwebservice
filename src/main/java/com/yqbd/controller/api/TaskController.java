package com.yqbd.controller.api;


import com.yqbd.beans.*;
import com.yqbd.constants.TaskStatus;
import com.yqbd.constants.UserTaskStatus;
import com.yqbd.controller.BaseController;
import com.yqbd.mapper.TaskMapper;
import com.yqbd.mapper.TaskTypeMapper;
import com.yqbd.mapper.TypeMapper;
import com.yqbd.mapper.UserTakeMapper;
import com.yqbd.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by 11022 on 2017/7/21.
 */
// 1、组别创建（group表加一项）、组别删除、查看公司已有组别
// 2、发布长期任务（isGroup为1）【前态：目标小组必须已经在group表中，否则弹出提示跳转到 1 】
//publishTask,属性多加一个组别
//问题：发布长期任务时，需要规定组别，这个组应该是已有的小组，task列里应该有写明这个任务发给了哪个组的属性
@RestController
@RequestMapping("/task")
public class TaskController extends BaseController {

    @Autowired
    protected TypeMapper typeMapper;

    @Autowired
    protected TaskMapper taskMapper;

    @Autowired
    protected UserTakeMapper userTakeMapper;

    @Autowired
    protected TaskTypeMapper taskTypeMapper;

    @RequestMapping(value = "/getAllTypes")
    public BaseJson getAllTypes() {
        BaseJson baseJson = new BaseJson();
        List<Type> types = typeMapper.selectAllTypes();
        baseJson.setObj(types);
        return baseJson;
    }

    @RequestMapping(value = "/getAllTasks")
    public BaseJson getAllTasks() {
        BaseJson baseJson = new BaseJson();
        List<Task> tasks = taskMapper.selectAllTasks();
        baseJson.setObj(tasks);
        return baseJson;
    }

    @RequestMapping(value = "/getSearchTypes")
    public BaseJson getSearchTypes() {
        List<Type> typelist = typeMapper.selectAllTypes();
        // typelist.stream().collect(Collectors.groupingBy(Type::getTypeClassification));
        /*Map<String, List<Type>> map = typelist.stream()
                .collect(Collectors.groupingBy(Type::getTypeClassification)).entrySet().stream()
                .map(this::parse).collect(Collectors.toList());*/
        List<SearchType> list = typelist.stream()
                .collect(Collectors.groupingBy(Type::getTypeClassification)).entrySet().stream()
                .map(this::parse).collect(Collectors.toList());
        /*for (Map.Entry<String, List<Type>> entry : map.entrySet()) {
            SearchType tmp = new SearchType();
            tmp.setName(entry.getKey());
            tmp.setTypes(entry.getValue());
            list.add(tmp);
        }*/
        BaseJson baseJson = new BaseJson();
        baseJson.setObj(list);
        return baseJson;
    }

    @RequestMapping(value = "/publishTask", method = RequestMethod.POST)
    @Transactional
    public BaseJson publishTask(@RequestBody TaskBean taskBean) {
        BaseJson baseJson = new BaseJson();
        Task task = parse(taskBean);
        task.setPublishTime(new Date());
        task.setTaskStatus(TaskStatus.NEW.getValue());
        taskMapper.insertSelective(task);
        for (TypeBean typeBean : taskBean.getTypeBeans()) {
            taskTypeMapper.insert(new TaskTypeKey(task.getTaskId(), typeBean.getTypeId()));
        }
        baseJson.setObj(taskMapper.selectByPrimaryKey(task.getTaskId()));
        return baseJson;
    }

    private Task parse(TaskBean taskBean) {
        Task task = new Task();
        BeanUtils.copyProperties(taskBean, task);
        task.setDeadline(new Date(taskBean.getDeadline()));
        return task;
    }

    private SearchType parse(Map.Entry<String, List<Type>> entry) {
        SearchType tmp = new SearchType();
        tmp.setName(entry.getKey());
        tmp.setTypes(entry.getValue());
        return tmp;
    }

    @RequestMapping(value = "/publishTasks")
    public BaseJson getMyPublishedTask(@RequestParam("userId") int userId) {
        System.out.println("getMyPublishedTask");
        BaseJson baseJson = new BaseJson();
        List<Task> tasks = taskMapper.getPublishedTasksByUserId(userId);
        baseJson.setObj(tasks);
        return baseJson;
    }

    @RequestMapping(value = "/takenTasks")
    public BaseJson getMyTaken(@RequestParam("userId") int userId) {
        System.out.println("getMyTaken");
        BaseJson baseJson = new BaseJson();
        List<Task> tasks = taskMapper.getTakenTasksByUserId(userId);
        baseJson.setObj(tasks);
        return baseJson;
    }

    @RequestMapping(value = "/isTaken")
    public BaseJson isTaken(@RequestParam("userId") int userId, @RequestParam("taskId") int taskId) {
        BaseJson baseJson = new BaseJson();
        UserTakeKey userTakeKey = new UserTakeKey();
        userTakeKey.setUserId(userId);
        userTakeKey.setTaskId(taskId);
        UserTake userTake = userTakeMapper.selectByPrimaryKey(userTakeKey);
        BaseBean baseBean = new BaseBean(Objects.nonNull(userTake));
        baseJson.setObj(baseBean);
        return baseJson;
    }

    @RequestMapping(value = "/getTaskById")
    public BaseJson getTaskById(@RequestParam("taskId") int taskId) {
        System.out.println("getTaskById");
        BaseJson baseJson = new BaseJson();
        Task task = taskMapper.selectByPrimaryKey(taskId);
        baseJson.setObj(task);
        return baseJson;
    }

    @RequestMapping(value = "/cancelPublishedTask")
    public void cancelPublishedTask(@RequestParam("taskId") int taskId) {
        taskMapper.deleteByPrimaryKey(taskId);
        System.out.println("task" + taskId + "已删除");
    }

    @RequestMapping(value = "/getSearch")
    public BaseJson getSearch(@RequestParam("map") String map) {
        System.out.println(map);
        String[] list = map.split(",");
        List<Integer> typeList = typeMapper.getSearchType(list);
        typeList.add(0);
        System.out.println(typeList);
        List<Task> tasks = taskMapper.getSearchTasks(typeList);
        BaseJson baseJson = new BaseJson();
        baseJson.setObj(tasks);
        return baseJson;
    }

    @RequestMapping(value = "/cancelTaken")
    public void cancelTaken(@RequestParam("taskId") int taskId, @RequestParam("userId") int userId) {
        UserTakeKey userTakeKey = new UserTakeKey();
        userTakeKey.setTaskId(taskId);
        userTakeKey.setUserId(userId);
        userTakeMapper.deleteByPrimaryKey(userTakeKey);
        System.out.println("delete user take~");
    }

    @RequestMapping(value = "/takeTask")
    public void takeTask(@RequestParam("taskId") int taskId, @RequestParam("userId") int userId) {
        UserTake userTake = new UserTake();
        userTake.setUserId(userId);
        userTake.setTaskId(taskId);
        userTake.setStatus(1);
        userTakeMapper.insert(userTake);
        System.out.println("insert user take~");
    }

    @RequestMapping(value = "/userTakeTask")
    public BaseJson userTakeTask(@RequestParam("taskId") int taskId, @RequestParam("userId") int userId) {
        BaseJson baseJson = new BaseJson();
        UserTakeKey userTakeKey = new UserTakeKey();
        userTakeKey.setUserId(userId);
        userTakeKey.setTaskId(taskId);
        UserTake userTake = userTakeMapper.selectByPrimaryKey(userTakeKey);
        if (userTake == null) {
            userTake = new UserTake();
            userTake.setUserId(userId);
            userTake.setTaskId(taskId);
            userTake.setStatus(UserTaskStatus.NEW.getValue());
            userTakeMapper.insertSelective(userTake);
        } else {
            baseJson.setReturnCode("E");
            baseJson.setErrorMessage("已经申请");
        }
        baseJson.setObj(userTake);
        return baseJson;
    }

    @RequestMapping(value = "/userCancelTakeTask")
    public BaseJson userCancelTakeTask(@RequestParam("taskId") int taskId, @RequestParam("userId") int userId) {
        BaseJson baseJson = new BaseJson();
        UserTakeKey userTakeKey = new UserTakeKey();
        userTakeKey.setUserId(userId);
        userTakeKey.setTaskId(taskId);
        UserTake userTake = userTakeMapper.selectByPrimaryKey(userTakeKey);
        if (userTake == null) {
            baseJson.setReturnCode("E");
            baseJson.setErrorMessage("未申请过");
        } else {
            //baseJson.setReturnCode("");
            switch (UserTaskStatus.valueOf(userTake.getStatus())) {
                case APPLY_FOR_CANCEL:
                    baseJson.setReturnCode("E");
                    baseJson.setErrorMessage("已经申请");
                    break;
                case CANCELLED:
                    baseJson.setReturnCode("E");
                    baseJson.setErrorMessage("已经取消");
                    break;
                default:
                    userTake.setStatus(UserTaskStatus.APPLY_FOR_CANCEL.getValue());
                    userTakeMapper.updateByPrimaryKeySelective(userTake);
                    break;
            }
        }
        baseJson.setObj(userTake);
        return baseJson;
    }

}

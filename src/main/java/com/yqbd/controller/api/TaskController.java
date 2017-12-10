package com.yqbd.controller.api;


import com.google.common.collect.Lists;
import com.yqbd.beans.*;
import com.yqbd.constants.TaskStatus;
import com.yqbd.constants.UserTaskStatus;
import com.yqbd.controller.BaseController;
import com.yqbd.mapper.*;
import com.yqbd.model.*;
import org.apache.tomcat.jni.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
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

    @Autowired
    protected UserCollectMapper userCollectMapper;

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
        baseJson.setObj(Lists.transform(tasks, this::parse));
        return baseJson;
    }

    @RequestMapping(value = "/getAcceptTasks")
    public BaseJson getAcceptTasks(@RequestParam("userId") int userId) {
        BaseJson baseJson = new BaseJson();
        List<Task> tasks = taskMapper.getAcceptTasks(userId);
        baseJson.setObj(Lists.transform(tasks, this::parse));
        return baseJson;
    }

    @RequestMapping(value = "/getCollectedTasks")
    public BaseJson getCollectedTasks(@RequestParam("userId") int userId) {
        BaseJson baseJson = new BaseJson();
        List<Task> tasks = taskMapper.getCollectedTasks(userId);
        baseJson.setObj(Lists.transform(tasks, this::parse));
        return baseJson;
    }

    @RequestMapping(value = "/getCompanyTasks")
    public BaseJson getCompanyTasks(@RequestParam("companyId") int companyId) {
        BaseJson baseJson = new BaseJson();
        baseJson.setObj(Lists.transform(taskMapper.getCompanyTasks(companyId), this::parse));
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

    @RequestMapping(value = "/getTakenTask")
    public BaseJson getMyPublishedTask(@RequestParam("userId") int userId) {
        System.out.println("getTakenTask");
        BaseJson baseJson = new BaseJson();
        List<Task> tasks = taskMapper.getTakenTasksByUserId(userId);
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

    @RequestMapping(value = "/singleTask")
    public BaseJson takeTask(@RequestParam("taskId") String taskId) {
        BaseJson baseJson = new BaseJson();
        HttpSession session = request.getSession();
        session.setAttribute("taskId", taskId);
        return baseJson;
    }

    @RequestMapping(value = "/editTask")
    public BaseJson editTask(@RequestParam("taskId") String taskId,
                             @RequestParam("taskTitle") String taskTitle,
                             @RequestParam("taskDescription") String taskDescription,
                             @RequestParam("pay") String pay,
                             @RequestParam("publishTime") String publishTime,
                             @RequestParam("deadline") String deadline,
                             @RequestParam("startTime") String startTime,
                             @RequestParam("completeTime") String completeTime,
                             @RequestParam("maxPeopleNumber") String maxPeopleNumber,
                             @RequestParam("taskAddress") String taskAddress
    ) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Task task = taskMapper.selectByPrimaryKey(Integer.valueOf(taskId));
        task.setTaskId(Integer.valueOf(taskId));
        task.setTaskTitle(taskTitle);
        task.setTaskDescription(taskDescription);
        task.setPay(Double.valueOf(pay.replace(",", "")));
        task.setPublishTime(sdf.parse(publishTime));
        task.setDeadline(sdf.parse(deadline));
        task.setStartTime(sdf.parse(startTime));
        task.setCompleteTime(sdf.parse(completeTime));
        task.setMaxPeopleNumber(Integer.valueOf(maxPeopleNumber));
        task.setTaskAddress(taskAddress);
        taskMapper.updateByPrimaryKeyWithBLOBs(task);
        BaseJson baseJson = new BaseJson();
        HttpSession session = request.getSession();
        session.setAttribute("taskId", taskId);
        return baseJson;
    }


    @RequestMapping(value = "/deleteTask")
    public BaseJson editTask(@RequestParam("taskId") String taskId) {
        int companyId = taskMapper.selectByPrimaryKey(Integer.valueOf(taskId)).getCompanyId();
        taskMapper.deleteByPrimaryKey(Integer.valueOf(taskId));
        BaseJson baseJson = new BaseJson();
        HttpSession session = request.getSession();
        session.setAttribute("companyId", companyId);
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

    @RequestMapping(value = "/isCollected")
    public BaseJson isCollected(@RequestParam("taskId") int taskId, @RequestParam("userId") int userId) {
        BaseJson baseJson = new BaseJson();
        UserCollectKey userTakeKey = new UserCollectKey();
        userTakeKey.setUserId(userId);
        userTakeKey.setTaskId(taskId);
        UserCollectKey userTake = userCollectMapper.selectByPrimaryKey(userTakeKey);
        BaseBean baseBean = new BaseBean(Objects.nonNull(userTake));
        baseJson.setObj(baseBean);
        baseJson.setReturnCode("1.0.C.0");
        return baseJson;
    }

    @RequestMapping(value = "/collect")
    public BaseJson collect(@RequestParam("taskId") int taskId, @RequestParam("userId") int userId) {
        BaseJson baseJson = new BaseJson();
        UserCollectKey userCollectKey = new UserCollectKey();
        userCollectKey.setUserId(userId);
        userCollectKey.setTaskId(taskId);
        UserCollectKey userTake = userCollectMapper.selectByPrimaryKey(userCollectKey);
        if (Objects.nonNull(userTake)) {
            userCollectMapper.deleteByPrimaryKey(userCollectKey);
        } else {
            userCollectMapper.insert(userCollectKey);
        }
        BaseBean baseBean = new BaseBean(Objects.isNull(userTake));
        baseJson.setObj(baseBean);
        baseJson.setReturnCode("1.0.C.0");
        return baseJson;
    }


    @RequestMapping(value = "/isTake")
    public BaseJson isTake(@RequestParam("taskId") int taskId, @RequestParam("userId") int userId) {
        BaseJson baseJson = new BaseJson();
        UserTakeKey userTakeKey = new UserTakeKey();
        userTakeKey.setUserId(userId);
        userTakeKey.setTaskId(taskId);
        UserTake userTake = userTakeMapper.selectByPrimaryKey(userTakeKey);
        BaseBean baseBean = new BaseBean(Objects.nonNull(userTake));
        baseJson.setObj(baseBean);
        baseJson.setReturnCode("1.0.T.0");
        return baseJson;
    }


    @RequestMapping(value = "/take")
    public BaseJson take(@RequestParam("taskId") int taskId, @RequestParam("userId") int userId) {
        BaseJson baseJson = new BaseJson();
        UserTakeKey userTakeKey = new UserTakeKey();
        userTakeKey.setUserId(userId);
        userTakeKey.setTaskId(taskId);
        UserTake userTake = userTakeMapper.selectByPrimaryKey(userTakeKey);
        if (Objects.nonNull(userTake)) {
            userTakeMapper.deleteByPrimaryKey(userTakeKey);
        } else {
            userTakeMapper.insertUserTake(userTakeKey);
        }
        BaseBean baseBean = new BaseBean(Objects.isNull(userTake));
        baseJson.setObj(baseBean);
        baseJson.setReturnCode("1.0.T.0");
        return baseJson;
    }

    @RequestMapping(value="/showParticipant")
    public BaseJson showParticipant(@RequestParam("taskId") int taskId){
        BaseJson baseJson = new BaseJson();
        BaseBean baseBean = new BaseBean();
        baseBean.setSingleResult(String.valueOf("taskId"));
        baseJson.setObj(baseBean);
        HttpSession session = request.getSession();
        session.setAttribute("taskId", taskId);
        return baseJson;

    }

    @RequestMapping(value="/getParticipant")
    public BaseJson getParticipant(@RequestParam("taskId") int taskId){
        BaseJson baseJson = new BaseJson();
        BaseBean baseBean = new BaseBean();

        List<UserInfoBean> userInfoList = taskMapper.getParticipant(taskId);
        baseJson.setObj(userInfoList);
        return baseJson;
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


}

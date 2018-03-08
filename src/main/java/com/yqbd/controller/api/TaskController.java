package com.yqbd.controller.api;


import com.google.common.collect.Lists;
import com.yqbd.annotation.Authentication;
import com.yqbd.beans.*;
import com.yqbd.constants.TaskStatus;
import com.yqbd.constants.UserTaskStatus;
import com.yqbd.controller.BaseController;
import com.yqbd.dto.Role;
import com.yqbd.dto.request.TaskRequest;
import com.yqbd.dto.request.UncollectTaskRequest;
import com.yqbd.dto.response.BaseJsonResponse;
import com.yqbd.mapper.*;
import com.yqbd.model.*;
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
@RestController
@RequestMapping("/v1/api/task")
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
    public BaseJsonResponse getAllTypes() {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        List<Type> types = typeMapper.selectAllTypes();
        baseJsonResponse.setObj(types);
        return baseJsonResponse;
    }

    @RequestMapping(value = "/getAllTasks")
    public BaseJsonResponse getAllTasks() {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        List<Task> tasks = taskMapper.selectAllTasks();
        Collections.shuffle(tasks);
        baseJsonResponse.setObj(Lists.transform(tasks, this::parse));
        return baseJsonResponse;
    }

    //used
    @RequestMapping(value = "/getAcceptTasks")
    @Authentication(Role.User)
    public BaseJsonResponse getAcceptTasks() {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        int userId = getToken().getId();
        List<TaskBean> tasks = new ArrayList<>();
        if (userId > 0) {
            tasks.addAll(Lists.transform(taskMapper.getAcceptTasks(userId), this::parse));
        }
        baseJsonResponse.setObj(tasks);
        return baseJsonResponse;
    }

    //used
    @RequestMapping(value = "/getCollectedTasks")
    @Authentication(Role.User)
    public BaseJsonResponse getCollectedTasks() {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        int userId = getToken().getId();
        List<Task> tasks = taskMapper.getCollectedTasks(userId);
        baseJsonResponse.setObj(Lists.transform(tasks, this::parse));
        return baseJsonResponse;
    }

    @RequestMapping(value = "/uncollectedTasks", method = RequestMethod.POST)
    @Authentication(Role.User)
    public BaseJsonResponse uncollectedTasks(@RequestBody UncollectTaskRequest uncollectTaskRequest) {
        if (uncollectTaskRequest != null && uncollectTaskRequest.getUserCollectList() != null) {
            int userId = getToken().getId();
            for (Integer id : uncollectTaskRequest.getUserCollectList()) {
                UserCollectKey key = new UserCollectKey();
                key.setTaskId(id);
                key.setUserId(userId);
                userCollectMapper.deleteByPrimaryKey(key);
            }
        }
        return getCollectedTasks();
    }

    @RequestMapping(value = "/getCompanyTasks")
    public BaseJsonResponse getCompanyTasks(@RequestParam("companyId") int companyId) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        baseJsonResponse.setObj(Lists.transform(taskMapper.getCompanyTasks(companyId), this::parse));
        return baseJsonResponse;
    }

    @RequestMapping(value = "/getSearchTypes")
    public BaseJsonResponse getSearchTypes() {
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
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        baseJsonResponse.setObj(list);
        return baseJsonResponse;
    }

    @RequestMapping(value = "/publishTask", method = RequestMethod.POST)
    @Authentication(Role.User)
    public BaseJsonResponse publishTask(@RequestBody TaskRequest taskRequest) {
        TaskBean taskBean = taskRequest.getTaskBean();
        taskBean.setUserId(getToken().getId());
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        Task task = parse(taskBean);
        task.setPublishTime(new Date());
        task.setTaskStatus(TaskStatus.NEW.getValue());
        taskMapper.insertSelective(task);
        if (taskBean.getTypeBeans() != null) {
            for (TypeBean typeBean : taskBean.getTypeBeans()) {
                taskTypeMapper.insert(new TaskTypeKey(task.getTaskId(), typeBean.getTypeId()));
            }
        }
        baseJsonResponse.setObj(taskMapper.selectByPrimaryKey(task.getTaskId()));
        return baseJsonResponse;
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
    public BaseJsonResponse getMyPublishedTask(@RequestParam("userId") int userId) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        List<Task> tasks = taskMapper.getTakenTasksByUserId(userId);
        baseJsonResponse.setObj(Lists.transform(tasks, this::parse));
        return baseJsonResponse;
    }


    @RequestMapping(value = "/takenTasks")
    public BaseJsonResponse getMyTaken(@RequestParam("userId") int userId) {
        System.out.println("getMyTaken");
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        List<Task> tasks = taskMapper.getTakenTasksByUserId(userId);
        baseJsonResponse.setObj(tasks);
        return baseJsonResponse;
    }

    @RequestMapping(value = "/isTaken")
    public BaseJsonResponse isTaken(@RequestParam("userId") int userId, @RequestParam("taskId") int taskId) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        UserTakeKey userTakeKey = new UserTakeKey();
        userTakeKey.setUserId(userId);
        userTakeKey.setTaskId(taskId);
        UserTake userTake = userTakeMapper.selectByPrimaryKey(userTakeKey);
        BaseBean baseBean = new BaseBean(Objects.nonNull(userTake));
        baseJsonResponse.setObj(baseBean);
        return baseJsonResponse;
    }

    @RequestMapping(value = "/getTaskById")
    public BaseJsonResponse getTaskById(@RequestParam("taskId") int taskId) {
        System.out.println("getTaskById");
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        Task task = taskMapper.selectByPrimaryKey(taskId);
        baseJsonResponse.setObj(task);
        return baseJsonResponse;
    }

    @RequestMapping(value = "/cancelPublishedTask")
    public void cancelPublishedTask(@RequestParam("taskId") int taskId) {
        taskMapper.deleteByPrimaryKey(taskId);
        System.out.println("task" + taskId + "已删除");
    }


    @RequestMapping(value = "/getSearch")
    public BaseJsonResponse getSearch(@RequestParam("map") String map) {
        System.out.println(map);
        String[] list = map.split(",");
        List<Integer> typeList = typeMapper.getSearchType(list);
        typeList.add(0);
        System.out.println(typeList);
        List<Task> tasks = taskMapper.getSearchTasks(typeList);
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        baseJsonResponse.setObj(tasks);
        return baseJsonResponse;
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
    public BaseJsonResponse userTakeTask(@RequestParam("taskId") int taskId, @RequestParam("userId") int userId) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
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
            baseJsonResponse.setReturnCode("E");
            baseJsonResponse.setErrorMessage("已经申请");
        }
        baseJsonResponse.setObj(userTake);
        return baseJsonResponse;
    }

    @RequestMapping(value = "/singleTask")
    public BaseJsonResponse takeTask(@RequestParam("taskId") String taskId) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        HttpSession session = servletRequest.getSession();
        session.setAttribute("taskId", taskId);
        return baseJsonResponse;
    }

    @RequestMapping(value = "/editTask")
    public BaseJsonResponse editTask(@RequestParam("taskId") String taskId,
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
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        HttpSession session = servletRequest.getSession();
        session.setAttribute("taskId", taskId);
        return baseJsonResponse;
    }


    @RequestMapping(value = "/deleteTask")
    public BaseJsonResponse editTask(@RequestParam("taskId") String taskId) {
        int companyId = taskMapper.selectByPrimaryKey(Integer.valueOf(taskId)).getCompanyId();
        taskMapper.deleteByPrimaryKey(Integer.valueOf(taskId));
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        HttpSession session = servletRequest.getSession();
        session.setAttribute("companyId", companyId);
        return baseJsonResponse;
    }

    @RequestMapping(value = "/userCancelTakeTask")
    public BaseJsonResponse userCancelTakeTask(@RequestParam("taskId") int taskId, @RequestParam("userId") int userId) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        UserTakeKey userTakeKey = new UserTakeKey();
        userTakeKey.setUserId(userId);
        userTakeKey.setTaskId(taskId);
        UserTake userTake = userTakeMapper.selectByPrimaryKey(userTakeKey);
        if (userTake == null) {
            baseJsonResponse.setReturnCode("E");
            baseJsonResponse.setErrorMessage("未申请过");
        } else {
            //baseJsonResponse.setReturnCode("");
            switch (UserTaskStatus.valueOf(userTake.getStatus())) {
                case APPLY_FOR_CANCEL:
                    baseJsonResponse.setReturnCode("E");
                    baseJsonResponse.setErrorMessage("已经申请");
                    break;
                case CANCELLED:
                    baseJsonResponse.setReturnCode("E");
                    baseJsonResponse.setErrorMessage("已经取消");
                    break;
                default:
                    userTake.setStatus(UserTaskStatus.APPLY_FOR_CANCEL.getValue());
                    userTakeMapper.updateByPrimaryKeySelective(userTake);
                    break;
            }
        }
        baseJsonResponse.setObj(userTake);
        return baseJsonResponse;
    }

    @RequestMapping(value = "/isCollected")
    public BaseJsonResponse isCollected(@RequestParam("taskId") int taskId, @RequestParam("userId") int userId) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        UserCollectKey userTakeKey = new UserCollectKey();
        userTakeKey.setUserId(userId);
        userTakeKey.setTaskId(taskId);
        UserCollectKey userTake = userCollectMapper.selectByPrimaryKey(userTakeKey);
        BaseBean baseBean = new BaseBean(Objects.nonNull(userTake));
        baseJsonResponse.setObj(baseBean);
        baseJsonResponse.setReturnCode("1.0.C.0");
        return baseJsonResponse;
    }

    @RequestMapping(value = "/collect")
    public BaseJsonResponse collect(@RequestParam("taskId") int taskId, @RequestParam("userId") int userId) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
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
        baseJsonResponse.setObj(baseBean);
        baseJsonResponse.setReturnCode("1.0.C.0");
        return baseJsonResponse;
    }


    @RequestMapping(value = "/isTake")
    public BaseJsonResponse isTake(@RequestParam("taskId") int taskId, @RequestParam("userId") int userId) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        UserTakeKey userTakeKey = new UserTakeKey();
        userTakeKey.setUserId(userId);
        userTakeKey.setTaskId(taskId);
        UserTake userTake = userTakeMapper.selectByPrimaryKey(userTakeKey);
        BaseBean baseBean = new BaseBean(Objects.nonNull(userTake));
        baseJsonResponse.setObj(baseBean);
        baseJsonResponse.setReturnCode("1.0.T.0");
        return baseJsonResponse;
    }


    @RequestMapping(value = "/take")
    public BaseJsonResponse take(@RequestParam("taskId") int taskId, @RequestParam("userId") int userId) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
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
        baseJsonResponse.setObj(baseBean);
        baseJsonResponse.setReturnCode("1.0.T.0");
        return baseJsonResponse;
    }

    @RequestMapping(value = "/showParticipant")
    public BaseJsonResponse showParticipant(@RequestParam("taskId") int taskId) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        BaseBean baseBean = new BaseBean();
        baseBean.setSingleResult(String.valueOf("taskId"));
        baseJsonResponse.setObj(baseBean);
        HttpSession session = servletRequest.getSession();
        session.setAttribute("taskId", taskId);
        return baseJsonResponse;

    }

    @RequestMapping(value = "/getParticipant")
    public BaseJsonResponse getParticipant(@RequestParam("taskId") int taskId) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        BaseBean baseBean = new BaseBean();
        List<UserInfoBean> userInfoList = taskMapper.getParticipant(taskId);
        baseJsonResponse.setObj(userInfoList);
        return baseJsonResponse;
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

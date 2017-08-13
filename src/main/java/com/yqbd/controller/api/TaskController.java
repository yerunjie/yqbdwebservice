package com.yqbd.controller.api;


import com.yqbd.beans.BaseJson;
import com.yqbd.controller.BaseController;
import com.yqbd.mapper.TaskMapper;
import com.yqbd.mapper.TypeMapper;
import com.yqbd.mapper.UserTakeMapper;
import com.yqbd.model.Task;
import com.yqbd.model.Type;
import com.yqbd.model.UserTake;
import com.yqbd.model.UserTakeKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

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

    @RequestMapping(value = "/getAllTypes")
    public BaseJson getAllTypes() {
        BaseJson baseJson = new BaseJson();
        List<Type> types = typeMapper.selectAllTypes();
        baseJson.setObj(types);
        return baseJson;
    }

    @RequestMapping(value = "/publishTask", method = RequestMethod.POST)
    public BaseJson publishTask(@RequestParam("userId") int userId,
                                @RequestParam("companyId") int companyId,
                                @RequestParam("taskTitle") String taskTitle,
                                @RequestParam("taskDescription") String taskDescription,
                                @RequestParam("taskAddress") String taskAddress,
                                @RequestParam("pay") double pay,
                                @RequestParam("maxPeopleNumber") int maxPeopleNumber,
                                @RequestParam("deadline") long deadline,
                                @RequestParam("simpleDrawingAddress") String simpleDrawingAddress,
                                @RequestParam("groupId") int groupId,
                                @RequestParam("typeBeans") String typeBeans) {
        BaseJson baseJson = new BaseJson();
        Task task = new Task();
        if (userId != 0) {
            task.setUserId(userId);
        }
        if (companyId != 0) {
            task.setCompanyId(companyId);
        }
        task.setSimpleDrawingAddress(simpleDrawingAddress);
        task.setTaskAddress(taskAddress);
        task.setDeadline(new Date(deadline));
        task.setGroupId(groupId);
        task.setSignUpPeopleNumber(0);
        task.setCurrentPeopleNumber(0);
        task.setMaxPeopleNumber(maxPeopleNumber);
        task.setPay(pay);
        task.setTaskDescription(taskDescription);
        task.setTaskTitle(taskTitle);
        taskMapper.insertSelective(task);
        baseJson.setObj(taskMapper.selectByPrimaryKey(task.getTaskId()));
        return baseJson;
    }

    @RequestMapping(value = "/myTask")
    public BaseJson getMyPublishedTask(@RequestParam("userId") int userId) {
        System.out.println("getMyPublishedTask");
        BaseJson baseJson = new BaseJson();
        List<Task> tasks = taskMapper.getPublishedTasksById(userId);
        baseJson.setObj(tasks);
        return baseJson;
    }

    @RequestMapping(value="/myTaken")
    public BaseJson getMyTaken(@RequestParam("userId") int userId){
        System.out.println("getMyTaken");
        BaseJson baseJson=new BaseJson();
        List<Task> tasks=taskMapper.getTasksById(userId);
        baseJson.setObj(tasks);
        return baseJson;
    }

    @RequestMapping(value = "/isTaken")
    public boolean isTaken(@RequestParam("userId") int userId, @RequestParam("taskId") int taskId){
        boolean isTaken;
        UserTakeKey userTakeKey=new UserTakeKey();
        userTakeKey.setUserId(userId);
        userTakeKey.setTaskId(taskId);
        UserTake userTake=userTakeMapper.selectByPrimaryKey(userTakeKey);
        if(userTake!=null) isTaken=true;
        else isTaken=false;
        return isTaken;
    }

    @RequestMapping(value = "/getTaskById")
    public BaseJson getTaskById(@RequestParam("taskId") int taskId){
        System.out.println("getTaskById");
        BaseJson baseJson=new BaseJson();
        Task task=taskMapper.selectByPrimaryKey(taskId);
        baseJson.setObj(task);
        return baseJson;
    }

    @RequestMapping(value = "/cancelPublishedTask")
    public void cancelPublishedTask(@RequestParam("taskId") int taskId){
        taskMapper.deleteByPrimaryKey(taskId);
        System.out.println("task"+taskId+"已删除");
    }

    @RequestMapping(value = "/cancelTaken")
    public void cancelTaken(@RequestParam("taskId") int taskId, @RequestParam("userId") int userId){
        UserTakeKey userTakeKey=new UserTakeKey();
        userTakeKey.setTaskId(taskId);
        userTakeKey.setUserId(userId);
        userTakeMapper.deleteByPrimaryKey(userTakeKey);
        System.out.println("delete user take~");
    }

    @RequestMapping(value = "/takeTask")
    public void takeTask(@RequestParam("taskId") int taskId, @RequestParam("userId") int userId){
        UserTake userTake=new UserTake();
        userTake.setUserId(userId);
        userTake.setTaskId(taskId);
        userTake.setStatus(1);
        userTakeMapper.insert(userTake);
        System.out.println("insert user take~");
    }




}

package com.yqbd.controller.api;


import com.yqbd.beans.ApplicationBean;
import com.yqbd.beans.BaseJson;
import com.yqbd.mapper.GroupInfoMapper;
import com.yqbd.mapper.GroupMemberMapper;
import com.yqbd.mapper.UserInfoMapper;
import com.yqbd.model.GroupInfo;
import com.yqbd.model.GroupMember;
import com.yqbd.model.GroupMemberKey;
import com.yqbd.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joy12 on 2017/7/23.
 */
@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupInfoMapper groupInfoMapper;

    @Autowired
    private GroupMemberMapper groupMemberMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;


    @RequestMapping(value = "/getCompanyGroups", method = RequestMethod.POST)
    public BaseJson getCompanyGroups(@RequestParam("companyId") int companyId) {
        BaseJson baseJson = new BaseJson();
        ArrayList<GroupInfo> groups = new ArrayList<>();
        groups.addAll(groupInfoMapper.selectByCompanyId(companyId));
        baseJson.setObj(groups);
        return baseJson;
    }

    @RequestMapping(value = "/getGroupByGroupId", method = RequestMethod.POST)
    public  BaseJson getGroupByGroupId(@RequestParam("groupId") int groupId){
        BaseJson baseJson = new BaseJson();
        GroupInfo group = groupInfoMapper.selectByPrimaryKey(groupId);
        if (group != null){
            baseJson.setReturnCode("4.8.0");
            baseJson.setErrorMessage("成功");
            baseJson.setObj(group);
        }else {
            baseJson.setReturnCode("4.8.E.1");
            baseJson.setErrorMessage("小组不存在");
        }

        return baseJson;
    }

    @RequestMapping(value = "/getGroupsByUserIdAndStatus", method = RequestMethod.POST)
    public  BaseJson getGroupsByUserIdAndStatus(@RequestParam("userId") int userId,
                                                @RequestParam("status") int status){
        BaseJson baseJson = new BaseJson();
        List<GroupMember> groupMemberList = new ArrayList<>();
        List<GroupInfo> groupInfoList = new ArrayList<>();
        groupMemberList.addAll(groupMemberMapper.selectByUserId(userId));

        if (groupMemberList.size() > 0){
            for (GroupMember groupMember : groupMemberList){
                if (groupMember.getStatus() == status)
                    groupInfoList.add(groupInfoMapper.selectByPrimaryKey(groupMember.getGroupId()));
            }
            baseJson.setErrorMessage("获取成功");
            baseJson.setReturnCode("4.9.0.0");
        } else {
            baseJson.setErrorMessage("未找到符合条件的小组");
            baseJson.setReturnCode("4.9.0.1");
        }
        System.out.println("找到" + groupInfoList.size() + "组");
        baseJson.setObj(groupInfoList);

        return baseJson;
    }

    @RequestMapping(value = "/getUncheckedApplications", method = RequestMethod.POST)
    public  BaseJson getUncheckedApplications(@RequestParam("companyId") int companyId,
                                              @RequestParam("status") int status){
        BaseJson baseJson = new BaseJson();
        List<ApplicationBean> uncheckedApplications = new ArrayList<>();
        List<GroupInfo> companyGroupList = new ArrayList<>();
        companyGroupList.addAll(groupInfoMapper.selectByCompanyId(companyId));

        if (companyGroupList.size() > 0){
            for (GroupInfo groupInfo : companyGroupList){
                List<GroupMember> tmpList = groupMemberMapper.selectByGroupId(groupInfo.getGroupId());
                for (GroupMember item : tmpList){
                    if (item.getStatus() == status){
                        UserInfo candidateInfo = userInfoMapper.selectByPrimaryKey(item.getUserId());
                        ApplicationBean applicationBean = new ApplicationBean(groupInfo,candidateInfo,item);
                        uncheckedApplications.add(applicationBean);
                    }
                }
            }
            System.out.println("公司" + companyId + "成功获取" + uncheckedApplications.size() + "个状态为" + status + "的申请");
            baseJson.setErrorMessage("获取成功");
            baseJson.setReturnCode("4.10.0.0");
        } else {
            baseJson.setErrorMessage("您尚未建立任何小组");
            baseJson.setReturnCode("4.10.0.1");
        }
        baseJson.setObj(uncheckedApplications);

        return baseJson;
    }

    @RequestMapping(value = "/createGroup", method = RequestMethod.POST)
    public BaseJson createGroup(@RequestParam("companyId") int companyId,
                                @RequestParam("maxPeopleNumber") int maxPeopleNumber,
                                @RequestParam("groupTitle") String groupTitle,
                                @RequestParam("groupDescription") String groupDescription) {
        BaseJson baseJson = new BaseJson();
        baseJson.setReturnCode("4.0.0");
        List<GroupInfo> groups = groupInfoMapper.selectByCompanyId(companyId);
        //groups.addAll(groupInfoMapper.selectByCompanyId(companyId));
        for (GroupInfo group : groups){
            if (group.getGroupTitle().equals(groupTitle)){
                baseJson.setObj(group);
                baseJson.setReturnCode("4.0.E.1");
                baseJson.setErrorMessage("该小组已存在");
                break;
            }
        }
        if (baseJson.getReturnCode().equals("4.0.0")){
            GroupInfo group = new GroupInfo(companyId,groupTitle,0,maxPeopleNumber,groupDescription);
            groupInfoMapper.insertSelective(group);
            groups.addAll(groupInfoMapper.selectByCompanyId(companyId));
            for (GroupInfo targetgroup : groups){
                if (targetgroup.getGroupTitle().equals(groupTitle)){
                    baseJson.setObj(targetgroup);
                    baseJson.setErrorMessage("成功");
                    break;
                }
            }
        }
        return baseJson;
    }


    @RequestMapping(value = "/deleteGroup" , method = RequestMethod.POST)
    public BaseJson deleteGroup(@RequestParam("groupId") Integer groupId,
                                @RequestParam("companyId") Integer companyId){
        BaseJson baseJson = new BaseJson();
        GroupInfo groupInfo = groupInfoMapper.selectByPrimaryKey(groupId);
        if (groupInfo != null){
            if (groupInfo.getCompanyId() == companyId){
                groupInfoMapper.deleteByPrimaryKey(groupId);
                groupMemberMapper.deleteByGroupId(groupId);
                baseJson.setReturnCode("4.1.0");
                baseJson.setErrorMessage("成功");
            } else {
                baseJson.setReturnCode("4.1.E.2");
                baseJson.setErrorMessage("您非该小组的所属公司，无法进行删除操作");
            }

        } else {
            groupInfo = new GroupInfo();
            baseJson.setReturnCode("4.1.E.1");
            baseJson.setErrorMessage("该小组不存在");
        }
        baseJson.setObj(groupInfo);
        return baseJson;
    }


    //个人用户用：status=0 已加入状态，status=1 加入申请审核状态
    @RequestMapping(value = "/joinApply" , method = RequestMethod.POST)
    public BaseJson joinApply(@RequestParam("groupId") int groupId,
                                     @RequestParam("userId") int userId){
        BaseJson baseJson = new BaseJson();

        GroupMemberKey groupMemberKey = new GroupMemberKey();
        groupMemberKey.setGroupId(groupId);
        groupMemberKey.setUserId(userId);
        GroupMember groupMember = groupMemberMapper.selectByPrimaryKey(groupMemberKey);
        if (groupMember != null){
            baseJson.setReturnCode("4.2.E.1");
            baseJson.setErrorMessage("您已提交过入组申请");
        } else {
            GroupInfo group = groupInfoMapper.selectByPrimaryKey(groupId);
            groupMember = new GroupMember();
            groupMember.setUserId(userId);
            groupMember.setGroupId(groupId);
            if (group.getCurrentPeopleNumber() < group.getMaxPeopleNumber()){
                groupMember.setStatus(1);
                groupMemberMapper.insertSelective(groupMember);
                baseJson.setReturnCode("4.2.0");
                baseJson.setErrorMessage("申请提交成功，请等待企业审核");
            } else {
                groupMember = new GroupMember();
                groupMember.setStatus(-1);
                baseJson.setReturnCode("4.2.E.1");
                baseJson.setErrorMessage("很抱歉，该组成员已满");
            }
        }
        baseJson.setObj(groupMember);

        return baseJson;
    }

    //个人用户用，status=0 已加入状态，status=1 加入申请审核状态
    @RequestMapping(value = "/joinCancel" , method = RequestMethod.POST)
    public BaseJson joinCancel(@RequestParam("groupId") int groupId,
                              @RequestParam("userId") int userId){
        BaseJson baseJson = new BaseJson();

        GroupMemberKey groupMemberKey = new GroupMemberKey();
        groupMemberKey.setGroupId(groupId);
        groupMemberKey.setUserId(userId);

        GroupMember groupMember = groupMemberMapper.selectByPrimaryKey(groupMemberKey);

        if (groupMember == null){
            groupMember = new GroupMember();
            baseJson.setReturnCode("4.3.E.1");
            baseJson.setErrorMessage("您未提交过入组申请");
        } else {
            groupMemberMapper.deleteByPrimaryKey(groupMemberKey);
            baseJson.setReturnCode("4.3.0");
            baseJson.setErrorMessage("成功");
        }
        baseJson.setObj(groupMember);

        return baseJson;
    }

    //个人用户用：status=0 已加入状态，status=1 加入申请审核状态，status=2 退出申请审核状态
    @RequestMapping(value = "/quitApply" , method = RequestMethod.POST)
    public BaseJson quitApply(@RequestParam("groupId") int groupId,
                              @RequestParam("userId") int userId){
        BaseJson baseJson = new BaseJson();

        GroupMemberKey groupMemberKey = new GroupMemberKey();
        groupMemberKey.setGroupId(groupId);
        groupMemberKey.setUserId(userId);
        GroupMember groupMember = groupMemberMapper.selectByPrimaryKey(groupMemberKey);
        if (groupMember == null){
            groupMember = new GroupMember();
            baseJson.setReturnCode("4.5.E.1");
            baseJson.setErrorMessage("您不是该小组成员");
        } else {
            groupMember.setStatus(2);
            groupMemberMapper.updateByPrimaryKeySelective(groupMember);
            baseJson.setReturnCode("4.5.0");
            baseJson.setErrorMessage("退出申请提交成功");
        }
        baseJson.setObj(groupMember);

        return baseJson;
    }

    //企业用，status=0 已加入状态，加入申请审核不通过 status=-1
    @RequestMapping(value = "/joinCheck" , method = RequestMethod.POST)
    public BaseJson joinCheck(@RequestParam("groupId") int groupId,
                              @RequestParam("userId") int userId,
                              @RequestParam("isPass") boolean isPass){
        BaseJson baseJson = new BaseJson();

        GroupMemberKey groupMemberKey = new GroupMemberKey();
        groupMemberKey.setGroupId(groupId);
        groupMemberKey.setUserId(userId);
        GroupMember groupMember = groupMemberMapper.selectByPrimaryKey(groupMemberKey);

        if (groupMember == null){
            groupMember = new GroupMember();
            baseJson.setReturnCode("4.4.E.1");
            baseJson.setErrorMessage("该用户并未提交入组申请");
        } else if (groupMember.getStatus() != 1){
            baseJson.setReturnCode("4.4.E.2");
            baseJson.setErrorMessage("该用户并不处于等待入组申请审核状态");
        }else if (isPass){
            GroupInfo group = groupInfoMapper.selectByPrimaryKey(groupId);
            if (group.getCurrentPeopleNumber() < group.getMaxPeopleNumber()){
                groupMember.setStatus(0);
                groupMemberMapper.updateByPrimaryKeySelective(groupMember);
                group.setCurrentPeopleNumber(group.getCurrentPeopleNumber() + 1);
                groupInfoMapper.updateByPrimaryKeySelective(group);
                baseJson.setReturnCode("4.4.0");
                baseJson.setErrorMessage("审核通过，该用户成为小组一员");
            } else {
                groupMember.setStatus(-1);
                groupMemberMapper.updateByPrimaryKeySelective(groupMember);
                baseJson.setReturnCode("4.4.E.3");
                baseJson.setErrorMessage("小组成员已满，审核失败");
            }

        } else if (!isPass){
            groupMember.setStatus(-1);
            groupMemberMapper.updateByPrimaryKeySelective(groupMember);
            baseJson.setReturnCode("4.4.0.1");
            baseJson.setErrorMessage("已拒绝该用户的申请");
        }

        baseJson.setObj(groupMember);

        return baseJson;
    }

    @RequestMapping(value = "/quitCheck" , method = RequestMethod.POST)
    public BaseJson quitCheck(@RequestParam("groupId") int groupId,
                              @RequestParam("userId") int userId,
                              @RequestParam("isPass") boolean isPass){
        BaseJson baseJson = new BaseJson();

        GroupMemberKey groupMemberKey = new GroupMemberKey();
        groupMemberKey.setGroupId(groupId);
        groupMemberKey.setUserId(userId);
        GroupMember groupMember = groupMemberMapper.selectByPrimaryKey(groupMemberKey);

        if (groupMember == null){
            groupMember = new GroupMember();
            baseJson.setReturnCode("4.6.E.1");
            baseJson.setErrorMessage("该用户并不是小组成员");
        } else if (groupMember.getStatus() != 2){
            baseJson.setReturnCode("4.6.E.2");
            baseJson.setErrorMessage("该用户并不处于等待退组申请审核状态");
        }else if (isPass){
            GroupInfo group = groupInfoMapper.selectByPrimaryKey(groupId);
            groupMemberMapper.deleteByPrimaryKey(groupMemberKey);
            group.setCurrentPeopleNumber(group.getCurrentPeopleNumber() - 1);
            groupInfoMapper.updateByPrimaryKeySelective(group);
            baseJson.setReturnCode("4.6.0.0");
            baseJson.setErrorMessage("审核通过，该用户退出小组");
        } else if (!isPass){
            groupMember.setStatus(0);
            groupMemberMapper.updateByPrimaryKeySelective(groupMember);
            baseJson.setReturnCode("4.6.0.1");
            baseJson.setErrorMessage("已拒绝该用户的申请");
        }

        baseJson.setObj(groupMember);

        return baseJson;
    }

    //企业用
    @RequestMapping(value = "/deleteMember" , method = RequestMethod.POST)
    public BaseJson deleteMember(@RequestParam("groupId") int groupId,
                                 @RequestParam("userId") int userId){
        BaseJson baseJson = new BaseJson();

        GroupMemberKey groupMemberKey = new GroupMemberKey();
        groupMemberKey.setGroupId(groupId);
        groupMemberKey.setUserId(userId);
        GroupMember groupMember = groupMemberMapper.selectByPrimaryKey(groupMemberKey);

        if (groupMember == null || groupMember.getStatus() != 0){
            baseJson.setReturnCode("4.7.E.1");
            baseJson.setErrorMessage("该用户并不是小组成员");
        } else {
            groupMemberMapper.deleteByPrimaryKey(groupMemberKey);
            baseJson.setReturnCode("4.7.0");
            baseJson.setErrorMessage("删除成功");
        }

        baseJson.setObj(groupMember);

        return baseJson;
    }
}

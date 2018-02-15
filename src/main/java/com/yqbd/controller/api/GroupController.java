package com.yqbd.controller.api;


import com.yqbd.beans.ApplicationBean;
import com.yqbd.dto.response.BaseJsonResponse;
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
    public BaseJsonResponse getCompanyGroups(@RequestParam("companyId") int companyId) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        ArrayList<GroupInfo> groups = new ArrayList<>();
        groups.addAll(groupInfoMapper.selectByCompanyId(companyId));
        baseJsonResponse.setObj(groups);
        return baseJsonResponse;
    }

    @RequestMapping(value = "/getGroupByGroupId", method = RequestMethod.POST)
    public BaseJsonResponse getGroupByGroupId(@RequestParam("groupId") int groupId) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        GroupInfo group = groupInfoMapper.selectByPrimaryKey(groupId);
        if (group != null) {
            baseJsonResponse.setReturnCode("4.8.0");
            baseJsonResponse.setErrorMessage("成功");
            baseJsonResponse.setObj(group);
        } else {
            baseJsonResponse.setReturnCode("4.8.E.1");
            baseJsonResponse.setErrorMessage("小组不存在");
        }

        return baseJsonResponse;
    }

    @RequestMapping(value = "/getGroupsByUserIdAndStatus", method = RequestMethod.POST)
    public BaseJsonResponse getGroupsByUserIdAndStatus(@RequestParam("userId") int userId,
                                                       @RequestParam("status") int status) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        List<GroupMember> groupMemberList = new ArrayList<>();
        List<GroupInfo> groupInfoList = new ArrayList<>();
        groupMemberList.addAll(groupMemberMapper.selectByUserId(userId));

        if (groupMemberList.size() > 0) {
            for (GroupMember groupMember : groupMemberList) {
                if (groupMember.getStatus() == status)
                    groupInfoList.add(groupInfoMapper.selectByPrimaryKey(groupMember.getGroupId()));
            }
            baseJsonResponse.setErrorMessage("获取成功");
            baseJsonResponse.setReturnCode("4.9.0.0");
        } else {
            baseJsonResponse.setErrorMessage("未找到符合条件的小组");
            baseJsonResponse.setReturnCode("4.9.0.1");
        }
        System.out.println("找到" + groupInfoList.size() + "组");
        baseJsonResponse.setObj(groupInfoList);

        return baseJsonResponse;
    }

    @RequestMapping(value = "/getUncheckedApplications", method = RequestMethod.POST)
    public BaseJsonResponse getUncheckedApplications(@RequestParam("companyId") int companyId,
                                                     @RequestParam("status") int status) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        List<ApplicationBean> uncheckedApplications = new ArrayList<>();
        List<GroupInfo> companyGroupList = new ArrayList<>();
        companyGroupList.addAll(groupInfoMapper.selectByCompanyId(companyId));

        if (companyGroupList.size() > 0) {
            for (GroupInfo groupInfo : companyGroupList) {
                List<GroupMember> tmpList = groupMemberMapper.selectByGroupId(groupInfo.getGroupId());
                for (GroupMember item : tmpList) {
                    if (item.getStatus() == status) {
                        UserInfo candidateInfo = userInfoMapper.selectByPrimaryKey(item.getUserId());
                        ApplicationBean applicationBean = new ApplicationBean(groupInfo, candidateInfo, item);
                        uncheckedApplications.add(applicationBean);
                    }
                }
            }
            System.out.println("公司" + companyId + "成功获取" + uncheckedApplications.size() + "个状态为" + status + "的申请");
            baseJsonResponse.setErrorMessage("获取成功");
            baseJsonResponse.setReturnCode("4.10.0.0");
        } else {
            baseJsonResponse.setErrorMessage("您尚未建立任何小组");
            baseJsonResponse.setReturnCode("4.10.0.1");
        }
        baseJsonResponse.setObj(uncheckedApplications);

        return baseJsonResponse;
    }

    @RequestMapping(value = "/createGroup", method = RequestMethod.POST)
    public BaseJsonResponse createGroup(@RequestParam("companyId") int companyId,
                                        @RequestParam("maxPeopleNumber") int maxPeopleNumber,
                                        @RequestParam("groupTitle") String groupTitle,
                                        @RequestParam("groupDescription") String groupDescription) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        baseJsonResponse.setReturnCode("4.0.0");
        List<GroupInfo> groups = groupInfoMapper.selectByCompanyId(companyId);
        //groups.addAll(groupInfoMapper.selectByCompanyId(companyId));
        for (GroupInfo group : groups) {
            if (group.getGroupTitle().equals(groupTitle)) {
                baseJsonResponse.setObj(group);
                baseJsonResponse.setReturnCode("4.0.E.1");
                baseJsonResponse.setErrorMessage("该小组已存在");
                break;
            }
        }
        if (baseJsonResponse.getReturnCode().equals("4.0.0")) {
            GroupInfo group = new GroupInfo(companyId, groupTitle, 0, maxPeopleNumber, groupDescription);
            groupInfoMapper.insertSelective(group);
            groups.addAll(groupInfoMapper.selectByCompanyId(companyId));
            for (GroupInfo targetgroup : groups) {
                if (targetgroup.getGroupTitle().equals(groupTitle)) {
                    baseJsonResponse.setObj(targetgroup);
                    baseJsonResponse.setErrorMessage("成功");
                    break;
                }
            }
        }
        return baseJsonResponse;
    }


    @RequestMapping(value = "/deleteGroup", method = RequestMethod.POST)
    public BaseJsonResponse deleteGroup(@RequestParam("groupId") Integer groupId,
                                        @RequestParam("companyId") Integer companyId) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
        GroupInfo groupInfo = groupInfoMapper.selectByPrimaryKey(groupId);
        if (groupInfo != null) {
            if (groupInfo.getCompanyId() == companyId) {
                groupInfoMapper.deleteByPrimaryKey(groupId);
                groupMemberMapper.deleteByGroupId(groupId);
                baseJsonResponse.setReturnCode("4.1.0");
                baseJsonResponse.setErrorMessage("成功");
            } else {
                baseJsonResponse.setReturnCode("4.1.E.2");
                baseJsonResponse.setErrorMessage("您非该小组的所属公司，无法进行删除操作");
            }

        } else {
            groupInfo = new GroupInfo();
            baseJsonResponse.setReturnCode("4.1.E.1");
            baseJsonResponse.setErrorMessage("该小组不存在");
        }
        baseJsonResponse.setObj(groupInfo);
        return baseJsonResponse;
    }


    //个人用户用：status=0 已加入状态，status=1 加入申请审核状态
    @RequestMapping(value = "/joinApply", method = RequestMethod.POST)
    public BaseJsonResponse joinApply(@RequestParam("groupId") int groupId,
                                      @RequestParam("userId") int userId) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();

        GroupMemberKey groupMemberKey = new GroupMemberKey();
        groupMemberKey.setGroupId(groupId);
        groupMemberKey.setUserId(userId);
        GroupMember groupMember = groupMemberMapper.selectByPrimaryKey(groupMemberKey);
        if (groupMember != null) {
            baseJsonResponse.setReturnCode("4.2.E.1");
            baseJsonResponse.setErrorMessage("您已提交过入组申请");
        } else {
            GroupInfo group = groupInfoMapper.selectByPrimaryKey(groupId);
            groupMember = new GroupMember();
            groupMember.setUserId(userId);
            groupMember.setGroupId(groupId);
            if (group.getCurrentPeopleNumber() < group.getMaxPeopleNumber()) {
                groupMember.setStatus(1);
                groupMemberMapper.insertSelective(groupMember);
                baseJsonResponse.setReturnCode("4.2.0");
                baseJsonResponse.setErrorMessage("申请提交成功，请等待企业审核");
            } else {
                groupMember = new GroupMember();
                groupMember.setStatus(-1);
                baseJsonResponse.setReturnCode("4.2.E.1");
                baseJsonResponse.setErrorMessage("很抱歉，该组成员已满");
            }
        }
        baseJsonResponse.setObj(groupMember);

        return baseJsonResponse;
    }

    //个人用户用，status=0 已加入状态，status=1 加入申请审核状态
    @RequestMapping(value = "/joinCancel", method = RequestMethod.POST)
    public BaseJsonResponse joinCancel(@RequestParam("groupId") int groupId,
                                       @RequestParam("userId") int userId) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();

        GroupMemberKey groupMemberKey = new GroupMemberKey();
        groupMemberKey.setGroupId(groupId);
        groupMemberKey.setUserId(userId);

        GroupMember groupMember = groupMemberMapper.selectByPrimaryKey(groupMemberKey);

        if (groupMember == null) {
            groupMember = new GroupMember();
            baseJsonResponse.setReturnCode("4.3.E.1");
            baseJsonResponse.setErrorMessage("您未提交过入组申请");
        } else {
            groupMemberMapper.deleteByPrimaryKey(groupMemberKey);
            baseJsonResponse.setReturnCode("4.3.0");
            baseJsonResponse.setErrorMessage("成功");
        }
        baseJsonResponse.setObj(groupMember);

        return baseJsonResponse;
    }

    //个人用户用：status=0 已加入状态，status=1 加入申请审核状态，status=2 退出申请审核状态
    @RequestMapping(value = "/quitApply", method = RequestMethod.POST)
    public BaseJsonResponse quitApply(@RequestParam("groupId") int groupId,
                                      @RequestParam("userId") int userId) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();

        GroupMemberKey groupMemberKey = new GroupMemberKey();
        groupMemberKey.setGroupId(groupId);
        groupMemberKey.setUserId(userId);
        GroupMember groupMember = groupMemberMapper.selectByPrimaryKey(groupMemberKey);
        if (groupMember == null) {
            groupMember = new GroupMember();
            baseJsonResponse.setReturnCode("4.5.E.1");
            baseJsonResponse.setErrorMessage("您不是该小组成员");
        } else {
            groupMember.setStatus(2);
            groupMemberMapper.updateByPrimaryKeySelective(groupMember);
            baseJsonResponse.setReturnCode("4.5.0");
            baseJsonResponse.setErrorMessage("退出申请提交成功");
        }
        baseJsonResponse.setObj(groupMember);

        return baseJsonResponse;
    }

    //企业用，status=0 已加入状态，加入申请审核不通过 status=-1
    @RequestMapping(value = "/joinCheck", method = RequestMethod.POST)
    public BaseJsonResponse joinCheck(@RequestParam("groupId") int groupId,
                                      @RequestParam("userId") int userId,
                                      @RequestParam("isPass") boolean isPass) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();

        GroupMemberKey groupMemberKey = new GroupMemberKey();
        groupMemberKey.setGroupId(groupId);
        groupMemberKey.setUserId(userId);
        GroupMember groupMember = groupMemberMapper.selectByPrimaryKey(groupMemberKey);

        if (groupMember == null) {
            groupMember = new GroupMember();
            baseJsonResponse.setReturnCode("4.4.E.1");
            baseJsonResponse.setErrorMessage("该用户并未提交入组申请");
        } else if (groupMember.getStatus() != 1) {
            baseJsonResponse.setReturnCode("4.4.E.2");
            baseJsonResponse.setErrorMessage("该用户并不处于等待入组申请审核状态");
        } else if (isPass) {
            GroupInfo group = groupInfoMapper.selectByPrimaryKey(groupId);
            if (group.getCurrentPeopleNumber() < group.getMaxPeopleNumber()) {
                groupMember.setStatus(0);
                groupMemberMapper.updateByPrimaryKeySelective(groupMember);
                group.setCurrentPeopleNumber(group.getCurrentPeopleNumber() + 1);
                groupInfoMapper.updateByPrimaryKeySelective(group);
                baseJsonResponse.setReturnCode("4.4.0");
                baseJsonResponse.setErrorMessage("审核通过，该用户成为小组一员");
            } else {
                groupMember.setStatus(-1);
                groupMemberMapper.updateByPrimaryKeySelective(groupMember);
                baseJsonResponse.setReturnCode("4.4.E.3");
                baseJsonResponse.setErrorMessage("小组成员已满，审核失败");
            }

        } else if (!isPass) {
            groupMember.setStatus(-1);
            groupMemberMapper.updateByPrimaryKeySelective(groupMember);
            baseJsonResponse.setReturnCode("4.4.0.1");
            baseJsonResponse.setErrorMessage("已拒绝该用户的申请");
        }

        baseJsonResponse.setObj(groupMember);

        return baseJsonResponse;
    }

    @RequestMapping(value = "/quitCheck", method = RequestMethod.POST)
    public BaseJsonResponse quitCheck(@RequestParam("groupId") int groupId,
                                      @RequestParam("userId") int userId,
                                      @RequestParam("isPass") boolean isPass) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();

        GroupMemberKey groupMemberKey = new GroupMemberKey();
        groupMemberKey.setGroupId(groupId);
        groupMemberKey.setUserId(userId);
        GroupMember groupMember = groupMemberMapper.selectByPrimaryKey(groupMemberKey);

        if (groupMember == null) {
            groupMember = new GroupMember();
            baseJsonResponse.setReturnCode("4.6.E.1");
            baseJsonResponse.setErrorMessage("该用户并不是小组成员");
        } else if (groupMember.getStatus() != 2) {
            baseJsonResponse.setReturnCode("4.6.E.2");
            baseJsonResponse.setErrorMessage("该用户并不处于等待退组申请审核状态");
        } else if (isPass) {
            GroupInfo group = groupInfoMapper.selectByPrimaryKey(groupId);
            groupMemberMapper.deleteByPrimaryKey(groupMemberKey);
            group.setCurrentPeopleNumber(group.getCurrentPeopleNumber() - 1);
            groupInfoMapper.updateByPrimaryKeySelective(group);
            baseJsonResponse.setReturnCode("4.6.0.0");
            baseJsonResponse.setErrorMessage("审核通过，该用户退出小组");
        } else if (!isPass) {
            groupMember.setStatus(0);
            groupMemberMapper.updateByPrimaryKeySelective(groupMember);
            baseJsonResponse.setReturnCode("4.6.0.1");
            baseJsonResponse.setErrorMessage("已拒绝该用户的申请");
        }

        baseJsonResponse.setObj(groupMember);

        return baseJsonResponse;
    }

    //企业用
    @RequestMapping(value = "/deleteMember", method = RequestMethod.POST)
    public BaseJsonResponse deleteMember(@RequestParam("groupId") int groupId,
                                         @RequestParam("userId") int userId) {
        BaseJsonResponse baseJsonResponse = new BaseJsonResponse();

        GroupMemberKey groupMemberKey = new GroupMemberKey();
        groupMemberKey.setGroupId(groupId);
        groupMemberKey.setUserId(userId);
        GroupMember groupMember = groupMemberMapper.selectByPrimaryKey(groupMemberKey);

        if (groupMember == null || groupMember.getStatus() != 0) {
            baseJsonResponse.setReturnCode("4.7.E.1");
            baseJsonResponse.setErrorMessage("该用户并不是小组成员");
        } else {
            groupMemberMapper.deleteByPrimaryKey(groupMemberKey);
            baseJsonResponse.setReturnCode("4.7.0");
            baseJsonResponse.setErrorMessage("删除成功");
        }

        baseJsonResponse.setObj(groupMember);

        return baseJsonResponse;
    }
}

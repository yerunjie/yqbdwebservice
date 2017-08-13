package com.yqbd.mapper;

import com.yqbd.model.GroupMember;
import com.yqbd.model.GroupMemberKey;

import java.util.List;

public interface GroupMemberMapper {
    int deleteByPrimaryKey(GroupMemberKey key);

    int insert(GroupMember record);

    int insertSelective(GroupMember record);

    GroupMember selectByPrimaryKey(GroupMemberKey key);

    int updateByPrimaryKeySelective(GroupMember record);

    int updateByPrimaryKey(GroupMember record);

    int deleteByGroupId(Integer groupId);

    List<GroupMember> selectByUserId (Integer userId);

    List<GroupMember> selectByGroupId (Integer userId);

}
package com.yqbd.mapper;

import com.yqbd.model.GroupMember;
import com.yqbd.model.GroupMemberKey;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GroupMemberMapper {
    @Delete({
            "delete from group_member",
            "where group_id = #{groupId,jdbcType=INTEGER}",
            "and user_id = #{userId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(GroupMemberKey key);

    @Insert({
            "insert into group_member (group_id, user_id, ",
            "participate_time, status)",
            "values (#{groupId,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
            "#{participateTime,jdbcType=TIMESTAMP}, #{status,jdbcType=INTEGER})"
    })
    int insert(GroupMember record);

    int insertSelective(GroupMember record);

    @Select({
            "select",
            "group_id, user_id, participate_time, status",
            "from group_member",
            "where group_id = #{groupId,jdbcType=INTEGER}",
            "and user_id = #{userId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    GroupMember selectByPrimaryKey(GroupMemberKey key);

    int updateByPrimaryKeySelective(GroupMember record);

    @Update({
            "update group_member",
            "set participate_time = #{participateTime,jdbcType=TIMESTAMP},",
            "status = #{status,jdbcType=INTEGER}",
            "where group_id = #{groupId,jdbcType=INTEGER}",
            "and user_id = #{userId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(GroupMember record);

    @Select(
            "    select * from group_member where user_id = #{userId,jdbcType=INTEGER}"
    )
    @ResultMap("BaseResultMap")
    List<GroupMember> selectByUserId(Integer userId);


    @Select(
            "   select * from group_member where group_id = #{groupId,jdbcType=INTEGER}"
    )
    @ResultMap("BaseResultMap")
    List<GroupMember> selectByGroupId(Integer groupId);


    @Delete(
            "delete from group_member where group_id = #{groupId,jdbcType=INTEGER}"
    )
    int deleteByGroupId(Integer groupId);
}
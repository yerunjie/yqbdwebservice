package com.yqbd.mapper;

import com.yqbd.model.UserTake;
import com.yqbd.model.UserTakeKey;
import org.apache.ibatis.annotations.*;

public interface UserTakeMapper {
    @Delete({
        "delete from user_take",
        "where task_id = #{taskId,jdbcType=INTEGER}",
          "and user_id = #{userId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(UserTakeKey key);


    @Insert({
            "insert into user_take (task_id, user_id, ",
            "status, publisher_comment_id, ",
            "receiver_comment_id)",
            "values (#{taskId,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
            "0, 0, ",
            "0)"
    })
    int insertUserTake(UserTakeKey record);


    @Insert({
        "insert into user_take (task_id, user_id, ",
        "status, publisher_comment_id, ",
        "receiver_comment_id)",
        "values (#{taskId,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{status,jdbcType=INTEGER}, #{publisherCommentId,jdbcType=INTEGER}, ",
        "#{receiverCommentId,jdbcType=INTEGER})"
    })
    int insert(UserTake record);

    int insertSelective(UserTake record);

    @Select({
        "select",
        "task_id, user_id, status, publisher_comment_id, receiver_comment_id",
        "from user_take",
        "where task_id = #{taskId,jdbcType=INTEGER}",
          "and user_id = #{userId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    UserTake selectByPrimaryKey(UserTakeKey key);

    int updateByPrimaryKeySelective(UserTake record);

    @Update({
        "update user_take",
        "set status = #{status,jdbcType=INTEGER},",
          "publisher_comment_id = #{publisherCommentId,jdbcType=INTEGER},",
          "receiver_comment_id = #{receiverCommentId,jdbcType=INTEGER}",
        "where task_id = #{taskId,jdbcType=INTEGER}",
          "and user_id = #{userId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserTake record);


}
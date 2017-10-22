package com.yqbd.mapper;

import com.yqbd.model.UserCollectKey;
import com.yqbd.model.UserTake;
import com.yqbd.model.UserTakeKey;
import org.apache.ibatis.annotations.*;

public interface UserCollectMapper {
    @Delete({
            "delete from user_collect",
            "where task_id = #{taskId,jdbcType=INTEGER}",
            "and user_id = #{userId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(UserCollectKey key);

    @Insert({
            "insert into user_collect (task_id, user_id)",
            "values (#{taskId,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER})"
    })
    int insert(UserCollectKey record);

    @Select({
            "select *",
            "from user_collect",
            "where task_id = #{taskId,jdbcType=INTEGER}",
            "and user_id = #{userId,jdbcType=INTEGER}"
    })
    @ResultType(UserCollectKey.class)
    UserCollectKey selectByPrimaryKey(UserCollectKey key);
}
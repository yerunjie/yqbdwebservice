package com.yqbd.mapper;

import com.yqbd.model.TaskTypeKey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

public interface TaskTypeMapper {
    @Delete({
        "delete from task_type",
        "where task_id = #{taskId,jdbcType=INTEGER}",
          "and type_id = #{typeId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(TaskTypeKey key);

    @Insert({
        "insert into task_type (task_id, type_id)",
        "values (#{taskId,jdbcType=INTEGER}, #{typeId,jdbcType=INTEGER})"
    })
    int insert(TaskTypeKey record);

    int insertSelective(TaskTypeKey record);



}
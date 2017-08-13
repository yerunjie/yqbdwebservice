package com.yqbd.mapper;

import com.yqbd.model.Task;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskMapper {
    int deleteByPrimaryKey(Integer taskId);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Integer taskId);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKeyWithBLOBs(Task record);

    int updateByPrimaryKey(Task record);

    List<Task> getTasksById(@Param("userId") int userId);

    List<Task>getPublishedTasksById(@Param("userId") int userId);
}
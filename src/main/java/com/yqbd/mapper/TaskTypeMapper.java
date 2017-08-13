package com.yqbd.mapper;

import com.yqbd.model.TaskTypeKey;

public interface TaskTypeMapper {
    int deleteByPrimaryKey(TaskTypeKey key);

    int insert(TaskTypeKey record);

    int insertSelective(TaskTypeKey record);
}
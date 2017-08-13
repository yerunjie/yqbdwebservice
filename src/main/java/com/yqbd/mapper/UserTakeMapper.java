package com.yqbd.mapper;

import com.yqbd.model.UserTake;
import com.yqbd.model.UserTakeKey;

public interface UserTakeMapper {
    int deleteByPrimaryKey(UserTakeKey key);

    int insert(UserTake record);

    int insertSelective(UserTake record);

    UserTake selectByPrimaryKey(UserTakeKey key);

    int updateByPrimaryKeySelective(UserTake record);

    int updateByPrimaryKey(UserTake record);
}
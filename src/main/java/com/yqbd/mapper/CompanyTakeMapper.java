package com.yqbd.mapper;

import com.yqbd.model.CompanyTake;

public interface CompanyTakeMapper {
    int deleteByPrimaryKey(Integer taskId);

    int insert(CompanyTake record);

    int insertSelective(CompanyTake record);

    CompanyTake selectByPrimaryKey(Integer taskId);

    int updateByPrimaryKeySelective(CompanyTake record);

    int updateByPrimaryKey(CompanyTake record);
}
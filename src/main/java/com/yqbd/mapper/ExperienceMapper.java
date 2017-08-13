package com.yqbd.mapper;

import com.yqbd.model.Experience;

public interface ExperienceMapper {
    int deleteByPrimaryKey(Integer experienceId);

    int insert(Experience record);

    int insertSelective(Experience record);

    Experience selectByPrimaryKey(Integer experienceId);

    int updateByPrimaryKeySelective(Experience record);

    int updateByPrimaryKeyWithBLOBs(Experience record);

    int updateByPrimaryKey(Experience record);
}
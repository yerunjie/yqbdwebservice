package com.yqbd.mapper;

import com.yqbd.model.ExperiencePictureKey;

public interface ExperiencePictureMapper {
    int deleteByPrimaryKey(ExperiencePictureKey key);

    int insert(ExperiencePictureKey record);

    int insertSelective(ExperiencePictureKey record);
}
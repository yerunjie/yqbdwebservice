package com.yqbd.mapper;

import com.yqbd.model.ExperiencePictureKey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

public interface ExperiencePictureMapper {
    @Delete({
        "delete from experience_picture",
        "where experience_id = #{experienceId,jdbcType=INTEGER}",
          "and picture_address = #{pictureAddress,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(ExperiencePictureKey key);

    @Insert({
        "insert into experience_picture (experience_id, picture_address)",
        "values (#{experienceId,jdbcType=INTEGER}, #{pictureAddress,jdbcType=VARCHAR})"
    })
    int insert(ExperiencePictureKey record);

    int insertSelective(ExperiencePictureKey record);
}
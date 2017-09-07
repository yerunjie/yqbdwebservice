package com.yqbd.mapper;

import com.yqbd.model.Experience;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ExperienceMapper {
    @Delete({
        "delete from experience",
        "where experience_id = #{experienceId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer experienceId);

    @Insert({
        "insert into experience (experience_id, user_id, ",
        "company_id, task_id, ",
        "title, content)",
        "values (#{experienceId,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{companyId,jdbcType=INTEGER}, #{taskId,jdbcType=INTEGER}, ",
        "#{title,jdbcType=VARCHAR}, #{content,jdbcType=LONGVARCHAR})"
    })
    int insert(Experience record);

    int insertSelective(Experience record);

    @Select({
        "select",
        "experience_id, user_id, company_id, task_id, title, content",
        "from experience",
        "where experience_id = #{experienceId,jdbcType=INTEGER}"
    })
    @ResultMap("ResultMapWithBLOBs")
    Experience selectByPrimaryKey(Integer experienceId);

    int updateByPrimaryKeySelective(Experience record);

    @Update({
        "update experience",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "company_id = #{companyId,jdbcType=INTEGER},",
          "task_id = #{taskId,jdbcType=INTEGER},",
          "title = #{title,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where experience_id = #{experienceId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(Experience record);

    @Update({
        "update experience",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "company_id = #{companyId,jdbcType=INTEGER},",
          "task_id = #{taskId,jdbcType=INTEGER},",
          "title = #{title,jdbcType=VARCHAR}",
        "where experience_id = #{experienceId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Experience record);
}
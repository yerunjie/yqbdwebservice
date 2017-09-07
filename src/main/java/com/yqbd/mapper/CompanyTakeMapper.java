package com.yqbd.mapper;

import com.yqbd.model.CompanyTake;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CompanyTakeMapper {
    @Delete({
        "delete from company_take",
        "where task_id = #{taskId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer taskId);

    @Insert({
        "insert into company_take (task_id, company_id, ",
        "status, user_comment_id, ",
        "company_comment_id)",
        "values (#{taskId,jdbcType=INTEGER}, #{companyId,jdbcType=INTEGER}, ",
        "#{status,jdbcType=INTEGER}, #{userCommentId,jdbcType=INTEGER}, ",
        "#{companyCommentId,jdbcType=INTEGER})"
    })
    int insert(CompanyTake record);

    int insertSelective(CompanyTake record);

    @Select({
        "select",
        "task_id, company_id, status, user_comment_id, company_comment_id",
        "from company_take",
        "where task_id = #{taskId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    CompanyTake selectByPrimaryKey(Integer taskId);

    int updateByPrimaryKeySelective(CompanyTake record);

    @Update({
        "update company_take",
        "set company_id = #{companyId,jdbcType=INTEGER},",
          "status = #{status,jdbcType=INTEGER},",
          "user_comment_id = #{userCommentId,jdbcType=INTEGER},",
          "company_comment_id = #{companyCommentId,jdbcType=INTEGER}",
        "where task_id = #{taskId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CompanyTake record);
}
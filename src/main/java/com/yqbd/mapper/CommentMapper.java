package com.yqbd.mapper;

import com.yqbd.model.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CommentMapper {
    @Delete({
        "delete from comment",
        "where comment_id = #{commentId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer commentId);

    @Insert({
        "insert into comment (comment_id, comment_content, ",
        "rate)",
        "values (#{commentId,jdbcType=INTEGER}, #{commentContent,jdbcType=VARCHAR}, ",
        "#{rate,jdbcType=INTEGER})"
    })
    int insert(Comment record);

    int insertSelective(Comment record);

    @Select({
        "select",
        "comment_id, comment_content, rate",
        "from comment",
        "where comment_id = #{commentId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Comment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comment record);

    @Update({
        "update comment",
        "set comment_content = #{commentContent,jdbcType=VARCHAR},",
          "rate = #{rate,jdbcType=INTEGER}",
        "where comment_id = #{commentId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Comment record);
}
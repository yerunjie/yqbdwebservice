package com.yqbd.mapper;

import com.yqbd.model.Type;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TypeMapper {
    @Delete({
        "delete from type",
        "where type_id = #{typeId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer typeId);

    @Insert({
        "insert into type (type_id, type_classification, ",
        "type_name)",
        "values (#{typeId,jdbcType=INTEGER}, #{typeClassification,jdbcType=VARCHAR}, ",
        "#{typeName,jdbcType=VARCHAR})"
    })
    int insert(Type record);

    int insertSelective(Type record);

    @Select({
        "select",
        "type_id, type_classification, type_name",
        "from type",
        "where type_id = #{typeId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Type selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(Type record);

    @Update({
        "update type",
        "set type_classification = #{typeClassification,jdbcType=VARCHAR},",
          "type_name = #{typeName,jdbcType=VARCHAR}",
        "where type_id = #{typeId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Type record);
}
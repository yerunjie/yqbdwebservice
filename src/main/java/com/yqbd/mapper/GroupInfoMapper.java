package com.yqbd.mapper;

import com.yqbd.model.GroupInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface GroupInfoMapper {
    @Delete({
        "delete from group_info",
        "where group_id = #{groupId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer groupId);

    @Insert({
        "insert into group_info (group_id, company_id, ",
        "group_title, current_people_number, ",
        "max_people_number, group_description)",
        "values (#{groupId,jdbcType=INTEGER}, #{companyId,jdbcType=INTEGER}, ",
        "#{groupTitle,jdbcType=VARCHAR}, #{currentPeopleNumber,jdbcType=INTEGER}, ",
        "#{maxPeopleNumber,jdbcType=INTEGER}, #{groupDescription,jdbcType=LONGVARCHAR})"
    })
    int insert(GroupInfo record);

    int insertSelective(GroupInfo record);

    @Select({
        "select",
        "group_id, company_id, group_title, current_people_number, max_people_number, ",
        "group_description",
        "from group_info",
        "where group_id = #{groupId,jdbcType=INTEGER}"
    })
    @ResultMap("ResultMapWithBLOBs")
    GroupInfo selectByPrimaryKey(Integer groupId);

    int updateByPrimaryKeySelective(GroupInfo record);

    @Update({
        "update group_info",
        "set company_id = #{companyId,jdbcType=INTEGER},",
          "group_title = #{groupTitle,jdbcType=VARCHAR},",
          "current_people_number = #{currentPeopleNumber,jdbcType=INTEGER},",
          "max_people_number = #{maxPeopleNumber,jdbcType=INTEGER},",
          "group_description = #{groupDescription,jdbcType=LONGVARCHAR}",
        "where group_id = #{groupId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(GroupInfo record);

    @Update({
        "update group_info",
        "set company_id = #{companyId,jdbcType=INTEGER},",
          "group_title = #{groupTitle,jdbcType=VARCHAR},",
          "current_people_number = #{currentPeopleNumber,jdbcType=INTEGER},",
          "max_people_number = #{maxPeopleNumber,jdbcType=INTEGER}",
        "where group_id = #{groupId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(GroupInfo record);
}
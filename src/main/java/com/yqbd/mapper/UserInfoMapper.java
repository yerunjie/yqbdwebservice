package com.yqbd.mapper;

import com.yqbd.model.UserInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserInfoMapper {
    @Delete({
        "delete from user_info",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userId);

    @Insert({
        "insert into user_info (user_id, account_number, ",
        "password, sex, real_name, ",
        "nick_name, head_portrait, ",
        "professional_level, credit_level, ",
        "telephone, school, ",
        "occupation, company_name)",
        "values (#{userId,jdbcType=INTEGER}, #{accountNumber,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{sex,jdbcType=VARCHAR}, #{realName,jdbcType=VARCHAR}, ",
        "#{nickName,jdbcType=VARCHAR}, #{headPortrait,jdbcType=VARCHAR}, ",
        "#{professionalLevel,jdbcType=INTEGER}, #{creditLevel,jdbcType=INTEGER}, ",
        "#{telephone,jdbcType=VARCHAR}, #{school,jdbcType=VARCHAR}, ",
        "#{occupation,jdbcType=VARCHAR}, #{companyName,jdbcType=VARCHAR})"
    })
    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    @Select({
        "select",
        "user_id, account_number, password, sex, real_name, nick_name, head_portrait, ",
        "professional_level, credit_level, telephone, school, occupation, company_name",
        "from user_info",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    UserInfo selectByPrimaryKey(Integer userId);

    @Update({
            "update user_info",
            "set account_number = #{accountNumber,jdbcType=VARCHAR},",
            "password = #{password,jdbcType=VARCHAR},",
            "sex = #{sex,jdbcType=VARCHAR},",
            "real_name = #{realName,jdbcType=VARCHAR},",
            "nick_name = #{nickName,jdbcType=VARCHAR},",
            "head_portrait = #{headPortrait,jdbcType=VARCHAR},",
            "professional_level = #{professionalLevel,jdbcType=INTEGER},",
            "credit_level = #{creditLevel,jdbcType=INTEGER},",
            "telephone = #{telephone,jdbcType=VARCHAR},",
            "school = #{school,jdbcType=VARCHAR},",
            "occupation = #{occupation,jdbcType=VARCHAR},",
            "company_name = #{companyName,jdbcType=VARCHAR}",
            "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserInfo record);

    int updateByPrimaryKeySelective(UserInfo record);



    @Select(
            "SELECT * FROM user_info WHERE account_number = #{accountNumber}"
    )
    @ResultMap("BaseResultMap")
    UserInfo selectByAccountNumber(String account_number);
}
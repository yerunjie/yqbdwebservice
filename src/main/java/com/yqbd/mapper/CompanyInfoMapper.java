package com.yqbd.mapper;

import com.yqbd.model.CompanyInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CompanyInfoMapper {
    @Delete({
            "delete from company_info",
            "where company_id = #{companyId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer companyId);

    @Insert({
            "insert into company_info (company_id, company_name, ",
            "company_account, password, ",
            "classification, summary, head_portrait_address)",
            "values (#{companyId,jdbcType=INTEGER}, #{companyName,jdbcType=VARCHAR}, ",
            "#{companyAccount,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
            "#{classification,jdbcType=VARCHAR}, #{summary,jdbcType=VARCHAR}, #{headPortraitAddress,jdbcType=VARCHAR})"
    })
    int insert(CompanyInfo record);

    int insertSelective(CompanyInfo record);

    @Select({
            "select *",
            "from company_info",
            "where company_id = #{companyId,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    CompanyInfo selectByPrimaryKey(Integer companyId);

    int updateByPrimaryKeySelective(CompanyInfo record);

    @Update({
            "update company_info",
            "set company_name = #{companyName,jdbcType=VARCHAR},",
            "company_account = #{companyAccount,jdbcType=VARCHAR},",
            "password = #{password,jdbcType=VARCHAR},",
            "classification = #{classification,jdbcType=VARCHAR},",
            "summary = #{summary,jdbcType=VARCHAR},",
            "head_portrait_address = #{headPortraitAddress,jdbcType=VARCHAR}",
            "where company_id = #{companyId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CompanyInfo record);

    @Select(
            "SELECT * FROM company_info WHERE company_account = #{accountNumber,jdbcType=VARCHAR}  "
    )
    @ResultMap("BaseResultMap")
    CompanyInfo selectByCompanyAccount(String accountNumber);

    @Select("select * from company_info")
    @ResultType(CompanyInfo.class)
    List<CompanyInfo> getAllCompanies();
}
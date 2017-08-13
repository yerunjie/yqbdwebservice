package com.yqbd.mapper;

import com.yqbd.model.CompanyInfo;
import org.apache.ibatis.annotations.Param;

public interface CompanyInfoMapper {
    int deleteByPrimaryKey(Integer companyId);

    int insert(CompanyInfo record);

    int insertSelective(CompanyInfo record);

    CompanyInfo selectByPrimaryKey(Integer companyId);

    int updateByPrimaryKeySelective(CompanyInfo record);

    int updateByPrimaryKey(CompanyInfo record);

    CompanyInfo selectByCompanyAccount(@Param("accountNumber") String accountNumber);
}
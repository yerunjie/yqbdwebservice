package com.yqbd.mapper;

import com.yqbd.model.GroupInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupInfoMapper {
    int deleteByPrimaryKey(Integer groupId);

    int insert(GroupInfo record);

    int insertSelective(GroupInfo record);

    GroupInfo selectByPrimaryKey(Integer groupId);

    int updateByPrimaryKeySelective(GroupInfo record);

    int updateByPrimaryKeyWithBLOBs(GroupInfo record);

    int updateByPrimaryKey(GroupInfo record);

    List<GroupInfo> selectByCompanyId(@Param("companyId") Integer companyId);
}
package com.kaishengit.mapper;


import com.kaishengit.entitys.AccountLoginLog;
import com.kaishengit.entitys.AccountLoginLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountLoginLogMapper {
    long countByExample(AccountLoginLogExample example);

    int deleteByExample(AccountLoginLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountLoginLog record);

    int insertSelective(AccountLoginLog record);

    List<AccountLoginLog> selectByExample(AccountLoginLogExample example);

    AccountLoginLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountLoginLog record, @Param("example") AccountLoginLogExample example);

    int updateByExample(@Param("record") AccountLoginLog record, @Param("example") AccountLoginLogExample example);

    int updateByPrimaryKeySelective(AccountLoginLog record);

    int updateByPrimaryKey(AccountLoginLog record);
}
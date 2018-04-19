package com.kaishengit.mapper;

import com.kaishengit.entitys.SaleAccount;
import com.kaishengit.entitys.SaleAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaleAccountMapper {
    long countByExample(SaleAccountExample example);

    int deleteByExample(SaleAccountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SaleAccount record);

    int insertSelective(SaleAccount record);

    List<SaleAccount> selectByExample(SaleAccountExample example);

    SaleAccount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SaleAccount record, @Param("example") SaleAccountExample example);

    int updateByExample(@Param("record") SaleAccount record, @Param("example") SaleAccountExample example);

    int updateByPrimaryKeySelective(SaleAccount record);

    int updateByPrimaryKey(SaleAccount record);
}
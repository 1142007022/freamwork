package com.kaishengit.mapper;

import com.kaishengit.entitys.TicketSale;
import com.kaishengit.entitys.TicketSaleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TicketSaleMapper {
    long countByExample(TicketSaleExample example);

    int deleteByExample(TicketSaleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TicketSale record);

    int insertSelective(TicketSale record);

    List<TicketSale> selectByExample(TicketSaleExample example);

    TicketSale selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TicketSale record, @Param("example") TicketSaleExample example);

    int updateByExample(@Param("record") TicketSale record, @Param("example") TicketSaleExample example);

    int updateByPrimaryKeySelective(TicketSale record);

    int updateByPrimaryKey(TicketSale record);

    TicketSale findByCustomerId(@Param("customerId") String customerId);
}
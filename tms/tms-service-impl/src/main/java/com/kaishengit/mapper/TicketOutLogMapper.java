package com.kaishengit.mapper;

import com.kaishengit.entitys.TicketOutLog;
import com.kaishengit.entitys.TicketOutLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TicketOutLogMapper {
    long countByExample(TicketOutLogExample example);

    int deleteByExample(TicketOutLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TicketOutLog record);

    int insertSelective(TicketOutLog record);

    List<TicketOutLog> selectByExample(TicketOutLogExample example);

    TicketOutLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TicketOutLog record, @Param("example") TicketOutLogExample example);

    int updateByExample(@Param("record") TicketOutLog record, @Param("example") TicketOutLogExample example);

    int updateByPrimaryKeySelective(TicketOutLog record);

    int updateByPrimaryKey(TicketOutLog record);
}
package com.kaishengit.mapper;

import com.kaishengit.entitys.TicketInLog;
import com.kaishengit.entitys.TicketInLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TicketInLogMapper {
    long countByExample(TicketInLogExample example);

    int deleteByExample(TicketInLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TicketInLog record);

    int insertSelective(TicketInLog record);

    List<TicketInLog> selectByExample(TicketInLogExample example);

    TicketInLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TicketInLog record, @Param("example") TicketInLogExample example);

    int updateByExample(@Param("record") TicketInLog record, @Param("example") TicketInLogExample example);

    int updateByPrimaryKeySelective(TicketInLog record);

    int updateByPrimaryKey(TicketInLog record);
}
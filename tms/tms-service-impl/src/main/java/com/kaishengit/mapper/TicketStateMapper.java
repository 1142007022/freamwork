package com.kaishengit.mapper;

import com.kaishengit.entitys.TicketState;
import com.kaishengit.entitys.TicketStateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TicketStateMapper {
    long countByExample(TicketStateExample example);

    int countByState(@Param("state") String state);

    int deleteByExample(TicketStateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TicketState record);

    int insertSelective(TicketState record);

    List<TicketState> selectByExample(TicketStateExample example);

    TicketState selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TicketState record, @Param("example") TicketStateExample example);

    int updateByExample(@Param("record") TicketState record, @Param("example") TicketStateExample example);

    int updateByPrimaryKeySelective(TicketState record);

    int updateByPrimaryKey(TicketState record);
}
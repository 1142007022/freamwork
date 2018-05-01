package com.kaishengit.mapper;

import com.kaishengit.entitys.Ticket;
import com.kaishengit.entitys.TicketExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TicketMapper {

    void insertCount(@Param("list") List<Ticket> list);

    long countByExample(TicketExample example);

    int deleteByExample(TicketExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Ticket record);

    int insertSelective(Ticket record);

    List<Ticket> selectByExample(TicketExample example);

    Ticket selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Ticket record, @Param("example") TicketExample example);

    int updateByExample(@Param("record") Ticket record, @Param("example") TicketExample example);

    int updateByPrimaryKeySelective(Ticket record);

    int updateByPrimaryKey(Ticket record);

    Ticket findByNum(int num);

    Integer getCountByTicketofficeIdAndState(@Param("id") Integer id, @Param("state") String saled_state);
}
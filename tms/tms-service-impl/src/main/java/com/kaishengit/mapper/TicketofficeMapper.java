package com.kaishengit.mapper;

import com.kaishengit.entitys.Ticketoffice;
import com.kaishengit.entitys.TicketofficeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TicketofficeMapper {
    long countByExample(TicketofficeExample example);

    int deleteByExample(TicketofficeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Ticketoffice record);

    int insertSelective(Ticketoffice record);

    List<Ticketoffice> selectByExample(TicketofficeExample example);

    Ticketoffice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Ticketoffice record, @Param("example") TicketofficeExample example);

    int updateByExample(@Param("record") Ticketoffice record, @Param("example") TicketofficeExample example);

    int updateByPrimaryKeySelective(Ticketoffice record);

    int updateByPrimaryKey(Ticketoffice record);

    List<Ticketoffice> findAll();
}
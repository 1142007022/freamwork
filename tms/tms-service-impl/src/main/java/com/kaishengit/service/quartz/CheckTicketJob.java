package com.kaishengit.service.quartz;

import com.kaishengit.entitys.Ticket;
import com.kaishengit.entitys.TicketState;
import com.kaishengit.service.TicketService;
import com.kaishengit.service.TicketStateService;
import org.joda.time.DateTime;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckTicketJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext){
        try {
            ApplicationContext applicationContext = (ApplicationContext) jobExecutionContext.getScheduler().getContext().get("applicationContext");
            TicketService ticketService = applicationContext.getBean(TicketService.class);
            TicketStateService ticketStateService = applicationContext.getBean(TicketStateService.class);
            List<Ticket> ticketList = ticketService.findTicketByState(TicketState.saled_state);

            //定义一个集合存放当前已过期的年票
            List<TicketState> outTimeTicketStateList = new ArrayList<>();

            for(Ticket ticket : ticketList) {
                Date date = ticket.getOverDataTime();
                DateTime dateTime = new DateTime(date.getTime());
                dateTime = dateTime.withTime(0,0,0,0);
                dateTime = dateTime.plusDays(1);

                if(dateTime.isBeforeNow()) {
                    TicketState ticketState = ticketStateService.findByNum(ticket.getNum());
                    ticketState.setState(TicketState.overDate_state);
                    outTimeTicketStateList.add(ticketState);
                }
            }

        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new RuntimeException("执行定时任务异常",e);
        }

    }

}

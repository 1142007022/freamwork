package com.kaishengit.shiro;

import com.kaishengit.entitys.Ticketoffice;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class ShiroUtil {

    public Ticketoffice getTicketoffice(){
        Subject subject = SecurityUtils.getSubject();
        Ticketoffice ticketoffice = (Ticketoffice)subject.getPrincipal();
        return ticketoffice;
    }

}

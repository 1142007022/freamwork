package com.kaishengit.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Test {

    public static void main(String[] args){
        Date date = new Date();
        System.out.println(date);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR,1);
        System.out.println(calendar.getTime());
    }

}

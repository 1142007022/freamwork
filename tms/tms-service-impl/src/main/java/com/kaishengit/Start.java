package com.kaishengit;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Start {



    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring*.xml");
        classPathXmlApplicationContext.start();
        System.out.println("-------main class start--------");
        System.in.read();
    }

}

package com.kaishengit;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class ZhuJieTest {

    @JmsListener(destination = "test",containerFactory = "jmsQueueListenerContainerFactory")
    public void queue(Message message) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        System.out.println(textMessage.getText());
    }

    @JmsListener(destination = "spring-test")
    public void topic(Message message) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        System.out.println(textMessage.getText());
    }

}

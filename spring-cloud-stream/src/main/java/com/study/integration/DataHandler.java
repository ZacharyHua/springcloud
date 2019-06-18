package com.study.integration;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

@Configuration
public class DataHandler {
    // 注解：标志着我们的 消息 是通过哪一个channel的到的
    @ServiceActivator(inputChannel = "amqpInputChannel")
    public void printMessage(Message<?> message){
        System.out.println("已接受到MQ消息："+message.getPayload().toString());
    }
}

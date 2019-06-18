package com.study.customer.event;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;
// 自定义事件发布
public class MyEvent extends RemoteApplicationEvent {
    private  MyEvent(){
    }
    public  MyEvent(Object source, String originService){
        // 通过super的方式发布  source 事件  originService 指定发送给谁
        super(source,originService,"**");
    }
}

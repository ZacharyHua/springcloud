package com.study.customer.event;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;
import org.springframework.stereotype.Component;

@Component
public class MyEvent extends RemoteApplicationEvent {
    private  MyEvent(){
    }

    public  MyEvent(Object source, String originService){
        super(source,originService,"**");
    }
}

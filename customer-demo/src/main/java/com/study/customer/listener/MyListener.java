package com.study.customer.listener;

import com.study.customer.event.MyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
    public class MyListener implements ApplicationListener<MyEvent> {
        public void onApplicationEvent(MyEvent event) {

            System.out.println("收到事件xxxx"+event.getSource().toString());
        }
}

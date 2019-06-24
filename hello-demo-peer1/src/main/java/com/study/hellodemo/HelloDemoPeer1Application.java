package com.study.hellodemo;


import com.study.hellodemo.accept.StreamClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableBinding(value = {StreamClient.class})
@RemoteApplicationEventScan("com.study")
public class HelloDemoPeer1Application {

    public static void main(String[] args) {
//        SpringApplication.run(HelloDemoPeer1Application.class, args);

        ConfigurableApplicationContext context=  SpringApplication.run(HelloDemoPeer1Application.class, args);
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册到我们的spring上下文中
        applicationContext.register(EventConfiguration.class);
        //启动上下文
        applicationContext.refresh();

        ApplicationEventPublisher publisher = applicationContext;

        publisher.publishEvent(new MyApplicationEvent("开始学习springcloud -bus事件"));
    }



    @GetMapping("")
    public String index() throws InterruptedException {
        Random random = new Random();
        int timeOut = random.nextInt(150);
        System.out.println("当前线程休眠时间是:"+timeOut);
        Thread.sleep(timeOut);

         String str =  "这是服务端1返回的应答";
        return new String(str);
    }


    //自定义发布事件
    public static class MyApplicationEvent extends ApplicationEvent {

        public MyApplicationEvent(String msg) {
            super(msg);
        }
    }


    @Configuration
    public static  class EventConfiguration{

        @EventListener
        public void onEvent(MyApplicationEvent event){
            System.out.println("监听到事件"+event);
        }
    }


    @GetMapping("user")
    public String getName(@RequestParam("name") String name){
        return name;
    }

    @PostMapping("/find-user")
    public String getName(@RequestBody Map<String,Object> map){
        return (String) map.get("name");
    }


}

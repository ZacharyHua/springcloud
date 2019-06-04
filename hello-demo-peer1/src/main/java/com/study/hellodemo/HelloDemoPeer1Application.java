package com.study.hellodemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class HelloDemoPeer1Application {

    public static void main(String[] args) {
        SpringApplication.run(HelloDemoPeer1Application.class, args);
    }

    @GetMapping("")
    public Object index() throws InterruptedException {
        Random random = new Random();
        int timeOut = random.nextInt(150);
        System.out.println("当前线程休眠时间是："+timeOut);
        Thread.sleep(timeOut);
        String str = "这是服务端1返回的应答";
        return new String(str);
    }
}

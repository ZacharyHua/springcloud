package com.study.hellodemo;


import com.study.hellodemo.accept.StreamClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableBinding(value = {StreamClient.class})
@RemoteApplicationEventScan("com.dn")
public class HelloDemoPeer1Application {

    public static void main(String[] args) {
        SpringApplication.run(HelloDemoPeer1Application.class, args);
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

    @GetMapping("user")
    public String getName(@RequestParam("name") String name){
        return name;
    }

    @PostMapping("/find-user")
    public String getName(@RequestBody Map<String,Object> map){
        return (String) map.get("name");
    }


}

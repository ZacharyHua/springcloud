package com.study.springcloudfeign.controller;

import com.study.springcloudfeign.server.HelloDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CustomerController {
    @Autowired
    HelloDemoService helloDemoService;

    @GetMapping("index")
    public Object getIndex(){
        return helloDemoService.index();
    }

}

package com.study.customer.controller;

import com.study.customer.stream.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class CustomerController {

    private ApplicationContext applicationContext;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    StreamClient streamClient;

    @GetMapping("index")
    public Object getIndex(){
    //定义修改，一旦有请求过来，发送一个事件给服务端响应
        streamClient.output().send(new GenericMessage("已经发送了一个请求，请处理"));


//        ServiceInstance serviceInstance = loadBalancerClient.choose("helloserver");
//        String ip = serviceInstance.getHost();
//        int port = serviceInstance.getPort();
//        System.out.println(ip+port);
        return restTemplate.getForObject("http://helloserver",String.class,"");
     }



}

package com.study.springcloudfeign.server;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(name="helloserver")
public interface HelloDemoService {

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String index();
}

package com.study.springcloudfeign.server;

import com.study.springcloudfeign.fallback.HelloDemoFallback;
import feign.Body;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name="helloserver" , fallback = HelloDemoFallback.class)
public interface HelloDemoService {

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String index();

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String getName(@RequestParam("name") String name);

    @Body("%7B\"name\":\"{name}\"%7D")
    @RequestMapping(value = "/test-user",method = RequestMethod.POST)
    public String testName(@Param("name")String name);
}

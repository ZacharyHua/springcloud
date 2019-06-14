package com.study.definedfegin.service;



import com.study.definedfegin.annotation.FeginClinet;
import com.study.definedfegin.annotation.FeginGet;
import org.springframework.stereotype.Component;

@Component
@FeginClinet(baseUrl = "http://www.baidu.com:80")
public interface TestFeign {

    @FeginGet(url = "/index.html")
    Object getSomeThing();
}

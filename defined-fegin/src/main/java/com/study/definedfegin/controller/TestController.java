package com.study.definedfegin.controller;


import com.study.definedfegin.service.TestFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    TestFeign testFeign;

    @GetMapping("")
    public Object getIndex(){
        return testFeign.getSomeThing();
    }
}

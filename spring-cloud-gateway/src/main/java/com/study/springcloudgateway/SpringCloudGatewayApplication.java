package com.study.springcloudgateway;

import com.study.springcloudgateway.filter.JwtCheckSSSS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayApplication.class, args);
    }

    @Bean
    JwtCheckSSSS filterFactory1(){
        return new JwtCheckSSSS();
    }

}

package com.study.definedfegin;

import com.study.definedfegin.utils.FeignRegistar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(FeignRegistar.class)
public class DefinedFeginApplication {

    public static void main(String[] args) {
        SpringApplication.run(DefinedFeginApplication.class, args);
    }

}

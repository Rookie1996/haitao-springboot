package com.xjr.miniapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@EnableScheduling
@SpringBootApplication
@MapperScan(basePackages = "com.xjr.mapper")
@ComponentScan(basePackages = {"com.xjr", "org.n3r.idworker"})
public class MiniapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniapiApplication.class, args);
    }

}

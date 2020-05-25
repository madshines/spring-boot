package com.madshines.springboottest2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ImportResource(locations = {"classpath:beans.xml"})
@Slf4j
public class SpringBootTest2Application {

    public static void main(String[] args) {
        log.info("日志信息");
        SpringApplication.run(SpringBootTest2Application.class, args);
    }

}

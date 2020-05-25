package com.madshines.springboottest2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:madshines
 * Date:2020/5/6
 * Package:com.madshines.springboottest2.controller
 **/
@RestController
@Slf4j
public class HelloController {
    @Value("${student.dog.name}")
    private String dogName;
    @RequestMapping("hello")
    public String hello(){
        log.info(dogName);
        String s1="南开大学滨海学院";
        return s1;
    }
}

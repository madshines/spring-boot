package com.madshines.springbootjdbc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:madshines
 * Date:2020/5/15
 * Package:com.madshines.springbootjdbc.controller
 * Description:测试hello
 **/
@RestController
public class HelloController {
    @RequestMapping("hello")
    public String hello(){
        return "hello world";
    }
}

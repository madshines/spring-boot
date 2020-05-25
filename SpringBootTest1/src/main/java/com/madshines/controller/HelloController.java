package com.madshines.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author:madshines
 * Date:2020/5/5
 * Package:com.madshines.controller
 **/
@Controller
public class HelloController {
    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }
}

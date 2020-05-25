package com.madshines.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

/**
 * Author:madshines
 * Date:2020/5/24
 * Package:com.madshines.controller
 * Description:测试controller
 **/
@Controller
@RequestMapping("test")
public class MyController {
    @RequestMapping("test")
    public String test(Model model){
        model.addAttribute("message","fuck");
        model.addAttribute("list", Arrays.asList("one","two","three"));
        return "ThymeleafTest";
    }
}

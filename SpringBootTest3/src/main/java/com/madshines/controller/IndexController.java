package com.madshines.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author:madshines
 * Date:2020/5/24
 * Package:com.madshines.controller
 * Description:首页跳转
 **/
//在templates目录下的所有页面只能通过controller进行跳转,需要有模板引擎的支持
@Controller
public class IndexController {
    @RequestMapping("index")
    public String index(){
        return "index";
    }
}

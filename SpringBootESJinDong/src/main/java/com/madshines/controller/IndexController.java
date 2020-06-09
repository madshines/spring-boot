package com.madshines.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author :madshines
 * @Date: 2020-06-09
 * @Description: com.madshines.controller
 * @version: 1.0
 */
@Controller
public class IndexController {

    @RequestMapping({"/index","/"})
    public String index(){
        return "index";
    }
}

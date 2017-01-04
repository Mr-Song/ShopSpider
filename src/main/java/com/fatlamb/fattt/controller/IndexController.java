package com.fatlamb.fattt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by 58 on 2017/1/3.
 */

@Controller
public class IndexController {
    @RequestMapping(value = "/" , method = GET)
    public String index(){
        return "index";
    }
}

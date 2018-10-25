package com.jb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 统一控制管理平台的页面跳转
 * Created by 27654 on 2018/10/10.
 */
@Controller
@RequestMapping("/back")
public class PlatController {

    @RequestMapping("/login")
    public String login(){
        return "plat/index";
    }

    @RequestMapping("/admin/{jsp}")
    public String home(@PathVariable  String jsp){
        if(jsp == null || jsp.trim().indexOf("#") > 0){
            return "redirect: err/404";
        }
        return  "plat/"+jsp;
    }
}

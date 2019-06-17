package com.jfsoft.bbs.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chenxc
 * @email 997909544@qq.com
 * @Date 2019/6/17  10:48
 */
@RequestMapping("/admin")
@Controller
public class PageController {

    /**
     * 首页
     * @param model
     * @return
     */
    @RequestMapping({"/","/index",""})
    public String index(Model model){
        return "index";
    }
    /**
     * 欢迎页
     * @param model
     * @return
     */
    @RequestMapping({"/welcome"})
    public String welcome(Model model){
        return "welcome";
    }

    /**
     * 登录页
     * @param model
     * @return
     */
    @RequestMapping({"/login"})
    public String login(Model model){
        return "login";
    }
}

package com.jfsoft.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chenxc
 * @email 997909544@qq.com
 * @Date 2019/2/22  17:24
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/jie/add")
    public String addPost() {
        return "jie/add";
    }

    @RequestMapping("/jie/detail/{id}")
    public String postDetail(@PathVariable Integer id, Model model) {
        model.addAttribute("postId", id);
        return "jie/detail";
    }
}

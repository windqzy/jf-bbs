package com.jfsoft.bbs.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenxc
 * @email 997909544@qq.com
 * @Date 2019/2/26  16:04
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    /**
     * 存储路径
     */
    @Value("${app.filePath}")
    private String filePath;

    /**
     * 映射路径
     */
    @Value("${app.staticUrl}")
    private String staticUrl;


}

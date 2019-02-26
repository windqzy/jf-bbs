package com.jfsoft.bbs.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author chenxc
 * @email 997909544@qq.com
 * @Date 2019/2/26  16:06
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Value("${app.filePath}")
    private String filePath;

    @Value("${app.staticUrl}")
    private String staticUrl;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/**").addResourceLocations("file:" + staticUrl);
        super.addResourceHandlers(registry);
    }

}

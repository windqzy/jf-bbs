package com.jfsoft.bbs.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 * @ClassName WebSocketConfig
 * @Description 开启websocket支持
 * @Author Lxd
 * @Date 2019/3/22 9:21
 * @Version 1.0
 **/
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}

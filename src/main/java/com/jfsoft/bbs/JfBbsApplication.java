package com.jfsoft.bbs;

import com.jfsoft.bbs.controller.socket.WebSocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JfBbsApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(JfBbsApplication.class, args);
        WebSocketServer.setApplicationContext(applicationContext);
        System.out.println("服务启动成功！");
    }

}

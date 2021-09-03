package com.bj.rabbitmq;

import com.bj.rabbitmq.service.ReceiveService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(Application.class,args);
        ReceiveService service =(ReceiveService) ac.getBean("receiveService");

        //service.receive();
    }

}

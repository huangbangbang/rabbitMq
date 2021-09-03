package com.bj.rabbitmq;

import com.bj.rabbitmq.service.SendService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(Application.class,args);
        SendService service =(SendService) ac.getBean("sendService");

        //service.SendMessage("hhhhhhhhhhhh");
        //service.SendFanoutMessage("bbbbbbbbbbb");
        service.SendTopicMessage("hhhhhhhhhhhh");
    }

}

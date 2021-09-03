package com.bj.rabbitmq.service.impl;

import com.bj.rabbitmq.service.SendService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("sendService")
public class SendServiceImpl implements SendService {

    @Resource
    private AmqpTemplate amqpTemplate;
    /*@Override
    public void SendMessage(String message) {
        amqpTemplate.convertAndSend("bootDirectExchange","bootDirectRoutingKey",message);
    }

    @Override

    public void SendFanoutMessage(String message) {
        amqpTemplate.convertAndSend("fanoutExchange","",message);
    }*/

    @Override
    public void SendTopicMessage(String message) {
        amqpTemplate.convertAndSend("topicExchange","aa.bb",message);
    }
}

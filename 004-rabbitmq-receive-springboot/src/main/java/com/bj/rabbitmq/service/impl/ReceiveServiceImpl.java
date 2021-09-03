package com.bj.rabbitmq.service.impl;

import com.bj.rabbitmq.service.ReceiveService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("receiveService")
public class ReceiveServiceImpl implements ReceiveService {

    @Resource
    private AmqpTemplate amqpTemplate;

    /*@Override
    public void receive() {
        String message = (String) amqpTemplate.receiveAndConvert("bootDirectQueue");
        //System.out.println(message);
    }

    @Override
    @RabbitListener(queues = {"bootDirectQueue"})
    public void directReceive(String message) {
        //System.out.println(message);
        //System.out.println(10/0);
    }*/


    /*@RabbitListener(bindings = {
            @QueueBinding(value = @Queue(),
                    exchange = @Exchange(name = "fanoutExchange",type = "fanout")
            )
    }
    )
    public void fanoutReceive01(String message) {
        //System.out.println(message);
    }*/



    /*@RabbitListener(bindings = {
            @QueueBinding(value = @Queue(),
                    exchange = @Exchange(name = "fanoutExchange",type = "fanout")
            )
    }
    )
    public void fanoutReceive02(String message) {
        //System.out.println(message);
    }*/



    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue("topic01"),
                    key = {"aa"},
                    exchange = @Exchange(name = "topicExchange",type = "topic")
            )
    }
    )
    public void topicReceive01(String message){
        System.out.println("aa "+message);
    }



    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue("topic02"),
                    key = {"aa.*"},
                    exchange = @Exchange(name = "topicExchange",type = "topic")
            )
    }
    )
    public void topicReceive02(String message){
        System.out.println("aa.*"+message);
    }


    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue("topic03"),
                    key = {"aa.#"},
                    exchange = @Exchange(name = "topicExchange",type = "topic")
            )
    }
    )
    public void topicReceive03(String message){
        System.out.println("aa.#"+message);
    }
}

package com.bj.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("bootDirectExchange");
    }
    @Bean
    public Queue directQueue(){
        return new Queue("bootDirectQueue");
    }
    @Bean
    public Binding directBinding(Queue directQueue,DirectExchange directExchange){
        return BindingBuilder.bind(directQueue).to(directExchange).with("bootDirectRoutingKey");
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }
}

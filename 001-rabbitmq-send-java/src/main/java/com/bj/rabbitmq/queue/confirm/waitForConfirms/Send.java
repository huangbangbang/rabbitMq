package com.bj.rabbitmq.queue.confirm.waitForConfirms;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.17.128");//设置RabbitMQ的主机IP
        factory.setPort(5672);//设置RabbitMQ的端口号
        factory.setUsername("root");//设置访问用户名
        factory.setPassword("root");//设置访问密码
        Connection connection = null;//定义链接对象
        Channel channel = null;//定义通道对象


        try {
            connection = factory.newConnection();//实例化链接对象
            channel = connection.createChannel();//实例化通道对象

            /*channel.queueDeclare("myDirectQueue", true, false, false, null);
            channel.queueBind("myDirectQueue","directExchange","myRoutingKey");*/
            channel.queueDeclare("confirmQueue",true,false,false,null);
            channel.exchangeDeclare("directConfirmExchange", "direct", true);
            channel.queueBind("confirmQueue","directConfirmExchange","confirmRoutingKey");
            String message = "Hello World!";

            channel.confirmSelect();
            channel.basicPublish("directConfirmExchange", "confirmRoutingKey", null, message.getBytes("UTF-8"));
            Boolean flag = channel.waitForConfirms();
            System.out.println(flag);
            System.out.println("消息发送成功3: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

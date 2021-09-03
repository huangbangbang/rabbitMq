package com.bj.rabbitmq.transaction;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receive {
    public static void main(String[] args) {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("192.168.17.128");//设置RabbitMQ的主机IP
        factory.setPort(5672);//设置RabbitMQ的端口号
        factory.setUsername("root");//设置访问用户名
        factory.setPassword("root");//设置访问密码
        Connection connection=null;//定义链接对象
        Channel channel=null;//定义通道对象

        try {
            connection=factory.newConnection();//实例化链接对象
            channel=connection.createChannel();//实例化通道对象

            channel.queueDeclare("transactionQueue", true, false, false, null);
            channel.exchangeDeclare("directTransactionExchange", "direct", true);
            channel.queueBind("transactionQueue","directTransactionExchange","transactionRoutingKey");

            channel.txSelect();
            channel.basicConsume("transactionQueue",true,"",new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body,"utf-8");
                    System.out.println("consumer "+message);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}

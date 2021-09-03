package com.bj.rabbitmq.confirm;

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
            channel.queueDeclare("confirmQueue", true, false, false, null);
            channel.exchangeDeclare("directConfirmExchange", "direct", true);
            channel.queueBind("confirmQueue","directConfirmExchange","confirmRoutingKey");

            channel.txSelect();

            channel.basicConsume("confirmQueue",false,"",new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    /*System.out.println(10/0);*/
                    //System.out.println(envelope.isRedeliver());
                    Boolean isRedeliver = envelope.isRedeliver();
                    Channel c = this.getChannel();
                    if (!isRedeliver){
                        String message = new String(body);
                        System.out.println("consumer "+message);

                        long tag = envelope.getDeliveryTag();

                    }else {

                    }


                    /*c.basicAck(tag,true);
                    c.txCommit();*/
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}

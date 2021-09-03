package com.bj.rabbitmq.service;

public interface SendService {
    //void SendMessage(String message);
    //void SendFanoutMessage(String message);
    void SendTopicMessage(String message);
}

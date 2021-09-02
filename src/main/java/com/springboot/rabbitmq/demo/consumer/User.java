package com.springboot.rabbitmq.demo.consumer;

import com.springboot.rabbitmq.demo.config.RabbitConfig;
import com.springboot.rabbitmq.demo.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void consumeMessagesFromQueue(OrderStatus orderStatus) {
        System.out.println("Message recieved from queue : " + orderStatus);
    }
}

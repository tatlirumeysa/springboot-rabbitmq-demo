package com.springboot.rabbitmq.demo.publisher;

import com.springboot.rabbitmq.demo.config.RabbitConfig;
import com.springboot.rabbitmq.demo.dto.Order;
import com.springboot.rabbitmq.demo.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
        order.setOrderId(UUID.randomUUID().toString());
        //restaurantservice
        //payment service
        OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "order placed succesfully in " + restaurantName);
        template.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.ROUTING_KEY, orderStatus);
        return "Booking is successfully completed!";
    }
}

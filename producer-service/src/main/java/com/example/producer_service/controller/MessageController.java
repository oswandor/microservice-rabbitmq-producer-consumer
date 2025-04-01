package com.example.producer_service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.producer_service.model.Order;
import com.example.producer_service.service.MessageProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class MessageController {

    private final MessageProducer messageProducer;
    private final ObjectMapper objectMapper;

    public MessageController(MessageProducer messageProducer, ObjectMapper objectMapper) {
        this.messageProducer = messageProducer;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/orders")
    public String createOrder(@RequestBody Order order) throws JsonProcessingException {
        String orderJson = objectMapper.writeValueAsString(order);
        messageProducer.sendMessage(orderJson);
        return "Order sent: " + order.getOrderId();
    }
}
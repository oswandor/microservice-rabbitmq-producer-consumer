package com.example.producer_service.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.example.producer_service.config.RabbitMQConfig;

/**
 * Servicio responsable de enviar mensajes a RabbitMQ.
 * Esta clase utiliza RabbitTemplate para publicar mensajes en la cola configurada.
 */
@Service
public class MessageProducer {

    /**
     * Instancia de RabbitTemplate que se utiliza para interactuar con RabbitMQ.
     * Permite enviar mensajes a colas y exchanges.
     */
    private final RabbitTemplate rabbitTemplate;

    /**
     * Constructor que inyecta la dependencia de RabbitTemplate.
     * Spring Boot se encarga de proporcionar esta instancia automáticamente.
     *
     * @param rabbitTemplate La instancia de RabbitTemplate configurada por Spring
     */
    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Envía un mensaje a la cola configurada en RabbitMQConfig.
     * El mensaje se envía directamente a la cola sin pasar por un exchange específico.
     *
     * @param message El mensaje a enviar (generalmente un JSON serializado)
     */
    public void sendMessage(String message) {
        // Envía el mensaje al exchange con la clave de enrutamiento definida
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                message);
    }
}
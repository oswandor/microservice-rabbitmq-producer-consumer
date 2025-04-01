package com.example.consumer_service.service;


import com.example.consumer_service.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


/**
 * Servicio encargado de consumir mensajes de la cola RabbitMQ.
 * Proporciona métodos para procesar los mensajes recibidos.
 */
@Service
public class MessageConsumer {

    /**
     * Método que escucha la cola definida en RabbitMQConfig.
     * Se ejecuta automáticamente cuando un mensaje llega a la cola especificada.
     *
     * @param message El contenido del mensaje recibido de la cola RabbitMQ
     */
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(String message) {
        System.out.println("Mensaje recibido: " + message);
        // Aquí puedes procesar el mensaje como sea necesario
    }
}
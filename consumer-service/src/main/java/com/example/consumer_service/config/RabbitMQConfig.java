package com.example.consumer_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de RabbitMQ para el servicio consumidor.
 * Define la cola, exchange y binding necesarios para recibir mensajes.
 */
@Configuration
public class RabbitMQConfig {


    public static final String QUEUE_NAME = "order_created";
    public static final String EXCHANGE_NAME = "order_exchange";
    public static final String ROUTING_KEY = "order";

    /**
     * Define y registra la cola en RabbitMQ.
     * @return Una cola no durable con el nombre especificado.
     */
    @Bean
    public Queue orderCreated() {
        return new Queue(QUEUE_NAME, false);
    }

    /**
     * Define y registra el exchange en RabbitMQ.
     * @return Un DirectExchange que enruta mensajes basado en la clave de enrutamiento exacta.
     */
    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    /**
     * Establece la vinculación entre el exchange y la cola.
     * Los mensajes enviados al exchange con la clave "order" serán
     * direccionados a la cola "order_created".
     *
     * @param orderCreated La cola definida
     * @param orderExchange El exchange definido
     * @return El binding configurado
     */
    @Bean
    public Binding bindingOrder(Queue orderCreated, DirectExchange orderExchange) {
        return BindingBuilder.bind(orderCreated)
                .to(orderExchange)
                .with(ROUTING_KEY);
    }
}

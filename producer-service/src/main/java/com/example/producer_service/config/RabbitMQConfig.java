package com.example.producer_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración de RabbitMQ.
 * Define la cola, el exchange y la clave de enrutamiento utilizados por la aplicación.
 */

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "order_created";
    public static final String EXCHANGE_NAME = "order_exchange";
    public static final String ROUTING_KEY = "order";



    /**
     * Define la cola donde se almacenarán los mensajes de órdenes creadas.
     * El segundo parámetro (false) indica que la cola no es durable.
     */
    @Bean
    public Queue orderCreated() {
        return new Queue(QUEUE_NAME, false);
    }



    /**
     * Define el exchange que distribuirá los mensajes a las colas correspondientes.
     * Se utiliza un DirectExchange que enruta mensajes basado en la clave de enrutamiento.
     */
    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    /**
     * Establece el binding (vinculación) entre el exchange y la cola.
     * Los mensajes enviados al exchange con la clave de enrutamiento "order"
     * serán direccionados a la cola "order_created".
     */
    @Bean
    public Binding bindingOrder(Queue orderCreated, DirectExchange orderExchange) {
        return BindingBuilder.bind(orderCreated)
                .to(orderExchange)
                .with(ROUTING_KEY);
    }


}

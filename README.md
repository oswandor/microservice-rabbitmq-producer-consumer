# Microservicios con RabbitMQ y Jaeger

Este proyecto implementa una arquitectura de microservicios utilizando RabbitMQ para la comunicación asíncrona entre servicios y Jaeger para el rastreo distribuido.

## Requisitos previos

- Docker y Docker Compose
- Java 11 o superior
- Maven

## Componentes del proyecto

- **producer-service**: Microservicio que envía mensajes a la cola RabbitMQ
- **consumer-service**: Microservicio que procesa los mensajes de la cola

## Configuración del entorno

### Iniciar RabbitMQ

```bash
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

### Iniciar Jaeger

```bash
docker run -d --name jaeger \
  -e COLLECTOR_ZIPKIN_HOST_PORT=:9411 \
  -p 6831:6831/udp \
  -p 6832:6832/udp \
  -p 5778:5778 \
  -p 16686:16686 \
  -p 14268:14268 \
  -p 14250:14250 \
  -p 4317:4317 \
  -p 4318:4318 \
  -p 9411:9411 \
  jaegertracing/all-in-one:latest
```

## Estructura de los servicios

### Producer Service

Servicio encargado de generar y enviar mensajes a RabbitMQ:

- **RabbitMQConfig.java**: Define la configuración de la cola, exchange y binding
- **MessageProducer.java**: Servicio para enviar mensajes a la cola

### Consumer Service

Servicio que recibe y procesa los mensajes de la cola:

- **RabbitMQConfig.java**: Define la misma configuración que el productor para conectar a la cola
- **MessageConsumer.java**: Servicio que escucha la cola y procesa los mensajes

## Acceso a las herramientas

- **RabbitMQ Management**: http://localhost:15672 (usuario: guest, contraseña: guest)
- **Jaeger UI**: http://localhost:16686

## Ejecución

1. Inicie los servicios Docker (RabbitMQ y Jaeger)
2. Compile y ejecute el consumer-service:

```bash
cd consumer-service
mvn spring-boot:run
```

3. Compile y ejecute el producer-service:

```bash
cd producer-service
mvn spring-boot:run
```

## Diagrama de la arquitectura

```
┌────────────────┐        ┌──────────┐        ┌────────────────┐
│                │        │          │        │                │
│ Producer       │ ─────> │ RabbitMQ │ ─────> │ Consumer       │
│ Service        │        │          │        │ Service        │
│                │        │          │        │                │
└────────────────┘        └──────────┘        └────────────────┘
        │                                            │
        └───────────────────┐   ┌──────────────────┘
                          ┌─┴───┴─┐
                          │       │
                          │ Jaeger│
                          │       │
                          └───────┘
```

## Pruebas

Para probar el envío y recepción de mensajes, utilice los endpoints del productor y verifique los logs del consumidor para confirmar la recepción.

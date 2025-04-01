package com.example.producer_service.config;

import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OtlConfiguration {

    @Bean
    OtlpHttpSpanExporter otelTracingExporter(@Value("${otel.exporter.otlp.endpoint}") String endpoint) {
        return OtlpHttpSpanExporter.builder()
            .setEndpoint(endpoint)
            .build();
    }
}

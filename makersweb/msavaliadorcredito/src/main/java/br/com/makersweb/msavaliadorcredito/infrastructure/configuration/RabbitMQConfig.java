package br.com.makersweb.msavaliadorcredito.infrastructure.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author aaristides
 */
@Configuration
public class RabbitMQConfig {

    @Value("${mq.queues.emissao-cartoes}")
    private String queueEmissaoCartoesFila;

    @Bean
    public Queue queueEmissaoCartoes() {
        return new Queue(queueEmissaoCartoesFila, true);
    }

}

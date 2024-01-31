package br.com.jwprogammer.avalicacao.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.emissao-de-cartao}")
    private String emissaoDeCartaoFila;

    @Bean
    public Queue queueEmissaoCartao() {
        return new Queue(emissaoDeCartaoFila, true);
    }
}

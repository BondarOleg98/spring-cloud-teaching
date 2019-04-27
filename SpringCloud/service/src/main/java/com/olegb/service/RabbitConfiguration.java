package com.olegb.service;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class RabbitConfiguration {
    Logger logger = Logger.getLogger(String.valueOf(RabbitConfiguration.class));

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory("localhost");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
        return rabbitAdmin;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(ProducerJackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Queue CreateStudentQueue() {
        return new Queue("CreateStudent");
    }

    @Bean
    public Queue RemoveStudentQueue() {
        return new Queue("RemoveStudent");
    }

    @Bean
    public Queue GetStudentQueue() {
        return new Queue("GetStudent");
    }

    @Bean
    public Queue HelloWorldQueue() {
        return new Queue("HelloWorld");
    }

    @Bean
    public Jackson2JsonMessageConverter ProducerJackson2JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}

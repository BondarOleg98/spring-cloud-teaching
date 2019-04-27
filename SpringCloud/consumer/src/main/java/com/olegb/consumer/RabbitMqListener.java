package com.olegb.consumer;

import com.olegb.consumer.Models.Log;
import com.olegb.consumer.Repository.LogRepository;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@EnableRabbit
@Component
public class RabbitMqListener {
    Logger logger = Logger.getLogger(String.valueOf(RabbitMqListener.class));


    @Autowired
    private LogRepository logRepository;


    @RabbitListener(queues = "HelloWorld")
    public void processMessage1(String message) {
        logger.info(""+ message);
    }

    @RabbitListener(queues = "GetStudent")
    public void processMessage2(Log log){
        logger.info("GettingMessage");
        logRepository.save(log);
    }
    @RabbitListener(queues = "CreateStudent")
    public void processMessage3(Log log){
        logger.info("CreatingMessage");
        logRepository.save(log);
    }
    @RabbitListener(queues = "RemoveStudent")
    public void processMessage4(Log log){
        logger.info("RemoveStudent");
        logRepository.save(log);
    }
}
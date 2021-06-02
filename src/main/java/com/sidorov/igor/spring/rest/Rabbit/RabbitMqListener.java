package com.sidorov.igor.spring.rest.Rabbit;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class RabbitMqListener {
    Logger logger = Logger.getLogger(RabbitMqListener.class);

    @RabbitListener(queues = "queue1")
    public void processQueue1(String message) throws InterruptedException {
        logger.info("Received from queue 1: " + message);
        Thread.sleep(20_000);
    }
}

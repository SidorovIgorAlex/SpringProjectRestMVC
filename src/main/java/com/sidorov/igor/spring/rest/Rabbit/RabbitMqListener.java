package com.sidorov.igor.spring.rest.Rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sidorov.igor.spring.rest.Service.EmployeeService;
import com.sidorov.igor.spring.rest.controller.ControllerEmployee;
import com.sidorov.igor.spring.rest.entity.Employee;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@EnableRabbit
@Component
public class RabbitMqListener {
    Logger logger = Logger.getLogger(RabbitMqListener.class);

    @Autowired
    private EmployeeService employeeService;

    @RabbitListener(queues = "queue1")
    public String processQueue1(String message) throws InterruptedException, JsonProcessingException {
        logger.info("Get message : " + message);
        ObjectMapper objectMapper = new ObjectMapper();
        return message.equals("ShowAllEmployees") ? objectMapper.writeValueAsString(employeeService.getAllEmployees())
                : objectMapper.writeValueAsString(employeeService.getEmployee(Integer.parseInt(message)));
    }
}

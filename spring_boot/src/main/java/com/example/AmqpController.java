package com.example;

import com.example.beans.Person;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhougb on 2016/8/21.
 */
@RestController
public class AmqpController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/amqp/sendperson")
    public Person sendPerson(Person person){
        String exchange = "person.fanoutexchange";
        String routingkey = "person.routingkey";
        rabbitTemplate.convertAndSend(exchange, routingkey, person);

        //rabbitTemplate.convertAndSend("person.queue", person);
        return person;
    }
}

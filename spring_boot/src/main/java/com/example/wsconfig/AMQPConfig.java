package com.example.wsconfig;

import com.example.beans.Person;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by zhougb on 2016/8/21.
 */
//@Configuration
//@Component
public class AMQPConfig {
    private static final Log logger = LogFactory.getLog(AMQPConfig.class);

    @RabbitListener(queues = "person.queue")
    public void receiveAmqpMessage(Person person){
        logger.info("receiveAmqpMessage person:"+person);
    }

    @Bean
    public Queue queue(){
        Queue queue = new Queue("person.queue");
        return queue;
    }


    @Bean
    public Binding binding(){
        Binding binding = new Binding("person.destination", Binding.DestinationType.EXCHANGE,
                "person.fanoutexchange", "person.routingkey", null);
        return binding;
    }

    @Bean
    public Exchange exchange(){
        //Exchange exchange = new DirectExchange("person.directexchange");
        Exchange exchange = new FanoutExchange("person.fanoutexchange");
        return exchange;
    }
}

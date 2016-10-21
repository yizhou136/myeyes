package com.example.test.service.impl;

import com.example.beans.Person;
import com.example.test.service.AmqpService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhougb on 2016/8/21.
 */
@Service
public class AmqpServiceImpl implements AmqpService {
    private static final Log logger = LogFactory.getLog(AmqpServiceImpl.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public Person receivePerson() {
        Person person = (Person) amqpTemplate.receiveAndConvert();
        logger.info("receivePerson person:"+person);
        return person;
    }
}

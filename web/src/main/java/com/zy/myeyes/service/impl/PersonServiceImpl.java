package com.zy.myeyes.service.impl;

import com.zy.myeyes.beans.Person;
import com.zy.myeyes.dao.PersonDao;
import com.zy.myeyes.service.PersonService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhougb on 2016/5/10.
 */
@Service
public class PersonServiceImpl implements PersonService{
    private final static Log logger = LogFactory.getLog(PersonServiceImpl.class);

    @Autowired
    private PersonDao personDao;

    @Override
    public void save(Person person) {
        personDao.save(person);
        personDao.save(person);
        personDao.save(person);
        personDao.save(person);
        personDao.save(person);
        personDao.save(person);
        personDao.save(person);
        logger.debug("save person service id:"+person.getId());

        Pageable pageable = new PageRequest(0, 3);
        List<Person> list = personDao.findByName(person.getName(), pageable);
        logger.debug("findByName list:"+list.size());

        Person personNew = personDao.findById(person.getId());
        logger.debug("findById person1:"+personNew);

        personNew = personDao.findById(person.getId());
        logger.debug("findById person2:"+personNew);

        personNew = personDao.findById(person.getId());
        logger.debug("findById person3:"+personNew);
    }
}

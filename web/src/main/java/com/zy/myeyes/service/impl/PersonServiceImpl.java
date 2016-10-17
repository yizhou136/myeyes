package com.zy.myeyes.service.impl;

import com.zy.myeyes.beans.Person;
import com.zy.myeyes.dao.PersonDao;
import com.zy.myeyes.service.PersonService;
import com.zy.myeyes.utils.JpaToHibernateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by zhougb on 2016/5/10.
 */
@Service
@Transactional(transactionManager = "jpaTransactionManager")
public class PersonServiceImpl implements PersonService{
    private final static Log logger = LogFactory.getLog(PersonServiceImpl.class);

    @Autowired
    private PersonDao personDao;

    //@PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Person person) {
        person = new Person();
        person.setName("zy");
        personDao.save(person);


        /*SecondLevelCacheStatistics secondLevelCacheStatistics =
                JpaToHibernateUtils.getSessionFactory(entityManager).getStatistics().getSecondLevelCacheStatistics("com.zy.myeyes.beans.Person");

        logger.debug("SecondLevelCacheStatistics: "+secondLevelCacheStatistics);

        personDao.flush();
        logger.debug("save person service id:"+person.getId()+" count:"+personDao.count());


        Pageable pageable = new PageRequest(1, 3);
        List<Person> list = personDao.findByName(person.getName(), pageable);
        logger.debug("findByName1 list:"+list);
        secondLevelCacheStatistics =
                JpaToHibernateUtils.getSessionFactory(entityManager).getStatistics().getSecondLevelCacheStatistics("org.hibernate.cache.internal.StandardQueryCache");

        logger.debug("SecondLevelCacheStatistics: "+secondLevelCacheStatistics);

        list = personDao.findByName(person.getName(), pageable);
        logger.debug("findByName2 list:"+list);
        secondLevelCacheStatistics =
                JpaToHibernateUtils.getSessionFactory(entityManager).getStatistics().getSecondLevelCacheStatistics("org.hibernate.cache.internal.StandardQueryCache");

        logger.debug("SecondLevelCacheStatistics: "+secondLevelCacheStatistics);

        list = personDao.findByName(person.getName(), pageable);
        logger.debug("findByName3 list:"+list);
        secondLevelCacheStatistics =
                JpaToHibernateUtils.getSessionFactory(entityManager).getStatistics().getSecondLevelCacheStatistics("org.hibernate.cache.internal.StandardQueryCache");

        logger.debug("SecondLevelCacheStatistics: "+secondLevelCacheStatistics);

        long personId = person.getId();
        Person personNew = personDao.findById(personId);
        logger.debug("findById person1:"+personNew);

        personNew = personDao.findById(personId);
        logger.debug("findById person2:"+personNew);

        personNew = personDao.findById(personId);
        logger.debug("findById person3:"+personNew);
        //SessionFactory.getStatistics().getSecondLevelCacheStatistics()

        personNew = personDao.findByNameOrderByAge("zy");
        logger.debug("xxxxxxxxxxxxxxx:"+personNew.getId());
        secondLevelCacheStatistics =
                JpaToHibernateUtils.getSessionFactory(entityManager).getStatistics().getSecondLevelCacheStatistics("com.zy.myeyes.beans.Person");

        logger.debug("SecondLevelCacheStatistics: "+secondLevelCacheStatistics);
        /*for (int i = 0; i< 10; i++){
            logger.debug("findById person index:"+i+ " list:"+personDao.findAll());
        }*/

    }

    @Override
    public Person findById(long id) {
        return personDao.findById(id);
    }
}

package com.zy.myeyes.dao.impl;

import com.zy.myeyes.beans.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by zhougb on 2016/5/13.
 */
public class PersonDaoImpl {

    //@PersistenceContext
    private EntityManager entityManager;

    //@Query("from Person p where p.name = :name ORDER BY  p.age desc")
    public Person findByNameOrderByAge(String name){
        return null;//entityManager.
    }
}

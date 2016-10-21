package com.example.dao.impl;

import com.example.beans.Person;
import com.example.dao.CustomRepository;
import com.example.dao.specs.CustomerSpecs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;

/**
 * Created by zhougb on 2016/8/17.
 */
public class CustomRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements CustomRepository<T,ID>{
    private final EntityManager entityManager;

    public CustomRepositoryImpl(Class<T> domainClass, EntityManager entityManager){
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<T> findByAuto(T example, Pageable pageable) {
        Query query = entityManager.createNativeQuery("", Person.class);
        //query.
        return findAll(CustomerSpecs.byAuto(entityManager, example), pageable);
    }
}

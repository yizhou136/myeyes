package com.zy.myeyes.utils;

import com.zy.myeyes.beans.Person;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by zhougb on 2016/5/10.
 */
public class TestJpa {
    private static final Log logger = LogFactory.getLog(TestJpa.class.getSimpleName());


    public static void setUp(){
        logger.debug("setUp");
    }


    public static void createTable(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysqlJPA");
        entityManagerFactory.close();
    }

    public static void save(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysqlJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Person person = new Person();
        person.setName("zhang hao");
        entityManager.persist(person);
        entityTransaction.commit();

        /*person = entityManager.find(Person.class, 1L);
        System.out.println("find person:"+person.getName());
        entityTransaction.begin();
        entityManager.remove(person);
        entityTransaction.commit();*/


        person = entityManager.getReference(Person.class, 2L);
        System.out.println("find person:"+person.getName());

        Query query = entityManager.createQuery("select p from Person p where p.name =:name");
        query.setParameter("name", "zhang hao");
        List<Person> list = query.getResultList();
        System.out.println("find person list:"+list);

        entityManager.close();
        entityManagerFactory.close();
    }

    public static void main(String args[]){
        //createTable();
        save();
    }
}

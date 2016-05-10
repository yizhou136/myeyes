package com.zy.myeyes.test.jpa;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by zhougb on 2016/5/10.
 */
public class TestJpa {
    private static final Log logger = LogFactory.getLog(TestJpa.class.getSimpleName());

    @BeforeClass
    public static void setUp(){
        logger.debug("setUp");
    }

    @Test
    public void createTable(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysqlJPA");
        entityManagerFactory.close();
    }

}

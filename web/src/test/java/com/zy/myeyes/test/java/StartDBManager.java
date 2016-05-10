package com.zy.myeyes.test.java;

import org.hsqldb.util.DatabaseManagerSwing;

import javax.annotation.PostConstruct;

/**
 * Created by zhougb on 2016/5/10.
 */
public class StartDBManager {

    @PostConstruct
    public void startDBManager() {

        //hsqldb
        DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", "" });

        //derby
        //DatabaseManagerSwing.main(new String[] { "--url", "jdbc:derby:memory:testdb", "--user", "", "--password", "" });

        //h2
        //DatabaseManagerSwing.main(new String[] { "--url", "jdbc:h2:mem:testdb", "--user", "sa", "--password", "" });

    }
}

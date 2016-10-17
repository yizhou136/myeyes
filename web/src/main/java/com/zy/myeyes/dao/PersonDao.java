package com.zy.myeyes.dao;

import com.zy.myeyes.beans.Person;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * Created by zhougb on 2016/5/10.
 */
public interface PersonDao extends JpaRepository<Person, Long> {


    //@QueryHints( @QueryHint(name = "org.hibernate.cacheable", value = "true"))
    List<Person> findByName(String name, Pageable pageable);

    @Query("from Person p where p.id = :id")
    //@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
    Person findById(@Param("id")long id);


    @Query("from Person p where p.name = :name ORDER BY  p.age desc")
    //@QueryHints(@QueryHint(name = "JDBC.MAX.RESULT", value = "1"))
    Person findByNameOrderByAge(@Param("name")String name);

    public static interface MyPersonDao {

    }

}



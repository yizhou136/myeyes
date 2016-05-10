package com.zy.myeyes.dao;

import com.zy.myeyes.beans.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by zhougb on 2016/5/10.
 */
public interface PersonDao extends JpaRepository<Person, Long> {


    List<Person> findByName(String name, Pageable pageable);

    @Query("from Person p where p.id = :id")
    Person findById(@Param("id")long id);


}

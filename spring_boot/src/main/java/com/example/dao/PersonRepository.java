package com.example.dao;

import com.example.beans.Person;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhougb on 2016/8/17.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
//public interface PersonRepository extends CustomRepository<Person, Long>{


    List<Person> findByAddress(String address);

    @Cacheable(value = "person", key = "#p0.toString()_#p1.toString()")
    Person findByNameAndAddress(String name, String address);

    @Transactional
    @CacheEvict(value = "person", key = "#p1.toString()")
    @Modifying
    @Query("update Person p set p.address= :address where p.pid= :pid")
    int  updatePerson(@Param("address") String address, @Param("pid") Long pid);

    @Cacheable(value = "person", key = "#p0.toString()")
    Person findByPid(Long pid);

    @Query("select p from Person p where p.name= :name and p.address= :address")
    Person withNameAndAddressQuery(@Param("name") String name,
                                   @Param("address") String address);

    Person withNameAndAddressNamedQuery(String name, String address);
}

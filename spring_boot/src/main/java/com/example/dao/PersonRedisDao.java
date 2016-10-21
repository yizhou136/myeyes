package com.example.dao;

import com.example.beans.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by zhougb on 2016/8/17.
 */
@Repository
public class PersonRedisDao {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String,String > valOpsStr;

    @Resource(name = "redisTemplate")
    ValueOperations<Object,Object> valOpsObj;


    public void save(Person person){
        valOpsObj.set(person.getPid().toString(), person);
    }

    public Person getPerson(Long pid){
        return (Person) valOpsObj.get(pid);
    }
}

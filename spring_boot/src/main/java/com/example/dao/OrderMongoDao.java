package com.example.dao;

import com.example.beans.Order;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoOperations;
//import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by zhougb on 2016/8/18.
 */
@Repository
public class OrderMongoDao {
    /*@Autowired
    private MongoOperations mongoTemplate;

    public Order findById(String id){
        return mongoTemplate.findById(id, Order.class);
    }

    public void save(Order order){
        mongoTemplate.save(order, "order");
    }*/
}

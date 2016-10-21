package com.example.dao;

import com.example.beans.Order;

/*import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.security.access.annotation.Secured;*/
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * Created by zhougb on 2016/8/18.
 */
public interface OrderRepository {//extends MongoRepository<Order, String>{

    List<Order> findByCustomerAndType(String c, String t);

    //@Secured("ROLE_ADMIN")
    /*@PreAuthorize("hasRole('abc')")
    @Query("{'customer':'haha', 'type': ?0}")
    List<Order> findByQuery(String t);*/
}

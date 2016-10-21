package com.example;

import com.example.beans.Item;
import com.example.beans.Order;
import com.example.dao.OrderMongoDao;
import com.example.dao.OrderRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by zhougb on 2016/8/18.
 */
@RestController
public class OrderController {
    private static final Log logger = LogFactory.getLog(OrderController.class);

    @Autowired
    private OrderMongoDao orderMongoDao;

    @Autowired(required = false)
    private OrderRepository orderRepository;

    @RequestMapping("/or/save")
    public Order save(){
        Order order  = new Order();
        order.setCustomer("haha");
        order.setType("taobao");
        Collection<Item> items = new LinkedHashSet<>();
        for(int i=0; i< 10; i++){
            Item item = new Item();
            ///item.setOrder(order);
            item.setPrice(23+i);
            item.setProduct("iphone"+i);
            item.setQuantity(323+i);
            items.add(item);
        }
        order.setItems(items);
        //orderMongoDao.save(order);

        return order;
    }


    @RequestMapping("/or/{id}")
    public Order findByid(@PathVariable("id") String id){

        return null;//orderMongoDao.findById(id);
    }

    @PreAuthorize("hasRole('abc')")
    @RequestMapping("/or/{customer}/{type}")
    public List<Order> findByid(@PathVariable("customer") String c,
                                @PathVariable("type") String t){
        //logger.info("customer:"+c+" type:"+t+" count:"+orderRepository.count());
        List<Order> list = orderRepository.findByCustomerAndType(c, t);

        logger.info("customer:"+c+" type:"+t+" result:"+list);

        //list = orderRepository.findByQuery("taobao");
        return list;
    }


}

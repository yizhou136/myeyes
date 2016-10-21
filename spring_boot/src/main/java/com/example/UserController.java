package com.example;

import com.example.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

/**
 * Created by zhougb on 2016/9/21.
 */
@RestController
public class UserController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDao userDao;

    @RequestMapping("/u/get")
    public List<com.example.beans.User> get(int age){
        log.info("get user by age:{}", age);

        return userDao.get(age);
    }
}

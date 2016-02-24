package com.zy.myeyes.service.impl;

import com.zy.myeyes.beans.User;
import com.zy.myeyes.dao.UserDao;
import com.zy.myeyes.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhougb on 2016/2/23.
 */
@Service
public class UserServiceImpl implements UserService{
    private final static Log logger = LogFactory.getLog(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    @Override
    public User register(String userName, String password) {
        logger.debug("register begin userName:"+userName+" password:"+password);
        User user = new User();
        user.setAge(12);
        user.setUserName(userName);
        logger.debug("register before save User to DB");
        long uid = userDao.save(user);
        logger.debug("register after save User to DB");
        user.setUid(uid);
        logger.debug("register end userName:"+userName+" password:"+password+"");
        return user;
    }
}

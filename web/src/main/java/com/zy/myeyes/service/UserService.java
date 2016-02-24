package com.zy.myeyes.service;

import com.zy.myeyes.beans.User;
import com.zy.myeyes.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhougb on 2016/2/23.
 */

public interface UserService {
    public User register(String userName, String password);
}

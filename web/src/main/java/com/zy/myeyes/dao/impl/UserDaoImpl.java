package com.zy.myeyes.dao.impl;

import com.zy.myeyes.beans.User;
import com.zy.myeyes.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by zhougb on 2016/2/23.
 */
@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public long save(User user) {
        return 0;
    }

    @Override
    public User read(long id) {
        return null;
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    public int delete(long id) {
        return 0;
    }
}
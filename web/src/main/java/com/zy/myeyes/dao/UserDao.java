package com.zy.myeyes.dao;

import com.zy.myeyes.beans.User;

/**
 * Created by zhougb on 2016/2/23.
 */
public interface UserDao {
    public long save(User user);
    public int  update(User user);
    public User read(long id);
    public int  delete(long id);
}

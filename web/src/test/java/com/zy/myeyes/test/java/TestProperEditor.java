package com.zy.myeyes.test.java;

import com.zy.myeyes.beans.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 * Created by zhougb on 2016/4/27.
 */
public class TestProperEditor {

    public static void main(String args[]){
        BeanPropertyRowMapper<User> bprm = BeanPropertyRowMapper.newInstance(User.class);
        System.out.println(bprm.getMappedClass());
    }
}

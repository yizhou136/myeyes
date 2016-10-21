package com.example.dao;

import com.example.beans.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zhougb on 2016/9/21.
 */

@Mapper
public interface UserDao {

    List<User>  get(int age);
}

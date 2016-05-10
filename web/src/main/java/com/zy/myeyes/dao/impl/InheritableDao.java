package com.zy.myeyes.dao.impl;

import com.zy.myeyes.dao.BaseDao;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhougb on 2016/5/9.
 */
public class InheritableDao<T> extends AbstractBaseDao<T> implements BaseDao<T>{

    @Override
    public T get(long id) {
        return get(id, modelSqlBuilder);
    }

    @Override
    public List<T> getAll() {
        return null;
    }

    @Override
    public long save(T t) {
        return save(t, modelSqlBuilder);
    }

    @Override
    public void update(Object o) {

    }

    @Override
    public void batchUpdate(List list) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void deleteAll() {

    }
}

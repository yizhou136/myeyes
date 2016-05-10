package com.zy.myeyes.dao.impl;

import com.zy.myeyes.dao.GenericDao;
import com.zy.myeyes.utils.ModelSqlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhougb on 2016/5/9.
 */
@Repository
public class GenericDaoImp extends AbstractBaseDao implements GenericDao{

    @Override
    public <G> G get(long id, Class<G> clazz) {
        final ModelSqlBuilder<G> modelSqlBuilder = new ModelSqlBuilder<G>(clazz);
        return (G)get(id, modelSqlBuilder);
    }

    @Override
    public <G> List<G> getAll(Class<G> clazz) {

        return null;
    }


    public <G> long save(G g, Class<G> clazz) {
        final ModelSqlBuilder<G> modelSqlBuilder = new ModelSqlBuilder<G>(clazz);
        return save(g, modelSqlBuilder);
    }

    @Override
    public <G> void update(G g, Class<G> clazz) {

    }

    @Override
    public <G> void batchUpdate(List<G> list, Class<G> clazz) {

    }

    @Override
    public <G> void delete(long id, Class<G> clazz) {

    }

    @Override
    public <G> void deleteAll(Class<G> clazz) {

    }
}

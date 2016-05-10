package com.zy.myeyes.dao.impl;

import com.zy.myeyes.utils.ModelSqlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhougb on 2016/5/6.
 */
public abstract class AbstractBaseDao<T>{
    protected final Log logger = LogFactory.getLog(getClass().getSimpleName());

    protected Class<T>    entityClass;
    protected ModelSqlBuilder<T> modelSqlBuilder;
    @Autowired
    protected JdbcTemplate jdbcTemplate;


    public AbstractBaseDao() {
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof  ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            entityClass = (Class<T>) (parameterizedType.getActualTypeArguments()[0]);
            modelSqlBuilder = new ModelSqlBuilder<T>(entityClass);
        }
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public ModelSqlBuilder<T> getModelSqlBuilder() {
        return modelSqlBuilder;
    }

    public void setModelSqlBuilder(ModelSqlBuilder<T> modelSqlBuilder) {
        this.modelSqlBuilder = modelSqlBuilder;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public <G> G  get(long id, final ModelSqlBuilder<G> modelSqlBuilder){
        final String sql = modelSqlBuilder.buildQueryStringById();
        return jdbcTemplate.query(sql, new ResultSetExtractor<G>(){
            @Override
            public G extractData(ResultSet rs) throws SQLException, DataAccessException {
                G t = BeanUtils.instantiate(modelSqlBuilder.getMappedClass());
                BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(t);
                ResultSetMetaData resultSetMetaData = rs.getMetaData();
                int columnCount = resultSetMetaData.getColumnCount();
                logger.debug(" extractData columnCount: "+columnCount);
                while (rs.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        String propertyName = resultSetMetaData.getColumnName(i);
                        String fieldName = modelSqlBuilder.propertyNameToFieldName(propertyName);
                        logger.debug(" extractData columnCount: "+i+" fieldName:"+fieldName+" propertyName:"+propertyName);
                        bw.setPropertyValue(fieldName, rs.getObject(i));
                    }
                }
                return t;
            }
        }, new Object[]{id});
    }

    public long save(T t, ModelSqlBuilder<T> modelSqlBuilder){
        final List<Object> list = new ArrayList<Object>();
        final String sql = modelSqlBuilder.buildInsertString(t, list);
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                for(int i = 1; i <= list.size(); i++) {
                    ps.setObject(i, list.get(i-1));
                }
                return ps;
            }
        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, generatedKeyHolder);
        //user.setUid(generatedKeyHolder.getKey().intValue());
        return generatedKeyHolder.getKey().longValue();
    }


}
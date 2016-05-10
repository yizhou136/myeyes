package com.zy.myeyes.dao.impl;

import com.zy.myeyes.beans.User;
import org.springframework.stereotype.Component;

/**
 * Created by zhougb on 2016/2/23.
 */
@Component
public class UserDaoImpl extends InheritableDao<User> {

    public static void main(String args[]){
        UserDaoImpl userDao = new UserDaoImpl();
    }

/*    @Override
    public long save(final User user) {
        final String sql = "insert into user(name, age) value(?, ?)";
        //jdbcTemplate.update(sql, P);
        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(0, user.getUserName());
                ps.setInt(1, user.getAge());
            }
        };
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setObject(1, user.getUserName());
                ps.setObject(2, user.getAge());
                return ps;
            }
        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, generatedKeyHolder);
        user.setUid(generatedKeyHolder.getKey().intValue());
        //jdbcTemplate.queryForObject()
        return user.getUid();
    }

    @Override
    public User read(long id) {
        final String sql = "select uid, age, name from user where uid = ?";
        return jdbcTemplate.query(sql, new ResultSetExtractor<User>() {
            @Override
            public User extractData(ResultSet rs) throws SQLException, DataAccessException {
                User user = new User();
                while (rs.next()) {
                    user.setUid(rs.getInt("uid"));
                    user.setAge(rs.getInt("age"));
                    user.setUserName(rs.getString("name"));
                }
                return user;
            }
        }, new Object[]{id});
    }

    @Override
    public int update(User user) {
        final String sql = "update user set name = ? , age = ? WHERE uid = ?";
        return jdbcTemplate.update(sql, new Object[]{user.getUserName(), user.getAge(), user.getUid()});
    }

    @Override
    public int delete(long id) {
        final String sql = "delete from user where uid = ?";
        return jdbcTemplate.update(sql, new Object[]{id});
    }

    @Override
    public List<User> findAll() {
        final String sql = "select * from user";
        return jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setUid(rs.getInt("uid"));
                user.setUserName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                return user;
            }
        });
    }*/
}
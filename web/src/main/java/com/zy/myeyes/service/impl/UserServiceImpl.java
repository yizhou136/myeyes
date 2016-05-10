package com.zy.myeyes.service.impl;

import com.zy.myeyes.beans.Product;
import com.zy.myeyes.beans.User;
import com.zy.myeyes.dao.BaseDao;
import com.zy.myeyes.dao.GenericDao;
import com.zy.myeyes.dao.impl.UserDaoImpl;
import com.zy.myeyes.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by zhougb on 2016/2/23.
 */
@Service
public class UserServiceImpl implements UserService , InitializingBean{
    private final static Log logger = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private GenericDao genericDao;

    @Override
    public User register(String userName, String password) {
        /*try {
            ParameterizedType parameterizedType = (ParameterizedType) getClass().getDeclaredField("genericDao").getGenericType();
            Class<User> clazz = (Class<User>) parameterizedType.getActualTypeArguments()[0];
            logger.debug("register begin userName:" + userName + " password:" + password + "  xxxxxxxxx " + clazz);
        }catch (Exception e){ throw  new RuntimeException(e);}*/
        User user = new User();
        user.setAge(12);
        user.setName(userName);
        logger.debug("register before save User to DB");
        long uid = genericDao.save(user, User.class);
        logger.debug("register after save User to DB");
        user.setUid(uid);
        logger.debug("register end userName:"+userName+" password:"+password+"");

        Product product = new Product();
        product.setProductDesc("asdfasdf");
        product.setProductName("afasdfa");
        product.setType(32);
        long pid = genericDao.save(product, Product.class);
        logger.debug("save product pid:"+pid);


        product = genericDao.get(1, Product.class);
        logger.debug("get product by genericDao pid:"+product.getPid());

        user = userDao.get(3);

        logger.debug("get user by userDao uid:"+user.getUid());

        return user;
    }

    @Override
    public int update(User user) {
        return 0;//userDao.update(user);
    }

    @Override
    public User queryById(int uid) {
        return null;//userDao.read(uid);
    }

    @Override
    public int deleteById(int uid) {
        return 0;//userDao.delete(uid);
    }

    @Override
    public List<User> findAll() {
        return null;//userDao.findAll();
    }


    @Override
    public void afterPropertiesSet() throws Exception {

    }
}

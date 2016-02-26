package com.zy.myeyes.controllers;


import com.zy.myeyes.beans.User;
import com.zy.myeyes.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zhougb on 2016/2/23.
 */
@Controller
public class RegController {
    private static final Log logger = LogFactory.getLog(RegController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value={"reg"}, method={RequestMethod.POST})
    public User reg(String userName){
        logger.debug("reg begin xxxxxxxxxxxxxxxxxxxxxxxx");
        User user = userService.register(userName, "nopwd");

        logger.debug("reg end return user"+user+" xxxxxxxxxxxxxxxxxxxxxxxx");
        return user;
    }
}

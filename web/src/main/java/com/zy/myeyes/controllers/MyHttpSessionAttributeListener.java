package com.zy.myeyes.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Created by zhougb on 2016/10/10.
 */
@WebListener
public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener{
    private final Log log = LogFactory.getLog(MyHttpSessionAttributeListener.class);

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        log.info("attributeAdded  event:"+event.getName());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        log.info("attributeRemoved  event:"+event.getName());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        log.info("attributeReplaced  event:"+event.getName());
    }
}

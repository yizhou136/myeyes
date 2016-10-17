package com.zy.myeyes.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by zhougb on 2016/10/10.
 */

@WebListener
public class MyServletRequestListener implements ServletRequestListener {
    private final Log log = LogFactory.getLog(MyServletRequestListener.class);

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        log.info("requestInitialized xxxxxxxxxxxx sre:{}"+ sre);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        log.info("requestDestroyed xxxxxxxxxxxx sre:{}"+ sre);
    }
}

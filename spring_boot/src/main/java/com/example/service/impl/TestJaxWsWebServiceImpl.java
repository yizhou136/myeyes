package com.example.service.impl;

import com.example.service.TestJaxWsWebService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.Date;

/**
 * Created by zhougb on 2016/8/19.
 */
@Service
//@WebService(name = "currentDate", serviceName = "currentDateService", targetNamespace = "http://test.com")
public class TestJaxWsWebServiceImpl implements TestJaxWsWebService{
    private static final Log logger = LogFactory.getLog(TestJaxWsWebServiceImpl.class);

    @Override
    public Date currentDate() {
        logger.info("currentDate");
        return new Date();
    }
}

package com.example.service.impl;

import com.example.service.TestJaxWsWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Date;

/**
 * Created by zhougb on 2016/8/19.
 */

public class JaxWsEndPoint implements TestJaxWsWebService{
    @Autowired
    private TestJaxWsWebService testJaxWsWebService;

    @WebMethod
    public Date currentDate(){
        return testJaxWsWebService.currentDate();
    }
}
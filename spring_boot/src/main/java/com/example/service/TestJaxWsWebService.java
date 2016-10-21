package com.example.service;

import javax.jws.WebService;
import java.util.Date;

/**
 * Created by zhougb on 2016/8/19.
 */
//@WebService(name = "currentDate", serviceName = "currentDateService", targetNamespace = "http://test.com")
public interface TestJaxWsWebService {
    public Date  currentDate();
}

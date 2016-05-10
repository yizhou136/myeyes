package com.zy.myeyes.test.java;


import com.zy.myeyes.beans.User;
import com.zy.myeyes.dao.BaseDao;
import com.zy.myeyes.dao.impl.AbstractBaseDao;

import java.lang.reflect.ParameterizedType;
import java.util.LinkedList;
import java.util.List;

public class GenericTest{
    public List<String> list = new LinkedList<String>();


    public static void main(String[] args) throws SecurityException, NoSuchFieldException
    {
        ParameterizedType pt = (ParameterizedType) GenericTest.class.getField(
                "baseDao").getGenericType();
        System.out.println(pt.getActualTypeArguments().length);
        System.out.println(pt.getActualTypeArguments()[0]);
                //ba

        GenericTest genericTest = new GenericTest();
        //System.out.println(genericTest.baseDao);
    }
}

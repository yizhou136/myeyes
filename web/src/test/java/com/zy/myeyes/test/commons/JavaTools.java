package com.zy.myeyes.test.commons;

import com.sun.beans.TypeResolver;
import com.zy.myeyes.beans.User;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by zhougb on 2016/2/24.
 */
public class JavaTools {

    public static void main(String args[]) throws Exception{
        Method method = T.class.getMethod("m");
        System.out.println(method);
        Class type = (Class)TypeResolver.resolveInClass(User.class, method.getReturnType());
        Class clazz = TypeResolver.erase(type);
        System.out.println(type+ "  " +method.getReturnType()+ " "+ clazz);
    }
}

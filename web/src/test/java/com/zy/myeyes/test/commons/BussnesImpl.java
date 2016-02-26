package com.zy.myeyes.test.commons;

/**
 * Created by zhougb on 2016/2/25.
 */
public class BussnesImpl implements Bussness{
    @Override
    public String doit(String name) {
        System.out.println("BussnesImpl doit"+name);
        return name;
    }
}

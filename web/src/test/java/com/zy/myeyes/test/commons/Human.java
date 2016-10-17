package com.zy.myeyes.test.commons;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by zhougb on 2016/2/25.
 */

//@Component
public class Human implements Sleepable {
    private int age;

    public Human(){}

    public Human(int age){
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void sleep() {
        System.out.println("睡觉了！梦中自有颜如玉！"+age);
    }

    @Override
    public void hulu() {
        System.out.println("打呼噜了"+age);
    }
}

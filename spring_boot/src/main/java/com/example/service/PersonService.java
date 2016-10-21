package com.example.service;

import com.example.beans.Person;

/**
 * Created by zhougb on 2016/8/17.
 */
public interface PersonService {

    public Person  saveWithRollBack(Person person);
    public Person  saveWithoutRollBack(Person person);
}

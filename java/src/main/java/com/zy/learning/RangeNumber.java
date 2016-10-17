package com.zy.learning;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhougb on 2016/9/19.
 */
public class RangeNumber {
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);

    public void setLower(int i){
        if (i > upper.get()){
            throw  new IllegalArgumentException("cant set lower "+i+" > upper");
        }

        int lowerExpect = lower.get();
        int lowerUpdate = i;
        while (lower.compareAndSet(lowerExpect, lowerUpdate)){
            if (i > upper.get()){
                continue;
            }else {

            }
        }


    }


    public void setUpper(int i){

    }

    public boolean isInRange(int i){
        return (i >= lower.get() && i <= upper.get());
    }
}

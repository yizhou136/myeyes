package com.zy.learning;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by zhougb on 2016/7/20.
 */
public class TestPark {

    public Thread newThread(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("Thread "+currentThread().getName()+" parked");
                LockSupport.park(TestPark.this);
                System.out.println("Thread "+currentThread().getName()+" park returned");
            }
        };
        thread.start();
        return thread;
    }

    public static void main(String args[]){
        TestPark testPark = new TestPark();
        Thread thread1 = testPark.newThread();
        Thread thread2 = testPark.newThread();
        Thread thread3 = testPark.newThread();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.unpark(thread2);
    }



}

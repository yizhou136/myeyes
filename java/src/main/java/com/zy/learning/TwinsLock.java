package com.zy.learning;

import javax.sql.rowset.spi.TransactionalWriter;
import java.util.Collection;
import java.util.concurrent.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhougb on 2016/6/30.
 */
public class TwinsLock {
    private static class Sync extends AbstractQueuedSynchronizer {
        Sync(int count){
            //setState(count);
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        /*@Override
        protected int tryAcquireShared(int reduceCount) {
            for(;;){
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount < 0 || compareAndSetState(current, newCount))
                    return newCount;
            }
        }

        @Override
        protected boolean tryReleaseShared(int returnCount) {
            for (;;) {
                int current = getState();
                int newCount = current + returnCount;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }*/

        @Override
        protected boolean tryAcquire(int arg) {
            System.out.println("tryAcquire Thread:"+Thread.currentThread()+" state:"+getState()+" args:"+arg);
            if (compareAndSetState(0, 1)){
                //System.out.println("tryAcquire success Thread:"+Thread.currentThread()+" state:"+getState()+" args:"+arg);
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0) throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        public Condition newCondition(){
            return new ConditionObject();
        }
    }

    private final Sync sync = new Sync(0);
    public void lock()                { sync.acquire(1); }
    public boolean tryLock()          { return sync.tryAcquire(1); }
    public void unlock()              { sync.release(1); }
    public Condition newCondition()   { return sync.newCondition(); }
    public boolean isLocked()         { return sync.isHeldExclusively(); }
    public boolean hasQueuedThreads() { return sync.hasQueuedThreads(); }
    public Collection<Thread> getQueuedThreads(){return  sync.getQueuedThreads();}
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }
    public boolean tryLock(long timeout, TimeUnit unit)
        throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }

    public void lockShared(){ sync.acquireShared(1);}
    public void unlockShared(){sync.releaseShared(1);}

    public static Thread newThread(final TwinsLock twinsLock, final Condition condition){
        Thread thread = new Thread(new Runnable() {
            public void run() {
                twinsLock.lock();
                //twinsLock.lockShared();
                /*try {
                    twinsLock.lockInterruptibly();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                try {
                    System.out.println("Thread:"+Thread.currentThread()+" get lock  and await");
                    //condition.await();
                    //condition.await();
                    //System.out.println("Thread:"+Thread.currentThread()+" await up");
                    //Thread.sleep(5000);
                    //condition.signalAll();
                    condition.await();
                    System.out.println("Thread:"+Thread.currentThread()+" get lock  and await end");
                    //System.out.println("Thread:"+Thread.currentThread()+" sleep up "+Thread.currentThread().isInterrupted());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    twinsLock.unlock();
                }
                //twinsLock.unlockShared();
            }
        });
        thread.start();
        return thread;
    }

    public static void main(String args[]) throws InterruptedException {
        TwinsLock twinsLock = new TwinsLock();
        Thread thread = null;
        Condition condition =  twinsLock.newCondition();

        twinsLock.lock();
        //twinsLock.lockShared();
        try{
            thread = newThread(twinsLock, condition);
            thread = newThread(twinsLock, condition);
            condition.await(2, TimeUnit.SECONDS);
            //thread = newThread(twinsLock, condition);
            //Thread.sleep(100);
            System.out.println("main thread await up and signalAll");
            condition.signalAll();
            //thread.interrupt();
            //System.out.println(twinsLock.getQueuedThreads());
            //twinsLock.lock();
            //condition.signalAll();
        }finally {
            twinsLock.unlock();
            //twinsLock.unlockShared();
        }

        CountDownLatch countDownLatch;

        ReentrantLock reentrantLock;

        ConcurrentHashMap map;
        ConcurrentSkipListMap skipListMap;
        ConcurrentLinkedQueue linkedQueue;
        ConcurrentSkipListSet skipListSet;

        Executor executor = Executors.newCachedThreadPool();
        executor.execute(new Runnable() {
            public void run() {

            }
        });

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (TwinsLock.class){
            TwinsLock.class.wait();
        }
    }
}

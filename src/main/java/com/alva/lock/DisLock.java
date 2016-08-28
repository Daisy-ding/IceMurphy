package com.alva.lock;

import java.util.concurrent.TimeUnit;

/**
 * 类DisLock.java的实现描述：分布式锁
 * 
 * @author hzyangshaokai 2016年1月25日 下午3:50:00
 */
public interface DisLock {

    /**
     * 获取锁
     */
    public void lock();

    /**
     * 如果锁在给定的等待时间内空闲，并且当前线程未被中断，则获取锁
     * 
     * @param time 等待锁的最长时间
     * @param unit time 参数的时间单位
     * @return
     */
    public boolean tryLock(long time, TimeUnit unit);

    /**
     * 释放锁
     */
    public void unlock();

}

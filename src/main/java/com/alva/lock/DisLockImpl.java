package com.alva.lock;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.concurrent.TimeUnit;

/**
 * 类DisLockImpl.java的实现描述：分布式锁实现
 * 
 * @author hzyangshaokai 2016年1月25日 下午3:51:48
 */
public class DisLockImpl implements DisLock {

    private InterProcessMutex lockProxy;

    public DisLockImpl(CuratorFramework client, String path){
        lockProxy = new InterProcessMutex(client, path);
    }

    public void lock() {
        try {
            lockProxy.acquire();
        } catch (Exception e) {
            throw new RuntimeException("DisLockImpl.lock error!", e);
        }

    }

    public boolean tryLock(long time, TimeUnit unit) {

        try {
            return lockProxy.acquire(time, unit);
        } catch (Exception e) {
            throw new RuntimeException("DisLockImpl.tryLock error!", e);
        }
    }

    public void unlock() {
        try {
            lockProxy.release();
        } catch (Exception e) {
            throw new RuntimeException("DisLockImpl.unlock error!", e);
        }
    }

}

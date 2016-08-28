package com.alva.lock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 类DisLockManagerImpl.java的实现描述：锁管理器实现
 * 
 * @author hzyangshaokai 2016年1月25日 下午4:05:21
 */
public class DisLockManagerImpl implements DisLockManager {


    private static final String DEFAULT_NAMESPACE = "fund_lock";

    private CuratorFramework    client;
    
    private String              connectionString;

    private volatile boolean    started           = false;

    public DisLockManagerImpl(String connectionString){
        this.connectionString = connectionString;
    }

    public void init() {

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.builder().connectString(connectionString).retryPolicy(retryPolicy).namespace(DEFAULT_NAMESPACE).build();
        client.start();

        started = true;
    }

    public void destroy() {
        if (client != null) {
            client.close();
            started = false;
        }
    }

    public DisLock getLock(String key) {
        if (!started) {
            throw new RuntimeException("zookeeper is not connected!can't get lock!");
        }

        return new DisLockImpl(client, createPath(key));

    }

    private String createPath(String key) {
        return "/locks/" + key.toUpperCase();
    }

}

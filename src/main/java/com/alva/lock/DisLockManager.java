package com.alva.lock;

/**
 * 类DisLockManager.java的实现描述：分布式锁管理器
 * 
 * @author hzyangshaokai 2016年1月25日 下午4:02:49
 */
public interface DisLockManager {

    /**
     * 获取指定键名的锁对象，如果锁对象不存在则创建
     * 
     * @param key
     * @return
     */
    public DisLock getLock(String key);
}

package com.varela.lock.zk;


import com.varela.lock.DistributedLock;
import com.varela.lock.LocKey;
import com.varela.utils.properties.ResourceUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.utils.ZKPaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author lance
 *         2015-08-14 11:13
 *         zookeeper分布式锁
 */
@Component
public class DistributedZKLock implements DistributedLock {

    @Autowired
    private ResourceUtils resourceUtils;

    private Logger logger = LoggerFactory.getLogger(DistributedZKLock.class);

    public static final String LOCK_ROOT = "lock";

    private String parentLockPath = ZKPaths.fixForNamespace(ZKUtils.SEPARATOR, LOCK_ROOT);

    private ThreadLocal<InterProcessMutex> lock = new ThreadLocal<InterProcessMutex>();

    private int DEFAULT_WAIT_TIME = 3;


    private CuratorFramework client = null;

    @PostConstruct
    public void init() {
        String zk_host = this.resourceUtils.getStringValue(LocKey.Keys.ZK_HOST);
        client = CuratorFrameworkFactory.newClient(zk_host, new RetryOneTime(1000));
        client.start();
    }


    public boolean getLock(String key) throws Exception {
        if (StringUtils.isBlank(key)) {
            return false;
        }
        boolean success = false;
        long start = System.currentTimeMillis();
        try {
            String path = getLockPath(key);
            InterProcessMutex realLock = new InterProcessMutex(client, path);
            lock.set(realLock);
            success = realLock.acquire(DEFAULT_WAIT_TIME, TimeUnit.SECONDS);
            long end = System.currentTimeMillis();
            if (success) {
                logger.info("get lock {} success, time cost {}", key, (end - start));
                return true;
            }
        } catch (Exception e) {
            if (success) {
                releaseLock(key);
            }
            long end = System.currentTimeMillis();
            logger.error("get lock {} failed, error: {},time cost {}", key, e.getMessage(), (end - start));
            return true;
        }
        return false;
    }


    public void releaseLock(String key) {
        try {
            InterProcessMutex realLock = lock.get();
            if (realLock != null) {
                realLock.release();
                String path = getLockPath(key);
                if (this.client.getChildren().forPath(path).isEmpty()) {
                    client.delete().guaranteed().forPath(path);
                }
                logger.info("release lock {} success", key);
            }
        } catch (Exception e) {
            logger.info("release lock {} failed,{}", key, e.getMessage());
        }
    }


    private String getLockPath(String key) {
        String path = ZKPaths.fixForNamespace(parentLockPath, key);
        return path;
    }


}

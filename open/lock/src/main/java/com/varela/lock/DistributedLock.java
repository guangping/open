package com.varela.lock;

/**
 * 
 * @author yangzhou
 *
 */
public interface DistributedLock {

	 boolean getLock(String key) throws Exception;

	 void releaseLock(String key);

}

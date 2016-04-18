package com.varela.cache;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.Set;

/**
 * Created by lance on 2016/4/18.
 * redis集群
 */
@Component
public class JedisClusterFactory implements FactoryBean<JedisCluster>, InitializingBean {

    private Set<HostAndPort> sentinels;
    private JedisCluster jedisCluster;
    private Integer timeout;
    private Integer maxRedirections;
    private GenericObjectPoolConfig genericObjectPoolConfig;

    @Override
    public JedisCluster getObject() throws Exception {
        return jedisCluster;
    }

    @Override
    public Class<?> getObjectType() {
        return (this.jedisCluster != null ? this.jedisCluster.getClass() : JedisCluster.class);
    }

    @Override
    public boolean isSingleton() {
        return true;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        jedisCluster = new JedisCluster(sentinels, timeout, maxRedirections, genericObjectPoolConfig);
    }


    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public void setMaxRedirections(Integer maxRedirections) {
        this.maxRedirections = maxRedirections;
    }

    public void setGenericObjectPoolConfig(GenericObjectPoolConfig genericObjectPoolConfig) {
        this.genericObjectPoolConfig = genericObjectPoolConfig;
    }

    public Set<HostAndPort> getSentinels() {
        return sentinels;
    }

    public void setSentinels(Set<HostAndPort> sentinels) {
        this.sentinels = sentinels;
    }
}

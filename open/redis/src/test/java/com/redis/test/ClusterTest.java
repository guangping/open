package com.redis.test;

import com.varela.cache.JedisClusterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by lance on 2016/4/18.
 */
@ContextConfiguration("classpath:applicationContext-test.xml")
public class ClusterTest  extends AbstractTestNGSpringContextTests {


    @Autowired
    private JedisClusterFactory jedisCluster;


    @Test
    public void run(){
      try {
          this.jedisCluster.getObject().set("","");
      }catch (Exception e){}
    }



}

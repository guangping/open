package com.varela;

import com.varela.utils.properties.ResourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

/**
 * Created by lance on 10/27/2015.
 */
@ContextConfiguration("classpath:applicationContext.xml")
public class ResourceTest {
    @Autowired
    private ResourceUtils resourceUtils;

    @Test
    public void run() {
        String key = "weibo";
        String val = this.resourceUtils.getStringValue(key);
        System.out.println(val);
        key="mac";
        val = this.resourceUtils.getStringValue(key);
        System.out.println(val);
    }

}

package com.api.test.db;

import com.api.entity.API;
import com.api.service.api.APIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by lance on 12/21/2015.
 */
@ContextConfiguration("classpath:applicationContext.xml")
public class DBTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private APIService apiService;

    @Test
    public void saveApi() {
        API api = new API();
        api.setMethod("api/session/get");
        api.setTitle("获取sessionId");
        api.setState(1);

        long id = this.apiService.save(api);
        System.out.println(id);
    }


}

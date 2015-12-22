package com.api.test.db;

import com.api.entity.API;
import com.api.entity.Developer;
import com.api.entity.DeveloperApi;
import com.api.service.api.APIService;
import com.api.service.api.DeveloperApiService;
import com.api.service.api.DeveloperService;
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

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private DeveloperApiService developerApiService;

    @Test
    public void saveApi() {
        API api = new API();
        api.setMethod("api/session/get");
        api.setTitle("获取sessionId");
        api.setState(1);

        long id = this.apiService.save(api);
        System.out.println(id);
    }

    @Test
    public void queryApi() {
        API api = this.apiService.queryObj(1);
        System.out.println(api);
    }

    @Test
    public void saveDeveloper() {
        Developer developer = new Developer();
        developer.setAppKey("00001");
        developer.setSecret("123");
        developer.setContacts("lance");
        developer.setMobile("18999999999");

        long id = this.developerService.save(developer);
        System.out.println(id);
    }

    @Test
    public void queryDeveloper() {
        Developer developer = this.developerService.queryObj(1);
        System.out.println(developer);
    }


    @Test
    public void saveDeveloperApi() {
        DeveloperApi developerApi = new DeveloperApi();
        developerApi.setApiId(1);
        developerApi.setDeveloperId(1);
        long id=this.developerApiService.save(developerApi);
        System.out.println(id);
    }


}

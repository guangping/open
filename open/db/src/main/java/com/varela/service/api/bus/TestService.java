package com.varela.service.api.bus;

import com.varela.entity.API;
import com.varela.entity.Developer;
import com.varela.entity.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lance on 9/30/2015.
 */
@Service
public class TestService  {
    @Autowired
    private LevelService levelService;

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private APIService apiService;

    @Transactional
    public void init(){
        this.initLevel();
        this.initDeveloper();
        this.initApi();
    }

    private void initApi() {
        API api = new API();
        api.setMethod("user.getSession");
        api.setVersion("1.0");
        api.setTitle("获取session");
        this.apiService.save(api);


        api = new API();
        api.setMethod("user.login");
        api.setVersion(null);
        api.setTitle("登陆方法");
        this.apiService.save(api);
    }

    private void initLevel() {
        Level level = new Level();
        level.setName("等级一");
        level.setDayCallCount(100000);
        this.levelService.save(level);
        System.out.println("level===》" + level.getId());


        level = new Level();
        level.setName("等级二");
        level.setDayCallCount(1000000);
        this.levelService.save(level);
        System.out.println("level===》" + level.getId());

    }

    private void initDeveloper() {
        Developer developer = new Developer();
        developer.setAccessId("00001");
        developer.setAccessSecret("123");
        developer.setLevelId("1");
        developer.setContacts("Marry");
        this.developerService.save(developer);

        developer = new Developer();
        developer.setAccessId("00002");
        developer.setAccessSecret("123");
        developer.setLevelId("1");
        developer.setContacts("Marry");
        this.developerService.save(developer);

        developer = new Developer();
        developer.setAccessId("00003");
        developer.setAccessSecret("123");
        developer.setLevelId("1");
        developer.setContacts("Marry");
        this.developerService.save(developer);
    }

}

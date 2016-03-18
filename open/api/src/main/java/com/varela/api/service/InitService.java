package com.varela.api.service;

import com.varela.api.entity.API;
import com.varela.api.entity.Developer;
import com.varela.api.entity.DeveloperApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * Created by lance on 2016/3/18.
 * 初始化调用
 */
@Service
public class InitService {

    private Logger logger = LoggerFactory.getLogger(InitService.class);

    /**
     * 初始化标识符
     */
    @Value("${init.cache:false}")
    private String init;

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private APIService apiService;

    @Autowired
    private DeveloperApiService developerApiService;

    @PostConstruct
    public void init() {
        logger.info("是否初始化数据{}", Boolean.valueOf(init));
        if (Boolean.valueOf(init)) {
            this.initDeveloper();
            this.initApi();
            this.initDeveloperApi();
        }
    }

    @PreDestroy
    public void destory() {
        logger.info("destory......");
    }

    /**
     * 初始化开发者信息
     **/
    private void initDeveloper() {
        List<Developer> list = this.developerService.queryList(null);
        if (null != list && !list.isEmpty()) {
            for (Developer developer : list) {
                this.developerService.setCache(developer);
            }
        }
    }

    /**
     * 初始化开发者API信息
     */
    private void initDeveloperApi() {
        List<DeveloperApi> list = this.developerApiService.queryList(null);
        if (null != list && !list.isEmpty()) {
            for (DeveloperApi developerApi : list) {
                this.developerApiService.setCache(developerApi);
            }
        }
    }

    /**
     * 初始化API信息
     */
    private void initApi() {
        List<API> list = this.apiService.queryList(null);
        if (null != list && !list.isEmpty()) {
            for (API api : list) {
                this.apiService.setCache(api);
            }
        }
    }

}

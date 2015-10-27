package com.varela.service.init;

import com.varela.cache.RedisCache;
import com.varela.cache.RedisKey;
import com.varela.entity.Developer;
import com.varela.entity.Level;
import com.varela.service.bus.DeveloperService;
import com.varela.service.bus.LevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by lance on 9/29/2015.
 */
@Service
public class InItService {

    private Logger logger = LoggerFactory.getLogger(InItService.class);

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private LevelService levelService;

/*    @Autowired
    private RedisCache redisCache;*/

    @PostConstruct
    public void init() {
        logger.info("初始化接入者信息.......");
        this.initDev();
    }

    /**
     * 初始化接入者信息
     */
    public void initDev() {
        List<Developer> list = this.developerService.queryList();
        String key = null;
        Level level = null;
        if (null != list && list.size() > 0) {
            for (Developer developer : list) {
                key = RedisKey.getAccessAppKey(developer.getAccessId());
                level = this.levelService.queryById(developer.getId());
                developer.setLevel(level);
             /*   this.redisCache.set(key, developer);*/
            }
        }
    }
}

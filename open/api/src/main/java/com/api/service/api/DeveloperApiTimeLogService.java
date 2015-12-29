package com.api.service.api;

import com.api.dao.api.impl.DeveloperApiTimeLogDBService;
import com.api.entity.API;
import com.api.entity.Developer;
import com.api.entity.DeveloperApiTimeLog;
import com.api.pojo.APIKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by lance on 12/29/2015.
 */
@Service
public class DeveloperApiTimeLogService {

    private Logger logger = LoggerFactory.getLogger(DeveloperApiTimeLogService.class);

    @Autowired
    private DeveloperApiTimeLogDBService apiTimeLogDBService;

    @Autowired
    private APIService apiService;

    @Autowired
    private DeveloperService developerService;

    private static LinkedBlockingDeque<DeveloperApiTimeLog> queue = new LinkedBlockingDeque<>();


    public void set(DeveloperApiTimeLog arg) throws InterruptedException {
        queue.put(arg);
    }

    public void set(String appKey, String method, long time) {
        API api = this.apiService.queryObj(method);
        Developer developer = this.developerService.queryObj(appKey);
        if (null != api && null != developer) {
            DeveloperApiTimeLog timeLog = new DeveloperApiTimeLog();
            try {
                timeLog.setDeveloperId(developer.getId());
                timeLog.setApiId(api.getId());
                timeLog.setTime(time);
                this.set(timeLog);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @PostConstruct
    public void init() {
        logger.info("记录api调用超过{}ms start.......", APIKey.TIME);
        Timer timer = new Timer();
        for (int i = 0; i < 5; i++) {
            timer.schedule(new SaveDeveloperApiTimeLogTask(), 1000, 60000);
        }
    }

    @Transactional
    public void saveBatch(List<DeveloperApiTimeLog> logList) {
        this.apiTimeLogDBService.saveBatch(logList);
    }

    class SaveDeveloperApiTimeLogTask extends TimerTask {
        @Override
        public void run() {
            int size = queue.size();
            if (size > 0 && size >= 300) {
                size = 300;
            }
            List<DeveloperApiTimeLog> list = new ArrayList<>();
            DeveloperApiTimeLog timeLog = null;
            for (int i = 0; i < size; i++) {
                try {
                    timeLog = queue.take();
                    if (null != timeLog) {
                        list.add(timeLog);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!list.isEmpty()) {
                saveBatch(list);
            }
        }
    }
}

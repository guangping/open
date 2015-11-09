package com.varela.service.api.bus;

import com.varela.dao.api.impl.LevelDBService;
import com.varela.entity.Level;
import com.varela.log.annotation.MethodLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lance on 9/29/2015.
 */
@Service
public class LevelService {
    @Autowired
    private LevelDBService levelDBService;

    @MethodLog(event = "id查询等级信息", paramClazz = {String.class})
    public Level queryById(String id) {
        Level search = new Level();
        search.setId(id);
        return this.levelDBService.queryObject(search);
    }


    @Transactional
    public String save(Level level) {
        if (StringUtils.isBlank(level.getName())) {
            throw new RuntimeException("名称不能为空!");
        }
        return this.levelDBService.save(level);
    }
}

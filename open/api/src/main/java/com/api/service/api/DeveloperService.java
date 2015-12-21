package com.api.service.api;

import com.varela.dao.api.impl.DeveloperDBService;
import com.varela.entity.Developer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lance on 9/29/2015.
 */
@Service
public class DeveloperService {

    @Autowired
    private DeveloperDBService developerDBService;

    public List<Developer> queryList(){
        return this.developerDBService.queryList(new Developer());
    }


    @Transactional
    public String save(Developer developer){
        if(StringUtils.isBlank(developer.getAccessId()) || StringUtils.isBlank(developer.getAccessSecret())){
            throw new RuntimeException("accessid 或 accessSecret 为空!");
        }
        return this.developerDBService.save(developer);
    }
}

package com.varela.service.bus;

import com.varela.dao.impl.APIDBService;
import com.varela.entity.API;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lance on 9/29/2015.
 */
@Service
public class APIService {

    @Autowired
    private APIDBService apiDBService;

    public API queryById(String id) {
        API search = new API();
        search.setId(id);
        return this.apiDBService.queryObject(search);
    }

    @Transactional
    public String save(API api) {
        if (StringUtils.isBlank(api.getMethod()) || StringUtils.isBlank(api.getVersion()) || StringUtils.isBlank(api.getTitle())) {
            throw new RuntimeException("参数不能为空!");
        }
        return this.apiDBService.save(api);
    }

}

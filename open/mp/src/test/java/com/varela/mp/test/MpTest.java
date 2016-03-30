package com.varela.mp.test;

import com.alibaba.fastjson.JSON;
import com.varela.entity.SysUser;
import com.varela.mp.service.SysUserService;
import com.varela.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by lance on 2016/3/23.
 */
@ContextConfiguration("classpath:applicationContext.xml")
public class MpTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void run() {
        int currentPage=1;
        int pageSize=2;
        Page<SysUser> page=this.sysUserService.queryList(new SysUser(),currentPage,pageSize);
        System.out.println(JSON.toJSONString(page));
    }
}

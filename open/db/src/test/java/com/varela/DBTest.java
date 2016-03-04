package com.varela;/*import com.varela.entity.Level;
import com.varela.service.api.bus.LevelService;
import org.springframework.beans.factory.annotation.Autowired;*/

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.varela.dao.DiscoverViewerDataDao;
import com.varela.dao.UserDao;
import com.varela.entity.DiscoverViewerData;
import com.varela.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by lance on 10/14/2015.
 */
@ContextConfiguration("classpath:applicationContext-db.xml")
public class DBTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    private DiscoverViewerDataDao discoverViewerDataDao;

    @Test
    public void run() {
        System.out.println(userDao);
    }

    @Test
    public void queryById() {
        long id = 1;
        User user = this.userDao.selectByPrimaryKey(id);
        System.out.println(user);
    }

    @Test
    public void insert() {
        User user = new User();
        user.setName("东海");
        this.userDao.insert(user);
        System.out.println("id==>" + user.getId());
    }

    @Test
    public void list() {
        long start = System.currentTimeMillis();
        PageBounds pageBounds = null;
        DiscoverViewerData param = null;
        List<DiscoverViewerData> list = null;
        for (int i = 0; i < 200; i++) {
            pageBounds = new PageBounds();
            pageBounds.setAsyncTotalCount(true);
            pageBounds.setPage((i + 1));
            pageBounds.setLimit(500);

            param = new DiscoverViewerData();
            list = this.discoverViewerDataDao.queryList(param, pageBounds);
            if (list.isEmpty()) {
                System.out.println("page:" + (i + 1));
                break;
            }
            this.update(list);
            //System.out.println("===>" + list.size());
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start));
    }


    public void update(List<DiscoverViewerData> list) {
        DiscoverViewerData param = null;
        int i=0;
        for (DiscoverViewerData obj : list) {
            //判断是否修改
            if (StringUtils.isNotBlank(obj.getLocName2())) {
                String items[] = obj.getLocName2().split("\\s+");
                if (null!=items && items.length == 2) {
                    param = new DiscoverViewerData();
                    param.setId(obj.getId());
                    param.setLocName2(items[0]);
                    param.setLocName3(items[1]);
                    i=this.update(param);
                    System.out.println("影响记录数:"+i+",id:"+obj.getId());
                }
            }
        }
    }

    @Transactional
    public int update(DiscoverViewerData param) {
        int i=this.discoverViewerDataDao.updateByPrimaryKeySelective(param);
        if(i>=1){
            throw new RuntimeException("测试");
        }
        return i;
    }


    @Test
    public void update(){
        DiscoverViewerData param=new DiscoverViewerData();
        param.setId(1);
        param.setLocName1("中国");
        this.update(param);
    }

}

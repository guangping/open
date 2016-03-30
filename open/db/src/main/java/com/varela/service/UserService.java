package com.varela.service;

import com.varela.dao.UserDao;
import com.varela.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lance on 2016/3/17.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public long save(User user) {
        try{
            int count = this.userDao.insert(user);
            if (count > 0) {
                throw new RuntimeException("测试事务!");
            }
        }catch (Exception e){
            throw e;

        }
        return user.getId();
    }

    @Transactional
    public long save2(User user){
        int count = this.userDao.insert(user);

        return user.getId();
    }
}

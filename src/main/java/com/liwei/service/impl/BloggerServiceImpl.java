package com.liwei.service.impl;

import com.liwei.dao.BloggerDao;
import com.liwei.entity.Blogger;
import com.liwei.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Liwei on 2016/8/1.
 * 博主实现类
 */
@Service("blogService")
public class BloggerServiceImpl implements BloggerService {

    @Autowired
    private BloggerDao bloggerDao;


    @Override
    public Blogger getByUserName(String userName) {
        Blogger blogger =  bloggerDao.getByUserName(userName);
        return blogger;
    }
}

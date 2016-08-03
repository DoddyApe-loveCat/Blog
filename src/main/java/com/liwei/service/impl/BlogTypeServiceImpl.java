package com.liwei.service.impl;

import com.liwei.dao.BlogTypeDao;
import com.liwei.entity.BlogType;
import com.liwei.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Liwei on 2016/8/2.
 */
@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService{

    @Autowired
    private BlogTypeDao blogTypeDao;


    @Override
    public List<BlogType> countList() {
        return blogTypeDao.countList();
    }
}

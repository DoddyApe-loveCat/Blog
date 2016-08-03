package com.liwei.service.impl;

import com.liwei.dao.BlogDao;

import com.liwei.entity.Blog;
import com.liwei.service.BlogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Liwei on 2016/8/2.
 * 博客实现类
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;


    @Override
    public List<Blog> countList() {
        return blogDao.countList();
    }

    @Override
    public Blog findByBlogId(Integer id) {
        return blogDao.findByBlogId(id);
    }
}

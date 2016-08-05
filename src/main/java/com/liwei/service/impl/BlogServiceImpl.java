package com.liwei.service.impl;

import com.liwei.dao.BlogDao;

import com.liwei.entity.Blog;
import com.liwei.service.BlogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<Blog> list(Map<String, Object> params) {
        return blogDao.list(params);
    }

    @Override
    public Long getTotal(Map<String, Object> params) {
        return blogDao.getTotal(params);
    }

    @Override
    public void update(Blog blog) {
        blogDao.update(blog);
    }

    @Override
    public Blog getPrevious(Integer currentArticleId) {
        return blogDao.getPrevious(currentArticleId);
    }

    @Override
    public Blog getNext(Integer currentArticleId) {
        return blogDao.getNext(currentArticleId);
    }
}

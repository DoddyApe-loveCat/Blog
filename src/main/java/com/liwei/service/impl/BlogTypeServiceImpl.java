package com.liwei.service.impl;

import com.liwei.dao.BlogTypeDao;
import com.liwei.entity.BlogType;
import com.liwei.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<BlogType> list(Map<String, Object> params) {
        return blogTypeDao.list(params);
    }

    @Override
    public Long getTotal(Map<String, Object> params) {
        return blogTypeDao.getTotal(params);
    }

    @Override
    public Integer add(BlogType blogType) {
        return blogTypeDao.add(blogType);
    }

    @Override
    public Integer update(BlogType blogType) {
        return blogTypeDao.update(blogType);
    }

    @Override
    public Integer delete(Integer id) {
        return blogTypeDao.delete(id);
    }

    @Override
    public Integer deleteList(List<Integer> idList) {
        return blogTypeDao.deleteList(idList);
    }

    @Override
    public List<BlogType> findAll() {
        return blogTypeDao.findAll();
    }
}

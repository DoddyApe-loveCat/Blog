package com.liwei.service;

import com.liwei.entity.Blog;

import java.util.List;

/**
 *
 * 博客 Service 接口
 * Created by Liwei on 2016/8/2.
 */
public interface BlogService {


    /**
     * 根据日期分月分组查询
     * @return
     */
    public List<Blog> countList();


    Blog findByBlogId(Integer id);
}

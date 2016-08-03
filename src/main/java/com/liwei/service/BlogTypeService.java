package com.liwei.service;

import com.liwei.entity.BlogType;

import java.util.List;

/**
 * 博客类型 Service 接口
 * Created by Liwei on 2016/8/2.
 */
public interface BlogTypeService {

    /**
     * 查询所有的博客类型，以及博客的数量
     * @return
     */
    public List<BlogType> countList();

}

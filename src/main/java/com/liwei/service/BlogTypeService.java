package com.liwei.service;

import com.liwei.entity.BlogType;

import java.util.List;
import java.util.Map;

/**
 * 博客类型 Service 接口
 * Created by Liwei on 2016/8/2.
 */
public interface BlogTypeService {

    /**
     * 查询所有的博客类型，以及博客的数量
     * @return
     */
    List<BlogType> countList();

    List<BlogType> list(Map<String,Object> params);

    Long getTotal(Map<String,Object> params);

    Integer add(BlogType blogType);

    Integer update(BlogType blogType);

    Integer delete(Integer id);

}

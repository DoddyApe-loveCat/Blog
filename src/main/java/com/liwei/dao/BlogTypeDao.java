package com.liwei.dao;

import com.liwei.entity.BlogType;

import java.util.List;
import java.util.Map;

/**
 * Created by Liwei on 2016/8/2.
 * 博客类型 Dao 接口
 *
 */
public interface BlogTypeDao {


    /**
     * 查看所有的博客类型，以及对应的博客数量
     * @return
     */
    public List<BlogType> countList();

    List<BlogType> list(Map<String,Object> params);

    Long getTotal(Map<String,Object> params);

    Integer add(BlogType blogType);

    Integer update(BlogType blogType);

    Integer delete(Integer id);

}

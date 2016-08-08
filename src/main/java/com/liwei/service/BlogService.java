package com.liwei.service;

import com.liwei.entity.Blog;

import java.util.List;
import java.util.Map;

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
    List<Blog> countList();

    /**
     *
     * 根据 id 查询博客实体（关联博客类型实体）
     * @param id
     * @return
     */
    Blog findByBlogId(Integer id);


    /**
     * 根据条件分页查询博客列表
     * @param params
     * @return
     */
    List<Blog> list(Map<String,Object> params);


    /**
     * 获取总记录数
     * @param params
     * @return
     */
    Long getTotal(Map<String,Object> params);

    /**
     * 更新 Blog 实例
     * @param blog
     */
    Integer update(Blog blog);

    /**
     * 获取上一篇博客
     * @param currentArticleId
     * @return
     */
    Blog getPrevious(Integer currentArticleId);

    /**
     * 获取下一篇博客
     * @param currentArticleId
     * @return
     */
    Blog getNext(Integer currentArticleId);

    /**
     * 添加博客信息
     * @param blog
     * @return
     */
    Integer add(Blog blog);

}

package com.liwei.dao;

import com.liwei.entity.Blog;

import java.util.List;
import java.util.Map;

/**
 * Created by Liwei on 2016/8/2.
 * 博客 Dao 接口
 */
public interface BlogDao {

    /**
     * 根据日期分组分月查询
     * @return
     */
    public List<Blog> countList();

    /**
     * 根据 blog_id 查询博客实体内容
     * @param id
     * @return
     */
    Blog findByBlogId(Integer id);



    List<Blog> list(Map<String,Object> params);

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

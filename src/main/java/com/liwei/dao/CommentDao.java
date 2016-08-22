package com.liwei.dao;

import com.liwei.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Liwei on 2016/8/5.
 */
public interface CommentDao {

    /**
     * 查看用户评论信息
     * @param params
     * @return
     */
    List<Comment> list(Map<String,Object> params);

    /**
     * 添加评论
     * @param comment
     * @return
     */
    Integer add(Comment comment);

    /**
     * 根据条件查询评论总数
     * @param params
     * @return
     */
    Long getTotal(Map<String,Object> params);

    /**
     * 更新单条评论
     * @param comment
     * @return
     */
    Integer update(Comment comment);

    /**
     * 删除单条评论
     * @param id
     * @return
     */
    Integer delete(Integer id);


    Integer batchUpdateState(@Param("ids") List<Integer> ids, @Param("state") Integer state);
}

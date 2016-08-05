package com.liwei.service;

import com.liwei.entity.Comment;

import java.util.List;
import java.util.Map;

/**
 * Created by Liwei on 2016/8/5.
 * 用户评论的 Service 接口
 *
 */
public interface CommentService {

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
}

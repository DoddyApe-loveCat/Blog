package com.liwei.service.impl;

import com.liwei.dao.CommentDao;
import com.liwei.entity.Comment;
import com.liwei.service.CommentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Liwei on 2016/8/5.
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentDao commentDao;


    @Override
    public List<Comment> list(Map<String, Object> params) {
        return commentDao.list(params);
    }

    @Override
    public Integer add(Comment comment) {
        return commentDao.add(comment);
    }

    @Override
    public Long getTotal(Map<String, Object> params) {
        return commentDao.getTotal(params);
    }

    @Override
    public Integer update(Comment comment) {
        return commentDao.update(comment);
    }

    @Override
    public Integer delete(Integer id) {
        return commentDao.delete(id);
    }

    @Override
    public Integer batchUpdateState(@Param("ids") List<Integer> ids, @Param("state") Integer state) {
        return commentDao.batchUpdateState(ids,state);
    }

    @Override
    public Integer batchDelete(List<Integer> idList) {
        return commentDao.batchDelete(idList);
    }
}

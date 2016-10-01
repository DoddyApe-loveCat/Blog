package com.liwei.entity;

import java.util.Date;

/**
 * Created by Liwei on 2016/8/5.
 * 评论实体
 */
public class Comment {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 用户 IP
     */
    private String userIp;
    /**
     * 评论的内容
     */
    private String content;
    /**
     * 被评论的博客
     */
    private Blog blog;
    /**
     * 评论日期（和时间）
     */
    private Date commentDate; // 评论日期
    /**
     * 审核状态
     */
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}

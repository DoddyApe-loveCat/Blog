package com.liwei.entity;

import java.util.Date;

/**
 * 博客实体（一篇博客文章就是一个博客实体对象）
 * Created by Liwei on 2016/8/2.
 */
public class Blog {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 博客标题
     */
    private String title;
    /**
     * 摘要（默认取文章的前 155 个字符作为文章的摘要）
     * 由前端 ueditor 提取正文（只取纯文本）的前 155 个字符
     * 添加文章时生成，持久化在数据库中
     */
    private String summary;
    /**
     * 发布日期
     */
    private Date releaseDate;
    /**
     * 查看次数
     */
    private Integer clickHit;
    /**
     * 回复次数
     */
    private Integer replyHit;
    /**
     * 博客内容
     */
    private String content;
    /**
     * 博客实体内容，
     * 无网页标签，用于添加 Lucene 索引和查询
     */
    private String contentNoTag;
    /**
     * 博客类型
     */
    private BlogType blogType;
    /**
     * 关键字，用空格隔开
     */
    private String keyword;
    /**
     * 博客数量，非博客实际属性
     * 主要是 根据发布日期归档查询数量时用到
     */
    private Integer blogCount;
    /**
     * 表示发布日期的字符串，只取年和月
     */
    private String releaseDateStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getClickHit() {
        return clickHit;
    }

    public void setClickHit(Integer clickHit) {
        this.clickHit = clickHit;
    }

    public Integer getReplyHit() {
        return replyHit;
    }

    public void setReplyHit(Integer replyHit) {
        this.replyHit = replyHit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BlogType getBlogType() {
        return blogType;
    }

    public void setBlogType(BlogType blogType) {
        this.blogType = blogType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }

    public String getReleaseDateStr() {
        return releaseDateStr;
    }

    public void setReleaseDateStr(String releaseDateStr) {
        this.releaseDateStr = releaseDateStr;
    }

    public void setContentNoTag(String contentNoTag) {
        this.contentNoTag = contentNoTag;
    }

    public String getContentNoTag() {
        return contentNoTag;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", releaseDate=" + releaseDate +
                ", clickHit=" + clickHit +
                ", replyHit=" + replyHit +
                ", content='" + content + '\'' +
                ", blogType=" + blogType +
                ", keyword='" + keyword + '\'' +
                ", blogCount=" + blogCount +
                ", releaseDateStr='" + releaseDateStr + '\'' +
                '}';
    }
}

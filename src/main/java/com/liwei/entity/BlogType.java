package com.liwei.entity;

/**
 * 博客类型实体
 * Created by Liwei on 2016/8/2.
 */
public class BlogType {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 博客类型名称
     */
    private String typeName;
    /**
     * 排序序号，升序
     */
    private Integer orderNo;
    /**
     * 博客数量
     */
    private Integer blogCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }

    @Override
    public String toString() {
        return "BlogType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", orderNo=" + orderNo +
                ", blogCount=" + blogCount +
                '}';
    }
}

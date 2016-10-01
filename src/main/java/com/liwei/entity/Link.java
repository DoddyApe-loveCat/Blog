package com.liwei.entity;

/**
 * Created by Liwei on 2016/8/2.
 *
 * 友情链接实体类
 */
public class Link {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 链接名称
     */
    private String linkName;
    /**
     * 链接地址
     */
    private String linkUrl;
    /**
     * 排序序号，升序排序
     */
    private Integer orderNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", linkName='" + linkName + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", orderNo=" + orderNo +
                '}';
    }
}

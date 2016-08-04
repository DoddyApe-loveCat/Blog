package com.liwei.entity;

/**
 * Created by Liwei on 2016/8/4.
 *
 * 分页的 Model 类
 * 说明：这是一个最最简单的分页的  Model 类，
 * 构造函数也是最简单的，请求第 几 页，每页 几 条记录
 * 然后由 PageBean 对象的 getStart() 方法自动计算
 * MySQL limit 查询里面须要使用到的参数
 *
 */
public class PageBean {
    /**
     * 请求第几页
     */
    private Integer page;

    /**
     * 每页显示多少条数据
     */
    private Integer pageSize;

    /**
     * mysql 分页查询 limit 子句的第 1 个参数
     */
    private Integer start;


    public PageBean() {
    }

    public PageBean(Integer page, Integer pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStart() {
        return (page - 1)* pageSize;
    }

    public void setStart(Integer start) {
        this.start = start;
    }
}

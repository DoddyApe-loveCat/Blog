package com.liwei.dao;

import com.liwei.entity.Link;

import java.util.List;
import java.util.Map;

/**
 * Created by Liwei on 2016/8/2.
 */
public interface LinkDao {

    /**
     * 根据条件查询友情链接的集合
     * @param params
     * @return
     */
    List<Link> list(Map<String,Object> params);

    Long getTotal(Map<String,Object> params);

    Integer add(Link link);

    Integer update(Link link);

    Integer delete(Integer id);

    Integer batchDelete(List<Integer> idList);
}

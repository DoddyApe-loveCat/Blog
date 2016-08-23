package com.liwei.service;

import com.liwei.entity.Link;

import java.util.List;
import java.util.Map;

/**
 * Created by Liwei on 2016/8/2.
 * 友情链接的 Service 接口
 */
public interface LinkService {

    /**
     * 根据条件查找友情链接信息
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

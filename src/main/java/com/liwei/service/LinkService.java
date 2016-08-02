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
    public List<Link> list(Map<String,Object> params);
}

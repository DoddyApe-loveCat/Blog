package com.liwei.service.impl;

import com.liwei.dao.LinkDao;
import com.liwei.entity.Link;
import com.liwei.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Liwei on 2016/8/2.
 */
@Service("linkService")
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkDao linkDao;

    @Override
    public List<Link> list(Map<String, Object> params) {
        return linkDao.list(params);
    }

    @Override
    public Long getTotal(Map<String, Object> params) {
        return linkDao.getTotal(params);
    }

    @Override
    public Integer add(Link link) {
        return linkDao.add(link);
    }

    @Override
    public Integer update(Link link) {
        return linkDao.update(link);
    }

    @Override
    public Integer delete(Integer id) {
        return linkDao.delete(id);
    }
}

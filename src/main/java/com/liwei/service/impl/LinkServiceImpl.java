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
}

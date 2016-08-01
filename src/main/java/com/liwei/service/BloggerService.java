package com.liwei.service;

import com.liwei.entity.Blogger;
import org.springframework.stereotype.Service;

/**
 * Created by Liwei on 2016/8/1.
 * 博主接口
 *
 */
@Service
public interface BloggerService {


    /**
     * 通过用户名查找用户
     * @param userName
     * @return
     */
    public Blogger getByUserName(String userName);
}

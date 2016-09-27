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

    /**
     * 查询博主信息
     * @return
     */
    public Blogger find();

    /**
     * 更新博主信息
     * @param blogger
     * @return
     */
    Integer update(Blogger blogger);

    /**
     * 添加博主信息,主要用于单元测试使用
     * @param blogger
     * @return
     */
    Integer add(Blogger blogger);
}

package com.liwei.dao;

import com.liwei.entity.Blogger;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Liwei on 2016/8/1.
 * 博主 dao 接口
 */
public interface BloggerDao {

    /**
     * 通过用户名查找用户
     * @param userName
     * @return
     */
    Blogger getByUserName(@Param("userName") String userName);

    /**
     * 查询博主信息
     * @return
     */
    Blogger find();
}

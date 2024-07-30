package com.wyu.demo.mapper;

import com.wyu.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     * 根据用户名查询数据库中的数据
     * @param username
     */
    User getByUsername(String username);
}

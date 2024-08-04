package com.wyu.demo.mapper;

import com.wyu.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    /**
     * 根据用户名查询数据库中的数据
     * @param username
     */
    User getByUsername(String username);

    @Update("UPDATE user SET login_fail_count = #{loginFailCount} WHERE username = #{username}")
    void updateLoginFailCount(User user);

    void userRegister(User user);

    User getByPhone(String phone);

}

/**
 * UserMapper.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-10-12 13:50
 */
package com.mapper;

import com.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-10-12 13:50
 * @Description
 **/
@Repository
public interface UserMapper {
    //通过用户名查询，并获取其角色
    UserInfo getUserByName(String username);
    //通过用户名模糊查询，并获取其角色
    UserInfo[] getUserFuzzy(UserInfo userInfo);
    //添加用户(基本用户信息)
    Integer addUser(UserInfo userInfo);
    //级联删除
    Integer deleteUser(Integer userId);
    //用户修改密码或状态
    Integer updateUser(UserInfo userInfo);

    Integer deleteUserRole(UserInfo userInfo);

    Integer addUserRole(UserInfo userInfo);

    String getUserNameById(Integer userId);

    Integer userLoginTime(UserInfo userInfo);

}

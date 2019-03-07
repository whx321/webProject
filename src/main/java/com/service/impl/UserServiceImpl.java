/**
 * UserServiceImpl.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-10-12 14:13
 */
package com.service.impl;

import com.mapper.UserMapper;
import com.pojo.RoleInfo;
import com.pojo.UserInfo;
import com.service.UserService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.owasp.esapi.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import javax.annotation.Resource;
import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-10-12 14:13
 * @Description
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    private static String salt = "123";
    @Resource
    private UserMapper userMapper;

    @Override
    public UserInfo getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    @Override
    public UserInfo[] getUserFuzzy(UserInfo userInfo) {
        return userMapper.getUserFuzzy(userInfo);
    }

    @Override
    //@RequiresRoles(value={"admin"})
    public Integer addUser(UserInfo userInfo) {
        userInfo.setPassword(md5(userInfo.getPassword()));
        return  userMapper.addUser(userInfo);
    }

    @Override
    @RequiresRoles(value={"admin"})
    public Integer deleteUser(Integer userId) {
        return userMapper.deleteUser(userId);
    }

    @Override
    //@RequiresRoles(value={"admin"})
    public Integer updateUser(UserInfo userInfo) {
        if(userInfo.getPassword()!=null) {
            userInfo.setPassword(md5(userInfo.getPassword()));
        }
        return userMapper.updateUser(userInfo);
    }

    @Override
    public void updateUserRole(UserInfo userInfo) {
/*          List<Integer> oldRoleIds = userMapper.getUserRoleById(userInfo.getUserId());
          List<Integer> newRoleIds = new ArrayList<Integer>();
          for(RoleInfo roleInfo :userInfo.getRoleInfoList()){
              newRoleIds.add(roleInfo.getRoleId());
          }
          */

          String username =  userMapper.getUserNameById(userInfo.getUserId());
          System.out.println(username+"用户名！！！");
          UserInfo userInfo1 = userMapper.getUserByName(username);
           System.out.println("我靠");
           for(RoleInfo r :userInfo1.getRoleInfoList()){
               System.out.println(r.getRoleId());
           }
          userMapper.deleteUserRole(userInfo1);
        System.out.println("second!!!!");
          userMapper.addUserRole(userInfo);
        System.out.println("last!!!");
    }

    @Override
    public Integer userLoginTime(UserInfo userInfo) {
        return userMapper.userLoginTime(userInfo);
    }

    /**
     * 描述：md5方法说明 md5加密
     * 创建人: 梁家鹄 2018-10-24 15:17
     * 修改人：
     * 修改说明：
     * @param：[s]
     * @return：java.lang.String
     */
    public static String md5(String s){
        return DigestUtils.md5DigestAsHex((salt + s).getBytes());
    }

    /*public  Boolean verifiMd5(String s, String m){
        if (m.equals(md5(s))){
            return true;
        }
        return false;
    }*/

}

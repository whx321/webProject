/**
 * MyRealm.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-10-12 13:42
 */
package com.config;

import com.pojo.PermissionInfo;
import com.pojo.RoleInfo;
import com.pojo.UserInfo;
import com.service.RoleService;
import com.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-10-12 13:42
 * @Description
 **/
public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        UserInfo user = (UserInfo) principalCollection.getPrimaryPrincipal();

        List<RoleInfo>  roleInfos =  userService.getUserByName(user.getUsername()).getRoleInfoList();
        for(RoleInfo rs : roleInfos){
            authorizationInfo.addRole(rs.getRolename());
            System.out.println("角色名1："+rs.getRolename());
            List<PermissionInfo> ps = roleService.getRoleByName(rs.getRolename()).getPermissionInfoList();
            for(PermissionInfo p : ps){
                System.out.println("权限名："+p.getPermissionname());
                authorizationInfo.addStringPermission(p.getPermissionname());

            }
        }
        System.out.println("我是权限认证！");
       return authorizationInfo;
    }

    //身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String username = (String)authenticationToken.getPrincipal();

        UserInfo userInfo = userService.getUserByName(username);

        if(userInfo==null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo,
                userInfo.getPassword(),
                ByteSource.Util.bytes(userInfo.getSalt()),
                getName()
        );

        System.out.println("我是身份认证！2");
        return authenticationInfo;

    }

}

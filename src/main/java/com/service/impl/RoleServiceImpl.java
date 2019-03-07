/**
 * RoleServiceImpl.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-10-26 14:00
 */
package com.service.impl;

import com.google.inject.spi.PrivateElements;
import com.mapper.RoleMapper;
import com.pojo.PermissionInfo;
import com.pojo.RoleInfo;
import com.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.management.relation.Role;
import java.util.List;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-10-26 14:00
 * @Description
 **/
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public RoleInfo getRoleByName(String rolename) {
        return roleMapper.getRoleByName(rolename);
    }

    @Override
    public RoleInfo[] getRoleFuzzy(String rolename) {
        return roleMapper.getRoleFuzzy(rolename);
    }

    @Override
    public Integer addRole(RoleInfo roleInfo) {
        return roleMapper.addRole(roleInfo);
    }

    @Override
    public Integer deleteRole(Integer roleId) {
        return roleMapper.deleteRole(roleId);
    }

    @Override
    public Integer updateRole(RoleInfo roleInfo) {
        return roleMapper.updateRole(roleInfo);
    }

    @Override
    public void updateRolePermission(RoleInfo roleInfo) {
        String rolename = roleMapper.getRoleNameById(roleInfo.getRoleId());
        RoleInfo roleInfo1 = roleMapper.getRoleByName(rolename);
        System.out.println(rolename);
        for(PermissionInfo p: roleInfo.getPermissionInfoList()){
            System.out.println(p.getPermissionId());
        }
        roleMapper.deleteRolePermission(roleInfo1);

        roleMapper.addRolePermission(roleInfo);

    }
}

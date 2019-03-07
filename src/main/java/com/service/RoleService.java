/**
 * RoleService.interface
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-10-26 13:52
 */
package com.service;

import com.pojo.RoleInfo;

import java.util.List;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-10-26 13:52
 * @Description
 **/
public interface RoleService {
     RoleInfo getRoleByName(String rolename);

    RoleInfo[] getRoleFuzzy(String rolename);

    Integer addRole(RoleInfo roleInfo);

    Integer deleteRole(Integer roleId);

    Integer updateRole(RoleInfo roleInfo);

    void updateRolePermission(RoleInfo roleInfo);
}

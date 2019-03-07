/**
 * RoleMapper.interface
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-10-26 13:39
 */
package com.mapper;

import com.pojo.RoleInfo;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-10-26 13:39
 * @Description
 **/
@Repository
public interface RoleMapper {

    RoleInfo getRoleByName(String rolename);

    RoleInfo[]  getRoleFuzzy(String rolename);

    Integer addRole(RoleInfo roleInfo);

    Integer deleteRole(Integer roleId);

    Integer updateRole(RoleInfo roleInfo);

    Integer deleteRolePermission(RoleInfo roleInfo);

    Integer addRolePermission(RoleInfo roleInfo);

    String getRoleNameById(Integer roleId);
}

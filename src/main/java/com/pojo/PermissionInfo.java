/**
 * PermissionInfo.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-10-12 11:18
 */
package com.pojo;

import java.util.List;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-10-12 11:18
 * @Description
 **/
public class PermissionInfo {

    private Integer permissionId;

    private String permissionname;

    private String permission_description;

    private List<RoleInfo> roleInfoList;


    public PermissionInfo(Integer permissionId, String permissionname, String permission_description) {
        this.permissionId = permissionId;
        this.permissionname = permissionname;
        this.permission_description = permission_description;
    }

    public PermissionInfo() {
    }

    public List<RoleInfo> getRoleInfoList() {
        return roleInfoList;
    }

    public void setRoleInfoList(List<RoleInfo> roleInfoList) {
        this.roleInfoList = roleInfoList;
    }

    public String getPermission_description() {
        return permission_description;
    }

    public void setPermission_description(String permission_description) {
        this.permission_description = permission_description;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionid) {
        this.permissionId = permissionid;
    }

    public String getPermissionname() {
        return permissionname;
    }

    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname;
    }
}

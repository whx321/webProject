/**
 * UserInfo.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-10-12 11:07
 */
package com.pojo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-10-12 11:07
 * @Description
 **/
public class UserInfo {

    private Integer userId;

    private String username;

    private String password;

    private String salt;

    private String status;

    private String realname;

    private String lasttime;

    private List<RoleInfo> roleInfoList;

    public UserInfo() {}

    public UserInfo(Integer userId, String username, String password, String salt, String status, String realname, String lasttime) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.status = status;
        this.realname = realname;
        this.lasttime = lasttime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getLasttime() {
        return lasttime;
    }

    public void setLasttime(String lasttime) {
        this.lasttime = lasttime;
    }

    public List<RoleInfo> getRoleInfoList() {
        return roleInfoList;
    }

    public void setRoleInfoList(List<RoleInfo> roleInfoList) {
        this.roleInfoList = roleInfoList;
    }
}
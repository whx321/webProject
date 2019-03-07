/**
 * RolesController.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-10-26 13:39
 */
package com.controller;

import com.common.AjaxResult;
import com.pojo.RoleInfo;
import com.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-10-26 13:39
 * @Description
 **/
@Controller
@RequestMapping("/role")
@Api(tags = "角色管理")
public class RolesController {

    @Resource
    private RoleService roleService;

    @ApiOperation(value = "角色查询",notes = "角色查询",httpMethod = "POST")
    @ApiImplicitParam(name = "roleInfo" ,value = "输入rolename")
    @RequestMapping(value = "/selectRole",method={RequestMethod.POST})
    @ResponseBody
    public RoleInfo[] selectRole(@RequestBody RoleInfo roleInfo){
        RoleInfo[] lists = roleService.getRoleFuzzy(roleInfo.getRolename());
        return lists;
    }

    @ApiOperation(value = "角色添加",notes = "角色添加",httpMethod = "POST")
    @ApiImplicitParam(name = "roleInfo" ,value = "输入rolename and role_description")
    @RequestMapping(value="/addRole",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult addRole(@RequestBody RoleInfo roleInfo) {
        RoleInfo r = roleService.getRoleByName(roleInfo.getRolename());
        if(r==null){
            roleService.addRole(roleInfo);
            return AjaxResult.success("success",1);
        }
        return AjaxResult.error(-1,"角色已存在");
    }

    @ApiOperation(value = "角色删除",notes = "角色删除",httpMethod = "POST")
    @ApiImplicitParam(name = "roleInfo" ,value = "输入roleId")
    @RequestMapping(value="/deleteRole",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult deleteRole(@RequestBody RoleInfo roleInfo){
        try{
            roleService.deleteRole(roleInfo.getRoleId());
        }catch (Exception exception){
            return AjaxResult.error(-1,"删除失败");
        }
        return AjaxResult.success("删除成功",1);
    }

    @ApiOperation(value = "角色基本信息修改",notes = "角色修改",httpMethod = "POST")
    @ApiImplicitParam(name = "roleInfo" ,value = "输入roleId,rolename or role_description")
    @RequestMapping(value="/updateRole",method = {RequestMethod.POST})
    @ResponseBody
    //@RequiresRoles(value={"admin"})
    public AjaxResult updateUser(@RequestBody RoleInfo roleInfo){
        try{
            roleService.updateRole(roleInfo);
        }catch (Exception exception){
            return AjaxResult.error("修改失败");
        }
        return AjaxResult.success("修改成功");
    }

    @ApiOperation(value = "角色权限修改",notes = "角色修改权限",httpMethod = "POST")
    @ApiImplicitParam(name = "roleInfo" ,value = "输入roleId, PermissionId")
    @RequestMapping(value="updateRolePermission",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult updateRolePermission(@RequestBody RoleInfo roleInfo){
        roleService.updateRolePermission(roleInfo);
        return AjaxResult.success("修改成功",1);
    }








}

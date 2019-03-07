/**
 * UserController.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-10-16 16:45
 */
package com.controller;


import com.common.AjaxResult;
import com.pojo.RoleInfo;
import com.pojo.UserInfo;
import com.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-10-16 16:45
 * @Description
 **/
@Controller
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController  {

    @Resource
    private UserService userService;


/**
 * 描述：Login登录方法说明
 * 创建人: 梁家鹄 2018-10-24 15:00
 * 修改人：     
 * 修改说明：
 * @param：[userInfo, model, session]
 * @return：java.lang.String
 */
    @RequestMapping(value = "/login",method={RequestMethod.POST})
    @ApiOperation(value = "用户登录",notes = "登录",httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "userInfo" ,value = "输入username,password"),
                       @ApiImplicitParam(name = "session" ,value = "session(不用填)" )
                      })
    @ResponseBody
    public AjaxResult Login(@RequestBody UserInfo userInfo, HttpSession session){
        
        System.out.println("参数"+userInfo.getUsername() + userInfo.getPassword());
        String username = userInfo.getUsername();
        String password = userInfo.getPassword();
        if(userInfo.getUsername()==null){
            //model.addAttribute("message", "账号不为空");
            return AjaxResult.error("账号不能为空");
        }
        //主体,当前状态为没有认证的状态“未认证”
        Subject subject = SecurityUtils.getSubject();
        // 登录后存放进shiro token
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);

        //登录方法（认证是否通过）
        //使用subject调用securityManager,安全管理器调用Realm
        try {
            //利用异常操作
            //需要开始调用到Realm中
            subject.login(token);
            userInfo = (UserInfo) subject.getPrincipal();
            session.setAttribute("user","已登录");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time=formatter.format(new Date());
            Timestamp dates = Timestamp.valueOf(time);
            String timeStr=dates
                    .toString()
                    .substring(0, dates.toString().indexOf("."));
            System.out.println(timeStr);
            userInfo.setLasttime(timeStr);
            userService.userLoginTime(userInfo);
        } catch (Exception e) {
            return  AjaxResult.error(-1,"账号密码不正确");
        }


        return  AjaxResult.success("登录成功",1);
    }

    @ApiOperation(value = "用户退出",notes = "登出",httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/loginOut",method={RequestMethod.POST})
    public AjaxResult loginOut(){

        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        System.out.println("登出2");
       // session.setAttribute("user","a");
        return AjaxResult.success("退出成功",1);
    }

    /**
     * 描述：selectUser方法说明
     * 创建人: 梁家鹄 2018-10-24 14:45
     * 修改人：     
     * 修改说明：
     * @param：[username]
     * @return：java.util.List<com.pojo.UserInfo>
     */
    @ApiOperation(value = "用户查询",notes = "查询",httpMethod = "POST")
    @ApiImplicitParam(name = "userInfo" ,value = "输入username")
    @RequestMapping(value = "/selectUser",method={RequestMethod.POST})
    @ResponseBody
    public UserInfo[] selectUser(@RequestBody UserInfo userInfo){
        UserInfo[] lists = userService.getUserFuzzy(userInfo);
        for(UserInfo u:lists){
            System.out.println(u.getLasttime());
        }
        return lists;
    }

    @ApiOperation(value = "查询所有用户",notes = "查询",httpMethod = "POST")
    @RequestMapping(value = "/selectAllUser",method={RequestMethod.POST})
    @ResponseBody
    public UserInfo[] selectAllUser(){
        UserInfo userInfo = new UserInfo();
        UserInfo[] lists = userService.getUserFuzzy(userInfo);
        for(UserInfo u:lists){
            System.out.println(u.getLasttime());
        }
        return lists;
    }

    /**
     * 描述：addUser方法说明
     * 创建人: 梁家鹄 2018-10-24 15:51
     * 修改人：
     * 修改说明：
     * @param：[userInfo]
     * @return：java.util.List<com.pojo.UserInfo>
     */
    @ApiOperation(value = "用户注册",notes = "注册",httpMethod = "POST")
    @ApiImplicitParam(name = "userInfo" ,value = "输入username,password，")
    @RequestMapping(value="/addUser",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult addUser(@RequestBody UserInfo userInfo) {
           UserInfo u = userService.getUserByName(userInfo.getUsername());
           if(u==null){
               try {
                   userService.addUser(userInfo);
                   return AjaxResult.success("注册成功",1);
               }catch(UnauthorizedException exception){
                   return AjaxResult.error(-2,"没有权限");
               }

           }
        return AjaxResult.error(-1,"用户名已存在");
    }

    @ApiOperation(value = "用户删除",notes = "删除",httpMethod = "POST")
    @ApiImplicitParam(name = "userInfo" ,value = "输入userId")
    @RequestMapping(value="/deleteUser",method = {RequestMethod.POST})
    @ResponseBody
    //@RequiresRoles(value={"admin"})
    public AjaxResult deleteUser(@RequestBody UserInfo userInfo){
        System.out.println(userInfo.getUserId());
        try{
            userService.deleteUser(userInfo.getUserId());
        }catch (UnauthorizedException exception){
            return AjaxResult.error(-1,"删除失败,没有权限");
        }catch (AuthorizationException exception){
            return AjaxResult.error(-2,"删除失败,没有登录");
        }
        return AjaxResult.success("删除成功",1);
    }

    @ApiOperation(value = "用户修改",notes = "修改",httpMethod = "POST")
    @ApiImplicitParam(name = "userInfo" ,value = "输入username,passwor,status")
    @RequestMapping(value="/updateUser",method = {RequestMethod.POST})
    @ResponseBody
    //@RequiresRoles(value={"admin"})
    public AjaxResult updateUser(@RequestBody UserInfo userInfo){
        try{
            userService.updateUser(userInfo);
        }catch (UnauthorizedException exception){
            return AjaxResult.error(-1,"修改失败");
        }
        return AjaxResult.success("修改成功",1);
    }

    @ApiOperation(value = "用户赋予角色，删除角色",notes = "修改",httpMethod = "POST")
    @ApiImplicitParam(name = "userInfo" ,value = "输入userId,roleId")
    @RequestMapping(value="updateUserRole",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult updateUserRole(@RequestBody UserInfo userInfo){
        userService.updateUserRole(userInfo);
        return AjaxResult.success("");
    }


/*    @RequestMapping(value="/check",method = {RequestMethod.POST})
    @ResponseBody
    public int test (@RequestBody int userInfo){
        System.out.println("我的名字2："+userInfo);
        return userInfo;
    }*/

/*    @RequestMapping(value="/role",method = {RequestMethod.POST})
    public String test (Model model){
        Subject subject = SecurityUtils.getSubject();
        subject.toString();
        if(subject.hasRole("admin")) {
        //有权限
            model.addAttribute("role1", "有权限");
        } else {
        //无权限
            model.addAttribute("role1", "无");
        }
        return "roletest";
    }*/


}

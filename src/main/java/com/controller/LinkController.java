/**
 * LinkController.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-11-06 16:27
 */
package com.controller;

import com.common.AjaxResult;
import com.pojo.LinkInfo;
import com.pojo.ProductInfo;
import com.service.LinkService;
import com.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-11-06 16:27
 * @Description
 **/
@Controller
@RequestMapping("/link")
@Api(tags = "友情链接")
public class LinkController {
    @Resource
    private LinkService linkService;


    @ApiOperation(value = "查询所有链接",notes = "查询",httpMethod = "POST")
    @RequestMapping(value = "/selectAllLink",method={RequestMethod.POST})
    @ResponseBody
    public LinkInfo[] selectAllLink(){
        LinkInfo[] lists = linkService.selectAllLink();
        return lists;
    }

    @ApiOperation(value = "链接添加",notes = "添加",httpMethod = "POST")
    @ApiImplicitParam(name = "LinkInfo" ,value = "输入linkname,link_url,link_order")
    @RequestMapping(value="/addLink",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult addLink(@RequestBody LinkInfo linkInfo) {
        linkService.addLink(linkInfo);
        return AjaxResult.success("success",1);
    }
    @ApiOperation(value = "链接删除",notes = "删除",httpMethod = "POST")
    @ApiImplicitParam(name = "LinkInfo" ,value = "输入linkId")
    @RequestMapping(value="/deleteLink",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult deleteLink(@RequestBody LinkInfo linkInfo){
        try{
            System.out.println(linkInfo.getLinkId());
            linkService.deleteLink(linkInfo.getLinkId());
        }catch (Exception exception){
            return AjaxResult.error(-1,"删除失败");
        }
        return AjaxResult.success("删除成功",1);
    }

    @ApiOperation(value = "链接修改",notes = "修改",httpMethod = "POST")
    @ApiImplicitParam(name = "linkInfo" ,value = "输入linkId,linkname,link_url,link_order")
    @RequestMapping(value="/updateLink",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult updateLink(@RequestBody LinkInfo linkInfo){
        linkService.updateLink(linkInfo);

        return AjaxResult.success("修改成功",1);
    }






}

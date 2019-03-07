/**
 * ArticleController.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-10-24 10:42
 */
package com.controller;

import com.common.AjaxResult;
import com.pojo.Article;
import com.pojo.UploadFile;
import com.service.ArticleService;
import com.service.UploadFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.InetAddress;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 文件的说明
 *
 * @author 杨凯
 * @version 1.0.0, 2018-10-24 10:42
 **/
@Controller
@RequestMapping("/article")
@Api(tags = "文章管理")
public class ArticleController {
    @Autowired
    protected HttpServletRequest request;
    @Resource
    private ArticleService articleService;
    @Resource
    private UploadFileService uploadFileService;

    @ApiOperation(value = "添加文章",notes = "添加文章",httpMethod = "POST",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name="articleTitle",value = "文章标题",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articleDescription",value = "文章描述",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articleKeyword",value = "文章关键字",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articleCoverPhoto",value = "封面图片",paramType = "query",dataType = "file"),
            @ApiImplicitParam(name="articleSource",value = "文章来源",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articleReleaseDate",value = "发布日期",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articlePublisher",value = "发布人",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articleAbstract",value = "摘要",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articleContent",value = "文章内容",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articleEnclosure",value = "附件",paramType = "query",dataType = "file"),
            @ApiImplicitParam(name="articleReserve1",value = "预留1",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articleReserve2",value = "预留2",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articleReserve3",value = "预留3",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articleSortNumber",value = "排序号",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articleType",value = "文章类型",paramType = "query",dataType = "String"),
    })
    @RequestMapping(value = "/addArticle",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult addArticle(@RequestParam(value = "articleTitle",required = false,defaultValue = "") String articleTitle,
                                 @RequestParam(value = "articleDescription",required = false,defaultValue = "") String articleDescription,
                                 @RequestParam(value = "articleKeyword",required = false,defaultValue = "") String articleKeyword,
                                 @RequestParam(value = "articleCoverPhoto",required = false) MultipartFile articleCoverPhoto,
                                 @RequestParam(value = "articleSource",required = false,defaultValue = "") String articleSource,
                                 @RequestParam(value = "articleReleaseDate",required = false,defaultValue = "") String articleReleaseDate,
                                 @RequestParam(value = "articlePublisher",required = false,defaultValue = "") String articlePublisher,
                                 @RequestParam(value = "articleAbstract",required = false,defaultValue = "") String articleAbstract,
                                 @RequestParam(value = "articleContent",required = false,defaultValue = "") String articleContent,
                                 @RequestParam(value = "articleEnclosure",required = false) MultipartFile articleEnclosure,
                                 @RequestParam(value = "articleReserve1",required = false,defaultValue = "") String articleReserve1,
                                 @RequestParam(value = "articleReserve2",required = false,defaultValue = "") String articleReserve2,
                                 @RequestParam(value = "articleReserve3",required = false,defaultValue = "") String articleReserve3,
                                 @RequestParam(value = "articleSortNumber",required = false,defaultValue = "") Integer articleSortNumber,
                                 @RequestParam(value = "articleType",required = false,defaultValue = "") String articleType
                                 ){
        System.out.println("addArticle----C");
        if(articleService.selectArticleByArticleTitle(articleTitle)>0){
            return AjaxResult.error(-1,"文章标题重复已存在");
        }
        String name1x=System.currentTimeMillis()+"";
        String name2x=System.currentTimeMillis()+1+"";
        Article article=new Article();
        article.setArticleTitle(articleTitle);
        article.setArticleDescription(articleDescription);
        article.setArticleKeyword(articleKeyword);
        System.out.println("articleCoverPhoto.getName()="+articleCoverPhoto.getOriginalFilename());
        article.setArticleCoverPhoto("D:/upload/pic2/"+name1x+articleCoverPhoto.getOriginalFilename());
        article.setArticleSource(articleSource);
        System.out.println("articleReleaseDate="+articleReleaseDate);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date date = simpleDateFormat.parse(articleReleaseDate, pos);
        article.setArticleReleaseDate(date);
        article.setArticlePublisher(articlePublisher);
        article.setArticleAbstract(articleAbstract);
        article.setArticleContent(articleContent);
        System.out.println("articleEnclosure.getName()="+articleEnclosure.getOriginalFilename());
        article.setArticleEnclosure("D:/upload/file/"+name2x+articleEnclosure.getOriginalFilename());
        article.setArticleReserve1(articleReserve1);
        article.setArticleReserve2(articleReserve2);
        article.setArticleReserve3(articleReserve3);
        article.setArticleSortNumber(articleSortNumber);
        article.setArticleType(articleType);
        System.out.println(article.toString());
        Integer i=articleService.addArticle(article);
        Integer returnArticleId=articleService.selectArticleIdByTheLast();
        if(i>0){
            //保存封面
            System.out.println("保存封面");
            UploadFile uploadFile1 =new UploadFile();
            uploadFile1.setArticleId(returnArticleId);
            uploadFile1.setFileUrl("D:/upload/pic2/"+name1x+articleCoverPhoto.getOriginalFilename());
            uploadFile1.setFileType("pic2");
            System.out.println(uploadFile1.toString());
            Integer j1=uploadFileService.addUploadFile(uploadFile1);
            Integer returnUploadFileId=uploadFileService.selectUploadFileByTheLast();
            if(j1>0){
                //先保存封面，保存完了在保存附件
                System.out.println("保存封面文件");
                saveFile(articleCoverPhoto,"D:/upload","pic2",name1x);
                UploadFile uploadFile2 =new UploadFile();
                uploadFile2.setArticleId(returnArticleId);
                uploadFile2.setFileUrl("D:/upload/file/"+name2x+articleEnclosure.getOriginalFilename());
                uploadFile2.setFileType("file");
                System.out.println(uploadFile2.toString());
                Integer j2=uploadFileService.addUploadFile(uploadFile2);
                System.out.println("保存附件文件");
                if(j2>0){
                    System.out.println("保存附件");
                    saveFile(articleEnclosure,"D:/upload","file",name2x);
                    return AjaxResult.success();
                }else {
                    System.out.println("删除最近添加的文章");
                    uploadFileService.deleteUploadFileById(returnArticleId);
                    uploadFileService.deleteUploadFileById(returnUploadFileId);
                    File file=new File(uploadFile1.getFileUrl());
                    if(file.exists()&&file.isFile()) {
                        System.out.println("删除文件");
                        file.delete();
                    }
                    File file1=new File(uploadFile2.getFileUrl());
                    if(file1.exists()&&file1.isFile()) {
                        System.out.println("删除文件");
                        file1.delete();
                    }
                    return AjaxResult.error();
                }

            }else{
                System.out.println("删除最近添加的文章");
                uploadFileService.deleteUploadFileById(returnArticleId);
                return AjaxResult.error();
            }

        }else{
            return AjaxResult.error();
        }
    };
    @ApiOperation(value = "删除文章",notes = "删除文章",httpMethod = "POST",produces = "application/json")
    @RequestMapping(value = "/deleteArticleById",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult deleteArticleById(@RequestParam(value = "articleId",required = false) Integer[] articleId){
        System.out.println("deleteArticleById----C");
        //删除表记录
        Integer i=0;
        for (int k=0;k<articleId.length;k++){
            System.out.println("删除文章表");
            articleService.deleteArticleById(articleId[k]);
            List<UploadFile> uploadFiles=uploadFileService.selectUploadFileByArticleId(articleId[k]);
            System.out.println("删除文件表");
            uploadFileService.deleteUploadFileByArticleId(articleId[k]);
            //删除文件系统文件
            for(int j=0;j<uploadFiles.size();j++){
                File file=new File(uploadFiles.get(j).getFileUrl());
                if(file.exists()&&file.isFile()) {
                    System.out.println("删除文件");
                    file.delete();
                }
            }
        }
        System.out.println("删除成功");
        return AjaxResult.success();

    };

    @ApiOperation(value = "更新文章",notes = "更新文章",httpMethod = "POST",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name="articleTitle",value = "文章标题",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articleDescription",value = "文章描述",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articleKeyword",value = "文章关键字",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articleCoverPhoto",value = "封面图片",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articleSource",value = "文章来源",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articleReleaseDate",value = "发布日期",paramType = "query",dataType = "string"),
            @ApiImplicitParam(name="articlePublisher",value = "发布人",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articleAbstract",value = "摘要",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articleContent",value = "文章内容",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articleEnclosure",value = "附件",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articleReserve1",value = "预留1",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articleReserve2",value = "预留2",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articleReserve3",value = "预留3",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articleSortNumber",value = "排序号",paramType = "query",dataType = "String")
    })
    @RequestMapping(value = "/updateArticle",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult updateArticle(@RequestParam(value = "articleId",required = false) Integer articleId,
                                    @RequestParam(value = "articleTitle",required = false,defaultValue = "") String articleTitle,
                                    @RequestParam(value = "articleDescription",required = false,defaultValue = "") String articleDescription,
                                    @RequestParam(value = "articleKeyword",required = false,defaultValue = "") String articleKeyword,
                                    @RequestParam(value = "articleCoverPhoto",required = false) MultipartFile articleCoverPhoto,
                                    @RequestParam(value = "articleSource",required = false,defaultValue = "") String articleSource,
                                    @RequestParam(value = "articleReleaseDate",required = false,defaultValue = "") String articleReleaseDate,
                                    @RequestParam(value = "articlePublisher",required = false,defaultValue = "") String articlePublisher,
                                    @RequestParam(value = "articleAbstract",required = false,defaultValue = "") String articleAbstract,
                                    @RequestParam(value = "articleContent",required = false,defaultValue = "") String articleContent,
                                    @RequestParam(value = "articleEnclosure",required = false) MultipartFile articleEnclosure,
                                    @RequestParam(value = "articleReserve1",required = false,defaultValue = "") String articleReserve1,
                                    @RequestParam(value = "articleReserve2",required = false,defaultValue = "") String articleReserve2,
                                    @RequestParam(value = "articleReserve3",required = false,defaultValue = "") String articleReserve3,
                                    @RequestParam(value = "articleSortNumber",required = false) Integer articleSortNumber

    ){
        System.out.println("updateArticle----C");
        Article oldArticle = articleService.selectArticleByArticleId(articleId);
        String name1x=System.currentTimeMillis()+"";
        String name2x=System.currentTimeMillis()+1+"";
        Article article=new Article();
        article.setArticleId(articleId);
        article.setArticleTitle(articleTitle);
        article.setArticleDescription(articleDescription);
        article.setArticleKeyword(articleKeyword);
        //修改封面
        System.out.println("修改封面");
        System.out.println("articleCoverPhoto.getOriginalFilename()="+articleCoverPhoto.getOriginalFilename());
        article.setArticleCoverPhoto("D:/upload/pic2/"+name1x+articleCoverPhoto.getOriginalFilename());
        article.setArticleSource(articleSource);
        System.out.println("articleReleaseDate="+articleReleaseDate);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date date = simpleDateFormat.parse(articleReleaseDate, pos);
        article.setArticleReleaseDate(date);
        article.setArticlePublisher(articlePublisher);
        article.setArticleAbstract(articleAbstract);
        article.setArticleContent(articleContent);
        //修改附件
        System.out.println("修改附件");
        System.out.println("articleEnclosure.getOriginalFilename()="+articleEnclosure.getOriginalFilename());
        article.setArticleEnclosure("D:/upload/file/"+name2x+articleEnclosure.getOriginalFilename());
        article.setArticleReserve1(articleReserve1);
        article.setArticleReserve2(articleReserve2);
        article.setArticleReserve3(articleReserve3);
        article.setArticleSortNumber(articleSortNumber);
        System.out.println(article.toString());
        Integer i=articleService.updateArticle(article);
        if(i>0){
            //修改文件表
            Integer j1=uploadFileService.updateUploadFileByArticleId(articleId,"pic2","D:/upload/pic2/"+name1x+articleCoverPhoto.getOriginalFilename());
            if(j1>0){
                //保存封面
                System.out.println("修改文件表");
                if(!articleCoverPhoto.isEmpty()){
                    System.out.println("删除原封面");
                    File file=new File(oldArticle.getArticleCoverPhoto());
                    if(file.exists()&&file.isFile()) {
                        file.delete();
                    }
                    System.out.println("保存封面");
                    saveFile(articleCoverPhoto,"D:/upload","pic2",name1x);
                }
            }
            //修改文件表
            Integer j2=uploadFileService.updateUploadFileByArticleId(articleId,"file","D:/upload/file/"+name2x+articleEnclosure.getOriginalFilename());
            if(j2>0){
                //保存附件
                System.out.println("修改文件表");
                if(!articleEnclosure.isEmpty()){
                    System.out.println("删除原附件");
                    File file=new File(oldArticle.getArticleEnclosure());
                    if(file.exists()&&file.isFile()) {
                        file.delete();
                    }
                    System.out.println("保存附件");
                    saveFile(articleEnclosure,"D:/upload","file",name2x);
                }
            }
            System.out.println("成功");
            return AjaxResult.success();
        }else {
            System.out.println("失败");
            return AjaxResult.error();
        }

    };
    @ApiOperation(value = "按类别查询文章",notes = "按类别查询文章",httpMethod = "POST",produces = "application/json")
    @ApiImplicitParam(name="articleType",value = "文章类别",paramType = "query",dataType = "String")
    @RequestMapping(value = "/selectArticlesByType",method = {RequestMethod.POST})
    @ResponseBody
    public List<Article> selectArticlesByType(@RequestParam("articleType") String articleType){
        System.out.println("selectArticlesByType----C");
        System.out.println("articleType="+articleType);
        System.out.println(request.getLocalAddr()+":"+request.getLocalPort());
        List<Article> list=articleService.selectArticlesByType(articleType);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setArticleCoverPhoto("http://"+request.getLocalAddr()+":"+request.getLocalPort()+"/virtualFiles"
                    +list.get(i).getArticleCoverPhoto().substring(9));
            list.get(i).setArticleEnclosure("http://"+request.getLocalAddr()+":"+request.getLocalPort()+"/virtualFiles"
                    +list.get(i).getArticleEnclosure().substring(9));
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getArticleReleaseDate());
        }
        return list;
    }

    @ApiOperation(value = "条件查询文章",notes = "条件查询文章",httpMethod = "POST",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name="articleTitle",value = "文章标题",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articleSource",value = "文章来源",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="date1",value = "起始时间",paramType = "query",dataType = "string"),
            @ApiImplicitParam(name="date2",value = "结束时间",paramType = "query",dataType = "string"),
            @ApiImplicitParam(name="articlePublisher",value = "发布人",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articleContent",value = "文章内容",paramType = "query",dataType = "String"),
            @ApiImplicitParam(name="articleType",value = "文章类别",paramType = "query",dataType = "String")
    })
    @RequestMapping(value = "/selectArticlesByStrs",method = {RequestMethod.POST})
    @ResponseBody
    public List<Article> selectArticlesByStrs(@RequestParam(value = "articleTitle",required = false,defaultValue = "") String articleTitle,
                                              @RequestParam(value = "articleSource",required = false,defaultValue = "") String articleSource,
                                              @RequestParam(value = "date1",required = false,defaultValue = "") String date1,
                                              @RequestParam(value = "date2",required = false,defaultValue = "") String date2,
                                              @RequestParam(value = "articlePublisher",required = false,defaultValue = "") String articlePublisher,
                                              @RequestParam(value = "articleContent",required = false,defaultValue = "") String articleContent,
                                              @RequestParam(value = "articleType",required = false) String articleType


    ){
        System.out.println("selectArticlesByStrs----C");
        Article article=new Article();
        article.setArticleTitle(articleTitle);
        article.setArticleSource(articleSource);
        //String转date
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos1 = new ParsePosition(0);
        Date date1x = simpleDateFormat1.parse(date1, pos1);
        article.setDate1(date1x);
        //String转date
        SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos2 = new ParsePosition(0);
        Date date2x = simpleDateFormat2.parse(date2, pos2);
        article.setDate2(date2x);
        article.setArticlePublisher(articlePublisher);
        article.setArticleContent(articleContent);
        article.setArticleType(articleType);
        System.out.println(article.toString());
        List<Article> articles=articleService.selectArticlesByStrs(article);
        for (int i = 0; i < articles.size(); i++) {
            System.out.println(articles.get(i).getArticleReleaseDate());
        }
        return articles;
    };

    private boolean saveFile(MultipartFile file, String path , String type ,String pre) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                System.out.println(path);
                System.out.println(type);
                System.out.println(pre);
                File filepath = new File(path+"/"+type);
                if (!filepath.exists()) {
                    filepath.mkdirs();
                }
                // 文件保存路径
                String savePath = path +"/"+ type +"/"+ pre + file.getOriginalFilename();
                System.out.println(savePath);
                // 转存文件
                file.transferTo(new File(savePath));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}

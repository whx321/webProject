/**
 * UploadFileController.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-11-13 9:11
 */
package com.controller;

import com.common.AjaxResult;
import com.pojo.Article;
import com.pojo.ProductInfo;
import com.pojo.UploadFile;
import com.service.ArticleService;
import com.service.ProductService;
import com.service.UploadFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * 文件的说明
 *
 * @author 杨凯
 * @version 1.0.0, 2018-11-13 9:11
 **/
@Controller
@RequestMapping("/uploadFile")
@Api(tags = "素材管理")
public class UploadFileController {
    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ProductService productService;
    @Autowired
    protected HttpServletRequest request;

    @ApiOperation(value = "添加文件",notes = "添加文件",httpMethod = "POST",produces = "application/json")
    @RequestMapping(value = "/addFile",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult addFile(@RequestParam(required = false) MultipartFile file,
                              @RequestParam(required = false) Integer articleId,
                              @RequestParam(required = false) Integer productId,
                              @RequestParam(required = false,defaultValue = "") String fileType){
        System.out.println("addFile----C");
        String name1x=System.currentTimeMillis()+"";
        String path="D:/upload";
        //保存图片数据库表
        UploadFile uploadFile =new UploadFile();
        uploadFile.setArticleId(articleId);
        uploadFile.setArticleId(productId);
        uploadFile.setFileUrl(path+"/"+fileType+"/"+name1x+file.getOriginalFilename());
        uploadFile.setFileType(fileType);
        System.out.println("添加表记录");
        int i=uploadFileService.addUploadFile(uploadFile);
        if(i>0){
            //保存文件
            System.out.println("保存文件");
            System.out.println(fileType);
            saveFile(file, path, fileType, name1x);
            System.out.println("添加成功");
            return AjaxResult.success();
        }
        else {
            System.out.println("添加文件表失败");
            return AjaxResult.error();
        }
    }
    @ApiOperation(value = "查询所有文件",notes = "查询所有文件",httpMethod = "POST",produces = "application/json")
    @RequestMapping(value = "/selectUploadFiles",method = {RequestMethod.POST})
    @ResponseBody
    public List<UploadFile> selectUploadFiles(){
        List<UploadFile> list=uploadFileService.selectUploadFiles();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setFileUrl("http://"+request.getLocalAddr()+":"+request.getLocalPort()+"/virtualFiles"
                    +list.get(i).getFileUrl().substring(9));
        }
        return list;
    };
    @ApiOperation(value = "更新文件",notes = "更新文件",httpMethod = "POST",produces = "application/json")
    @RequestMapping(value = "/updateUploadFile",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult updateUploadFile(@RequestParam(required = false) Integer fileId,
                                       @RequestParam(required = false) MultipartFile file,
                                       @RequestParam(required = false) Integer articleId,
                                       @RequestParam(required = false) Integer productId,
                                       @RequestParam(required = false) String fileType

    ){
        System.out.println("updateUploadFile-------------C");
        String name1x=System.currentTimeMillis()+"";
        UploadFile uploadFilex=uploadFileService.selectUploadFileByFileId(fileId);
        UploadFile uploadFile=new UploadFile();
        uploadFile.setFileId(fileId);
        uploadFile.setArticleId(articleId);
        uploadFile.setProductId(productId);
        uploadFile.setFileUrl("D:/upload/"+fileType+"/"+name1x+file.getOriginalFilename());
        uploadFile.setFileType(fileType);
        System.out.println("修改文件表");
        System.out.println(uploadFile.toString());
        Integer i=uploadFileService.updateUploadFile(uploadFile);
        if(i>0){
            if(!file.isEmpty()){
                File filex=new File(uploadFilex.getFileUrl());
                if(filex.exists()&&filex.isFile()){
                    System.out.println("删除文件");
                    filex.delete();
                }
                System.out.println("增加文件");
                saveFile(file,"D:/upload",fileType, name1x);
            }
            System.out.println("修改成功");
            return AjaxResult.success();
        }else {
            System.out.println("修改文件表失败");
            return AjaxResult.error();
        }

    };
    @ApiOperation(value = "删除文件",notes = "删除文件",httpMethod = "POST",produces = "application/json")
    @RequestMapping(value = "/deleteUploadFileById",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult deleteUploadFileById(@RequestParam(required = false) Integer[] fileIds
    ){
        System.out.println("deleteUploadFileById-----------C");
        for (int i = 0; i < fileIds.length; i++) {
            System.out.println(fileIds[i]);
            UploadFile uploadFilex=uploadFileService.selectUploadFileByFileId(fileIds[i]);
            Integer articleId=uploadFilex.getArticleId();
            Integer productId=uploadFilex.getProductId();
            String fileUrl=uploadFilex.getFileUrl();
            System.out.println(articleId);
            System.out.println(productId);
            System.out.println(fileUrl);
            Article article=articleService.selectArticleByArticleId(articleId);
            ProductInfo productInfo=productService.selectOneProductByProductId(productId);
            if(!(article==null)&&fileUrl.equals(article.getArticleCoverPhoto())){
                System.out.println("文章封面正在使用");
                return AjaxResult.error(-1,"文章封面正在使用");
            }
            if(!(article==null)&&fileUrl.equals(article.getArticleEnclosure())){
                System.out.println("文章附件正在使用");
                return AjaxResult.error(-1,"文章附件正在使用");
            }
            if(!(productInfo==null)&&fileUrl.equals(productInfo.getProductPicture())){
                System.out.println("产品图片正在使用()");
                return AjaxResult.error(-1,"产品图片正在使用");
            }
            uploadFileService.deleteUploadFileById(fileIds[i]);
            System.out.println(uploadFilex.getFileUrl());
            File file=new File(uploadFilex.getFileUrl());
            if(file.exists()&&file.isFile()){
                System.out.println("删除文件");
                file.delete();
            }
        }
        System.out.println("删除成功");
        return AjaxResult.success();
    };

    private boolean saveFile(MultipartFile file, String path , String type ,String pre) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                System.out.println(path);
                System.out.println(type);
                System.out.println(pre);
                System.out.println(path+"/"+type);
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
        System.out.println("文件为空");
        return false;
    }

}

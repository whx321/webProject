/**
 * ProductController.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-11-06 14:05
 */
package com.controller;

import com.common.AjaxResult;
import com.pojo.ProductInfo;
import com.pojo.UploadFile;
import com.service.ProductService;
import com.service.UploadFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-11-06 14:05
 * @Description
 **/
@Controller
@RequestMapping("/product")
@Api(tags = "产品管理")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private HttpServletRequest request;

    @ApiOperation(value = "查询所有产品",notes = "查询",httpMethod = "POST")
    @RequestMapping(value = "/selectAllProducts",method={RequestMethod.POST})
    @ResponseBody
    public List<ProductInfo> selectAllProducts(){
        System.out.println("selectAllProducts----------C");
        List<ProductInfo> list = productService.selectAllProduct();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setProductPicture("http://"+request.getLocalAddr()+":"+request.getLocalPort()+"/virtualFiles"
                    +list.get(i).getProductPicture().substring(9));
        }
        return list;
    }
    @ApiOperation(value = "产品添加",notes = "产品添加",httpMethod = "POST")
    @RequestMapping(value="/addProduct",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult addProduct(@RequestParam(required = false,defaultValue = "") String productName,
                                 @RequestParam(required = false) MultipartFile productPicture,
                                 @RequestParam(required = false,defaultValue = "") String productDescrible,
                                 @RequestParam(required = false,defaultValue = "") String productContent
    ) {
        System.out.println("addProduct----------C");
        if(productService.selectProductByProductName(productName)>0){
            return AjaxResult.error(-1,"产品名称已存在，产品重复");
        }
        String name1x=System.currentTimeMillis()+"";
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductName(productName);
        productInfo.setProductPicture("D:/upload/pic1/"+name1x+productPicture.getOriginalFilename());
        productInfo.setProductDescrible(productDescrible);
        productInfo.setProductContent(productContent);
        System.out.println(productInfo.toString());
        Integer i=productService.addProduct(productInfo);
        if(i>0){
            UploadFile uploadFile=new UploadFile();
            uploadFile.setFileType("pic1");
            uploadFile.setFileUrl("D:/upload/pic1/"+name1x+productPicture.getOriginalFilename());
            uploadFile.setProductId(productService.selectProductIdByTheLast());
            //添加文件表
            uploadFileService.addUploadFile(uploadFile);
            //添加服务器文件
            saveFile(productPicture,"D:/upload","pic1",name1x);
            System.out.println("添加成功");
            return AjaxResult.success();
        }else {
            return AjaxResult.error();
        }


    }

    @ApiOperation(value = "产品删除",notes = "产品删除",httpMethod = "POST")
    @RequestMapping(value="/deleteProductByProductId",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult deleteProductByProductId(@RequestParam Integer[] productId){
        System.out.println("deleteProductByProductId----------C");
        int i=0;
        for (int k=0;k<productId.length;k++){
            System.out.println("删除文章表");
            productService.deleteProductByProductId(productId[k]);
            List<UploadFile> uploadFiles=uploadFileService.selectUploadFileByProductId(productId[k]);
            System.out.println("删除文件表");
            uploadFileService.deleteUploadFileByProductId(productId[k]);
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
    }

    @ApiOperation(value = "产品修改",notes = "修改",httpMethod = "POST")
    @RequestMapping(value="/updateProduct",method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult updateProduct(@RequestParam(required = false) Integer productId,
                                    @RequestParam(required = false) String productName,
                                    @RequestParam(required = false) MultipartFile productPicture,
                                    @RequestParam(required = false) String productDescrible,
                                    @RequestParam(required = false) String productContent
    ){
        System.out.println("updateProduct----------C");
        String name1x=System.currentTimeMillis()+"";
        ProductInfo productInfo1 = productService.selectOneProductByProductId(productId);
        String deleteUrl=productInfo1.getProductPicture();
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId(productId);
        productInfo.setProductName(productName);
        productInfo.setProductPicture("D:/upload/pic1/"+name1x+productPicture.getOriginalFilename());
        productInfo.setProductDescrible(productDescrible);
        productInfo.setProductContent(productContent);
        System.out.println(productInfo.toString());
        System.out.println("修改产品表");
        Integer i=productService.updateProduct(productInfo);
        if(i>0){
            System.out.println("修改文件表");
            Integer j=uploadFileService.updateUploadFileByProductId(productId,"pic1","D:/upload/pic1/"+name1x+productPicture.getOriginalFilename());
            //添加服务器文件
            if(j>0){
                if(!productPicture.isEmpty()){
                    System.out.println("删除原附件");
                    File file=new File(deleteUrl);
                    if(file.exists()&&file.isFile()) {
                        file.delete();
                    }
                    System.out.println("添加服务器文件");
                    saveFile(productPicture,"D:/upload","pic1",name1x);
                }
                System.out.println("修改成功");
                return AjaxResult.success();
            }else {
                return AjaxResult.error();
            }
        }else{
            return AjaxResult.error();
        }

    }

    private boolean saveFile(MultipartFile file, String path , String type ,String pre) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                File filepath = new File(path+"/"+type);
                if (!filepath.exists()) {
                    filepath.mkdirs();
                }
                // 文件保存路径
                String savePath = path +"/"+ type +"/"+ pre + file.getOriginalFilename();
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

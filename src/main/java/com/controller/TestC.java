/**
 * TestC.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-11-15 19:36
 */
package com.controller;

import com.common.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 文件的说明
 *
 * @author 杨凯
 * @version 1.0.0, 2018-11-15 19:36n
 **/
@Controller
@RequestMapping("/TestC")
public class TestC {
    @RequestMapping(value = "/ttt",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult ttt(@RequestParam MultipartFile file){

        String name1x=System.currentTimeMillis()+"";
        String path="D:/uuuuuuuuu";
        saveFile(file,path,"pic",name1x);
        return AjaxResult.success();
    }

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

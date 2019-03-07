/**
 * T1.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-11-15 16:28
 */
package com.test;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件的说明
 *
 * @author 杨凯
 * @version 1.0.0, 2018-11-15 16:28
 **/
public class T1 {
    public static void main(String[] args) {
        String name1x=System.currentTimeMillis()+"";
        String name2x=System.currentTimeMillis()+1+"";
        System.out.println(name1x);
        System.out.println(name2x);

        String path="D/uuuuuuuuu";
       // saveFile(file,path,"pic");
    }
    private static boolean saveFile(MultipartFile file, String path , String type) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                System.out.println(path.substring(0,10)+type);
                File filepath = new File(path.substring(0,10)+type);
                if (!filepath.exists()) {
                    filepath.mkdirs();
                }
                // 文件保存路径
                String savePath = path + file.getOriginalFilename();
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

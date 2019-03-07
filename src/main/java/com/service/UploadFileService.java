/**
 * PictureService.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-11-07 15:45
 */
package com.service;

import com.pojo.UploadFile;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文件的说明
 *
 * @author 杨凯
 * @version 1.0.0, 2018-11-07 15:45
 **/
@Repository
public interface UploadFileService {
    Integer addUploadFile(UploadFile uploadFile);
    Integer deleteUploadFileByArticleId(Integer articleId);
    Integer deleteUploadFileByProductId(Integer productId);
    Integer deleteUploadFileById(Integer fileId);
    Integer updateUploadFileByArticleId(Integer articleId,String fileType,String fileUrl);
    Integer updateUploadFileByProductId(Integer productId,String fileType,String fileUrl);
    Integer updateUploadFile(UploadFile uploadFile);
    List<UploadFile> selectUploadFileByArticleId(Integer articleId);
    List<UploadFile> selectUploadFileByProductId(Integer productId);
    UploadFile selectUploadFileByFileId(Integer fileId);
    Integer selectUploadFileByTheLast();
    List<UploadFile> selectUploadFiles();
}

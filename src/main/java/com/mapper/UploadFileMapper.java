/**
 * PictrueMapper.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-11-07 14:46
 */
package com.mapper;

import com.pojo.UploadFile;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文件的说明
 *
 * @author 杨凯
 * @version 1.0.0, 2018-11-07 14:46
 **/
@Repository
public interface UploadFileMapper {
    Integer addUploadFile(UploadFile uploadFile);
    Integer updateUploadFileByArticleId(@Param("articleId") Integer articleId, @Param("fileType") String fileType, @Param("fileUrl") String fileUrl);
    Integer updateUploadFileByProductId(@Param("productId") Integer productId, @Param("fileType") String fileType, @Param("fileUrl") String fileUrl);
    Integer updateUploadFile(UploadFile uploadFile);
    Integer deleteUploadFileByArticleId(Integer articleId);
    Integer deleteUploadFileByProductId(Integer productId);
    Integer deleteUploadFileById(Integer fileId);
    List<UploadFile> selectUploadFileByArticleId(Integer articleId);
    List<UploadFile> selectUploadFileByProductId(Integer productId);
    UploadFile selectUploadFileByFileId(Integer fileId);
    Integer selectUploadFileByTheLast();
    List<UploadFile> selectUploadFiles();
}

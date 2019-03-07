/**
 * PictureServiceImpl.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-11-07 15:47
 */
package com.service.impl;

import com.mapper.UploadFileMapper;
import com.pojo.UploadFile;
import com.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文件的说明
 *
 * @author 杨凯
 * @version 1.0.0, 2018-11-07 15:47
 **/
@Service
public class UploadFileServiceImpl implements UploadFileService {
    @Autowired
    private UploadFileMapper uploadFileMapper;

    @Override
    public Integer addUploadFile(UploadFile uploadFile) {
        return uploadFileMapper.addUploadFile(uploadFile);
    }

    @Override
    public Integer deleteUploadFileByArticleId(Integer articleId) {
        return uploadFileMapper.deleteUploadFileByArticleId(articleId);
    }

    @Override
    public Integer deleteUploadFileByProductId(Integer productId) {
        return uploadFileMapper.deleteUploadFileByProductId(productId);
    }

    @Override
    public Integer deleteUploadFileById(Integer fileId) {
        return uploadFileMapper.deleteUploadFileById(fileId);
    }

    @Override
    public Integer updateUploadFileByArticleId(Integer articleId, String fileType, String fileUrl) {
        return uploadFileMapper.updateUploadFileByArticleId(articleId,fileType,fileUrl);
    }

    @Override
    public Integer updateUploadFileByProductId(Integer productId, String fileType, String fileUrl) {
        return uploadFileMapper.updateUploadFileByProductId(productId,fileType,fileUrl);
    }

    @Override
    public Integer updateUploadFile(UploadFile uploadFile) {
        return uploadFileMapper.updateUploadFile(uploadFile);
    }

    @Override
    public List<UploadFile> selectUploadFileByArticleId(Integer articleId) {
        return uploadFileMapper.selectUploadFileByArticleId(articleId);
    }

    @Override
    public List<UploadFile> selectUploadFileByProductId(Integer productId) {
        return uploadFileMapper.selectUploadFileByProductId(productId);
    }

    @Override
    public UploadFile selectUploadFileByFileId(Integer fileId) {
        return uploadFileMapper.selectUploadFileByFileId(fileId);
    }

    @Override
    public Integer selectUploadFileByTheLast() {
        return uploadFileMapper.selectUploadFileByTheLast();
    }

    @Override
    public List<UploadFile> selectUploadFiles() {
        return uploadFileMapper.selectUploadFiles();
    }

}

/**
 * Picture.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-11-07 14:43
 */
package com.pojo;

/**
 * 文件的说明
 *
 * @author 杨凯
 * @version 1.0.0, 2018-11-07 14:43
 **/
public class UploadFile {
    private Integer fileId;
    private Integer articleId;
    private Integer productId;
    private String fileUrl;
    private String fileType;

    public UploadFile() {
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "UploadFile{" +
                "fileId=" + fileId +
                ", articleId=" + articleId +
                ", productId=" + productId +
                ", fileUrl='" + fileUrl + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}

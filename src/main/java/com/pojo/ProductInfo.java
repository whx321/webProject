/**
 * ProductInfo.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-11-06 13:30
 */
package com.pojo;

public class ProductInfo {
    private Integer productId;
    private String productName;
    private String productPicture;
    private String productDescrible;
    private String productContent;

    public ProductInfo() {
    }
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(String productPicture) {
        this.productPicture = productPicture;
    }

    public String getProductDescrible() {
        return productDescrible;
    }

    public void setProductDescrible(String productDescrible) {
        this.productDescrible = productDescrible;
    }

    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPicture='" + productPicture + '\'' +
                ", productDescrible='" + productDescrible + '\'' +
                ", productContent='" + productContent + '\'' +
                '}';
    }
}

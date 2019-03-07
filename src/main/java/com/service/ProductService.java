/**
 * ProductService.interface
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-11-06 14:02
 */
package com.service;

import com.pojo.ProductInfo;

import java.util.List;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-11-06 14:02
 * @Description
 **/
public interface ProductService {
    ProductInfo selectOneProductByProductId(Integer productId);

    List<ProductInfo> selectAllProduct();

    Integer addProduct(ProductInfo productInfo);

    Integer updateProduct(ProductInfo productInfo);

    Integer deleteProductByProductId(Integer productId);

    Integer selectProductIdByTheLast();

    Integer selectProductByProductName(String productName);
}

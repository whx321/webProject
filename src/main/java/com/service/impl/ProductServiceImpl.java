/**
 * ProductServiceImpl.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-11-06 14:02
 */
package com.service.impl;

import com.mapper.ProductMapper;
import com.pojo.ProductInfo;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-11-06 14:02
 * @Description
 **/
@Service()
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductInfo selectOneProductByProductId(Integer productId) {
        return productMapper.selectOneProductByProductId(productId);
    }
    @Override
    public List<ProductInfo> selectAllProduct() {
        return productMapper.selectAllProduct();
    }

    @Override
    public Integer addProduct(ProductInfo productInfo) {
        return productMapper.addProduct(productInfo);
    }

    @Override
    public Integer updateProduct(ProductInfo productInfo) {
        return productMapper.updateProduct(productInfo);
    }

    @Override
    public Integer deleteProductByProductId(Integer productId) {
        return productMapper.deleteProductByProductId(productId);
    }

    @Override
    public Integer selectProductIdByTheLast() {
        return productMapper.selectProductIdByTheLast();
    }

    @Override
    public Integer selectProductByProductName(String productName) {
        return productMapper.selectProductByProductName(productName);
    }
}

/**
 * ArticleServiceImpl.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-10-24 10:38
 */
package com.service.impl;

import com.pojo.Article;
import com.service.ArticleService;
import com.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 文件的说明
 *
 * @author 杨凯
 * @version 1.0.0, 2018-10-24 10:38
 **/
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public Integer addArticle(Article article) {
        return articleMapper.addArticle(article);
    }

    @Override
    public Integer deleteArticleById(Integer articleId) {
        return articleMapper.deleteArticleById(articleId);
    }

    @Override
    public Integer updateArticle(Article article) {
        return articleMapper.updateArticle(article);
    }

    @Override
    public Article selectArticleByArticleId(Integer articleId) {
        return articleMapper.selectArticleByArticleId(articleId);
    }

    @Override
    public List<Article> selectArticlesByStrs(Article article) {
        return articleMapper.selectArticlesByStrs(article);
    }

    @Override
    public List<Article> selectArticlesByType(String articleType) {
        return articleMapper.selectArticlesByType(articleType);
    }

    @Override
    public Integer selectArticleIdByTheLast() {
        return articleMapper.selectArticleIdByTheLast();
    }

    @Override
    public Integer selectArticleByArticleTitle(String articleTitle) {
        return articleMapper.selectArticleByArticleTitle(articleTitle);
    }
}

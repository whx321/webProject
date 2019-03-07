/**
 * ArticleService.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-10-24 10:37
 */
package com.service;

import com.pojo.Article;

import java.util.Date;
import java.util.List;

/**
 * 文件的说明
 *
 * @author 杨凯
 * @version 1.0.0, 2018-10-24 10:37
 **/
public interface ArticleService {
    Integer addArticle(Article article);
    Integer deleteArticleById(Integer articleId);
    Integer updateArticle(Article article);
    Article selectArticleByArticleId(Integer articleId);
    List<Article> selectArticlesByStrs(Article article);
    List<Article> selectArticlesByType(String articleType);
    Integer selectArticleIdByTheLast();
    Integer selectArticleByArticleTitle(String articleTitle);
}

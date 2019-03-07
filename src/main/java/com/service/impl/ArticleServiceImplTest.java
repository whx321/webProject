package com.service.impl;

import com.mapper.ArticleMapper;
import com.pojo.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@WebAppConfiguration
public class ArticleServiceImplTest {
    @Autowired
    private ArticleMapper articleMapper;
    @Test
    public void addArticle() {
    }

    @Test
    public void deleteArticleById() {
    }

    @Test
    public void updateArticle() {
        Article article=new Article();
        article.setArticleId(7);
        article.setArticleTitle("11111");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date date = simpleDateFormat.parse("2018-09-06", pos);
        article.setArticleReleaseDate(date);
        article.setArticleSource("2222");
        articleMapper.updateArticle(article);
    }

    @Test
    public void selectArticleByArticleId() {
    }

    @Test
    public void selectArticlesByStrs() {
    }

    @Test
    public void selectArticlesByType() {
    }

    @Test
    public void selectArticleIdByTheLast() {
    }
}
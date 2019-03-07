/**
 * Article.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-10-24 10:05
 */
package com.pojo;

import java.util.Date;

/**
 * 文件的说明
 *
 * @author 杨凯
 * @version 1.0.0, 2018-10-24 10:05
 **/
public class Article extends BaseEntity{
    private Integer articleId;
    private String articleTitle;
    private String articleDescription;
    private String articleKeyword;
    private String articleCoverPhoto;
    private String articleSource;
    private Date articleReleaseDate;
    private String articlePublisher;
    private String articleAbstract;
    private String articleContent;
    private String articleEnclosure;
    private String articleReserve1;
    private String articleReserve2;
    private String articleReserve3;
    private Integer articleSortNumber;
    private String articleType;
    private Date date1;
    private Date date2;

    public Article() {
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    public String getArticleKeyword() {
        return articleKeyword;
    }

    public void setArticleKeyword(String articleKeyword) {
        this.articleKeyword = articleKeyword;
    }

    public String getArticleCoverPhoto() {
        return articleCoverPhoto;
    }

    public void setArticleCoverPhoto(String articleCoverPhoto) {
        this.articleCoverPhoto = articleCoverPhoto;
    }

    public String getArticleSource() {
        return articleSource;
    }

    public void setArticleSource(String articleSource) {
        this.articleSource = articleSource;
    }

    public Date getArticleReleaseDate() {
        return articleReleaseDate;
    }

    public void setArticleReleaseDate(Date articleReleaseDate) {
        this.articleReleaseDate = articleReleaseDate;
    }

    public String getArticlePublisher() {
        return articlePublisher;
    }

    public void setArticlePublisher(String articlePublisher) {
        this.articlePublisher = articlePublisher;
    }

    public String getArticleAbstract() {
        return articleAbstract;
    }

    public void setArticleAbstract(String articleAbstract) {
        this.articleAbstract = articleAbstract;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleEnclosure() {
        return articleEnclosure;
    }

    public void setArticleEnclosure(String articleEnclosure) {
        this.articleEnclosure = articleEnclosure;
    }

    public String getArticleReserve1() {
        return articleReserve1;
    }

    public void setArticleReserve1(String articleReserve1) {
        this.articleReserve1 = articleReserve1;
    }

    public String getArticleReserve2() {
        return articleReserve2;
    }

    public void setArticleReserve2(String articleReserve2) {
        this.articleReserve2 = articleReserve2;
    }

    public String getArticleReserve3() {
        return articleReserve3;
    }

    public void setArticleReserve3(String articleReserve3) {
        this.articleReserve3 = articleReserve3;
    }

    public Integer getArticleSortNumber() {
        return articleSortNumber;
    }

    public void setArticleSortNumber(Integer articleSortNumber) {
        this.articleSortNumber = articleSortNumber;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleDescription='" + articleDescription + '\'' +
                ", articleKeyword='" + articleKeyword + '\'' +
                ", articleCoverPhoto='" + articleCoverPhoto + '\'' +
                ", articleSource='" + articleSource + '\'' +
                ", articleReleaseDate=" + articleReleaseDate +
                ", articlePublisher='" + articlePublisher + '\'' +
                ", articleAbstract='" + articleAbstract + '\'' +
                ", articleContent='" + articleContent + '\'' +
                ", articleEnclosure='" + articleEnclosure + '\'' +
                ", articleReserve1='" + articleReserve1 + '\'' +
                ", articleReserve2='" + articleReserve2 + '\'' +
                ", articleReserve3='" + articleReserve3 + '\'' +
                ", articleSortNumber='" + articleSortNumber + '\'' +
                ", articleType='" + articleType + '\'' +
                ", date1=" + date1 +
                ", date2=" + date2 +
                '}';
    }
}

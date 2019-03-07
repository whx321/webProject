/**
 * LinkInfo.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-11-06 15:47
 */
package com.pojo;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-11-06 15:47
 * @Description
 **/
public class LinkInfo {
    private Integer linkId;
    private String linkname;
    private String link_url;
    private Integer link_order;

    public LinkInfo() {
    }

    public LinkInfo(Integer linkId, String linkname, String link_url, Integer link_order) {
        this.linkId = linkId;
        this.linkname = linkname;
        this.link_url = link_url;
        this.link_order = link_order;
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getLinkname() {
        return linkname;
    }

    public void setLinkname(String linkname) {
        this.linkname = linkname;
    }

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }

    public Integer getLink_order() {
        return link_order;
    }

    public void setLink_order(Integer link_order) {
        this.link_order = link_order;
    }
}

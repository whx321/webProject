/**
 * LinkService.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-11-06 16:11
 */
package com.service;

import com.pojo.LinkInfo;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-11-06 16:11
 * @Description
 **/
public interface LinkService {
    LinkInfo[] selectAllLink();

    Integer addLink(LinkInfo linkInfo);

    Integer updateLink(LinkInfo linkInfo);

    Integer deleteLink(Integer linkId);
}

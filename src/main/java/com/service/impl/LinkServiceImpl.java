/**
 * LinkServiceImpl.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-11-06 16:11
 */
package com.service.impl;

import com.mapper.LinkMapper;
import com.pojo.LinkInfo;
import com.service.LinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-11-06 16:11
 * @Description
 **/
@Service("linkService")
public class LinkServiceImpl implements LinkService {

    @Resource
    private LinkMapper linkMapper;

    @Override
    public LinkInfo[] selectAllLink() {
        return linkMapper.selectAllLink();
    }

    @Override
    public Integer addLink(LinkInfo linkInfo) {
        return linkMapper.addLink(linkInfo);
    }

    @Override
    public Integer updateLink(LinkInfo linkInfo) {
        return linkMapper.updateLink(linkInfo);
    }

    @Override
    public Integer deleteLink(Integer linkId) {
        return linkMapper.deleteLink(linkId);
    }
}

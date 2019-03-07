/**
 * LinkMapper.interface
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-11-06 16:06
 */
package com.mapper;


import com.pojo.LinkInfo;
import org.springframework.stereotype.Repository;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-11-06 16:06
 * @Description
 **/
@Repository
public interface LinkMapper {

    LinkInfo[] selectAllLink();

    Integer addLink(LinkInfo linkInfo);

    Integer updateLink(LinkInfo linkInfo);

    Integer deleteLink(Integer linkId);
}

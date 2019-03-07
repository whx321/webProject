/**
 * LoginFilter.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-10-23 13:42
 */
package com.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-10-23 13:42
 * @Description
 **/
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse hsr = (HttpServletResponse) servletResponse;
        hsr.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        hsr.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        hsr.setDateHeader("Expires", 0);

/*      HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpSession session = request.getSession();
        Object value = session.getAttribute("user");
        if(value=="a"){
            System.out.println("我是空的=");
        }*/
        filterChain.doFilter(servletRequest,servletResponse);//放行
      //  System.out.println("我从过滤器出来了");
    }

    @Override
    public void destroy() {

    }
}

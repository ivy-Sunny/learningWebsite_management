package com.ivy.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AuthorityVerifyFilter
 *
 * @Author: ivy
 * @CreateTime: 2021-07-01
 */
@WebFilter(value = "/*")
public class AuthorityVerifyFilter implements Filter {
    private FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /**
         * 定义和协议相关的请求和响应对象
         */
        HttpServletRequest request;
        HttpServletResponse response;
        try {
            request = (HttpServletRequest) servletRequest;
            response = (HttpServletResponse) servletResponse;
            //1、获取本次操作
            //2、获取到当前登录人允许的操作
            //3、比对本次操作是否在当前登录人允许的操作范围内
            //3.1、如果允许放行
            //3.1、如果不允许跳转到非法访问页面
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {

    }
}

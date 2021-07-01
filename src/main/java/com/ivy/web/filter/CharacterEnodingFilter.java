package com.ivy.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CharacterEnodingFilter
 *
 * @Author: ivy
 * @CreateTime: 2021-06-27
 */
public class CharacterEnodingFilter implements Filter {
    private String encoding;
    private FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter((HttpServletRequest)servletRequest, (HttpServletResponse)servletResponse);
    }

    public void destroy() {

    }
}

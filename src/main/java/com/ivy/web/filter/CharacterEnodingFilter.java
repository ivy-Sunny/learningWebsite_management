package com.ivy.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * CharacterEnodingFilter
 *
 * @Author: ivy
 * @CreateTime: 2021-06-27
 */
public class CharacterEnodingFilter implements Filter {
    private String encoding;
    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding = filterConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest,servletResponse);

    }

    public void destroy() {

    }
}

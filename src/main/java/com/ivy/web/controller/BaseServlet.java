package com.ivy.web.controller;

import com.ivy.service.store.CatalogService;
import com.ivy.service.store.CompanyService;
import com.ivy.service.store.CourseService;
import com.ivy.service.store.QuestionService;
import com.ivy.service.store.impl.CatalogServiceImpl;
import com.ivy.service.store.impl.CompanyServiceImpl;
import com.ivy.service.store.impl.CourseServiceImpl;
import com.ivy.service.store.impl.QuestionServiceImpl;
import com.ivy.service.system.DeptService;
import com.ivy.service.system.UserService;
import com.ivy.service.system.impl.DeptServiceImpl;
import com.ivy.service.system.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * BaseServlet
 *
 * @Author: ivy
 * @CreateTime: 2021-06-27
 */
public class BaseServlet extends HttpServlet {
    protected CompanyService companyService;
    protected DeptService deptService;
    protected UserService userService;
    protected CourseService courseService;
    protected CatalogService catalogService;
    protected QuestionService questionService;
    @Override
    public void init() throws ServletException {
        companyService = new CompanyServiceImpl();
        deptService = new DeptServiceImpl();
        userService = new UserServiceImpl();
        courseService = new CourseServiceImpl();
        catalogService = new CatalogServiceImpl();
        questionService = new QuestionServiceImpl();
    }
}

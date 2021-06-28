package com.ivy.web.controller;

import com.ivy.service.store.*;
import com.ivy.service.store.impl.*;
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
    protected QuestionItemService questionItemService;
    @Override
    public void init() throws ServletException {
        companyService = new CompanyServiceImpl();
        deptService = new DeptServiceImpl();
        userService = new UserServiceImpl();
        courseService = new CourseServiceImpl();
        catalogService = new CatalogServiceImpl();
        questionService = new QuestionServiceImpl();
        questionItemService = new QuestionItemServiceImpl();
    }
}

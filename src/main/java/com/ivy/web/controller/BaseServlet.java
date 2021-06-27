package com.ivy.web.controller;

import com.ivy.service.store.CompanyService;
import com.ivy.service.store.impl.CompanyServiceImpl;
import com.ivy.service.system.DeptService;
import com.ivy.service.system.impl.DeptServiceImpl;

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
    @Override
    public void init() throws ServletException {
        companyService = new CompanyServiceImpl();
        deptService = new DeptServiceImpl();
    }
}

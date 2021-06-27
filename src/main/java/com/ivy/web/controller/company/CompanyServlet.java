package com.ivy.web.controller.company;

import com.github.pagehelper.PageInfo;
import com.ivy.domain.store.Company;
import com.ivy.service.store.CompanyService;
import com.ivy.service.store.impl.CompanyServiceImpl;
import com.ivy.utils.BeanUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CompanyServlet
 *
 * @Author: ivy
 * @CreateTime: 2021-06-26
 */
//uri : /store/company?operation=list
@WebServlet("/store/company")
public class CompanyServlet extends HttpServlet {
    //获取数据
    private CompanyService companyService = new CompanyServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");
        if ("list".equals(operation)) {
            this.list(req, resp);
        } else if ("toAdd".equals(operation)) {
            this.toAdd(req, resp);
        } else if ("save".equals(operation)) {

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int size = 10;
        if (StringUtils.isNotBlank(req.getParameter("page"))) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        if (StringUtils.isNotBlank(req.getParameter("size"))) {
            size = Integer.parseInt(req.getParameter("size"));
        }

        PageInfo all = companyService.findAll(page, size);
        //将数据保存到指定的位置
        req.setAttribute("page", all);
        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/store/company/list.jsp").forward(req, resp);
    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/store/company/add.jsp").forward(req, resp);
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //将数据获取到，封装成一个对象
        Company company = BeanUtil.fillBean(req, Company.class, "yyyy-MM-dd");
        //调用业务层接口save
        companyService.save(company);
        //跳转页面
        resp.sendRedirect(req.getContextPath() + "/store/company?operation=list");
    }
}

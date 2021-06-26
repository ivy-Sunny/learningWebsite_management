package com.ivy.web.controller.company;

import com.github.pagehelper.PageInfo;
import com.ivy.service.store.CompanyService;
import com.ivy.service.store.impl.CompanyServiceImpl;
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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");
        if ("list".equals(operation)){
            //获取数据
            CompanyService companyService = new CompanyServiceImpl();
            int page = 1;
            int size = 10;
            if (StringUtils.isNotBlank(req.getParameter("page"))){
                page = Integer.parseInt(req.getParameter("page"));
            }
            if (StringUtils.isNotBlank(req.getParameter("size"))){
                size = Integer.parseInt(req.getParameter("size"));
            }

            PageInfo all = companyService.findAll(page, size);
            //将数据保存到指定的位置
            req.setAttribute("page",all);
            //跳转页面
            req.getRequestDispatcher("/WEB-INF/pages/store/company/list.jsp").forward(req,resp);
        }else if ("add".equals(operation)){

        }else if ("add".equals(operation)){

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

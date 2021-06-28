package com.ivy.web.controller.store;

import com.github.pagehelper.PageInfo;
import com.ivy.domain.store.Company;
import com.ivy.utils.BeanUtil;
import com.ivy.web.controller.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
public class CompanyServlet extends BaseServlet {
    //获取数据

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");
        if ("list".equals(operation)) {
            this.list(req, resp);
        } else if ("toAdd".equals(operation)) {
            this.toAdd(req, resp);
        } else if ("save".equals(operation)) {
            this.save(req, resp);
        } else if ("toEdit".equals(operation)) {
            this.toEdit(req, resp);
        } else if ("edit".equals(operation)) {
            this.edit(req, resp);
        } else if ("delete".equals(operation)) {
            this.delete(req, resp);
        }
    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询要修改的数据
        String id = req.getParameter("id");
        Company company = companyService.findById(id);
        //将数据加载到指定区域，供页面获取
        req.setAttribute("company", company);
        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/store/company/update.jsp").forward(req, resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //将数据获取到，封装成一个对象
        Company company = BeanUtil.fillBean(req, Company.class, "yyyy-MM-dd");
        //调用业务层接口save
        companyService.update(company);
        //跳转页面
        resp.sendRedirect(req.getContextPath() + "/store/company?operation=list");
    }
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //将数据获取到，封装成一个对象
        Company company = BeanUtil.fillBean(req, Company.class, "yyyy-MM-dd");
        //调用业务层接口save
        companyService.delete(company);
        //跳转页面
        resp.sendRedirect(req.getContextPath() + "/store/company?operation=list");
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

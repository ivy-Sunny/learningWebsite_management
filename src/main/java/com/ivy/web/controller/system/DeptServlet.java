package com.ivy.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.ivy.domain.system.Dept;
import com.ivy.utils.BeanUtil;
import com.ivy.web.controller.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DeptServlet
 *
 * @Author: ivy
 * @CreateTime: 2021-06-27
 */
@WebServlet("/system/dept")
public class DeptServlet extends BaseServlet {
    //获取数据

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");
        System.out.println(operation);
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
        Dept dept = deptService.findById(id);
        //将数据加载到指定区域，供页面获取
        req.setAttribute("dept", dept);
        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/system/dept/update.jsp").forward(req, resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //将数据获取到，封装成一个对象
        Dept dept = BeanUtil.fillBean(req, Dept.class, "yyyy-MM-dd");
        //调用业务层接口save
        deptService.update(dept);
        //跳转页面
        resp.sendRedirect(req.getContextPath() + "/system/dept?operation=list");
    }
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //将数据获取到，封装成一个对象
        Dept dept = BeanUtil.fillBean(req, Dept.class, "yyyy-MM-dd");
        //调用业务层接口save
        deptService.delete(dept);
        //跳转页面
        resp.sendRedirect(req.getContextPath() + "/system/dept?operation=list");
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

        PageInfo all = deptService.findAll(page, size);
        //将数据保存到指定的位置
        req.setAttribute("page", all);
        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/system/dept/list.jsp").forward(req, resp);
    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/system/dept/add.jsp").forward(req, resp);
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //将数据获取到，封装成一个对象
        Dept dept = BeanUtil.fillBean(req, Dept.class, "yyyy-MM-dd");
        //调用业务层接口save
        deptService.save(dept);
        //跳转页面
        resp.sendRedirect(req.getContextPath() + "/system/dept?operation=list");
    }
}

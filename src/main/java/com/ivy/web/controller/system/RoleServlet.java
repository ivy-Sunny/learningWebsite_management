package com.ivy.web.controller.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.ivy.domain.system.Module;
import com.ivy.domain.system.Role;
import com.ivy.utils.BeanUtil;
import com.ivy.web.controller.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * RoleServlet
 *
 * @Author: ivy
 * @CreateTime: 2021-06-28
 */
@WebServlet("/system/role")
public class RoleServlet extends BaseServlet {
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
        } else if ("author".equals(operation)) {
            this.toAuthor(req, resp);
        } else if ("updateRoleModule".equals(operation)) {
            this.updateRoleModule(req, resp);
        }
    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询要修改的数据
        String id = req.getParameter("id");
        Role role = roleService.findById(id);
        //将数据加载到指定区域，供页面获取
        req.setAttribute("role", role);
        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/system/role/update.jsp").forward(req, resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //将数据获取到，封装成一个对象
        Role role = BeanUtil.fillBean(req, Role.class, "yyyy-MM-dd");
        //调用业务层接口save
        roleService.update(role);
        //跳转页面
        resp.sendRedirect(req.getContextPath() + "/system/role?operation=list");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //将数据获取到，封装成一个对象
        Role role = BeanUtil.fillBean(req, Role.class, "yyyy-MM-dd");
        //调用业务层接口save
        roleService.delete(role);
        //跳转页面
        resp.sendRedirect(req.getContextPath() + "/system/role?operation=list");
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

        PageInfo all = roleService.findAll(page, size);
        //将数据保存到指定的位置
        req.setAttribute("page", all);
        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/system/role/list.jsp").forward(req, resp);
    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/system/role/add.jsp").forward(req, resp);
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //将数据获取到，封装成一个对象
        Role role = BeanUtil.fillBean(req, Role.class, "yyyy-MM-dd");
        //调用业务层接口save
        roleService.save(role);
        //跳转页面
        resp.sendRedirect(req.getContextPath() + "/system/role?operation=list");
    }

    private void toAuthor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Role role = roleService.findById(id);
        req.setAttribute("role", role);
        //根据当前角色的id获取所有的模块数据，并加载关系
        List<Map> roleModule = moduleService.findAuthorDataByRoleId(id);
        ObjectMapper om = new ObjectMapper();
        String roleModuleJson = om.writeValueAsString(roleModule);
        req.setAttribute("roleModuleJson", roleModuleJson);
        req.getRequestDispatcher("/WEB-INF/pages/system/role/author.jsp").forward(req, resp);
    }

    private void updateRoleModule(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String roleId = req.getParameter("roleId");
        String moduleIds = req.getParameter("moduleIds");
        roleService.updateRoleModule(roleId, moduleIds);
        resp.sendRedirect(req.getContextPath() + "/system/role?operation=list");
    }
}

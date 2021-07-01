package com.ivy.web.controller.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.ivy.domain.system.Dept;
import com.ivy.domain.system.Module;
import com.ivy.domain.system.Role;
import com.ivy.domain.system.User;
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
 * UserServlet
 *
 * @Author: ivy
 * @CreateTime: 2021-06-27
 */
@WebServlet("/system/user")
public class UserServlet extends BaseServlet {
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
        } else if ("userRoleList".equals(operation)) {
            this.userRoleList(req, resp);
        } else if ("updateRole".equals(operation)) {
            this.updateRole(req, resp);
        } else if ("login".equals(operation)) {
            this.login(req, resp);
        } else if ("home".equals(operation)) {
            this.home(req, resp);
        } else if ("logout".equals(operation)) {
            this.logout(req, resp);
        }
    }

    private void home(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/home/home.jsp").forward(req, resp);
    }

    private List<Dept> getDeptList() {
        return deptService.findAll();
    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询要修改的数据
        String id = req.getParameter("id");
        User user = userService.findById(id);
        req.setAttribute("deptList", this.getDeptList());
        //将数据加载到指定区域，供页面获取
        req.setAttribute("user", user);
        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/system/user/update.jsp").forward(req, resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //将数据获取到，封装成一个对象
        User user = BeanUtil.fillBean(req, User.class, "yyyy-MM-dd");
        //调用业务层接口save
        userService.update(user);
        //跳转页面
        resp.sendRedirect(req.getContextPath() + "/system/user?operation=list");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //将数据获取到，封装成一个对象
        User user = BeanUtil.fillBean(req, User.class, "yyyy-MM-dd");
        //调用业务层接口save
        userService.delete(user);
        //跳转页面
        resp.sendRedirect(req.getContextPath() + "/system/user?operation=list");
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

        PageInfo all = userService.findAll(page, size);
        //将数据保存到指定的位置
        req.setAttribute("page", all);
        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/system/user/list.jsp").forward(req, resp);
    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //跳转页面
        req.setAttribute("deptList", this.getDeptList());
        req.getRequestDispatcher("/WEB-INF/pages/system/user/add.jsp").forward(req, resp);
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //将数据获取到，封装成一个对象
        User user = BeanUtil.fillBean(req, User.class, "yyyy-MM-dd");
        //调用业务层接口save
        userService.save(user);
        //跳转页面
        resp.sendRedirect(req.getContextPath() + "/system/user?operation=list");
    }

    private void userRoleList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("id");
        User user = userService.findById(userId);
        req.setAttribute("user", user);
        //获取所有的角色列表
        List<Map> allRole = roleService.findAllRoleByUserId(userId);
        req.setAttribute("roleList", allRole);
        req.getRequestDispatcher("/WEB-INF/pages/system/user/role.jsp").forward(req, resp);
    }

    private void updateRole(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = req.getParameter("userId");
        String[] roleIds = req.getParameterValues("roleIds");
        userService.updateRole(userId, roleIds);
        resp.sendRedirect(req.getContextPath() + "/system/user?operation=list");
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String email = req.getParameter("email");
        String pwd = req.getParameter("password");
        User user = userService.login(email, pwd);
        if (user != null) {
            req.getSession().setAttribute("userInfo", user);
            //如果登录成功，加载该用户角色下面对应的模块
            //根据用户查询底下的角色，再根据角色查询模块
            List<Module> moduleList = userService.findModuleById(user.getId());
            req.setAttribute("moduleList", moduleList);
            req.getRequestDispatcher("/WEB-INF/pages/home/main.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().removeAttribute("userInfo");
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}

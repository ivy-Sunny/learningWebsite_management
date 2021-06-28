package com.ivy.web.controller.store;

import com.github.pagehelper.PageInfo;
import com.ivy.domain.store.QuestionItem;
import com.ivy.utils.BeanUtil;
import com.ivy.web.controller.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * QuestionItemServlet
 *
 * @Author: ivy
 * @CreateTime: 2021-06-28
 */
@WebServlet("/store/questionItem")
public class QuestionItemServlet extends BaseServlet {
    //获取数据

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");
        System.out.println(operation);
        if ("list".equals(operation)) {
            this.list(req, resp);
        } else if ("save".equals(operation)) {
            this.save(req, resp);
        } else if ("delete".equals(operation)) {
            this.delete(req, resp);
        }
    }
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //将数据获取到，封装成一个对象
        QuestionItem questionItem = BeanUtil.fillBean(req, QuestionItem.class, "yyyy-MM-dd");
        //调用业务层接口save
        questionItemService.delete(questionItem);
        //跳转页面
        this.list(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int size = 10;
        String questionId = req.getParameter("questionId");
        if (StringUtils.isNotBlank(req.getParameter("page"))) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        if (StringUtils.isNotBlank(req.getParameter("size"))) {
            size = Integer.parseInt(req.getParameter("size"));
        }

        PageInfo all = questionItemService.findAll(questionId, 1, 100);
        System.out.println(all);
        //将数据保存到指定的位置
        req.setAttribute("page", all);
        //进入list页面时，添加对应的问题id，为添加操作使用
        req.setAttribute("questionId", questionId);
        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/store/questionItem/list.jsp").forward(req, resp);
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //将数据获取到，封装成一个对象
        Map<String, String[]> parameterMap = req.getParameterMap();

        QuestionItem questionItem = BeanUtil.fillBean(req, QuestionItem.class, "yyyy-MM-dd");
        //调用业务层接口save
        questionItemService.save(questionItem);
        //跳转页面
        this.list(req, resp);

    }
}

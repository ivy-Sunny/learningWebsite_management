package com.ivy.web.controller.store;

import com.github.pagehelper.PageInfo;
import com.ivy.domain.store.Catalog;
import com.ivy.domain.store.Company;
import com.ivy.domain.store.Question;
import com.ivy.utils.BeanUtil;
import com.ivy.web.controller.BaseServlet;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * QuestionServlet
 *
 * @Author: ivy
 * @CreateTime: 2021-06-27
 */
@WebServlet("/store/question")
public class QuestionServlet extends BaseServlet {
    //获取数据

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");
        if ("list".equals(operation)) {
            this.list(req, resp);
        } else if ("toAdd".equals(operation)) {
            this.toAdd(req, resp);
        } else if ("save".equals(operation)) {
            try {
                this.save(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("toEdit".equals(operation)) {
            this.toEdit(req, resp);
        } else if ("edit".equals(operation)) {
            this.edit(req, resp);
        } else if ("delete".equals(operation)) {
            this.delete(req, resp);
        }
    }

    private List<Company> getCompanyList() {
        return companyService.findAll();
    }

    private List<Catalog> getCatalogList() {
        return catalogService.findAll();
    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询要修改的数据
        String id = req.getParameter("id");
        Question question = questionService.findById(id);
        //将数据加载到指定区域，供页面获取
        req.setAttribute("question", question);
        req.setAttribute("companyList", this.getCompanyList());
        req.setAttribute("catalogList", this.getCatalogList());
        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/store/question/update.jsp").forward(req, resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //将数据获取到，封装成一个对象
        Question question = BeanUtil.fillBean(req, Question.class, "yyyy-MM-dd");
        //调用业务层接口save
        questionService.update(question);
        //跳转页面
        resp.sendRedirect(req.getContextPath() + "/store/question?operation=list");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //将数据获取到，封装成一个对象
        Question question = BeanUtil.fillBean(req, Question.class, "yyyy-MM-dd");
        //调用业务层接口save
        questionService.delete(question);
        //跳转页面
        resp.sendRedirect(req.getContextPath() + "/store/question?operation=list");
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

        PageInfo all = questionService.findAll(page, size);
        //将数据保存到指定的位置
        req.setAttribute("page", all);
        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/store/question/list.jsp").forward(req, resp);
    }



    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("companyList", this.getCompanyList());
        req.setAttribute("catalogList", this.getCatalogList());
        //跳转页面
        req.getRequestDispatcher("/WEB-INF/pages/store/question/add.jsp").forward(req, resp);
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1、确认该操作是否支持文件上传操作，enctype="multipart/form-data"
        if (ServletFileUpload.isMultipartContent(req)) {
            //2、创建磁盘工厂对象
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            //3、Servlet文件上传的核心对象
            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
            //4、从request中读取数据
            List<FileItem> fileItems = servletFileUpload.parseRequest(req);
            //---处理form表单提交过来的普通数据
            //将数据获取到，封装成一个对象
            Question question = BeanUtil.fillBean(fileItems, Question.class);
            //---处理form表单提交过来的文件数据
            for (FileItem item : fileItems) {
                //5、判断当前表单是否文件表单
                if (!item.isFormField()) {
                    //当前的字段是文件表单
                    item.write(new File(this.getServletContext().getRealPath("upload"),item.getName()));
                }
            }
            //调用业务层接口save
            questionService.save(question);
            //跳转页面
            resp.sendRedirect(req.getContextPath() + "/store/question?operation=list");
        }
    }
}

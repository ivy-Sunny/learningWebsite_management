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
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
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
            try {
                this.edit(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("delete".equals(operation)) {
            this.delete(req, resp);
        } else if ("toExamine".equals(operation)) {
            this.toExamine(req, resp);
        } else if ("downloadReport".equals(operation)) {
            this.downloadReport(req, resp);
        }
    }


    private List<Company> getCompanyList() {
        return companyService.findAll();
    }

    private List<Catalog> getCatalogList() {
        return catalogService.findAll();
    }

    private void toExamine(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Question question = BeanUtil.fillBean(req, Question.class);
        questionService.examine(question);
        resp.sendRedirect(req.getContextPath() + "/store/question?operation=list");
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

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws Exception {
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
            //调用业务层接口save
            boolean flag = false;
            for (FileItem item : fileItems) {
                if (StringUtils.isNotBlank(item.getName())) {
                    flag = true;
                    break;
                }
            }
            questionService.update(question, flag);
            //---处理form表单提交过来的文件数据
            for (FileItem item : fileItems) {
                //5、判断当前表单是否文件表单
                if (!item.isFormField()) {
                    //当前的字段是文件表单
                    item.write(new File(this.getServletContext().getRealPath("upload"), question.getId()));
                }
            }
            //跳转页面
            resp.sendRedirect(req.getContextPath() + "/store/question?operation=list");
        }
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
            //创建一个标记位
            boolean flag = false;
            for (FileItem item : fileItems) {
                if (StringUtils.isNotBlank(item.getName())) {
                    flag = true;
                    break;
                }
            }
            //---处理form表单提交过来的普通数据
            //将数据获取到，封装成一个对象
            Question question = BeanUtil.fillBean(fileItems, Question.class);
            //调用业务层接口save
            String picture = questionService.save(question, flag);
            //---处理form表单提交过来的文件数据
            for (FileItem item : fileItems) {
                //5、判断当前表单是否文件表单
                if (!item.isFormField()) {
                    //当前的字段是文件表单
                    item.write(new File(this.getServletContext().getRealPath("upload"), picture));
                }
            }
            //跳转页面
            resp.sendRedirect(req.getContextPath() + "/store/question?operation=list");
        }
    }
    private void downloadReport(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //返回的数据类型为文件xlsx类型
        resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = new String( (String.valueOf(System.currentTimeMillis()) + ".xlsx").getBytes(), "iso8859-1");
        resp.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
        //生成报告的文件，然后传递到前端页面
        ByteArrayOutputStream os = questionService.getReport();
        //获取产生响应的流对象
        ServletOutputStream sos = resp.getOutputStream();
        //将数据从原始的字节流对象中提取出来写入到servlet对应的输出流中
        os.writeTo(sos);
        //将输出流刷新
        sos.flush();
        os.close();
    }
}

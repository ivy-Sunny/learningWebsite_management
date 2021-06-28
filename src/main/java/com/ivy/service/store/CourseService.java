package com.ivy.service.store;

import com.github.pagehelper.PageInfo;
import com.ivy.domain.store.Course;

import java.util.List;

public interface CourseService {
    /**
     * 添加
     */
    void save(Course course);
    /**
     * 删除
     */
    void delete(Course course);

    /**
     * 修改
     */
    void update(Course course);

    /**
     * 查询单个
     */
    Course findById(String id);
    /**
     * 查询全部
     */
    List<Course> findAll();

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页数据量
     * @return
     */
    PageInfo findAll(int page, int size);
}

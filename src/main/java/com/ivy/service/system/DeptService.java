package com.ivy.service.system;

import com.github.pagehelper.PageInfo;
import com.ivy.domain.system.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 添加
     */
    void save(Dept dept);
    /**
     * 删除
     */
    void delete(Dept dept);

    /**
     * 修改
     */
    void update(Dept dept);

    /**
     * 查询单个
     */
    Dept findById(String id);
    /**
     * 查询全部
     */
    List<Dept> findAll();

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页数据量
     * @return
     */
    PageInfo findAll(int page, int size);
}

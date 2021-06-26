package com.ivy.service.store;

import com.github.pagehelper.PageInfo;
import com.ivy.domain.store.Company;

import java.util.List;

public interface CompanyService {
    /**
     * 添加公司
     */
    void save(Company company);
    /**
     * 删除公司
     */
    void delete(Company company);

    /**
     * 修改公司
     */
    void update(Company company);

    /**
     * 查询单个
     */
    Company findById(String id);
    /**
     * 查询全部
     */
    List<Company> findAll();

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页数据量
     * @return
     */
    PageInfo findAll(int page,int size);
}

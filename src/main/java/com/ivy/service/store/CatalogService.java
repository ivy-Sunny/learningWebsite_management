package com.ivy.service.store;

import com.github.pagehelper.PageInfo;
import com.ivy.domain.store.Catalog;

import java.util.List;

public interface CatalogService {
    /**
     * 添加
     */
    void save(Catalog catalog);
    /**
     * 删除
     */
    void delete(Catalog catalog);

    /**
     * 修改
     */
    void update(Catalog catalog);

    /**
     * 查询单个
     */
    Catalog findById(String id);
    /**
     * 查询全部
     */
    List<Catalog> findAll();

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页数据量
     * @return
     */
    PageInfo findAll(int page, int size);
}

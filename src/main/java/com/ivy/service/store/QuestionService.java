package com.ivy.service.store;

import com.github.pagehelper.PageInfo;
import com.ivy.domain.store.Question;

import java.util.List;

public interface QuestionService {
    /**
     * 添加
     */
    void save(Question question);
    /**
     * 删除
     */
    void delete(Question question);

    /**
     * 修改
     */
    void update(Question question);

    /**
     * 查询单个
     */
    Question findById(String id);
    /**
     * 查询全部
     */
    List<Question> findAll();

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页数据量
     * @return
     */
    PageInfo findAll(int page, int size);
}

package com.ivy.service.store;

import com.github.pagehelper.PageInfo;
import com.ivy.domain.store.QuestionItem;

import java.util.List;

public interface QuestionItemService {
    /**
     * 添加
     */
    void save(QuestionItem questionItem);
    /**
     * 删除
     */
    void delete(QuestionItem questionItem);

    /**
     * 修改
     */
    void update(QuestionItem questionItem);

    /**
     * 查询单个
     */
    QuestionItem findById(String id);

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页数据量
     * @param questionId 题目对应的id
     * @return
     */
    PageInfo findAll(String questionId,int page, int size);
}

package com.ivy.dao.store;

import com.ivy.domain.store.QuestionItem;

import java.util.List;

/**
 * QuestionItemItemDao
 *
 * @Author: ivy
 * @CreateTime: 2021-06-28
 */
public interface QuestionItemDao {
    int save(QuestionItem questionItem);

    int delete(QuestionItem questionItem);

    int update(QuestionItem questionItem);

    QuestionItem findById(String id);

    List<QuestionItem> findAll(String questionId);
}

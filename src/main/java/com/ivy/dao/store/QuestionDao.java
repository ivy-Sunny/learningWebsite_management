package com.ivy.dao.store;

import com.ivy.domain.store.Question;

import java.util.List;

public interface QuestionDao {
    int save(Question question);

    int delete(Question question);

    int update(Question question);

    int examine(Question question);

    Question findById(String id);

    List<Question> findAll();
}

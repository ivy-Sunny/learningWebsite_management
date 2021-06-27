package com.ivy.dao.system;

import com.ivy.domain.system.Dept;

import java.util.List;

public interface DeptDao {
    int save(Dept dept);

    int delete(Dept dept);

    int update(Dept dept);

    Dept findById(String id);

    List<Dept> findAll();
}

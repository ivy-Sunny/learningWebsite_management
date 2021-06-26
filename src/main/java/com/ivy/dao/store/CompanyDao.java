package com.ivy.dao.store;

import com.ivy.domain.store.Company;

import java.util.List;

/**
 * CompanyDao
 *
 * @Author: ivy
 * @CreateTime: 2021-06-25
 */
public interface CompanyDao {
    int save(Company company);

    int delete(Company company);

    int update(Company company);

    Company findById(String id);

    List<Company> findAll();
}

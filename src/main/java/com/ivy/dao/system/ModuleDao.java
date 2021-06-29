package com.ivy.dao.system;

import com.ivy.domain.system.Module;

import java.util.List;
import java.util.Map;

/**
 * ModuleDao
 *
 * @Author: ivy
 * @CreateTime: 2021-06-28
 */
public interface ModuleDao {

    int save(Module module);

    int delete(Module module);

    int update(Module module);

    Module findById(String id);

    List<Module> findAll();

    List<Map> findAuthorDataByRoleId(String roleId);
}

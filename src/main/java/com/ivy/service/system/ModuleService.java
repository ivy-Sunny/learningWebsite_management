package com.ivy.service.system;

import com.github.pagehelper.PageInfo;
import com.ivy.domain.system.Module;

import java.util.List;
import java.util.Map;

/**
 * ModuleService
 *
 * @Author: ivy
 * @CreateTime: 2021-06-28
 */
public interface ModuleService {
    /**
     * 添加
     */
    void save(Module module);
    /**
     * 删除
     */
    void delete(Module module);

    /**
     * 修改
     */
    void update(Module module);

    /**
     * 查询单个
     */
    Module findById(String id);
    /**
     * 查询全部
     */
    List<Module> findAll();

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页数据量
     * @return
     */
    PageInfo findAll(int page, int size);

    /**
     *
     * @param roleId  角色ID
     * @return  该角色的权限模块
     */
    public List<Map> findAuthorDataByRoleId(String roleId);

    List<Module> findModuleByUserId(String userId);
}

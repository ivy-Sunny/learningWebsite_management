package com.ivy.service.system;

import com.github.pagehelper.PageInfo;
import com.ivy.domain.system.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {
    /**
     * 添加
     */
    void save(Role role);
    /**
     * 删除
     */
    void delete(Role role);

    /**
     * 修改
     */
    void update(Role role);

    /**
     * 查询单个
     */
    Role findById(String id);
    /**
     * 查询全部
     */
    List<Role> findAll();

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页数据量
     * @return
     */
    PageInfo findAll(int page, int size);

    /**
     * 建立角色与模块之间的关联
     * @param roleId    角色ID
     * @param moduleIds 模块ID（多个）
     */
    void updateRoleModule(String roleId,String moduleIds);

    /**
     * 根据用户ID查询与角色的绑定关系
     * @param userId
     * @return
     */
    List<Map> findAllRoleByUserId(String userId);
}

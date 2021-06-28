package com.ivy.service.system;

import com.github.pagehelper.PageInfo;
import com.ivy.domain.system.User;

import java.util.List;

/**
 * UserService
 *
 * @Author: ivy
 * @CreateTime: 2021-06-27
 */
public interface UserService {
    /**
     * 添加
     */
    void save(User user);
    /**
     * 删除
     */
    void delete(User user);

    /**
     * 修改
     */
    void update(User user);

    /**
     * 查询单个
     */
    User findById(String id);
    /**
     * 查询全部
     */
    List<User> findAll();

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页数据量
     * @return
     */
    PageInfo findAll(int page, int size);
}

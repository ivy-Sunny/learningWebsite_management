package com.ivy.dao.system;

import com.ivy.domain.system.User;

import java.util.List;

public interface UserDao {
    int save(User user);

    int delete(User user);

    int update(User user);

    User findById(String id);

    List<User> findAll();
}

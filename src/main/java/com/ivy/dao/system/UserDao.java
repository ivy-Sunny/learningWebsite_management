package com.ivy.dao.system;

import com.ivy.domain.system.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    int save(User user);

    int delete(User user);

    int update(User user);

    User findById(String id);

    List<User> findAll();

    void updateRole(@Param("userId") String userId, @Param("roleId") String roleId);

    void deleteRole(String userId);

    User findByEmailAndPwd(@Param("email") String email, @Param("password") String password);
}

package com.ivy.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ivy.dao.system.RoleDao;
import com.ivy.domain.system.Role;
import com.ivy.service.system.RoleService;
import com.ivy.utils.MapperFactory;
import com.ivy.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * RoleServiceImpl
 *
 * @Author: ivy
 * @CreateTime: 2021-06-28
 */
public class RoleServiceImpl implements RoleService {
    public void save(Role role) {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            RoleDao roleDao = MapperFactory.getMapper(sqlSession, RoleDao.class);
            //id使用UUID的生成策略
            String id = UUID.randomUUID().toString();
            role.setId(id);
            //3、调用Dao层操作
            int result = roleDao.save(role);
            //4、提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //记录日志
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(Role role) {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            RoleDao roleDao = MapperFactory.getMapper(sqlSession, RoleDao.class);
            //3、调用Dao层操作
            int result = roleDao.delete(role);
            //4、提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            TransactionUtil.commit(sqlSession);
            throw new RuntimeException(e);
            //记录日志
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Role role) {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            RoleDao roleDao = MapperFactory.getMapper(sqlSession, RoleDao.class);
            //3、调用Dao层操作
            int result = roleDao.update(role);
            //4、提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            TransactionUtil.commit(sqlSession);
            throw new RuntimeException(e);
            //记录日志
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Role findById(String id) {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            RoleDao roleDao = MapperFactory.getMapper(sqlSession, RoleDao.class);
            //3、调用Dao层操作
            return roleDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Role> findAll() {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            RoleDao roleDao = MapperFactory.getMapper(sqlSession, RoleDao.class);
            //3、调用Dao层操作
            return roleDao.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public PageInfo findAll(int page, int size) {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            RoleDao roleDao = MapperFactory.getMapper(sqlSession, RoleDao.class);
            //3、调用Dao层操作
            PageHelper.startPage(page, size);
            List<Role> all = roleDao.findAll();
            PageInfo pageInfo = new PageInfo(all);
            return pageInfo;
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updateRoleModule(String roleId, String moduleIds) {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            RoleDao roleDao = MapperFactory.getMapper(sqlSession, RoleDao.class);
            //3、调用Dao层操作
            //修改role_module关系表
            //3.1现有的关系全部取消掉
            roleDao.deleteRoleModule(roleId);
            //3.2建立新的关系（多个）
            String[] moduleIdList = moduleIds.split(",");
            for (String moduleId : moduleIdList) {
                roleDao.saveRoleModule(roleId, moduleId);
            }
            //4、提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            TransactionUtil.commit(sqlSession);
            throw new RuntimeException(e);
            //记录日志
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Map> findAllRoleByUserId(String userId){
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            RoleDao roleDao = MapperFactory.getMapper(sqlSession, RoleDao.class);
            //3、调用Dao层操作
            List<Map> roles = roleDao.findAllRoleByUserId(userId);
            return roles;
        } catch (Exception e) {
            throw new RuntimeException(e);
            //记录日志
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

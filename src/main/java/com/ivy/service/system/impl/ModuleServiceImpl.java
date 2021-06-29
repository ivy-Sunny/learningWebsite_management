package com.ivy.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ivy.dao.system.ModuleDao;
import com.ivy.domain.system.Module;
import com.ivy.service.system.ModuleService;
import com.ivy.utils.MapperFactory;
import com.ivy.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * ModuleServiceImpl
 *
 * @Author: ivy
 * @CreateTime: 2021-06-28
 */
public class ModuleServiceImpl implements ModuleService {
    public void save(Module module) {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession, ModuleDao.class);
            //id使用UUID的生成策略
            String id = UUID.randomUUID().toString();
            module.setId(id);
            //3、调用Dao层操作
            int result = moduleDao.save(module);
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

    public void delete(Module module) {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession, ModuleDao.class);
            //3、调用Dao层操作
            int result = moduleDao.delete(module);
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

    public void update(Module module) {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession, ModuleDao.class);
            //3、调用Dao层操作
            int result = moduleDao.update(module);
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

    public Module findById(String id) {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession, ModuleDao.class);
            //3、调用Dao层操作
            return moduleDao.findById(id);
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

    public List<Module> findAll() {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession, ModuleDao.class);
            //3、调用Dao层操作
            return moduleDao.findAll();
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
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession, ModuleDao.class);
            //3、调用Dao层操作
            PageHelper.startPage(page, size);
            List<Module> all = moduleDao.findAll();
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
    public List<Map> findAuthorDataByRoleId(String roleId){
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession, ModuleDao.class);
            List<Map> moduleMap = moduleDao.findAuthorDataByRoleId(roleId);

            return moduleMap;
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

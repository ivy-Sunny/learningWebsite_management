package com.ivy.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ivy.dao.system.DeptDao;
import com.ivy.domain.system.Dept;
import com.ivy.service.system.DeptService;
import com.ivy.utils.MapperFactory;
import com.ivy.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

/**
 * DeptServiceImpl
 *
 * @Author: ivy
 * @CreateTime: 2021-06-27
 */
public class DeptServiceImpl implements DeptService {
    public void save(Dept dept) {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            DeptDao deptDao = MapperFactory.getMapper(sqlSession, DeptDao.class);
            //id使用UUID的生成策略
            String id = UUID.randomUUID().toString();
            dept.setId(id);
            //3、调用Dao层操作
            int result = deptDao.save(dept);
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

    public void delete(Dept dept) {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            DeptDao deptDao = MapperFactory.getMapper(sqlSession, DeptDao.class);
            //3、调用Dao层操作
            int result = deptDao.delete(dept);
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

    public void update(Dept dept) {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            DeptDao deptDao = MapperFactory.getMapper(sqlSession, DeptDao.class);
            //3、调用Dao层操作
            int result = deptDao.update(dept);
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

    public Dept findById(String id) {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            DeptDao deptDao = MapperFactory.getMapper(sqlSession, DeptDao.class);
            //3、调用Dao层操作
            return deptDao.findById(id);
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

    public List<Dept> findAll() {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            DeptDao deptDao = MapperFactory.getMapper(sqlSession, DeptDao.class);
            //3、调用Dao层操作
            return deptDao.findAll();
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
            DeptDao deptDao = MapperFactory.getMapper(sqlSession, DeptDao.class);
            //3、调用Dao层操作
            PageHelper.startPage(page, size);
            List<Dept> all = deptDao.findAll();
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
}

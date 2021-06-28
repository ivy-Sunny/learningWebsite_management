package com.ivy.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ivy.dao.store.CatalogDao;
import com.ivy.domain.store.Catalog;
import com.ivy.service.store.CatalogService;
import com.ivy.utils.MapperFactory;
import com.ivy.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * CatalogServiceImpl
 *
 * @Author: ivy
 * @CreateTime: 2021-06-27
 */
public class CatalogServiceImpl implements CatalogService {
    public void save(Catalog catalog) {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            CatalogDao catalogDao = MapperFactory.getMapper(sqlSession, CatalogDao.class);
            //id使用UUID的生成策略
            String id = UUID.randomUUID().toString();
            catalog.setCreateTime(new Date());
            System.out.println(catalog.getCreateTime());
            catalog.setId(id);
            //3、调用Dao层操作
            int result = catalogDao.save(catalog);
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

    public void delete(Catalog catalog) {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            CatalogDao catalogDao = MapperFactory.getMapper(sqlSession, CatalogDao.class);
            //3、调用Dao层操作
            int result = catalogDao.delete(catalog);
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

    public void update(Catalog catalog) {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            CatalogDao catalogDao = MapperFactory.getMapper(sqlSession, CatalogDao.class);
            //3、调用Dao层操作
            int result = catalogDao.update(catalog);
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

    public Catalog findById(String id) {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            CatalogDao catalogDao = MapperFactory.getMapper(sqlSession, CatalogDao.class);
            //3、调用Dao层操作
            return catalogDao.findById(id);
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

    public List<Catalog> findAll() {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            CatalogDao catalogDao = MapperFactory.getMapper(sqlSession, CatalogDao.class);
            //3、调用Dao层操作
            return catalogDao.findAll();
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
            CatalogDao catalogDao = MapperFactory.getMapper(sqlSession, CatalogDao.class);
            //3、调用Dao层操作
            PageHelper.startPage(page, size);
            List<Catalog> all = catalogDao.findAll();
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

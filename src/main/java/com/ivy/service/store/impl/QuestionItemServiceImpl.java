package com.ivy.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ivy.dao.store.QuestionItemDao;
import com.ivy.domain.store.QuestionItem;
import com.ivy.service.store.QuestionItemService;
import com.ivy.service.store.QuestionService;
import com.ivy.utils.MapperFactory;
import com.ivy.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

/**
 * QuestionItemServiceImpl
 *
 * @Author: ivy
 * @CreateTime: 2021-06-28
 */
public class QuestionItemServiceImpl implements QuestionItemService {
    public void save(QuestionItem questionItem) {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession, QuestionItemDao.class);
            //id使用UUID的生成策略
            String id = UUID.randomUUID().toString();
            questionItem.setId(id);
            //3、调用Dao层操作
            int result = questionItemDao.save(questionItem);
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

    public void delete(QuestionItem questionItem) {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession, QuestionItemDao.class);
            //3、调用Dao层操作
            int result = questionItemDao.delete(questionItem);
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

    public void update(QuestionItem questionItem) {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession, QuestionItemDao.class);
            //3、调用Dao层操作
            int result = questionItemDao.update(questionItem);
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

    public QuestionItem findById(String id) {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession, QuestionItemDao.class);
            //3、调用Dao层操作
            return questionItemDao.findById(id);
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

    public PageInfo findAll(String questionId, int page, int size) {
        SqlSession sqlSession = null;
        try {
            //1、获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2、获取Dao
            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession, QuestionItemDao.class);
            //3、调用Dao层操作
            PageHelper.startPage(page, size);
            List<QuestionItem> all = questionItemDao.findAll(questionId);
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

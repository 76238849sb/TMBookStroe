package com.zdj.TMBookStore.service.impl;

import com.zdj.TMBookStore.dao.CategoryDao;
import com.zdj.TMBookStore.dao.impl.CategoryDaoImpl;
import com.zdj.TMBookStore.po.CategoryList;
import com.zdj.TMBookStore.po.CategoryListTwo;
import com.zdj.TMBookStore.service.CategoryService;
import com.zdj.TMBookStore.utils.DruidPool;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 华韵流风
 * @ClassName CategoryServiceImpl
 * @Description TODO
 * @Date 2021/5/27 15:35
 * @packageName com.zdj.TMBookStore.service.impl
 */
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<CategoryList> findAllCategory()  {

        try {
            return categoryDao.findAllCategory();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException("查询findAllCategory失败！");
        }
    }

    @Override
    public List<CategoryListTwo> findCategoryTwo(String pid)  {

        try {
            return categoryDao.findCategoryTwo(pid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw  new RuntimeException("查询二级分类失败！");
        }
    }

    @Override
    public void addBookLevelOne(String cname,String desc) {
        try {
            DruidPool.beginTransaction();
            categoryDao.addBookLevelOne(cname,desc);
            DruidPool.commitTransaction();
        } catch (SQLException throwables) {
            try {
                DruidPool.rollbackTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
            throw  new RuntimeException("addBookLevelOne 出现的问题！");
        }
    }

    @Override
    public void updateOneLevel(String cname, String desc, String cid) {
        try {
            DruidPool.beginTransaction();
            categoryDao.updateOneLevel(cname,desc,cid);
            DruidPool.commitTransaction();
        } catch (SQLException throwables) {
            try {
                DruidPool.rollbackTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
            throw  new RuntimeException("updateTwoLevel修改失败!");
        }
    }

    @Override
    public void addBookLevelTwo(String came,String pid, String desc) {

        try {
            DruidPool.beginTransaction();
            categoryDao.addBookLevelTwo(came, pid, desc);
            DruidPool.commitTransaction();
        } catch (SQLException throwables) {
            try {
                DruidPool.rollbackTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
            throw new RuntimeException("addBookLevelTwo添加失败");
        }

    }


    @Override
    public void updateTwoLevel(String cname, String desc, String cid,String pid) {
        try {
            DruidPool.beginTransaction();
            categoryDao.updateTwoLevel(cname,desc,cid,pid);
            DruidPool.commitTransaction();
        } catch (SQLException throwables) {
            try {
                DruidPool.rollbackTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
            throw  new  RuntimeException("updateTwoLevel修改失败!");
        }
    }

    @Override
    public void deleteOneLevel(String cid) {
        try {
            DruidPool.beginTransaction();
            categoryDao.deleteOneLevel(cid);
            DruidPool.commitTransaction();
        } catch (SQLException throwables) {
            try {
                DruidPool.rollbackTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
            throw  new RuntimeException("deleteOneLevel数据删除失败！");
        }
    }

    @Override
    public void deleteTwoLevel(String cid) {
        try {
            DruidPool.beginTransaction();
            categoryDao.deleteOneLevel(cid);
            DruidPool.commitTransaction();
        } catch (SQLException throwables) {
            try {
                DruidPool.rollbackTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
            throw  new RuntimeException("deleteOneLevel数据删除失败！");
        }
    }

    @Override
    public Long checkCategoryName(String cname) {
        try {
            return categoryDao.checkCategoryName(cname);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw  new RuntimeException("查询重复分类名失败！");
        }
    }
}

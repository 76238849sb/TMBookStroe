package com.zdj.TMBookStore.dao;

import com.zdj.TMBookStore.po.CategoryList;
import com.zdj.TMBookStore.po.CategoryListTwo;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 华韵流风
 * @ClassName CateegoryDao
 * @Description TODO
 * @Date 2021/5/27 15:13
 * @packageName com.zdj.TMBookStore.dao
 */
public interface CategoryDao {

    /**
     * 差询有返回值，增删改不用返回值。查询所有的分类
     *
     * @return List<CategoryList>
     * @throws SQLException SQL
     */

    List<CategoryList> findAllCategory() throws SQLException;

    /**
     * 查询二级分类
     *
     * @param pid pid
     * @return List<CategoryListTwo>
     * @throws SQLException SQL
     */
    List<CategoryListTwo> findCategoryTwo(String pid) throws SQLException;


    /**
     * 添加一级分类
     *
     * @param cname cname
     * @param desc desc
     * @throws SQLException SQL
     */
    void addBookLevelOne(String cname, String desc) throws SQLException;

    /**
     * 修改一级分类
     *
     * @param cname cname
     * @param desc desc
     * @param cid cid
     * @throws SQLException SQL
     */
    void updateOneLevel(String cname, String desc,String cid) throws SQLException;

    /**
     * 添加二级分类
     *
     * @param came cname
     * @param pid pid
     * @param desc desc
     * @throws SQLException SQL
     */
    void  addBookLevelTwo(String came ,String pid, String desc) throws SQLException;

    /**
     * 修改二级分类
     *
     * @param cname cname
     * @param desc desc
     * @param cid cid
     * @param pid pid
     * @throws SQLException SQL
     */
    void updateTwoLevel(String cname ,String desc , String cid,String pid) throws SQLException;

    /**
     * 删除一级分类
     *
     * @param cid cid
     * @throws SQLException SQL
     */
    void deleteOneLevel(String cid) throws SQLException;

    /**
     * 删除二级分类
     *
     * @param cid cid
     * @throws SQLException SQL
     */
    void deleteTwoLevel(String cid) throws SQLException;

    /**
     * 检查分类名称是否重复
     *
     * @param cname cname
     * @return Long
     * @throws SQLException SQL
     */
    Long checkCategoryName(String cname) throws SQLException;
}

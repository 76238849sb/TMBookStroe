package com.zdj.TMBookStore.service;

import com.zdj.TMBookStore.po.CategoryList;
import com.zdj.TMBookStore.po.CategoryListTwo;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 华韵流风
 * @ClassName CategoryService
 * @Description TODO
 * @Date 2021/5/27 15:30
 * @packageName com.zdj.TMBookStore.service
 */
public interface CategoryService {

    /**
     * 差询有返回值，增删改不用返回值。查询所有的分类
     *
     * @return List<CategoryList>
     */

    List<CategoryList> findAllCategory();

    /**
     * 查询二级分类
     *
     * @param pid pid
     * @return List<CategoryListTwo>
     */
    List<CategoryListTwo> findCategoryTwo(String pid);


    /**
     * 添加一级分类
     *
     * @param cname cname
     * @param desc desc
     */
    void addBookLevelOne(String cname, String desc);

    /**
     * 修改一级分类
     *
     * @param cname cname
     * @param desc desc
     * @param cid cid
     */
    void updateOneLevel(String cname, String desc,String cid);

    /**
     * 添加二级分类
     *
     * @param came cname
     * @param pid pid
     * @param desc desc
     */
    void  addBookLevelTwo(String came ,String pid, String desc);

    /**
     * 修改二级分类
     *
     * @param cname cname
     * @param desc desc
     * @param cid cid
     * @param pid pid
     */
    void updateTwoLevel(String cname ,String desc , String cid,String pid);

    /**
     * 删除一级分类
     *
     * @param cid cid
     */
    void deleteOneLevel(String cid);

    /**
     * 删除二级分类
     *
     * @param cid cid
     */
    void deleteTwoLevel(String cid);

    /**
     * 检查分类名称是否重复
     *
     * @param cname cname
     * @return Long
     */
    Long checkCategoryName(String cname);
}

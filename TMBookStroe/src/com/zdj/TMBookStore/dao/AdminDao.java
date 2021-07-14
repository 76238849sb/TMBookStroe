package com.zdj.TMBookStore.dao;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.zdj.TMBookStore.po.*;
import com.zdj.TMBookStore.utils.PageBean;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 华韵流风
 * @ClassName AdminDao
 * @Description TODO
 * @Date 2021/5/23 9:04
 * @packageName com.zdj.TMBookStore.dao
 */
public interface AdminDao {

    /**
     * 管理员登录的类
     *
     * @param username 管理员用户名
     * @param password 管理员密码
     * @return AdminUser
     * @throws SQLException SQL
     */
    AdminUser adminLogin(String username, String password) throws SQLException;

}

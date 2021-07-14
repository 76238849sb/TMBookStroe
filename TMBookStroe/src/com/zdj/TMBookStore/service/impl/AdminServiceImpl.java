package com.zdj.TMBookStore.service.impl;

import com.zdj.TMBookStore.dao.AdminDao;
import com.zdj.TMBookStore.dao.impl.AdminDaoImpl;
import com.zdj.TMBookStore.po.*;
import com.zdj.TMBookStore.service.AdminService;
import com.zdj.TMBookStore.utils.DruidPool;
import com.zdj.TMBookStore.utils.PageBean;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 华韵流风
 */
public class AdminServiceImpl implements AdminService {
    private final AdminDao adminDao = new AdminDaoImpl();

    @Override
    public AdminUser adminLogin(String username, String password) {
        try {
            return adminDao.adminLogin(username, password);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException("账号与密码不匹配");
        }
    }
}

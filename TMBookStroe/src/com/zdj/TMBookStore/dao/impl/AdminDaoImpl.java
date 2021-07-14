package com.zdj.TMBookStore.dao.impl;

import com.zdj.TMBookStore.dao.AdminDao;
import com.zdj.TMBookStore.po.*;
import com.zdj.TMBookStore.utils.TxQueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;


/**
 * @author 华韵流风
 * @ClassName AdminDao
 * @Description TODO
 * @Date 2021/5/23 10:21
 * @packageName com.zdj.TMBookStore.dao.impl
 */
public class AdminDaoImpl implements AdminDao {
    private final TxQueryRunner tqr = new TxQueryRunner();
    @Override
    public AdminUser adminLogin(String username, String password) throws SQLException {
        String sql = "select * from t_admin where adminname=? and adminpwd=?";
        return tqr.query(sql, new BeanHandler<>(AdminUser.class),username,password);

    }

}

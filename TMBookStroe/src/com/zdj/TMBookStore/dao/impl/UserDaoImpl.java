package com.zdj.TMBookStore.dao.impl;

import com.zdj.TMBookStore.dao.UserDao;
import com.zdj.TMBookStore.po.User;
import com.zdj.TMBookStore.utils.TxQueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.UUID;

/**
 * @author 华韵流风
 * @ClassName UserDaoImpl
 * @Description TODO
 * @Date 2021/5/28 15:00
 * @packageName com.zdj.TMBookStore.dao.impl
 */
public class UserDaoImpl implements UserDao {
    private final TxQueryRunner tqr = new TxQueryRunner();

    @Override
    public void register(User user) throws SQLException {
        String sql = "insert into t_user values (?,?,?,?,?,?)";
        tqr.update(sql,user.getUid(),user.getLoginname(),user.getLoginpass(),user.getEmail(),user.getStatus(),user.getActivationCode());
    }

    @Override
    public void active(String uid) throws SQLException {
        String sql = "update t_user set status=1 where uid=?";
        tqr.update(sql,uid);
    }

    @Override
    public Long checkUserName(String loginname) throws SQLException {
        String sql = "select count(*) from t_user where loginname=?";
        return (Long)tqr.query(sql, new ScalarHandler(),loginname);
    }

    @Override
    public Long checkEmail(String email) throws SQLException {
        String sql = "select count(*) from t_user where email=?";
        return (Long)tqr.query(sql, new ScalarHandler(),email);
    }

    @Override
    public User login(String loginname, String loginpass) throws SQLException {
        String sql = "select * from t_user where loginname=? and loginpass=?";
        return tqr.query(sql, new BeanHandler<>(User.class),loginname,loginpass);
    }

    @Override
    public void changeUserPassword(String newLoginPass , String uid) throws SQLException {
        String sql ="update t_user set loginpass = ? where uid = ?";
        tqr.update(sql,newLoginPass,uid);
    }

    @Override
    public String checkOldPass(String uid) throws SQLException {
        String sql = "select * from t_user where uid=?";
        User user = tqr.query(sql, new BeanHandler<>(User.class),uid);
        return user.getLoginpass();
    }
}

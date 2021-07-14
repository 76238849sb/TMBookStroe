package com.zdj.TMBookStore.service.impl;

import com.zdj.TMBookStore.dao.UserDao;
import com.zdj.TMBookStore.dao.impl.UserDaoImpl;
import com.zdj.TMBookStore.face.FaceUserServlet;
import com.zdj.TMBookStore.po.User;
import com.zdj.TMBookStore.service.UserService;
import com.zdj.TMBookStore.utils.DruidPool;
import com.zdj.TMBookStore.utils.Tools;

import java.sql.SQLException;
import java.util.Random;

/**
 * @author 华韵流风
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Date 2021/5/28 15:05
 * @packageName com.zdj.TMBookStore.service.impl
 */
public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public void register(User user) {
        try {
            user.setLoginpass(Tools.md5(user.getLoginpass()));
            DruidPool.beginTransaction();
            userDao.register(user);
            DruidPool.commitTransaction();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            try {
                DruidPool.rollbackTransaction();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            throw new RuntimeException("注册失败！");
        }
    }

    @Override
    public void active(String uid) {
        try {
            DruidPool.beginTransaction();
            userDao.active(uid);
            DruidPool.commitTransaction();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            try {
                DruidPool.rollbackTransaction();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            throw new RuntimeException("激活账户失败！");
        }
    }

    @Override
    public Long checkUserName(String loginname) {
        try {
            return userDao.checkUserName(loginname);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException("查询重复用户名失败！");
        }
    }

    @Override
    public Long checkEmail(String email) {
        try {
            return userDao.checkEmail(email);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException("查询重复用户名失败！");
        }
    }

    @Override
    public User login(String loginname, String loginpass) {
        try {
            loginpass = Tools.md5(loginpass);
            return userDao.login(loginname, loginpass);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException("登录失败！");
        }
    }

    @Override
    public void changeUserPassword( String newLoginPass,String uid) {
        newLoginPass = Tools.md5(newLoginPass);
        try {
            DruidPool.beginTransaction();
            userDao.changeUserPassword(newLoginPass,uid);
            DruidPool.commitTransaction();
        } catch (SQLException throwables) {
            try {
                DruidPool.rollbackTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();

            throw  new RuntimeException("用户名修改失败！");
        }
    }

    @Override
    public String checkOldPass(String uid) {
        try {
            return userDao.checkOldPass(uid);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw  new RuntimeException("查询密码失败！");
        }
    }
}

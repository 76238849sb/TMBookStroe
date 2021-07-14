package com.zdj.TMBookStore.dao;

import com.zdj.TMBookStore.po.User;

import java.sql.SQLException;

/**
 * @author 华韵流风
 * @ClassName UserDao
 * @Description TODO
 * @Date 2021/5/28 14:59
 * @packageName com.zdj.TMBookStore.dao
 */
public interface UserDao {

    /**
     * 注册
     *
     * @param user user
     * @throws SQLException SQL
     */
    void register(User user) throws SQLException;

    /**
     * 激活账户
     *
     * @param uid uid
     * @throws SQLException SQL
     */
    void active(String uid)throws SQLException;

    /**
     * 查询是否有重复的用户名
     *
     * @param loginname name
     * @return Long
     * @throws SQLException SQL
     */
    Long checkUserName(String loginname) throws SQLException;

    /**
     * 查询是否有重复的邮箱
     *
     * @param email email
     * @return Long
     * @throws SQLException SQL
     */
    Long checkEmail(String email) throws SQLException;

    /**
     * 检查旧密码
     *
     * @param uid uid
     * @return boolean
     * @throws SQLException SQL
     */
    String checkOldPass(String uid) throws SQLException;

    /**
     * 登录
     *
     * @param loginname loginname
     * @param loginpass loginpass
     * @return User
     * @throws SQLException SQL
     */
    User login(String loginname, String loginpass) throws SQLException;


    /**
     * 修改密码
     *
     * @param newLoginPass 新的密码
     * @param uid uid
     * @throws SQLException SQL
     */
    void changeUserPassword (String newLoginPass , String uid) throws SQLException;

}

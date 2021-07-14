package com.zdj.TMBookStore.service;

import com.zdj.TMBookStore.po.User;

import java.sql.SQLException;

/**
 * @author 华韵流风
 * @ClassName UserService
 * @Description TODO
 * @Date 2021/5/28 14:55
 * @packageName com.zdj.TMBookStore.service
 */
public interface UserService {

    /**
     * 注册
     *
     * @param user user
     */
    void register(User user);

    /**
     * 激活账户
     *
     * @param uid uid
     */
    void active(String uid);

    /**
     * 查询是否有重复的用户名
     *
     * @param loginname name
     * @return Long
     */
    Long checkUserName(String loginname);

    /**
     * 查询是否有重复的邮箱
     *
     * @param email email
     * @return Long
     */
    Long checkEmail(String email);

    /**
     * 检查旧密码
     *
     * @param uid uid
     * @return String
     */
    String checkOldPass(String uid);

    /**
     * 登录
     *
     * @param loginname loginname
     * @param loginpass loginpass
     * @return User
     */
    User login(String loginname, String loginpass);

    /**
     * 修改密码
     *
     * @param newLoginPass newLoginPass
     * @param loginname loginname
     */
    void changeUserPassword ( String newLoginPass,String loginname);


}

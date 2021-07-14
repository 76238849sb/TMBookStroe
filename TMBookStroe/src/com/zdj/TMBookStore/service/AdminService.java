package com.zdj.TMBookStore.service;

import com.zdj.TMBookStore.po.*;

/**
 * @author 华韵流风
 * @ClassName AdminService
 * @Description TODO
 * @Date 2021/5/23 9:04
 * @packageName com.zdj.TMBookStore.service
 */
public interface AdminService {

    /**
     * 管理员登录的类
     *
     * @param username 管理员用户名
     * @param password 管理员密码
     * @return AdminUser
     */
    AdminUser adminLogin(String username, String password);
}

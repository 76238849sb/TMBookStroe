package com.zdj.TMBookStore.po;

/**
 * @author 华韵流风
 * @ClassName AdminUser
 * @Description TODO
 * @Date 2021/5/23 9:06
 * @packageName com.zdj.TMBookStore.po
 */
public class AdminUser {
    private String adminId;
    private String adminName;
    private String adminPwd;

    @Override
    public String toString() {
        return "AdminUser{" +
                "adminId='" + adminId + '\'' +
                ", adminName='" + adminName + '\'' +
                ", adminPwd='" + adminPwd + '\'' +
                '}';
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}

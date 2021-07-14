package com.zdj.TMBookStore.po;

/**
 * @author 华韵流风
 * @ClassName User
 * @Description TODO
 * @Date 2021/5/28 14:56
 * @packageName com.zdj.TMBookStore.po
 */
public class User {

    private String uid;
    private String loginname;
    private String loginpass;
    private String reloginpass;
    private String email;
    private int status;
    private String activationCode;

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", username='" + loginname + '\'' +
                ", loginpass='" + loginpass + '\'' +
                ", reloginpass='" + reloginpass + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", activationCode='" + activationCode + '\'' +
                '}';
    }

    public String getReloginpass() {
        return reloginpass;
    }

    public void setReloginpass(String reloginpass) {
        this.reloginpass = reloginpass;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getLoginpass() {
        return loginpass;
    }

    public void setLoginpass(String loginpass) {
        this.loginpass = loginpass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
}

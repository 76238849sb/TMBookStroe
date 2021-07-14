package com.zdj.TMBookStore.face;

import com.zdj.TMBookStore.po.User;
import com.zdj.TMBookStore.service.UserService;
import com.zdj.TMBookStore.service.impl.UserServiceImpl;
import com.zdj.TMBookStore.utils.BaseServlet;
import com.zdj.TMBookStore.utils.BeanUtil;
import com.zdj.TMBookStore.utils.SendEmailMsg;
import com.zdj.TMBookStore.utils.Tools;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;
import java.util.UUID;

/**
 * @author 华韵流风
 * @ClassName ${NAME}
 * @Description TODO
 * @Date 2021/5/28 10:58
 * @packageName ${PACKAGE_NAME}
 */
@WebServlet(name = "FaceUserServlet", urlPatterns = "/face/faceUserServlet")
public class FaceUserServlet extends BaseServlet {

    private final UserService userService = new UserServiceImpl();

    public String checkUserName(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        long count = userService.checkUserName(userName);
        String result = "正确";
        if (count != 0) {
            result = "错误";
        }

        return "a:{\"result\":\"" + result + "\"}";
    }

    public String checkCode(HttpServletRequest request, HttpServletResponse response){
        String result = "成功";
        String verifyCode = request.getParameter("verifyCode");
        if (!verifyCode.equals(request.getSession().getAttribute("validation_code"))) {
            result = "失败";
            return "a:{\"result\":\"" + result + "\"}";
        }
        return "a:{\"result\":\"" + result + "\"}";
    }


    public String register(HttpServletRequest request, HttpServletResponse response) {

        String uid = UUID.randomUUID().toString().replaceAll("-", "").trim();
        String activeCode = UUID.randomUUID().toString().replaceAll("-", "").trim();
        User user = BeanUtil.toBean(request.getParameterMap(), User.class);
        user.setUid(uid);
        user.setStatus(0);
        user.setActivationCode(activeCode);
        String email = user.getEmail();

        String title = "您正在进行注册操作！";
//        String code = getCode(6);
        String massage = "您的激活码是：" + activeCode + "，请勿告诉他人，如果这不是您本人操作，请忽略此邮件。<br><br>" + "请点击：<a href='"  + "http://localhost:8080/TMBookStroe/face/faceUserServlet?method=active&uid=" + uid + "'>激活</a>";
        try {
            SendEmailMsg.send(email, title, massage);
            userService.register(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "s:/web/jsps/user/login.jsp";
    }


    public String sendRegisterCode(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter("email");
        String result = "成功";
        String title = "您正在进行注册操作！";
        String code = getCode(6);
        String massage = "您的验证码是：" + code + "，请勿告诉他人，如果这不是您本人操作，请忽略此邮件。";


        request.getSession().removeAttribute("code");

        if (SendEmailMsg.send(email, title, massage)) {
            request.getSession().setAttribute("code", code);
        } else {
            result = "失败";
        }

        return "a:{\"result\"" + ":\"" + result + "\"}";
    }


    public String checkOldPass(HttpServletRequest request, HttpServletResponse response) {
        String uid = ((User) request.getSession().getAttribute("user")).getUid();
        String oldPass = request.getParameter("oldPass");
        String pass = userService.checkOldPass(uid);
        String result = "正确";
        oldPass = Tools.md5(oldPass);
        if (!oldPass.equals(pass)) {
            result = "错误";
        }

        return "a:{\"result" + "\":\"" + result + "\"}";
    }


    public String sendNewPassCode(HttpServletRequest request, HttpServletResponse response) {

        String email = ((User) request.getSession().getAttribute("user")).getEmail();
        String title = "您正在进行修改密码操作！";
        String code = getCode(6);
        String massage = "您的验证码是：" + code + "，请勿告诉他人，如果这不是您本人操作，请忽略此邮件。";
        String result = "成功";

        if (SendEmailMsg.send(email, title, massage)) {
            request.getSession().setAttribute("code", code);
        } else {
            result = "失败";
        }

        return "a:{\"result\"" + ":\"" + result + "\"}";

    }

    public String checkEmail(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        long count = userService.checkEmail(email);
        String result = "可以使用";
        if (count != 0) {
            result = "该邮箱已被注册！";
        }

        return "a:{\"result\":\"" + result + "\"}";
    }


    public String checkChangePass(HttpServletRequest request, HttpServletResponse response) {

        String code = (String) request.getSession().getAttribute("code");
        String vcode = request.getParameter("vcode").toUpperCase();

        if (!vcode.equals(code)) {
            request.setAttribute("err", 1);
            return "f:/web/jsps/main.jsp";
        } else {
            String uid = ((User) request.getSession().getAttribute("user")).getUid();
            String newLoginPass = request.getParameter("newLoginPass");
            userService.changeUserPassword(newLoginPass, uid);
            request.setAttribute("err", 2);
            return "f:/web/jsps/user/login.jsp";
        }

    }


    public String login(HttpServletRequest request, HttpServletResponse response) {
        String loginname = request.getParameter("loginname");
        String loginpass = request.getParameter("loginpass");
        String verifyCode = request.getParameter("verifyCode");


        if (!verifyCode.equals(request.getSession().getAttribute("validation_code"))) {
            request.setAttribute("errinfo", "验证码错误！");
            return "f:/web/jsps/user/login.jsp";
        }

        User user = userService.login(loginname, loginpass);

        if (user == null) {
            request.setAttribute("errinfo", "账号或密码错误！");
            return "f:/web/jsps/user/login.jsp";
        } else if (user.getStatus() != 1) {
            request.setAttribute("errinfo", "账号未激活！<a href='" + request.getContextPath() + "/face/faceUserServlet?method=active&uid=" + user.getUid()  + "'>激活</a>");
            return "f:/web/jsps/user/login.jsp";
        }
        request.getSession().setAttribute("user", user);

        try {
            //写入到cookie中
            Cookie usck = new Cookie("loginname", loginname);
            Cookie pwck = new Cookie("loginpass", loginpass);
            Cookie statu = new Cookie("statu", String.valueOf(user.getStatus()));
            usck.setMaxAge(60 * 60 * 24);
            pwck.setMaxAge(60 * 60 * 24);
            statu.setMaxAge(60*60*24);
            usck.setPath("/");
            pwck.setPath("/");
            statu.setPath("/");
            response.addCookie(usck);
            response.addCookie(pwck);
            response.addCookie(statu);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "f:/web/jsps/main.jsp";
    }

    public String active(HttpServletRequest request, HttpServletResponse response) {
        String uid = request.getParameter("uid");
        userService.active(uid);
        return "s:/web/jsps/user/login.jsp";
    }


    private String getCode(int length) {
        String reg = "0123456789qwertyuioplkjhgfdsazxcvbnm";
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < length; i++) {
            code.append(reg.charAt(new Random().nextInt(36)));
        }

        return code.toString().toUpperCase();

    }

}

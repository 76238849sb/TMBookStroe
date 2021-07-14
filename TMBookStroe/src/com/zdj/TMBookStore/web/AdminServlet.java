package com.zdj.TMBookStore.web;

import com.zdj.TMBookStore.po.AdminUser;
import com.zdj.TMBookStore.service.AdminService;
import com.zdj.TMBookStore.service.impl.AdminServiceImpl;
import com.zdj.TMBookStore.utils.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 华韵流风
 * @ClassName ${NAME}
 * @Description TODO
 * @Date 2021/5/23 9:01
 * @packageName ${PACKAGE_NAME}
 */
@WebServlet(name = "AdminServlet", urlPatterns = "/web/adminServlet")
public class AdminServlet extends BaseServlet {
    private final AdminService adminService = new AdminServiceImpl();

    public String login(HttpServletRequest request, HttpServletResponse response) {
        String adminname = request.getParameter("adminname");
        String adminpwd = request.getParameter("adminpwd");
        AdminUser adminUser = adminService.adminLogin(adminname, adminpwd);
        if (adminUser == null) {
            request.setAttribute("errinfo", "账号或密码错误！");
            return "f:/web/adminjsps/login.jsp";
        } else {
            request.getSession().setAttribute("adminUser", adminUser);
            return "s:/web/adminjsps/admin/main.jsp";
        }
    }



    public String exit(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("employee");
        return "s:/web/adminjsps/login.jsp";
    }
}

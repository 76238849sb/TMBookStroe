package com.zdj.TMBookStore.filter;

import com.zdj.TMBookStore.face.FaceUserServlet;
import com.zdj.TMBookStore.po.User;
import com.zdj.TMBookStore.service.AdminService;
import com.zdj.TMBookStore.service.UserService;
import com.zdj.TMBookStore.service.impl.AdminServiceImpl;
import com.zdj.TMBookStore.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 华韵流风
 * @ClassName ${NAME}
 * @Description TODO
 * @Date 2021/5/16 15:02
 * @packageName ${PACKAGE_NAME}
 */
@WebFilter(filterName = "AutoLoginFilter", urlPatterns = {"/web/jsps/user/login.jsp"})
public class AutoLoginFilter implements Filter {
    private final UserService userService = new UserServiceImpl();

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //取cookie值
        Cookie[] cookies = request.getCookies();
        String loginname = null;
        String loginpass = null;
        String statu = null;
        if (cookies == null) {
            chain.doFilter(request, response);
        } else {
            for (Cookie cookie : cookies) {
                if ("loginname".equals(cookie.getName())) {
                    loginname = cookie.getValue();
                } else if ("loginpass".equals(cookie.getName())) {
                    loginpass = cookie.getValue();
                } else if ("statu".equals(cookie.getName())) {
                    statu = cookie.getValue();
                }
            }
        }

        if (loginname != null && loginpass != null && !"1".equals(statu)) {
            User user = userService.login(loginname, loginpass);
            if (user == null) {
                return;
            }
            //把user放到session中
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/web/jsps/main.jsp");
        }else{
            chain.doFilter(req, resp);
        }

    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}

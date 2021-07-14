package com.zdj.TMBookStore.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 华韵流风
 * @ClassName ${NAME}
 * @Description TODO
 * @Date 2021/5/16 14:40
 * @packageName ${PACKAGE_NAME}
 */
@WebFilter(filterName = "PermissionFilter", urlPatterns = {"/web/adminjsps/admin/*"})
public class PermissionFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        if (session==null||session.getAttribute("adminUser") == null) {
            response.sendRedirect(request.getContextPath() + "/web/adminjsps/login.jsp");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}

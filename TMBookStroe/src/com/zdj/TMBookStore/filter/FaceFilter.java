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
 * @Date 2021/5/28 19:43
 * @packageName ${PACKAGE_NAME}
 */
@WebFilter(filterName = "FaceFilter", urlPatterns = {"/web/jsps/order/*", "/web/jsps/cart/*","/web/jsps/user/pwd.jsp"})
public class FaceFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/web/jsps/user/login.jsp");
            return;
        }
        chain.doFilter(request, response);


    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}

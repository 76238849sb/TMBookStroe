package com.zdj.TMBookStore.filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 用注解代替web.xml中的配置内容
 *
 * @author 华韵流风
 */
@WebFilter(filterName = "EncodingFilter", urlPatterns = "/web/*", initParams = {@WebInitParam(name = "encoding", value = "utf-8")})
public class EncodingFilter implements Filter {

    private String encoding;

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String method = request.getMethod();
        if ("post".equalsIgnoreCase(method)) {
            request.setCharacterEncoding(encoding);
        }
        request.setCharacterEncoding(encoding);
        chain.doFilter(request, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");
    }

}

package com.zdj.TMBookStore.utils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author 华韵流风
 * @ClassName ${NAME}
 * @Description TODO
 * @Date 2021/5/11 16:27
 * @packageName ${PACKAGE_NAME}
 * 在整个后台，主要功能是商品管理，对商品的所有操作统一使用同一个Servlet
 */
@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //1、对客户端的输出统一为utf-8的编码
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=utf-8");
        //2、得到method参数值
        String method = req.getParameter("method");
        //3、判断当前Servlet中是否有method方法，如果有就调用该方法，用到反射
        Method processMethod;
        try {
            processMethod = this.getClass().getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
        } catch (NoSuchMethodException e) {
            throw  new RuntimeException("方法不存在！");
        }
        //4 调用方法,也就是子类中添加的处理请求的方法，接收方法的返回值
        try {
            String result = (String) processMethod.invoke(this, req, res);
            //判断是否是重定向还是转发，取冒号所在的位置
            int pos = result.indexOf(":");
            //得到冒号前面的字符
            String c = result.substring(0,1);
            String path = result.substring(pos+1);
            if("f".equals(c)){
                req.getRequestDispatcher(path).forward(req,res);
            }else if ("s".equals(c)){
                res.sendRedirect(req.getContextPath()+path);
            }else if ("a".equals(c)){
                res.getWriter().print(path);
            }else if ("d".equals(c)){
                drawImg(res,path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawImg(HttpServletResponse response, String imgPath) throws IOException {
        String absolutePath = getServletContext().getRealPath("/web/");

        //得到图片的绝对路径
        String filePath = absolutePath + imgPath;
        FileInputStream fis = new FileInputStream(filePath);
        ServletOutputStream os = response.getOutputStream();
        int len;
        byte[] b = new byte[1024];
        while ((len = fis.read(b)) != -1) {
            os.write(b, 0, len);
        }
        os.flush();
        os.close();
        fis.close();

    }
}

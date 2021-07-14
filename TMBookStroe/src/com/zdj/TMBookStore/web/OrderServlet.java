package com.zdj.TMBookStore.web;

import com.zdj.TMBookStore.dao.AdminDao;
import com.zdj.TMBookStore.dao.impl.AdminDaoImpl;
import com.zdj.TMBookStore.po.Order;
import com.zdj.TMBookStore.po.OrderItem;
import com.zdj.TMBookStore.service.AdminService;
import com.zdj.TMBookStore.service.CarService;
import com.zdj.TMBookStore.service.OrderService;
import com.zdj.TMBookStore.service.impl.AdminServiceImpl;
import com.zdj.TMBookStore.service.impl.CarServiceImpl;
import com.zdj.TMBookStore.service.impl.OrderServiceImpl;
import com.zdj.TMBookStore.utils.BaseServlet;
import com.zdj.TMBookStore.utils.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 华韵流风
 * @ClassName ${NAME}
 * @Description TODO
 * @Date 2021/5/26 8:59
 * @packageName ${PACKAGE_NAME}
 */
@WebServlet(name = "OrderServlet",urlPatterns = "/web/orderServlet")
public class OrderServlet extends BaseServlet {

    private final OrderService orderService = new OrderServiceImpl();

    public String findAllOrderInfo(HttpServletRequest request, HttpServletResponse response){
        String pageNow = request.getParameter("pageNow");
        PageBean<Order> pageBean = orderService.findAllOrder(Integer.valueOf(pageNow),16);
        pageBean.setUrl(getUrl(request));
        request.setAttribute("pageBean", pageBean);
        request.setAttribute("pageNow", 1);

        return "f:/web/adminjsps/admin/order/list.jsp";
    }


    public String showOrderItem(HttpServletRequest request, HttpServletResponse response){
        String oid = request.getParameter("oid");
        List<OrderItem> orderItems = orderService.findOrderItem(oid);
        Order order = orderService.findOrder(oid);
        request.setAttribute("orderItems", orderItems);
        request.setAttribute("order", order);

        return "f:/web/adminjsps/admin/order/desc.jsp";
    }


    public String setStatus(HttpServletRequest request, HttpServletResponse response){
        String status = request.getParameter("status");
        String oid = request.getParameter("oid");

        orderService.setOrderStatus(Integer.valueOf(status), oid);

        StringBuilder orderType = new StringBuilder("{\"orderType\""+":\"");
        if("1".equals(status)){
            orderType.append( "未付款");
        }else if("2".equals(status)){
            orderType .append("已付款");
        }else if("3".equals(status)){
            orderType.append("已发货");
        }else if("4".equals(status)){
            orderType.append("交易成功");
        }else if("5".equals(status)){
            orderType.append("已取消");
        }
       orderType.append("\"}");

        return "a:"+orderType;
    }


    public String findCategoryOrder(HttpServletRequest request, HttpServletResponse response) {
        String status = request.getParameter("status");
        String pageNow = request.getParameter("pageNow");
        PageBean<Order> pageBean = orderService.findCategoryOrder(Integer.valueOf(pageNow),16,Integer.valueOf(status));
        pageBean.setUrl(getUrl(request));
        request.setAttribute("pageBean", pageBean);
        request.setAttribute("pageNow", 1);

        return "f:/web/adminjsps/admin/order/list.jsp";


    }

    private String getUrl(HttpServletRequest request) {
        String url = request.getRequestURI() + "?" + request.getQueryString();
        int index = url.lastIndexOf("&pageNow");
        url = url.substring(0, index);
        return url;
    }
}

package com.zdj.TMBookStore.face;

import com.zdj.TMBookStore.po.*;
import com.zdj.TMBookStore.service.CarService;
import com.zdj.TMBookStore.service.OrderService;
import com.zdj.TMBookStore.service.impl.CarServiceImpl;
import com.zdj.TMBookStore.service.impl.OrderServiceImpl;
import com.zdj.TMBookStore.utils.BaseServlet;
import com.zdj.TMBookStore.utils.BeanUtil;
import com.zdj.TMBookStore.utils.PageBean;
import com.zdj.TMBookStore.utils.SendEmailMsg;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 华韵流风
 * @ClassName ${NAME}
 * @Description TODO
 * @Date 2021/5/28 11:00
 * @packageName ${PACKAGE_NAME}
 */
@WebServlet(name = "FaceCarServlet", urlPatterns = "/face/faceCarServlet")
public class FaceCarServlet extends BaseServlet {

    private final CarService carService = new CarServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    public String findCarList(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("user") == null || request.getSession().getAttribute("user") == null) {
            return "s:/web/jsps/user/login.jsp";
        }

        User user = (User) request.getSession().getAttribute("user");
        List<CarItemList> carList = carService.findCarList(user.getUid());
        double total = 0;
        for (CarItemList carItemList : carList) {
            total += carItemList.getSubtotal();
        }
        request.setAttribute("carList", carList);
        request.setAttribute("total", total);

        return "f:/web/jsps/cart/list.jsp";
    }


    public String addCarItem(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("user") == null || request.getSession().getAttribute("user") == null) {
            return "s:/web/jsps/user/login.jsp";
        }

        CarItem carItem = BeanUtil.toBean(request.getParameterMap(), CarItem.class);
        User user = (User) request.getSession().getAttribute("user");
        carItem.setUid(user.getUid());
        carService.addCarItem(carItem);
        return "s:/face/faceBookServlet?method=findCategoryBookInfo&pageNow=1&add=1";
    }

    public String deleteCarItem(HttpServletRequest request, HttpServletResponse response) {
        String cartItemId = request.getParameter("cartItemId");
        String result = "失败";
        String subTotal = "";
        try {
            carService.deleteItem(cartItemId);
            result = "成功";

            String uid = ((User) request.getSession().getAttribute("user")).getUid();
            List<CarItemList> carList = carService.findCarList(uid);
            double total = 0;
            for (CarItemList carItemList : carList) {
                total += carItemList.getSubtotal();
            }

            subTotal = String.valueOf(total);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "a:{\"result" + "\":" + "\"" + result + "\",\"subTotal" + "\":\"" + subTotal + "\"}";
    }


    public String submitOrder(HttpServletRequest request, HttpServletResponse response) {
        String address = request.getParameter("address");
        String total = request.getParameter("total");
        String timeNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String uid = ((User) request.getSession().getAttribute("user")).getUid();
        String oid = UUID.randomUUID().toString().replaceAll("-", "").trim();
        Order order = new Order();
        order.setOid(oid);
        order.setAddress(address);
        order.setStatus(1);
        order.setUid(uid);
        order.setTotal(Double.valueOf(total));
        order.setOrdertime(timeNow);
        orderService.addOrder(order);

        List<CarItemList> carList = carService.findCarList(uid);

        for (CarItemList carItemList : carList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(carItemList.getQuantity());
            orderItem.setSubtotal(carItemList.getSubtotal());
            orderItem.setBid(carItemList.getBid());
            orderItem.setBname(carItemList.getBname());
            orderItem.setCurrPrice(carItemList.getCurrPrice());
            orderItem.setImage_b(carItemList.getImage_b());
            orderItem.setOid(oid);
            orderService.addOrderItem(orderItem);
        }
        //清空购物车
        carService.deleteCar(uid);

        request.setAttribute("oid", oid);
        request.setAttribute("carList", carList);
        return "f:/web/jsps/cart/showitem.jsp";
    }

    public String checkPay(HttpServletRequest request, HttpServletResponse response){
        String oid = request.getParameter("oid");
        Order order = carService.findOrder(oid);
        String result = "成功";
        if(order.getStatus()==2||order.getStatus()==4||order.getStatus()==3){
            result = "失败";

        }

        return "a:{\"result\":\"" + result + "\"}";
    }

    public String gotoPay(HttpServletRequest request, HttpServletResponse response) {
        String oid = request.getParameter("oid");
        Order order = carService.findOrder(oid);
        request.setAttribute("order", order);

        return "f:/web/jsps/order/pay.jsp";
    }

    public String findAllOrder(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("user") == null || request.getSession().getAttribute("user") == null) {
            return "s:/web/jsps/user/login.jsp";
        }

        String uid = ((User) request.getSession().getAttribute("user")).getUid();
        String pageNow = request.getParameter("pageNow");

        PageBean<Order> myOrder = carService.findMyOrder(uid, Integer.valueOf(pageNow), 6);

        for (Order order : myOrder.getList()) {
            order.setImages(carService.findOrderImg(order.getOid()));
        }

        myOrder.setUrl(getUrl(request));
        request.setAttribute("pageBean", myOrder);

        return "f:/web/jsps/order/list.jsp";
    }


    public String showMyOrderItem(HttpServletRequest request, HttpServletResponse response) {
        String oid = request.getParameter("oid");
        Order order = carService.findOrder(oid);
        List<OrderItem> myOrderItem = carService.findMyOrderItem(oid);
        request.setAttribute("orderItem", myOrderItem);
        request.setAttribute("order", order);
        return "f:/web/jsps/order/desc.jsp";
    }

    public String cancelOrder(HttpServletRequest request, HttpServletResponse response) {
        String oid = request.getParameter("oid");
        String result = "失败";
        Order order = carService.findOrder(oid);
        if (order.getStatus() == 4) {
            return "a:{\"result\":\"" + result + "\"}";
        }

        try {
            carService.cancelOrder(oid);
            result = "成功";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "a:{\"result\":\"" + result + "\"}";
    }

    public String receiptOrder(HttpServletRequest request, HttpServletResponse response) {

        String oid = request.getParameter("oid");
        String result = "失败";
        Integer status = carService.findOrder(oid).getStatus();
        if (status != 3) {
            return "a:{\"result\":\"" + result + "\"}";
        }

        try {
            carService.receiptOrder(oid);
            result = "成功";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "a:{\"result\":\"" + result + "\"}";
    }






    public String payOrder(HttpServletRequest request, HttpServletResponse response) {

        String oid = request.getParameter("oid");
        String result = "失败";
        User user = (User) request.getSession().getAttribute("user");
        Order order = carService.findOrder(oid);
        if (order.getStatus() == 2 || order.getStatus() == 3 || order.getStatus() == 4) {
            return "a:{\"result\":\"" + result + "\"}";
        }

        try {
            carService.payOrder(oid);
            result = "成功";


            String email = user.getEmail();
            String title = "您好，您有一份新订单：";

            String massage = "尊敬的" + user.getLoginname() + "，您好！您成功购买以下商品：<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;订单编号：" + order.getOid() + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;订单日期：" + order.getOrdertime() + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：" + order.getTotal() + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;收货地址：" + order.getAddress() + "<br>感谢您的支持！期待您的下次惠顾！";
            SendEmailMsg.send(email, title, massage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "a:{\"result\":\"" + result + "\"}";
    }


    private String getUrl(HttpServletRequest request) {
        String url = request.getRequestURI() + "?" + request.getQueryString();
        int index = url.lastIndexOf("&pageNow");
        url = url.substring(0, index);
        return url;
    }


}

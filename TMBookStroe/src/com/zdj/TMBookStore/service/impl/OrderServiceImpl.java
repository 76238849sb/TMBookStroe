package com.zdj.TMBookStore.service.impl;

import com.zdj.TMBookStore.dao.OrderDao;
import com.zdj.TMBookStore.dao.impl.OrderDaoImpl;
import com.zdj.TMBookStore.po.Order;
import com.zdj.TMBookStore.po.OrderItem;
import com.zdj.TMBookStore.service.OrderService;
import com.zdj.TMBookStore.utils.DruidPool;
import com.zdj.TMBookStore.utils.PageBean;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 华韵流风
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @Date 2021/5/27 15:35
 * @packageName com.zdj.TMBookStore.service.impl
 */
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao = new OrderDaoImpl();

    @Override
    public PageBean<Order> findAllOrder(Integer pageNow,Integer pageCount) {
        try {
            return orderDao.findAllOrder(pageNow,pageCount);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException("查询所有订单失败！");
        }
    }

    @Override
    public PageBean<Order> findCategoryOrder(Integer pageNow,Integer pageCount, Integer status) {
        try {
            return orderDao.findCategoryOrder(pageNow,pageCount,status);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException("查询分类订单失败！");
        }
    }

    @Override
    public List<OrderItem> findOrderItem(String oid) {
        try {
            return orderDao.findOrderItem(oid);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException("查询订单详情失败！");
        }
    }

    @Override
    public Order findOrder(String oid) {
        try {
            return orderDao.findOrder(oid);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException("查询指定订单信息失败！");
        }
    }

    @Override
    public void setOrderStatus(Integer status, String oid) {
        try {
            orderDao.setOrderStatus(status, oid);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException("设置订单状态失败！");
        }
    }

    @Override
    public void addOrder(Order order) {
        try {
            DruidPool.beginTransaction();
            orderDao.addOrder(order);
            DruidPool.commitTransaction();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            try {
                DruidPool.rollbackTransaction();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            throw new RuntimeException("添加订单失败！");
        }
    }

    @Override
    public void addOrderItem(OrderItem orderItem) {
        try {
            DruidPool.beginTransaction();
            orderDao.addOrderItem(orderItem);
            DruidPool.commitTransaction();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            try {
                DruidPool.rollbackTransaction();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            throw new RuntimeException("添加订单明细失败！");
        }
    }
}

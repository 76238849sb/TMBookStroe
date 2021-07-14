package com.zdj.TMBookStore.service;

import com.zdj.TMBookStore.po.Order;
import com.zdj.TMBookStore.po.OrderItem;
import com.zdj.TMBookStore.utils.PageBean;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 华韵流风
 * @ClassName OrderService
 * @Description TODO
 * @Date 2021/5/27 15:32
 * @packageName com.zdj.TMBookStore.service
 */
public interface OrderService {

    /**
     * 查询所有订单
     *
     * @return PageBean<Order>
     * @param pageNow pageNow
     * @param pageCount pageCount
     */
    PageBean<Order> findAllOrder(Integer pageNow,Integer pageCount);

    /**
     * 查询某个状态的订单
     *
     * @param pageNow pageNow
     * @param status status
     * @param pageCount pageCount
     * @return PageBean<Order>
     */
    PageBean<Order> findCategoryOrder(Integer pageNow,Integer pageCount, Integer status);

    /**
     * 查询该订单的订单明细
     *
     * @param oid oid
     * @return List<OrderItem>
     */
    List<OrderItem> findOrderItem(String oid);

    /**
     * 查询该订单的订单明细
     *
     * @param oid oid
     * @return Order
     */
    Order findOrder(String oid);

    /**
     * 设置订单状态
     *
     * @param status status
     * @param oid oid
     */
    void setOrderStatus(Integer status,String oid);


    /**
     * 添加订单
     *
     * @param order order
     */
    void addOrder(Order order);

    /**
     * 添加订单明细
     *
     * @param orderItem orderItem
     */
    void addOrderItem(OrderItem orderItem);
}

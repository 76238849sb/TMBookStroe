package com.zdj.TMBookStore.dao;

import com.zdj.TMBookStore.po.Order;
import com.zdj.TMBookStore.po.OrderItem;
import com.zdj.TMBookStore.utils.PageBean;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 华韵流风
 * @ClassName OrderDao
 * @Description TODO
 * @Date 2021/5/27 15:14
 * @packageName com.zdj.TMBookStore.dao
 */
public interface OrderDao {

    /**
     * 查询所有订单
     *
     * @return PageBean<Order>
     * @param pageNow pageNow
     * @param pageCount pageCount
     * @throws SQLException SQL
     */
    PageBean<Order> findAllOrder(Integer pageNow,Integer pageCount) throws SQLException;

    /**
     * 查询某个状态的订单
     *
     * @param pageNow pageNow
     * @param status status
     * @param pageCount pageCount
     * @return PageBean<Order>
     * @throws SQLException SQL
     */
    PageBean<Order> findCategoryOrder(Integer pageNow,Integer pageCount, Integer status) throws SQLException;

    /**
     * 查询该订单的订单明细
     *
     * @param oid oid
     * @return List<OrderItem>
     * @throws SQLException SQL
     */
    List<OrderItem> findOrderItem(String oid) throws SQLException;

    /**
     * 查询该订单的订单明细
     *
     * @param oid oid
     * @return Order
     * @throws SQLException SQL
     */
    Order findOrder(String oid) throws SQLException;

    /**
     * 设置订单状态
     *
     * @param status status
     * @param oid oid
     * @throws SQLException SQL
     */
    void setOrderStatus(Integer status,String oid)throws SQLException;

    /**
     * 添加订单
     *
     * @param order order
     * @throws SQLException SQL
     */
    void addOrder(Order order) throws SQLException;

    /**
     * 添加订单明细
     *
     * @param orderItem orderItem
     * @throws SQLException SQL
     */
    void addOrderItem(OrderItem orderItem) throws SQLException;
}

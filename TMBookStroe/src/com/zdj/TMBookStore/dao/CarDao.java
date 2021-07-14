package com.zdj.TMBookStore.dao;

import com.zdj.TMBookStore.po.CarItem;
import com.zdj.TMBookStore.po.CarItemList;
import com.zdj.TMBookStore.po.Order;
import com.zdj.TMBookStore.po.OrderItem;
import com.zdj.TMBookStore.utils.PageBean;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 华韵流风
 * @ClassName CarDao
 * @Description TODO
 * @Date 2021/5/28 20:23
 * @packageName com.zdj.TMBookStore.dao
 */
public interface CarDao {

    /**
     * 查询该用户的订单明细
     *
     * @param uid uid
     * @throws SQLException SQL
     * @return List<CarItemList>
     */
    List<CarItemList> findCarList(String uid) throws SQLException;

    /**
     * 删除订单项
     *
     * @param cartItemId cartItemId
     * @throws SQLException SQL
     */
    void deleteItem(String cartItemId) throws SQLException;

    /**
     * 添加订单
     *
     * @param carItem carItem
     * @throws SQLException SQL
     */
    void addCarItem(CarItem carItem) throws SQLException;

    /**
     * 查询该用户所有订单
     *
     * @param uid uid
     * @param pageNow pageNow
     * @param pageCount pageCount
     * @return PageBean<Order>
     * @throws SQLException SQL
     */
    PageBean<Order> findMyOrder(String uid,Integer pageNow,Integer pageCount) throws SQLException;

    /**
     * 查询该订单的图书图片
     *
     * @return List<String>
     * @param oid oid
     * @throws SQLException SQL
     */
    List<String> findOrderImg(String oid) throws SQLException;

    /**
     * 查询订单详情
     *
     * @param oid oid
     * @return List<OrderItem>
     * @throws SQLException SQL
     */
    List<OrderItem> findMyOrderItem(String oid) throws SQLException;

    /**
     * 清空购物车
     *
     * @param uid uid
     * @throws SQLException SQL
     */
    void deleteCar(String uid) throws SQLException;

    /**
     * 查询当前订单
     *
     * @param oid oid
     * @return Order
     * @throws SQLException SQL
     */
    Order findOrder(String oid) throws SQLException;

    /**
     * 取消订单
     *
     * @param oid oid
     * @throws SQLException SQL
     */
    void cancelOrder(String oid) throws SQLException;

    /**
     * 确认收货
     *
     * @param oid oid
     * @throws SQLException SQL
     */
    void receiptOrder(String oid) throws SQLException;

    /**
     * 支付订单
     *
     * @param oid oid
     * @throws SQLException SQL
     */
    void payOrder(String oid) throws SQLException;

}

package com.zdj.TMBookStore.service;

import com.zdj.TMBookStore.po.CarItem;
import com.zdj.TMBookStore.po.CarItemList;
import com.zdj.TMBookStore.po.Order;
import com.zdj.TMBookStore.po.OrderItem;
import com.zdj.TMBookStore.utils.PageBean;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 华韵流风
 * @ClassName CarService
 * @Description TODO
 * @Date 2021/5/28 20:43
 * @packageName com.zdj.TMBookStore.service
 */
public interface CarService {

    /**
     * 查询所有商品
     *
     * @param uid uid
     * @return List<CarItemList>
     */
    List<CarItemList> findCarList(String uid);

    /**
     * 删除订单项
     *
     * @param cartItemId cartItemId
     */
    void deleteItem(String cartItemId);

    /**
     * 添加订单
     *
     * @param carItem carItem
     */
    void addCarItem(CarItem carItem);

    /**
     * 查询该用户所有订单
     *
     * @param uid uid
     * @param pageNow pageNow
     * @param pageCount pageCount
     * @return PageBean<Order>
     */
    PageBean<Order> findMyOrder(String uid, Integer pageNow,Integer pageCount);

    /**
     * 查询该订单的图书图片
     *
     * @param oid oid
     * @return List<String>
     */
    List<String> findOrderImg(String oid);

    /**
     * 查询订单详情
     *
     * @param oid oid
     * @return List<OrderItem>
     */
    List<OrderItem> findMyOrderItem(String oid);

    /**
     * 清空购物车
     *
     * @param uid uid
     */
    void deleteCar(String uid);

    /**
     * 查询当前订单
     *
     * @param oid oid
     * @return Order
     */
    Order findOrder(String oid);

    /**
     * 取消订单
     *
     * @param oid oid
     */
    void cancelOrder(String oid);

    /**
     * 确认收货
     *
     * @param oid oid
     */
    void receiptOrder(String oid);

    /**
     * 支付订单
     *
     * @param oid oid
     */
    void payOrder(String oid);
}

package com.zdj.TMBookStore.service.impl;

import com.zdj.TMBookStore.dao.CarDao;
import com.zdj.TMBookStore.dao.impl.CarDaoImpl;
import com.zdj.TMBookStore.po.CarItem;
import com.zdj.TMBookStore.po.CarItemList;
import com.zdj.TMBookStore.po.Order;
import com.zdj.TMBookStore.po.OrderItem;
import com.zdj.TMBookStore.service.CarService;
import com.zdj.TMBookStore.utils.DruidPool;
import com.zdj.TMBookStore.utils.PageBean;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 华韵流风
 * @ClassName CarServiceImpl
 * @Description TODO
 * @Date 2021/5/28 20:45
 * @packageName com.zdj.TMBookStore.service.impl
 */
public class CarServiceImpl implements CarService {

    private final CarDao carDao = new CarDaoImpl();


    @Override
    public List<CarItemList> findCarList(String uid){
        try {
            return carDao.findCarList(uid);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException("查询该用户订单明细失败！");
        }
    }

    @Override
    public void deleteItem(String cartItemId) {
        try {
            DruidPool.beginTransaction();
            carDao.deleteItem(cartItemId);
            DruidPool.commitTransaction();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            try {
                DruidPool.rollbackTransaction();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            throw new RuntimeException("删除该图书订单失败！");
        }
    }

    @Override
    public void addCarItem(CarItem carItem) {



        try {
            DruidPool.beginTransaction();
            carDao.addCarItem(carItem);
            DruidPool.commitTransaction();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            try {
                DruidPool.rollbackTransaction();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            throw new RuntimeException("添加该图书订单失败！");
        }
    }

    @Override
    public PageBean<Order> findMyOrder(String uid, Integer pageNow,Integer pageCount) {
        try {
            return carDao.findMyOrder(uid,pageNow,pageCount);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException("查询该用户订单失败！");
        }
    }

    @Override
    public List<String> findOrderImg(String oid) {
        try {
            return carDao.findOrderImg(oid);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException("查询该用户订单图片失败！");
        }
    }

    @Override
    public List<OrderItem> findMyOrderItem(String oid) {
        try {
            return carDao.findMyOrderItem(oid);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException("查询该用户订单详情失败！");
        }
    }

    @Override
    public Order findOrder(String oid){
        try {
            return carDao.findOrder(oid);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException("查询该用户当前订单失败！");
        }
    }

    @Override
    public void cancelOrder(String oid) {
        try {
            DruidPool.beginTransaction();
            carDao.cancelOrder(oid);
            DruidPool.commitTransaction();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            try {
                DruidPool.rollbackTransaction();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            throw new RuntimeException("取消当前订单失败！");
        }
    }

    @Override
    public void receiptOrder(String oid) {
        try {
            DruidPool.beginTransaction();
            carDao.receiptOrder(oid);
            DruidPool.commitTransaction();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            try {
                DruidPool.rollbackTransaction();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            throw new RuntimeException("收货当前订单失败！");
        }
    }

    @Override
    public void payOrder(String oid) {
        try {
            DruidPool.beginTransaction();
            carDao.payOrder(oid);
            DruidPool.commitTransaction();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            try {
                DruidPool.rollbackTransaction();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            throw new RuntimeException("支付当前订单失败！");
        }
    }

    @Override
    public void deleteCar(String uid) {
        try {
            DruidPool.beginTransaction();
            carDao.deleteCar(uid);
            DruidPool.commitTransaction();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            try {
                DruidPool.rollbackTransaction();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            throw new RuntimeException("清空购物车失败！");
        }
    }
}

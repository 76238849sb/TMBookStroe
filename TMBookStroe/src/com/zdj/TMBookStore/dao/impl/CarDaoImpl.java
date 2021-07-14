package com.zdj.TMBookStore.dao.impl;

import com.zdj.TMBookStore.dao.CarDao;
import com.zdj.TMBookStore.po.CarItem;
import com.zdj.TMBookStore.po.CarItemList;
import com.zdj.TMBookStore.po.Order;
import com.zdj.TMBookStore.po.OrderItem;
import com.zdj.TMBookStore.utils.PageBean;
import com.zdj.TMBookStore.utils.TxQueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author 华韵流风
 * @ClassName CarDaoImpl
 * @Description TODO
 * @Date 2021/5/28 20:45
 * @packageName com.zdj.TMBookStore.dao.impl
 */
public class CarDaoImpl implements CarDao {

    private final TxQueryRunner tqr = new TxQueryRunner();

    @Override
    public List<CarItemList> findCarList(String uid) throws SQLException {
        String sql = "select c.cartItemId,c.bid,image_b,b.bname,b.currPrice,c.quantity,(b.currPrice*c.quantity) subtotal from t_cartitem c,t_book b where c.bid=b.bid and c.uid=?";
        return tqr.query(sql, new BeanListHandler<>(CarItemList.class), uid);
    }


    @Override
    public void deleteItem(String cartItemId) throws SQLException {
        String sql = "delete from t_cartitem where cartItemId=?";
        tqr.update(sql, cartItemId);
    }

    @Override
    public void addCarItem(CarItem carItem) throws SQLException {
        CarItem oldCar = selectBook(carItem.getBid());
        String sql;
        if (oldCar != null) {
            sql = "update t_cartitem set quantity=? where cartItemId=?";
            tqr.update(sql, carItem.getQuantity(), oldCar.getCartItemId());
        } else {
            String cartItemId = UUID.randomUUID().toString().trim().replaceAll("-", "");
            carItem.setCartItemId(cartItemId);
            sql = "insert into t_cartitem(cartItemId, quantity, bid, uid) value (?,?,?,?)";
            tqr.update(sql, carItem.getCartItemId(), carItem.getQuantity(), carItem.getBid(), carItem.getUid());
        }

    }

    private CarItem selectBook(String bid) {
        String sql = "select * from t_cartitem where bid=?";
        try {
            return tqr.query(sql, new BeanHandler<>(CarItem.class), bid);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public PageBean<Order> findMyOrder(String uid, Integer pageNow, Integer pageCount) throws SQLException {
        PageBean<Order> pageBean = new PageBean<>();
        String sql = "select * from t_order where uid = ? limit ?,?";

        //设置list
        List<Order> list = tqr.query(sql, new BeanListHandler<>(Order.class), uid, (pageNow - 1) * pageCount, pageCount);
        pageBean.setList(list);

        //设置每页记录数
        pageBean.setPageCount(pageCount);

        //设置当前页
        pageBean.setPageNow(pageNow);

        sql = "select count(*) from t_order where uid=?";

        //设置总记录数
        String result = tqr.query(sql, new ScalarHandler(), uid).toString();
        Integer totalCount = Integer.parseInt(result);
        pageBean.setTotalCount(totalCount);

        //设置总页数
        Integer totalPage = (int) totalCount % pageCount == 0 ? (int) totalCount / pageCount : (int) totalCount / pageCount + 1;
        pageBean.setTotalPage(totalPage);

        return pageBean;
    }

    @Override
    public List<String> findOrderImg(String oid) throws SQLException {
        String sql = "select image_b from t_orderitem where oid=?";
        List<String> imgs = new ArrayList<>();
        List<OrderItem> result = tqr.query(sql, new BeanListHandler<>(OrderItem.class), oid);
        for (OrderItem orderItem : result) {
            imgs.add(orderItem.getImage_b());
        }
        return imgs;
    }

    @Override
    public List<OrderItem> findMyOrderItem(String oid) throws SQLException {
        String sql = "select * from t_orderitem where oid = ?";
        return tqr.query(sql, new BeanListHandler<>(OrderItem.class), oid);
    }

    @Override
    public Order findOrder(String oid) throws SQLException {
        String sql = "select * from t_order where oid=?";
        return tqr.query(sql, new BeanHandler<>(Order.class), oid);
    }

    @Override
    public void cancelOrder(String oid) throws SQLException {
        String sql = "update t_order set status=5 where oid=?";
        tqr.update(sql, oid);
    }

    @Override
    public void receiptOrder(String oid) throws SQLException {
        String sql = "update t_order set status=4 where oid=?";
        tqr.update(sql, oid);
    }

    @Override
    public void payOrder(String oid) throws SQLException {
        String sql = "update t_order set status=2 where oid=?";
        tqr.update(sql, oid);
    }

    @Override
    public void deleteCar(String uid) throws SQLException {
        String sql = "delete from t_cartitem where uid=?";
        tqr.update(sql,uid);
    }
}

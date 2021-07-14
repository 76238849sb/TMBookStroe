package com.zdj.TMBookStore.dao.impl;

import com.zdj.TMBookStore.dao.OrderDao;
import com.zdj.TMBookStore.po.Order;
import com.zdj.TMBookStore.po.OrderItem;
import com.zdj.TMBookStore.utils.PageBean;
import com.zdj.TMBookStore.utils.TxQueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * @author 华韵流风
 * @ClassName OrderDaoImpl
 * @Description TODO
 * @Date 2021/5/27 15:16
 * @packageName com.zdj.TMBookStore.dao.impl
 */
public class OrderDaoImpl implements OrderDao {

    private final TxQueryRunner tqr = new TxQueryRunner();
//    private final Integer pageCount = 15;

    @Override
    public PageBean<Order> findAllOrder(Integer pageNow,Integer pageCount) throws SQLException {
        PageBean<Order> pageBean = new PageBean<>();
        String sql = "select * from t_order limit ?,?";

        //设置list
        List<Order> list = tqr.query(sql, new BeanListHandler<>(Order.class),  (pageNow - 1) * pageCount, pageCount);
        pageBean.setList(list);

        //设置每页记录数
        pageBean.setPageCount(pageCount);

        //设置当前页
        pageBean.setPageNow(pageNow);

        sql = "select count(*) from t_order";

        //设置总记录数
        String result = tqr.query(sql, new ScalarHandler()).toString();
        Integer totalCount = Integer.parseInt(result);
        pageBean.setTotalCount(totalCount);

        //设置总页数
        Integer totalPage = (int) totalCount % pageCount == 0 ? (int) totalCount / pageCount : (int) totalCount / pageCount + 1;
        pageBean.setTotalPage(totalPage);

        return pageBean;
    }

    @Override
    public PageBean<Order> findCategoryOrder(Integer pageNow,Integer pageCount, Integer status) throws SQLException {
        PageBean<Order> pageBean = new PageBean<>();
        String sql = "select * from t_order where status=? limit ?,?";

        //设置list
        List<Order> list = tqr.query(sql, new BeanListHandler<>(Order.class),  status,(pageNow - 1) * pageCount, pageCount);
        pageBean.setList(list);

        //设置每页记录数
        pageBean.setPageCount(pageCount);

        //设置当前页
        pageBean.setPageNow(pageNow);

        sql = "select count(*) from t_order where status=?";

        //设置总记录数
        String result = tqr.query(sql, new ScalarHandler(),status).toString();
        Integer totalCount = Integer.parseInt(result);
        pageBean.setTotalCount(totalCount);

        //设置总页数
        Integer totalPage = (int) totalCount % pageCount == 0 ? (int) totalCount / pageCount : (int) totalCount / pageCount + 1;
        pageBean.setTotalPage(totalPage);

        return pageBean;
    }

    @Override
    public List<OrderItem> findOrderItem(String oid) throws SQLException {
        String sql = "select * from t_orderitem where oid=?";
        return tqr.query(sql, new BeanListHandler<>(OrderItem.class),oid);
    }


    @Override
    public Order findOrder(String oid) throws SQLException {
        String sql = "select * from t_order where oid=?";
        return tqr.query(sql, new BeanHandler<>(Order.class),oid);
    }

    @Override
    public void setOrderStatus(Integer status,String oid) throws SQLException {
        String sql = "update t_order set status=? where oid=?";
        tqr.update(sql,status,oid);
    }

    @Override
    public void addOrder(Order order) throws SQLException {
        String sql = "insert into t_order values (?,?,?,?,?,?)";
        tqr.update(sql,order.getOid(),order.getOrdertime(),order.getTotal(),order.getStatus(),order.getAddress(),order.getUid());
    }

    @Override
    public void addOrderItem(OrderItem orderItem) throws SQLException {
        String orderItemId = UUID.randomUUID().toString().trim().replaceAll("-", "");
        String sql = "insert into t_orderitem (orderItemId, quantity, subtotal, bid, bname, currPrice, image_b, oid) values (?,?,?,?,?,?,?,?);";
        tqr.update(sql,orderItemId,orderItem.getQuantity(),orderItem.getSubtotal(),orderItem.getBid(),orderItem.getBname(),orderItem.getCurrPrice(),orderItem.getImage_b(),orderItem.getOid());
    }
}

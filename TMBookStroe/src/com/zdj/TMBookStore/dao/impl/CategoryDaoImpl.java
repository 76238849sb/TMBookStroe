package com.zdj.TMBookStore.dao.impl;

import com.zdj.TMBookStore.dao.CategoryDao;
import com.zdj.TMBookStore.po.CategoryList;
import com.zdj.TMBookStore.po.CategoryListTwo;
import com.zdj.TMBookStore.utils.TxQueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * @author 华韵流风
 * @ClassName CategoryDao
 * @Description TODO
 * @Date 2021/5/27 15:15
 * @packageName com.zdj.TMBookStore.dao.impl
 */
public class CategoryDaoImpl implements CategoryDao {
    private final TxQueryRunner tqr = new TxQueryRunner();

    @Override
    public List<CategoryList> findAllCategory() throws SQLException {
        String sql = "select * from t_category where pid is null order by cid";
        List<CategoryList> list = tqr.query(sql, new BeanListHandler<>(CategoryList.class));
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setCategoryListTwos(findCategoryTwo(list.get(i).getCid()));
        }
        return list;
    }


    @Override
    public List<CategoryListTwo> findCategoryTwo(String pid) throws SQLException {
        String sql = "select  a.* from t_category a , t_category b where a.pid=b.cid and a.pid=? order by a.orderBy";
        return tqr.query(sql, new BeanListHandler<>(CategoryListTwo.class), pid);
    }

    @Override
    public void addBookLevelOne(String cname, String desc) throws SQLException {
        String cid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        String sql = "insert into t_category(cid,cname,`desc`) value(?,?,?)";
        tqr.update(sql, cid, cname, desc);
    }

    @Override
    public void updateOneLevel(String cname, String desc, String cid) throws SQLException {
        String sql = "update  t_category set cname = ? , `desc` = ? where cid = ?;";
        tqr.update(sql, cname, desc, cid);
    }

    @Override
    public void addBookLevelTwo(String came, String pid, String desc) throws SQLException {
        String cid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        String sql = "insert into t_category(cid,cname,pid,`desc`) value (?,?,?,?) ";
        tqr.update(sql, cid, came, pid, desc);
    }

    @Override
    public void updateTwoLevel(String cname, String desc, String cid, String pid) throws SQLException {
        String sql = "update  t_category set cname = ? , `desc` = ?,pid=? where cid = ?";
        tqr.update(sql, cname, desc, pid, cid);

    }

    @Override
    public void deleteOneLevel(String cid) throws SQLException {

        String sql = "DELETE FROM t_category WHERE pid=?";
        tqr.update(sql, cid);
        sql = "DELETE FROM t_category WHERE cid=?";
        tqr.update(sql, cid);


    }

    @Override
    public void deleteTwoLevel(String cid) throws SQLException {
        String sql = "delete from t_book where cid=?";
        tqr.update(sql, cid);
        sql = "DELETE FROM t_category WHERE cid=?";
        tqr.update(sql, cid);

    }

    @Override
    public Long checkCategoryName(String cname) throws SQLException {
        String sql = "select count(*) from t_category where cname=?";
        return (Long)tqr.query(sql, new ScalarHandler(),cname);
    }
}

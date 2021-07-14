package com.zdj.TMBookStore.dao.impl;

import com.zdj.TMBookStore.dao.BookDao;
import com.zdj.TMBookStore.po.BookDet;
import com.zdj.TMBookStore.po.CategoryListTwo;
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
 */
public class BookDaoImpl implements BookDao {
    private final TxQueryRunner tqr = new TxQueryRunner();
//    private final Integer pageCount = 10;

    @Override
    public BookDet findBookInfo(String bid) throws SQLException {
        String sql = "select * from t_book where bid=?";
        return tqr.query(sql, new BeanHandler<>(BookDet.class), bid);
    }

    @Override
    public List<BookDet> findCategory(String cid) throws SQLException {
        String sql = "select * from t_book where cid = ?";
        return tqr.query(sql, new BeanListHandler<>(BookDet.class), cid);
    }

    @Override
    public void deleteBook(String bid) throws SQLException {
        String sql = "delete from t_book where bid=?";
        tqr.update(sql, bid);
    }

    @Override
    public CategoryListTwo findBookCategoryInfo(String cid) throws SQLException {
        String sql = "select  a.* from t_category a , t_category b where a.pid=b.cid and a.cid=?";
        return tqr.query(sql, new BeanHandler<>(CategoryListTwo.class), cid);
    }


    @Override
    public void updateBookInfo(String bid, BookDet bookDet) throws SQLException {
        String sql = "update t_book set bname=?,author= ?,price = ?, currPrice = ?, discount =?, press = ?,publishtime=?,edition=?,pageNum = ?, wordNum = ? , printtime = ?,booksize = ? ,paper = ?,cid=?   where bid=?";
        tqr.update(sql, bookDet.getBname(), bookDet.getAuthor(), bookDet.getPrice(), bookDet.getCurrPrice(), bookDet.getDiscount(),
                bookDet.getPress(), bookDet.getPublishtime(), bookDet.getEdition(), bookDet.getPageNum(), bookDet.getWordNum(),
                bookDet.getPrinttime(), bookDet.getBooksize(), bookDet.getPaper(), bookDet.getCid(), bid);

    }


    @Override
    public PageBean<BookDet> findBooksCategory(String cid, Integer pageNow, Integer pageCount) throws SQLException {

        String sqlList = "select * from t_book where cid=?  order by orderBy limit ?,?";
        String sqlCount = "select count(*) from t_book where cid=?";
        return getPageBean(sqlList, sqlCount, pageNow, pageCount, cid);

    }


    @Override
    public PageBean<BookDet> findBookByAuthor(String author, Integer pageNow, Integer pageCount) throws SQLException {

        String sqlList = "select * from t_book where author=? order by orderBy limit ?,?";
        String sqlCount = "select count(*) from t_book where author=?";
        return getPageBean(sqlList, sqlCount, pageNow, pageCount, author);
    }

    @Override
    public PageBean<BookDet> findBookByPress(String press, Integer pageNow, Integer pageCount) throws SQLException {

        String sqlList = "select * from t_book where press=? order by orderBy limit ?,?";
        String sqlCount = "select count(*) from t_book where press=?";
        return getPageBean(sqlList, sqlCount, pageNow, pageCount, press);


    }

    public PageBean<BookDet> getPageBean(String sqlList, String sqlCount, Integer pageNow, Integer pageCount, Object param) throws SQLException {

        PageBean<BookDet> pageBean = new PageBean<>();

        //设置list
        List<BookDet> list = tqr.query(sqlList, new BeanListHandler<>(BookDet.class), param, (pageNow - 1) * pageCount, pageCount);
        pageBean.setList(list);

        //设置每页记录数
        pageBean.setPageCount(pageCount);

        //设置当前页
        pageBean.setPageNow(pageNow);

        //设置总记录数
        String result = tqr.query(sqlCount, new ScalarHandler(), param).toString();
        Integer totalCount = Integer.valueOf(result);
        pageBean.setTotalCount(totalCount);

        //设置总页数
        Integer totalPage = (int) totalCount % pageCount == 0 ? (int) totalCount / pageCount : (int) totalCount / pageCount + 1;
        pageBean.setTotalPage(totalPage);

        return pageBean;
    }

    @Override
    public void addBook(BookDet bookDet) throws SQLException {
        String bid = UUID.randomUUID().toString().replaceAll("-", "").trim();
        String sql = "insert into t_book(bid,bname,author,price,currPrice,discount,press,publishtime,edition,pageNum,wordNum,printtime,booksize,paper,cid,image_w,image_b)" +
                " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        tqr.update(sql, bid, bookDet.getBname(), bookDet.getAuthor(), bookDet.getPrice(), bookDet.getCurrPrice(), bookDet.getDiscount(),
                bookDet.getPress(), bookDet.getPublishtime(), bookDet.getEdition(), bookDet.getPageNum(), bookDet.getWordNum(), bookDet.getPrinttime(),
                bookDet.getBooksize(), bookDet.getPaper(), bookDet.getCid(), bookDet.getImage_w(), bookDet.getImage_b());
    }

    @Override
    public PageBean<BookDet> searchBook(String bname, String author, String press, Integer pageNow, Integer pageCount) throws SQLException {

        PageBean<BookDet> pageBean = new PageBean<>();
        String sqlList = "select * from t_book where bname like ? or author like ? or press like ?  order by orderBy limit ?,?";
        //设置list
        List<BookDet> list = tqr.query(sqlList, new BeanListHandler<>(BookDet.class), bname, author, press, (pageNow - 1) * pageCount, pageCount);
        pageBean.setList(list);

        String sqlCount = "select count(*) from t_book where bname like ? or author like ? or press like ?";
        //设置每页记录数
        pageBean.setPageCount(pageCount);

        //设置当前页
        pageBean.setPageNow(pageNow);

        //设置总记录数
        String result = tqr.query(sqlCount, new ScalarHandler(), bname, author, press).toString();
        Integer totalCount = Integer.valueOf(result);
        pageBean.setTotalCount(totalCount);

        //设置总页数
        Integer totalPage = (int) totalCount % pageCount == 0 ? (int) totalCount / pageCount : (int) totalCount / pageCount + 1;
        pageBean.setTotalPage(totalPage);

        return pageBean;

    }

    @Override
    public PageBean<BookDet> searchBookByWord(String word, Integer pageNow, Integer pageCount) throws SQLException {
        PageBean<BookDet> pageBean = new PageBean<>();
        String sqlList = "select * from t_book where bname like ? or author like ? or press like ?  order by orderBy limit ?,?";
        //设置list
        List<BookDet> list = tqr.query(sqlList, new BeanListHandler<>(BookDet.class), word, word, word, (pageNow - 1) * pageCount, pageCount);
        pageBean.setList(list);

        String sqlCount = "select count(*) from t_book where bname like ? or author like ? or press like ?";
        //设置每页记录数
        pageBean.setPageCount(pageCount);

        //设置当前页
        pageBean.setPageNow(pageNow);

        //设置总记录数
        String result = tqr.query(sqlCount, new ScalarHandler(), word, word, word).toString();
        Integer totalCount = Integer.valueOf(result);
        pageBean.setTotalCount(totalCount);

        //设置总页数
        Integer totalPage = (int) totalCount % pageCount == 0 ? (int) totalCount / pageCount : (int) totalCount / pageCount + 1;
        pageBean.setTotalPage(totalPage);

        return pageBean;
    }
}

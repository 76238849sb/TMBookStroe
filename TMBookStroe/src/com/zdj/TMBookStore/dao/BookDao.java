package com.zdj.TMBookStore.dao;

import com.zdj.TMBookStore.po.BookDet;
import com.zdj.TMBookStore.po.CategoryList;
import com.zdj.TMBookStore.po.CategoryListTwo;
import com.zdj.TMBookStore.utils.PageBean;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {

    /**
     * 查询分类
     *
     * @param cid cid
     * @return List<BookDet>
     * @throws SQLException SQL
     */
    List<BookDet> findCategory(String cid) throws SQLException;

    /**
     * 查询分页信息
     *
     * @param cid cid
     * @param pageNow pageNow
     * @param pageCount pageCount
     * @return PageBean<BookDet>
     * @throws SQLException SQL
     */
    PageBean<BookDet> findBooksCategory(String cid, Integer pageNow,Integer pageCount) throws SQLException;

    /**
     * 查询某本书的详细信息
     *
     * @param bid bid
     * @return BookDet
     * @throws SQLException SQL
     */
    BookDet findBookInfo(String bid) throws SQLException;

    /**
     * 修改图书信息
     *
     * @param bid bid
     * @param bookDet bookDet
     * @throws SQLException SQL
     */
    void updateBookInfo(String bid , BookDet bookDet) throws SQLException;

    /**
     * 删除图书
     *
     * @param bid bid
     * @throws SQLException SQL
     */
    void deleteBook(String bid) throws SQLException;


    /**
     * 查询该图书的分类信息
     *
     * @param cid cid
     * @return CategoryList
     * @throws SQLException SQL
     */
    CategoryListTwo findBookCategoryInfo(String cid) throws SQLException;

    /**
     * 按作者查询
     *
     * @param author author
     * @param pageNow pageNow
     * @param pageCount pageCount
     * @return PageBean<BookDet>
     * @throws SQLException SQL
     */
    PageBean<BookDet> findBookByAuthor(String author,Integer pageNow,Integer pageCount) throws SQLException;

    /**
     * 按出版社查询
     *
     * @param press press
     * @param pageNow pageNow
     * @param pageCount pageCount
     * @return PageBean<BookDet>
     * @throws SQLException SQL
     */
    PageBean<BookDet> findBookByPress(String press,Integer pageNow,Integer pageCount) throws SQLException;

    /**
     * 添加图书
     *
     * @param bookDet bookDet
     * @throws SQLException SQL
     */
    void addBook(BookDet bookDet) throws SQLException;

    /**
     * 综合查询
     *
     * @param bname 书名
     * @param author 作者
     * @param press 出版社
     * @param pageNow pageNow
     * @param pageCount pageCount
     * @return PageBean<BookDet>
     * @throws SQLException SQL
     */
    PageBean<BookDet> searchBook(String bname,String author,String press,Integer pageNow,Integer pageCount) throws SQLException;

    /**
     * 模糊查询
     *
     * @param word word
     * @param pageNow pageNow
     * @param pageCount pageCount
     * @return PageBean<BookDet>
     * @throws SQLException SQL
     */
    PageBean<BookDet> searchBookByWord(String word,Integer pageNow,Integer pageCount) throws SQLException;

}

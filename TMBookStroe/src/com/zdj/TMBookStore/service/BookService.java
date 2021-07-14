package com.zdj.TMBookStore.service;

import com.zdj.TMBookStore.po.BookDet;
import com.zdj.TMBookStore.po.CategoryList;
import com.zdj.TMBookStore.po.CategoryListTwo;
import com.zdj.TMBookStore.utils.PageBean;

import java.sql.SQLException;
import java.util.List;

public interface BookService {

    /**
     * 查询分类
     *
     * @param cid cid
     * @return List<BookDet>
     */
    List<BookDet> findCategory(String cid);

    /**
     * 查询分页信息
     *
     * @param cid cid
     * @param pageNow pageNow
     * @param pageCount pageCount
     * @return PageBean<BookDet>
     */
    PageBean<BookDet> findBooksCategory(String cid, Integer pageNow,Integer pageCount);

    /**
     * 查询某本书的详细信息
     *
     * @param bid bid
     * @return BookDet
     */
    BookDet findBookInfo(String bid);

    /**
     * 修改图书信息
     *
     * @param bid bid
     * @param bookDet bookDet
     */
    void updateBookInfo(String bid , BookDet bookDet);

    /**
     * 删除图书
     *
     * @param bid bid
     */
    void deleteBook(String bid);


    /**
     * 查询该图书的分类信息
     *
     * @param cid cid
     * @return CategoryListTwo
     */
    CategoryListTwo findBookCategoryInfo(String cid);

    /**
     * 按作者查询
     *
     * @param author author
     * @param pageNow pageNow
     * @param pageCount pageCount
     * @return PageBean<BookDet>
     */
    PageBean<BookDet> findBookByAuthor(String author,Integer pageNow,Integer pageCount);

    /**
     * 按出版社查询
     *
     * @param press press
     * @param pageNow pageNow
     * @param pageCount pageCount
     * @return PageBean<BookDet>
     */
    PageBean<BookDet> findBookByPress(String press,Integer pageNow,Integer pageCount);

    /**
     * 添加图书
     *
     * @param bookDet bookDet
     */
    void addBook(BookDet bookDet);

    /**
     * 综合查询
     *
     * @param bname 书名
     * @param author 作者
     * @param press 出版社
     * @param pageNow pageNow
     * @param pageCount pageCount
     * @return PageBean<BookDet>
     */
    PageBean<BookDet> searchBook(String bname,String author,String press,Integer pageNow,Integer pageCount);

    /**
     * 模糊查询
     *
     * @param word word
     * @param pageNow pageNow
     * @param pageCount pageCount
     * @return PageBean<BookDet>
     */
    PageBean<BookDet> searchBookByWord(String word,Integer pageNow,Integer pageCount);


}

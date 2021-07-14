package com.zdj.TMBookStore.service.impl;

import com.zdj.TMBookStore.dao.BookDao;
import com.zdj.TMBookStore.dao.impl.BookDaoImpl;
import com.zdj.TMBookStore.po.BookDet;
import com.zdj.TMBookStore.po.CategoryListTwo;
import com.zdj.TMBookStore.service.BookService;
import com.zdj.TMBookStore.utils.DruidPool;
import com.zdj.TMBookStore.utils.PageBean;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 华韵流风
 */
public class BookServiceImpl implements BookService {
    private final BookDao bookDao = new BookDaoImpl();

    @Override
    public List<BookDet> findCategory(String cid) {
        try {
            return bookDao.findCategory(cid);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException("查询该类图书详细信息失败！");
        }
    }

    @Override
    public PageBean<BookDet> findBooksCategory(String cid, Integer pageNow, Integer pageCount) {

        try {
            return bookDao.findBooksCategory(cid, pageNow, pageCount);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException("查询分页信息失败！");
        }
    }

    @Override
    public BookDet findBookInfo(String bid) {
        try {
            return bookDao.findBookInfo(bid);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException("查询图书详细信息失败！");
        }
    }

    @Override
    public void updateBookInfo(String bid, BookDet bookDet) {
        try {
            DruidPool.beginTransaction();
            bookDao.updateBookInfo(bid, bookDet);
            DruidPool.commitTransaction();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                DruidPool.rollbackTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("updateBookInfo的方法失败！");
            }
        }

    }

    @Override
    public void deleteBook(String bid) {
        try {
            DruidPool.beginTransaction();
            bookDao.deleteBook(bid);
            DruidPool.commitTransaction();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                DruidPool.rollbackTransaction();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("deletBook的方法失败！");
            }
        }

    }

    @Override
    public CategoryListTwo findBookCategoryInfo(String cid) {
        try {
            return bookDao.findBookCategoryInfo(cid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("查询该图书分类信息失败");
        }
    }


    @Override
    public PageBean<BookDet> findBookByAuthor(String author, Integer pageNow, Integer pageCount) {
        try {
            return bookDao.findBookByAuthor(author, pageNow, pageCount);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException("按作者查询图书失败");
        }
    }

    @Override
    public PageBean<BookDet> findBookByPress(String press, Integer pageNow, Integer pageCount) {
        try {
            return bookDao.findBookByPress(press, pageNow, pageCount);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException("按出版社查询图书失败");
        }
    }

    @Override
    public void addBook(BookDet bookDet) {
        try {
            bookDao.addBook(bookDet);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException("添加图书失败");
        }
    }

    @Override
    public PageBean<BookDet> searchBook(String bname, String author, String press, Integer pageNow, Integer pageCount) {
        try {
            if (bname != null && !bname.isEmpty()) {
                bname = "%" + bname + "%";
            }
            if (author != null && !author.isEmpty()) {
                author = "%" + author + "%";
            }
            if (press != null && !press.isEmpty()) {
                press = "%" + press + "%";
            }


            return bookDao.searchBook(bname, author, press, pageNow, pageCount);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException("高级查询图书失败");
        }
    }

    @Override
    public PageBean<BookDet> searchBookByWord(String word, Integer pageNow, Integer pageCount) {
        word = "%" + word + "%";
        try {
            return bookDao.searchBookByWord(word, pageNow, pageCount);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException("模糊查询失败！");
        }
    }
}

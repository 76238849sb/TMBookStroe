package com.zdj.TMBookStore.web;

import com.zdj.TMBookStore.po.BookDet;
import com.zdj.TMBookStore.po.CategoryList;
import com.zdj.TMBookStore.po.CategoryListTwo;
import com.zdj.TMBookStore.service.BookService;
import com.zdj.TMBookStore.service.CategoryService;
import com.zdj.TMBookStore.service.impl.BookServiceImpl;
import com.zdj.TMBookStore.service.impl.CategoryServiceImpl;
import com.zdj.TMBookStore.utils.BaseServlet;
import com.zdj.TMBookStore.utils.BeanUtil;
import com.zdj.TMBookStore.utils.PageBean;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 华韵流风
 */
@WebServlet(name = "BookServlet", urlPatterns = "/web/bookServlet")
public class BookServlet extends BaseServlet {
    private final BookService bookService = new BookServiceImpl();
    private final CategoryService categoryService = new CategoryServiceImpl();


    public String findCategoryBookInfo(HttpServletRequest request, HttpServletResponse response) {
        String cid = request.getParameter("cid");
        String pageNow = request.getParameter("pageNow");
        if (cid == null || cid.isEmpty()) {
            List<CategoryList> allCategory = categoryService.findAllCategory();
            cid = allCategory.get(0).getCategoryListTwos().get(0).getCid();

        }
        PageBean<BookDet> pageBean = bookService.findBooksCategory(cid, Integer.valueOf(pageNow),10);
        pageBean.setUrl(getUrl(request));
        request.setAttribute("pageBean", pageBean);

        return "f:/web/adminjsps/admin/book/list.jsp";
    }


    public String findBookInfo(HttpServletRequest request, HttpServletResponse response) {
        String bid = request.getParameter("bid");
        String cid = request.getParameter("cid");

        BookDet bookInfo = bookService.findBookInfo(bid);
        CategoryListTwo bookCategoryInfo = bookService.findBookCategoryInfo(cid);
        List<CategoryList> allCategory = categoryService.findAllCategory();
        List<CategoryListTwo> categoryTwo = categoryService.findCategoryTwo(bookCategoryInfo.getPid());

        request.setAttribute("bookInfo", bookInfo);
        request.setAttribute("bookCategoryInfo", bookCategoryInfo);
        request.setAttribute("allCategory", allCategory);
        request.setAttribute("categoryTwo", categoryTwo);

        return "f:/web/adminjsps/admin/book/desc.jsp";
    }


    public String drawImg(HttpServletRequest request, HttpServletResponse response) {
        String path = request.getParameter("path");

        return "d:" + path;
    }


    public String showBookCategory(HttpServletRequest request, HttpServletResponse response) {
        String pid = request.getParameter("pid");
        List<CategoryListTwo> categoryTwo = categoryService.findCategoryTwo(pid);
        StringBuilder result = new StringBuilder();
        result.append("{\"cname" + "\":[");
        for (CategoryListTwo c2 : categoryTwo) {
            result.append("\"").append(c2.getCname()).append("\",");
        }
        int index = result.lastIndexOf(",");
        result.replace(index, index + 1, "],\"cid" + "\":[");
        for (CategoryListTwo c2 : categoryTwo) {
            result.append("\"").append(c2.getCid()).append("\",");
        }
        index = result.lastIndexOf(",");
        result.replace(index, index + 1, "]}");

        return "a:" + result;
    }

    public String deleteBook(HttpServletRequest request, HttpServletResponse response) {
        String bid = request.getParameter("bid");
        bookService.deleteBook(bid);

        return "f:/web/bookServlet?method=findCategoryBookInfo&pageNow=1";
    }

    public String updateBookInfo(HttpServletRequest request, HttpServletResponse response) {

        BookDet bookDet = BeanUtil.toBean(request.getParameterMap(), BookDet.class);
        //通过from参数下的input的隐藏的id名，value的值获得相应的值
        String bid = request.getParameter("bid");

        if (bookDet != null) {
            bookService.updateBookInfo(bid, bookDet);
        }

        return "s:/web/bookServlet?method=findBookInfo&bid=" + bid + "&cid=" + bookDet.getCid();
    }


    public String findBookByAuthor(HttpServletRequest request, HttpServletResponse response){
        String author = request.getParameter("author");
        String pageNow = request.getParameter("pageNow");
        PageBean<BookDet> bookByAuthor = bookService.findBookByAuthor(author, Integer.valueOf(pageNow),12);
        bookByAuthor.setUrl(getUrl(request));
        request.setAttribute("pageBean", bookByAuthor);
        return "f:/web/adminjsps/admin/book/list.jsp";
    }


    public String findBookByPress(HttpServletRequest request, HttpServletResponse response){
        String press = request.getParameter("press");
        String pageNow = request.getParameter("pageNow");
        PageBean<BookDet> bookByPress = bookService.findBookByPress(press, Integer.valueOf(pageNow),12);
        bookByPress.setUrl(getUrl(request));
        request.setAttribute("pageBean", bookByPress);
        return "f:/web/adminjsps/admin/book/list.jsp";
    }

    public String searchBook(HttpServletRequest request, HttpServletResponse response){

        String bname = request.getParameter("bname").trim();
        String author = request.getParameter("author").trim();
        String press = request.getParameter("press").trim();
        String pageNow = request.getParameter("pageNow");
        PageBean<BookDet> pageBean = bookService.searchBook(bname, author, press, Integer.valueOf(pageNow),12);
        pageBean.setUrl(getUrl(request));
        request.setAttribute("pageBean", pageBean);

        return "f:/web/adminjsps/admin/book/list.jsp";
    }


    private String getUrl(HttpServletRequest request) {
        String url = request.getRequestURI() + "?" + request.getQueryString();
        int index = url.lastIndexOf("&pageNow");
        url = url.substring(0, index);
        return url;
    }
}

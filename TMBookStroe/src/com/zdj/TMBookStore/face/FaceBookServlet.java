package com.zdj.TMBookStore.face;

import com.zdj.TMBookStore.po.BookDet;
import com.zdj.TMBookStore.po.CategoryList;
import com.zdj.TMBookStore.po.CategoryListTwo;
import com.zdj.TMBookStore.service.BookService;
import com.zdj.TMBookStore.service.CategoryService;
import com.zdj.TMBookStore.service.impl.BookServiceImpl;
import com.zdj.TMBookStore.service.impl.CategoryServiceImpl;
import com.zdj.TMBookStore.utils.BaseServlet;
import com.zdj.TMBookStore.utils.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 华韵流风
 * @ClassName ${NAME}
 * @Description TODO
 * @Date 2021/5/28 11:00
 * @packageName ${PACKAGE_NAME}
 */
@WebServlet(name = "FaceBookServlet",urlPatterns = "/face/faceBookServlet")
public class FaceBookServlet extends BaseServlet {
    private final BookService bookService = new BookServiceImpl();
    private final CategoryService categoryService = new CategoryServiceImpl();


    public String categoryManger(HttpServletRequest request, HttpServletResponse response) {

        String pageType = request.getParameter("pageType");
        List<CategoryList> allCategory = categoryService.findAllCategory();
        request.setAttribute("allCategory", allCategory);
        return "f:/web/jsps/left.jsp";
    }

    public String findCategoryBookInfo(HttpServletRequest request, HttpServletResponse response) {
        String cid = request.getParameter("cid");
        String pageNow = request.getParameter("pageNow");
        String add = request.getParameter("add");
        if (cid == null || cid.isEmpty()) {
            List<CategoryList> allCategory = categoryService.findAllCategory();
            cid = allCategory.get(0).getCategoryListTwos().get(0).getCid();

        }
        PageBean<BookDet> pageBean = bookService.findBooksCategory(cid, Integer.valueOf(pageNow),8);
        pageBean.setUrl(getUrl(request));
        request.setAttribute("pageBean", pageBean);
        request.setAttribute("add", add);

        return "f:/web/jsps/body.jsp";
    }

    public String findBookInfo(HttpServletRequest request, HttpServletResponse response) {
        String bid = request.getParameter("bid");
        String quantity = request.getParameter("quantity");
        BookDet bookInfo = bookService.findBookInfo(bid);
        request.setAttribute("quantity", quantity);
        request.setAttribute("bookInfo", bookInfo);

        return "f:/web/jsps/book/desc.jsp";
    }

    public String findBookByAuthor(HttpServletRequest request, HttpServletResponse response){
        String author = request.getParameter("author");
        String pageNow = request.getParameter("pageNow");
        PageBean<BookDet> bookByAuthor = bookService.findBookByAuthor(author, Integer.valueOf(pageNow),8);
        bookByAuthor.setUrl(getUrl(request));
        request.setAttribute("pageBean", bookByAuthor);
        return "f:/web/jsps/book/list.jsp";
    }


    public String findBookByPress(HttpServletRequest request, HttpServletResponse response){
        String press = request.getParameter("press");
        String pageNow = request.getParameter("pageNow");
        PageBean<BookDet> bookByPress = bookService.findBookByPress(press, Integer.valueOf(pageNow),8);
        bookByPress.setUrl(getUrl(request));
        request.setAttribute("pageBean", bookByPress);
        return "f:/web/jsps/book/list.jsp";
    }

    public String searchBook(HttpServletRequest request, HttpServletResponse response){
        String word = request.getParameter("word");
        String pageNow = request.getParameter("pageNow");
        PageBean<BookDet> pageBean = bookService.searchBookByWord(word, Integer.valueOf(pageNow), 8);
        request.setAttribute("pageBean", pageBean);
        return "f:/web/jsps/book/list.jsp";
    }




    public String drawImg(HttpServletRequest request, HttpServletResponse response) {
        String path = request.getParameter("path");
        return "d:" + path;
    }

    private String getUrl(HttpServletRequest request) {
        String url = request.getRequestURI() + "?" + request.getQueryString();
        int index = url.lastIndexOf("&pageNow");
        url = url.substring(0, index);
        return url;
    }
}

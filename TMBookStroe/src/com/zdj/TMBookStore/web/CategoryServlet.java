package com.zdj.TMBookStore.web;

import com.zdj.TMBookStore.po.CategoryList;
import com.zdj.TMBookStore.service.CategoryService;
import com.zdj.TMBookStore.service.impl.CategoryServiceImpl;
import com.zdj.TMBookStore.utils.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 华韵流风
 */
@WebServlet(name = "CategoryServlet", urlPatterns = "/web/categoryServlet")
public class CategoryServlet extends BaseServlet {
    private final CategoryService categoryService = new CategoryServiceImpl();


    public String categoryManger(HttpServletRequest request, HttpServletResponse response) {

        String pageType = request.getParameter("pageType");
        List<CategoryList> allCategory = categoryService.findAllCategory();
        request.setAttribute("allCategory", allCategory);
        if ("c".equals(pageType)) {
            return "f:/web/adminjsps/admin/category/list.jsp";
        } else if ("b".equals(pageType)) {
            return "f:/web/adminjsps/admin/book/left.jsp";
        } else {
            return "f:/web/adminjsps/admin/book/add.jsp";
        }

    }

    public String addOneLevel(HttpServletRequest request, HttpServletResponse response) {
        String cname = request.getParameter("cname").trim();
        String desc = request.getParameter("desc").trim();
        categoryService.addBookLevelOne(cname, desc);
        return "s:/web/categoryServlet?method=categoryManger&pageType=c";
    }

    public String addPid(HttpServletRequest request, HttpServletResponse response) {

        String pid = request.getParameter("pid");
        request.setAttribute("pid", pid);

        return "f:/web/adminjsps/admin/category/add2.jsp";
    }


    public String addTwoLevel(HttpServletRequest request, HttpServletResponse response) {
        String cname = request.getParameter("cname").trim();
        String desc = request.getParameter("desc").trim();
        String pid = request.getParameter("pid");
        categoryService.addBookLevelTwo(cname, pid, desc);
        return "s:/web/categoryServlet?method=categoryManger&pageType=c";

    }

    public String checkCategoryName(HttpServletRequest request, HttpServletResponse response){
        String cname = request.getParameter("cname");
        String result = "正确";
        Long count = categoryService.checkCategoryName(cname);
        if(count>=1){
            result = "错误";
        }

        return "a:{\"result\":\"" + result + "\"}";
    }


    public String showOneCategory(HttpServletRequest request, HttpServletResponse response) {

        String cid = request.getParameter("cid");
        String cname = request.getParameter("cname");
        String desc = request.getParameter("desc");

        request.setAttribute("cid", cid);
        request.setAttribute("cname", cname);
        request.setAttribute("desc", desc);

        if ("1".equals(request.getParameter("type"))) {
            return "f:/web/adminjsps/admin/category/edit.jsp";
        } else {
            return "f:/web/adminjsps/admin/category/edit2.jsp";
        }

    }

    public String updateOneCategory(HttpServletRequest request, HttpServletResponse response) {

        String cname = request.getParameter("cname");
        String desc = request.getParameter("desc");
        String cid = request.getParameter("cid");
        categoryService.updateOneLevel(cname, desc, cid);

        return "s:/web/categoryServlet?method=categoryManger&pageType=c";
    }


    public String deleteOneCategory(HttpServletRequest request, HttpServletResponse response) {
        String cid = request.getParameter("cid");

        categoryService.deleteOneLevel(cid);

        return "s:/web/categoryServlet?method=categoryManger&pageType=c";
    }


    public String showTwoCategory(HttpServletRequest request, HttpServletResponse response) {
        String cname = request.getParameter("cname");
        String desc = request.getParameter("desc");
        String pid = request.getParameter("pid");
        String cid = request.getParameter("cid");

        request.setAttribute("cname", cname);
        request.setAttribute("desc", desc);
        request.setAttribute("pid", pid);
        request.setAttribute("cid", cid);

        List<CategoryList> allCategory = categoryService.findAllCategory();
        request.setAttribute("allCategory", allCategory);

        return "f:/web/adminjsps/admin/category/edit2.jsp";
    }

    public String updateTwoCategory(HttpServletRequest request, HttpServletResponse response) {

        String cname = request.getParameter("cname");
        String desc = request.getParameter("desc");
        String cid = request.getParameter("cid");
        String pid = request.getParameter("pid");
        categoryService.updateTwoLevel(cname, desc, cid, pid);

        return "s:/web/categoryServlet?method=categoryManger&pageType=c";
    }

    public String deleteTwoCategory(HttpServletRequest request, HttpServletResponse response) {
        String cid = request.getParameter("cid");
        categoryService.deleteTwoLevel(cid);
        return "s:/web/categoryServlet?method=categoryManger&pageType=c";
    }



}

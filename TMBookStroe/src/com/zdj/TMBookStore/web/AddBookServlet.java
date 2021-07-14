package com.zdj.TMBookStore.web;

import com.zdj.TMBookStore.po.BookDet;
import com.zdj.TMBookStore.service.BookService;
import com.zdj.TMBookStore.service.impl.BookServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author 华韵流风
 * @ClassName ${NAME}
 * @Description TODO
 * @Date 2021/5/28 8:48
 * @packageName ${PACKAGE_NAME}
 */
@WebServlet(name = "AddBookServlet", urlPatterns = "/web/addBookServlet")
public class AddBookServlet extends HttpServlet {
    private final BookService bookService = new BookServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        //创建 FileItem 对象的工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //获取文件需要上传到的路径
        String path = request.getServletContext().getRealPath("/web/");
        //指定临时文件目录
        factory.setRepository(new File(path));
        //设置内存缓冲区的大小
        factory.setSizeThreshold(1024 * 1024);
        //负责处理上传的文件数据，并将表单中每个输入项封装成一个 FileItem 对象中
        ServletFileUpload upload = new ServletFileUpload(factory);

        List<FileItem> list;
        //两件事，把表单的普通控件数据写到po中，把文件的数据保存到磁盘中
        BookDet bookDet = new BookDet();
        try {
            //调用Upload.parseRequest方法解析request对象，得到一个保存了所有上传内容的List对象。
            list = upload.parseRequest(request);
            //对list进行迭代，每迭代一个FileItem对象，调用其isFormField方法判断是否是上传文件
            for (FileItem item : list) {
                String name = item.getFieldName();
                //为普通表单字段
                if (item.isFormField()) {
                    String value = new String(item.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                    if ("bname".equals(name)) {
                        bookDet.setBname(value);
                    } else if ("currPrice".equals(name)) {
                        bookDet.setCurrPrice(Double.valueOf(value));
                    } else if ("price".equals(name)) {
                        bookDet.setPrice(Double.parseDouble(value));
                    } else if ("discount".equals(name)) {
                        bookDet.setDiscount(Double.parseDouble(value));
                    } else if ("author".equals(name)) {
                        bookDet.setAuthor(value);
                    } else if ("press".equals(name)) {
                        bookDet.setPress(value);
                    } else if ("publishtime".equals(name)) {
                        bookDet.setPublishtime(value);
                    } else if ("edition".equals(name)) {
                        bookDet.setEdition(Integer.valueOf(value));
                    } else if ("pageNum".equals(name)) {
                        bookDet.setPageNum(Integer.valueOf(value));
                    } else if ("wordNum".equals(name)) {
                        bookDet.setWordNum(Integer.valueOf(value));
                    } else if ("printtime".equals(name)) {
                        bookDet.setPrinttime(value);
                    } else if ("booksize".equals(name)) {
                        bookDet.setBooksize(Integer.valueOf(value));
                    } else if ("paper".equals(name)) {
                        bookDet.setPaper(value);
                    } else if ("cid".equals(name)) {
                        bookDet.setCid(value);
                    }

                } else {//为上传文件，则调用item.write方法写文件
                    //得到文件名称
                    String value = item.getName();
                    int start = value.lastIndexOf("/");
                    //得到文件名
                    String filename = value.substring(start + 1);
                    filename = "book_img/" + filename;
                    item.write(new File(path, filename));

                    //设置图片
                    int type = value.lastIndexOf("_");
                    String tmp = value.substring(type + 1, type + 2);
                    if ("w".equals(tmp)) {
                        bookDet.setImage_w(filename);
                    } else if ("b".equals(tmp)) {
                        bookDet.setImage_b(filename);
                    }


                }
            }

            //保存商品数据
            ///调用业务逻辑，向表中添加商品
            try {
                bookService.addBook(bookDet);
            } catch (Exception e) {
                e.printStackTrace();
            }

            request.setAttribute("success", "1");
            request.getRequestDispatcher("/web/adminjsps/admin/book/main.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

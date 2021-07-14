<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>图书分类</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/web/adminjsps/admin/css/book/list.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/web/jquery/jquery-1.5.1.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/jsps/pager/pager.css"/>
</head>

<body>
<div class="divBook">

    <ul>
        <c:forEach items="${requestScope.pageBean.list}" var="book">
            <li>
                <div class="inner">
                    <a class="pic" href="${pageContext.request.contextPath}/web/bookServlet?method=findBookInfo&bid=${book.bid}&cid=${book.cid}">
                        <img src="${pageContext.request.contextPath}/web/bookServlet?method=drawImg&path=${book.image_b}"
                             border="0"/></a>
                    <p class="price">
                        <span class="price_n">&yen;${book.currPrice}</span>
                        <span class="price_r">&yen;${book.price}</span>
                        (<span class="price_s">${book.discount}折</span>)
                    </p>
                    <p style="overflow: hidden"><a id="bookname" title="${book.bname}"
                                                   href="${pageContext.request.contextPath}/web/bookServlet?method=findBookInfo&bid=${book.bid}&cid=${book.cid}">${book.bname}</a>
                    </p>
                    <p><a href="${pageContext.request.contextPath}/web/bookServlet?method=findBookByAuthor&author=${book.author}&pageNow=1"
                          name='P_zz' title='${book.author}'>${book.author}</a></p>
                    <p class="publishing">
                        <span>出版社：</span><a
                            href="${pageContext.request.contextPath}/web/bookServlet?method=findBookByPress&press=${book.press}&pageNow=1">${book.press}</a>
                    </p>

                </div>

            </li>
        </c:forEach>
    </ul>
</div>
<div style="float:left; width: 100%; text-align: center;">
    <hr/>
    <br/>
    <%@include file="/web/jsps/pager/pager.jsp" %>
</div>
</body>

</html>


<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>My JSP 'left.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/web/adminjsps/admin/css/book/left.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/web/menu/mymenu.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/menu/mymenu.css"
          type="text/css" media="all">
    <script>



        let bar = new Q6MenuBar("bar", "图书分类");

        let aaaa = () => {

            bar.colorStyle = 2;
            bar.config.imgDir = "${pageContext.request.contextPath}/web/menu/img/";
            bar.config.radioButton = true;

            <c:forEach items="${requestScope.allCategory}" var="category1" >
            <c:forEach items="${category1.categoryListTwos}" var="category2">
            bar.add("${category1.desc}", "${category2.cname}",
                "${pageContext.request.contextPath}/web/bookServlet?method=findCategoryBookInfo&cid=${category2.cid}&pageNow=1"
                , "body");
            </c:forEach>

            </c:forEach>
            let d = document.getElementById("menu");
            d.innerHTML = bar.toString();
        };


    </script>
</head>

<body onload="aaaa()">
<div id="menu">

</div>

</body>
</html>

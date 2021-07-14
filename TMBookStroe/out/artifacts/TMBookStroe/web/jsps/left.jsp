<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>left</title>
    <base target="body"/>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/web/jquery/jquery-1.5.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/web/menu/mymenu.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web/menu/mymenu.css" type="text/css" media="all">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/jsps/css/left.css">
    <script language="javascript">

            let bar = new Q6MenuBar("bar", "网上书城");

            let addOption = () => {

                bar.colorStyle = 4;
                bar.config.imgDir = "${pageContext.request.contextPath}/web/menu/img/";
                bar.config.radioButton = true;

                <c:forEach items="${requestScope.allCategory}" var="category1" >
                <c:forEach items="${category1.categoryListTwos}" var="category2">
                bar.add("${category1.desc}", "${category2.cname}",
                    "${pageContext.request.contextPath}/face/faceBookServlet?method=findCategoryBookInfo&cid=${category2.cid}&pageNow=1"
                    , "body");
                </c:forEach>

                </c:forEach>
                let d = document.getElementById("menu");
                d.innerHTML = bar.toString();
            };

    </script>
</head>

<body onload="addOption()">
<div id="menu"></div>
</body>
</html>

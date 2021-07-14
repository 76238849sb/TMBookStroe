<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>top</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <style type="text/css">
        body {
            background: #15B69A;
            margin: 0px;
            color: #ffffff;
        }

        a {
            text-decoration: none;
            color: #ffffff;
            font-weight: 900;
        }

        a:hover {
            text-decoration: underline;
            color: #ffffff;
            font-weight: 900;
        }
    </style>
</head>

<body>
<h1 style="text-align: center;">网上书城系统</h1>
<div style="font-size: 10pt; line-height: 10px;">

    <a href="${pageContext.request.contextPath}/web/jsps/user/login.jsp" target="_parent">会员登录</a> |&nbsp;
    <a href="${pageContext.request.contextPath}/web/jsps/user/regist.jsp" target="_parent">注册会员</a>

    传智会员：${sessionScope.user.loginname}&nbsp;&nbsp;|&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/face/faceCarServlet?method=findCarList" target="body">我的购物车</a>&nbsp;&nbsp;|&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/face/faceCarServlet?method=findAllOrder&pageNow=1"
       target="body">我的订单</a>&nbsp;&nbsp;|&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/web/jsps/user/pwd.jsp" target="body">修改密码</a>&nbsp;&nbsp;|&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/web/jsps/user/login.jsp" target="_parent">退出</a>&nbsp;&nbsp;|&nbsp;&nbsp;
    <%--		  <a href="http://www.ruandy.com/web1/index.php" target="_top">联系我们</a>--%>
<%--    <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=3218382370&site=qq&menu=yes" style="position: relative;top: 6px"><img border="0"--%>
<%--                                                                                     src="https://pub.idqqimg.com/wpa/images/counseling_style_52.png"--%>
<%--                                                                                     alt="联系我们" title="联系我们"/></a>--%>
	<a style="position: relative;top: 6px" href="tencent://message/?Menu=yes&uin=3218382370&Site=80fans&Service=300&sigT=45a1e5847943b64c6ff3990f8a9e644d2b31356cb0b4ac6b24663a3c8dd0f8aa12a545b1714f9d45">
		<img src="https://pub.idqqimg.com/wpa/images/counseling_style_52.png" alt="联系我们" title="联系我们"/>
	</a>
</div>
</body>
</html>

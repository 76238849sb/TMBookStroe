<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/jsps/css/book/list.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/jsps/pager/pager.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/jquery/jquery-1.5.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/jsps/js/book/list.js"></script>
  </head>
  
  <body>

<ul>

	<c:forEach items="${requestScope.pageBean.list}" var="book">

	<li>
		<div class="inner">
			<a class="pic" href="${pageContext.request.contextPath}/face/faceBookServlet?method=findBookInfo&bid=${book.bid}&quantity=1"><img src="${pageContext.request.contextPath}/face/faceBookServlet?method=drawImg&path=${book.image_b}" border="0"/></a>
			<p class="price">
				<span class="price_n">&yen;${book.currPrice}</span>
				<span class="price_r">&yen;${book.price}</span>
				(<span class="price_s">${book.discount}折</span>)
			</p>
			<p><a id="bookname" title="${book.bname}" href="${pageContext.request.contextPath}/face/faceBookServlet?method=findBookInfo&bid=${book.bid}&quantity=1">${book.bname}</a></p>
			<p><a href="${pageContext.request.contextPath}/face/faceBookServlet?method=findBookByAuthor&author=${book.author}&pageNow=1" name='P_zz' title='${book.author}'>${book.author}</a></p>
			<p class="publishing">
				<span>出 版 社：</span><a href="${pageContext.request.contextPath}/face/faceBookServlet?method=findBookByPress&press=${book.press}&pageNow=1">${book.press}</a>
			</p>
			<p class="publishing_time"><span>出版时间：</span>${book.publishtime}</p>
		</div>


		</c:forEach>

</ul>

<div style="float:left; width: 100%; text-align: center;">
	<hr/>
	<br/>
	<%@include file="/web/jsps/pager/pager.jsp" %>
</div>

  </body>
 
</html>


<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书详细</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/jsps/pager/pager.css" />
	<script src="${pageContext.request.contextPath}/web/jquery/jquery-1.5.1.js"></script>
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/jsps/css/book/desc.css">
	<script src="${pageContext.request.contextPath}/web/jsps/js/book/desc.js"></script>
  </head>
  
  <body>
  <div class="divBookName">${requestScope.bookInfo.bname}</div>
  <div>
    <img align="top" src="${pageContext.request.contextPath}/face/faceBookServlet?method=drawImg&path=${requestScope.bookInfo.image_w}" class="img_image_w"/>
    <div class="divBookDesc">
	    <ul>
	    	<li>商品编号：${requestScope.bookInfo.bid}</li>
	    	<li>传智价：<span class="price_n">&yen;${requestScope.bookInfo.currPrice}</span></li>
	    	<li>定价：<span class="spanPrice">&yen;${requestScope.bookInfo.price}</span>　折扣：<span style="color: #c30;">${requestScope.bookInfo.discount}</span>折</li>
	    </ul>
		<hr class="hr1"/>
		<table>
			<tr>
				<td colspan="3">
					作者：${requestScope.bookInfo.author} 著
				</td>
			</tr>
			<tr>
				<td colspan="3">
					出版社：${requestScope.bookInfo.press}
				</td>
			</tr>
			<tr>
				<td colspan="3">出版时间：${requestScope.bookInfo.publishtime}</td>
			</tr>
			<tr>
				<td>版次：${requestScope.bookInfo.edition}</td>
				<td>页数：${requestScope.bookInfo.pageNum}</td>
				<td>字数：${requestScope.bookInfo.wordNum}</td>
			</tr>
			<tr>
				<td width="180">印刷时间：${requestScope.bookInfo.printtime}</td>
				<td>开本：${requestScope.bookInfo.booksize} 开</td>
				<td>纸张：${requestScope.bookInfo.paper}</td>
			</tr>
		</table>


		<div class="divForm">
			<form id="form1" action="${pageContext.request.contextPath}/face/faceCarServlet" method="post">
				<input type="hidden" name="method" value="addCarItem"/>
				<input type="hidden" name="bid" value="${requestScope.bookInfo.bid}"/>
  				我要买：<input id="cnt" style="width: 60px;text-align: center;" type="number" min="1" name="quantity" value="${requestScope.quantity}"/>件
  			</form>
  			<a id="btn" href="javascript:$('#form1').submit();"></a>
  		</div>


	</div>
  </div>
  </body>
</html>

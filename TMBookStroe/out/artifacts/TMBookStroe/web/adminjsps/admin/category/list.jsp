<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>分类列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/adminjsps/admin/css/category/list.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/css/css.css">
  </head>
  
  <body>
    <h2 style="text-align: center;">分类列表</h2>
    <table align="center" border="1" cellpadding="0" cellspacing="0">
    	<caption class="captionAddOneLevel">
    	  <a href="${pageContext.request.contextPath}/web/adminjsps/admin/category/add.jsp">添加一级分类</a>
    	</caption>
    	<tr class="trTitle">
    		<th>分类名称</th>
    		<th>描述</th>
    		<th>操作</th>
    	</tr>
    	
    	<c:forEach items="${requestScope.allCategory}" var="category1">

    	<tr class="trOneLevel">
    		<td width="200px;">${category1.cname}</td>
    		<td>${category1.desc}</td>
    		<td width="200px;">
				<a href="${pageContext.request.contextPath}/web/categoryServlet?method=addPid&pid=${category1.cid}">添加二级分类</a>
    		  <a href="${pageContext.request.contextPath}/web/categoryServlet?method=showOneCategory&cid=${category1.cid}&cname=${category1.cname}&desc=${category1.desc}&type=1">修改</a>
    		  <a onclick="return confirm('您是否真要删除该一级分类？该操作会删除该分类下的所有二级分类！')"
				 href="${pageContext.request.contextPath}/web/categoryServlet?method=deleteOneCategory&cid=${category1.cid}">删除</a>
    		</td>
    	</tr>
			<c:forEach items="${category1.categoryListTwos}" var="category2">
    	<tr class="trTwoLevel">
    		<td>${category2.cname}</td>
    		<td>${category2.desc}</td>
    		<td width="200px;" align="right">
    		  <a href="${pageContext.request.contextPath}/web/categoryServlet?method=showTwoCategory&cid=${category2.cid}&cname=${category2.cname}&desc=${category2.desc}&pid=${category2.pid}">修改</a>
    		  <a onclick="return confirm('您是否真要删除该二级分类？该操作会删除所有该分类下的书籍！')"
				 href="${pageContext.request.contextPath}/web/categoryServlet?method=deleteTwoCategory&cid=${category2.cid}">删除</a>
    		</td>
    	</tr>
			</c:forEach>
		</c:forEach>
    </table>
  </body>
</html>

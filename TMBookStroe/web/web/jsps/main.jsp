<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>main</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/jsps/css/main.css">
	  <script type="text/javascript" src="${pageContext.request.contextPath}/web/jquery/jquery-1.5.1.js"></script>
	  <script>
		  $(function () {

			  if('${requestScope.err}'==='1'){
				  alert("验证码错误！修改失败");
			  }else if('${requestScope.err}'==='2'){
				  alert("修改成功！");
			  }
		  });
	  </script>
  </head>
  
  <body>
<table class="table" align="center">
	<tr class="trTop">
		<td colspan="2" class="tdTop">
			<iframe frameborder="0" src="${pageContext.request.contextPath}/web/jsps/top.jsp" name="top"></iframe>
		</td>
	</tr>
	<tr>
		<td class="tdLeft" rowspan="2">
			<iframe frameborder="0" src="${pageContext.request.contextPath}/face/faceBookServlet?method=categoryManger" name="left"></iframe>
		</td>
		<td class="tdSearch" style="border-bottom-width: 0px;">
			<iframe frameborder="0" src="${pageContext.request.contextPath}/web/jsps/search.jsp" name="search"></iframe>
		</td>
	</tr>
	<tr>
		<td style="border-top-width: 0px;">
			<iframe frameborder="0" src="${pageContext.request.contextPath}/face/faceBookServlet?method=findCategoryBookInfo&pageNow=1" name="body"></iframe>
		</td>
	</tr>
</table>
  </body>
</html>

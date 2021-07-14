<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>订单列表</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/jsps/css/order/list.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/jsps/pager/pager.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/web/jquery/jquery-1.5.1.js"></script>
    <script>

        let cancelOrder = (oid)=>{

            $.ajax({
                url: "${pageContext.request.contextPath}/face/faceCarServlet",  //请求的url，后台的servlet
                async: true,  //指定是否是异步请求
                type: 'GET',  //请求类型
                data: {method: "cancelOrder", oid: oid}, //请求的数据
                dataType: 'json',  //返回的数据类型
                cache: false,  //是否缓存
                success: function (data) { //回调函数，把data渲染到页面中
                    if(data.result === "成功"){
                        $("#"+oid).text("(已取消)");
                    }
                    else {
                        alert("取消失败！交易已完成");
                    }
                }

            });

        };

        let receiptOrder = (oid)=>{

            $.ajax({
                url: "${pageContext.request.contextPath}/face/faceCarServlet",  //请求的url，后台的servlet
                async: true,  //指定是否是异步请求
                type: 'GET',  //请求类型
                data: {method: "receiptOrder", oid: oid}, //请求的数据
                dataType: 'json',  //返回的数据类型
                cache: false,  //是否缓存
                success: function (data) { //回调函数，把data渲染到页面中
                    if(data.result === "成功"){
                        $("#"+oid).text("(交易成功)");
                    }
                    else {
                        alert("还未发货！");
                    }
                }

            });

        };

        let payOrder = (oid)=>{

            $.ajax({
                url: "${pageContext.request.contextPath}/face/faceCarServlet",  //请求的url，后台的servlet
                async: true,  //指定是否是异步请求
                type: 'GET',  //请求类型
                data: {method: "checkPay", oid: oid}, //请求的数据
                dataType: 'json',  //返回的数据类型
                cache: false,  //是否缓存
                success: function (data) { //回调函数，把data渲染到页面中
                    if(data.result === "成功"){
                        location.href = '${pageContext.request.contextPath}/face/faceCarServlet?method=gotoPay&oid='+oid;
                    }
                    else {
                        alert("已付款！");
                    }
                }

            });

        };




    </script>

</head>

<body>
<div class="divMain">
    <div class="divTitle">
        <span style="margin-left: 150px;margin-right: 280px;">商品信息</span>
        <span style="margin-left: 40px;margin-right: 38px;">金额</span>
        <span style="margin-left: 50px;margin-right: 40px;">订单状态</span>
        <span style="margin-left: 50px;margin-right: 50px;">操作</span>
    </div>
    <br/>
    <table align="center" border="0" width="100%" cellpadding="0" cellspacing="0">

<c:forEach items="${requestScope.pageBean.list}" var="order">

    <tr class="tt">
        <td width="320px">订单号：<a href="${pageContext.request.contextPath}/face/faceCarServlet?method=showMyOrderItem&oid=${order.oid}">${order.oid}</a>
        </td>
        <td width="200px">下单时间：${order.ordertime}</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>
    <tr style="padding-top: 10px; padding-bottom: 10px;">
        <td colspan="2">

            <c:forEach items="${order.images}" var="img">
                <a class="link2">
                    <img border="0" width="70" src="${pageContext.request.contextPath}/face/faceBookServlet?method=drawImg&path=${img}"/>
                </a>
            </c:forEach>


        </td>
        <td width="115px">
            <span class="price_t">&yen;${order.total}</span>
        </td>
        <td width="142px" id="${order.oid}">(
            <c:choose>
                <c:when test="${order.status == 1}">未付款</c:when>
                <c:when test="${order.status == 2}">已付款</c:when>
                <c:when test="${order.status == 3}">已发货</c:when>
                <c:when test="${order.status == 4}">交易成功</c:when>
                <c:when test="${order.status == 5}">已取消</c:when>
            </c:choose>)
        </td>
        <td>
            <a href="${pageContext.request.contextPath}/face/faceCarServlet?method=showMyOrderItem&oid=${order.oid}">查看</a><br/>

            <a href="javascript:payOrder('${order.oid}')">支付</a><br/>
            <a href="javascript:cancelOrder('${order.oid}')">取消</a><br/>

            <a href="javascript:receiptOrder('${order.oid}')">确认收货</a><br/>

        </td>
    </tr>
</c:forEach>


    </table>
    <br/>
    <%@include file="/web/jsps/order/pager.jsp" %>
</div>
</body>
</html>

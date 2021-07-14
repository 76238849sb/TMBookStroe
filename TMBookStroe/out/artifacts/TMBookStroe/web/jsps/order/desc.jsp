<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>订单详细</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/jsps/css/order/desc.css">
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
                        alert("取消成功！");
                        $("#orderType").text("(已取消)");
                    }
                    else {
                        alert("取消失败！");
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
                        $("#orderType").text("(交易成功)");
                    }
                    else {
                        alert("还未发货！");
                    }
                }

            });

        };


    </script>

</head>

<body>
<div class="divOrder">
		<span>订单号：${requestScope.order.oid}
            <span id="orderType">
                ( <c:choose>
                <c:when test="${order.status == 1}">未付款</c:when>
                <c:when test="${order.status == 2}">已付款</c:when>
                <c:when test="${order.status == 3}">已发货</c:when>
                <c:when test="${order.status == 4}">交易成功</c:when>
                <c:when test="${order.status == 5}">已取消</c:when>
            </c:choose>)
            </span>

		　　　下单时间：${requestScope.order.ordertime}</span>
</div>
<div class="divContent">
    <div class="div2">
        <dl>
            <dt>收货人信息</dt>
            <dd>${requestScope.order.address}</dd>
        </dl>
    </div>
    <div class="div2">
        <dl>
            <dt>商品清单</dt>
            <dd>
                <table cellpadding="0" cellspacing="0">
                    <tr>
                        <th class="tt">商品名称</th>
                        <th class="tt" align="left">单价</th>
                        <th class="tt" align="left">数量</th>
                        <th class="tt" align="left">小计</th>
                    </tr>


                    <c:forEach items="${requestScope.orderItem}" var="item">
                        <tr style="padding-top: 20px; padding-bottom: 20px;">
                            <td class="td" width="400px">
                                <div class="bookname">
                                    <img align="middle" width="70"
                                         src="${pageContext.request.contextPath}/face/faceBookServlet?method=drawImg&path=${item.image_b}"/>
                                    <a href="${pageContext.request.contextPath}/face/faceBookServlet?method=findBookInfo&bid=${item.bid}&quantity=${item.quantity}">${item.bname}</a>
                                </div>
                            </td>
                            <td class="td">
                                <span>&yen;${item.currPrice}</span>
                            </td>
                            <td class="td">
                                <span>${item.quantity}</span>
                            </td>
                            <td class="td">
                                <span>&yen;${item.subtotal}</span>
                            </td>
                        </tr>
                    </c:forEach>


                </table>
            </dd>
        </dl>
    </div>
    <div style="margin: 10px 10px 10px 550px;">
        <span style="font-weight: 900; font-size: 15px;">合计金额：</span>
        <span class="price_t">&yen;${requestScope.order.total}</span><br/>

        <a href="${pageContext.request.contextPath}/face/faceCarServlet?method=gotoPay&oid=${requestScope.order.oid}" class="pay"></a><br/>
        <a id="cancel" href="javascript:cancelOrder('${requestScope.order.oid}')">取消订单</a><br/>
        <a id="confirm" href="javascript:receiptOrder('${requestScope.order.oid}')">确认收货</a><br/>
    </div>
</div>
</body>
</html>


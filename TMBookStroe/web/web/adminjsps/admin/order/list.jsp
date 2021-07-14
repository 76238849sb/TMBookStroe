<%@ page language="java" pageEncoding="UTF-8" %>
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/jsps/pager/pager.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/web/adminjsps/admin/css/order/list.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/css/css.css"/>


    <script src="https://s3.pstatp.com/cdn/expire-1-M/jquery/3.3.1/jquery.min.js"></script>
    <script>
        let deliver = (status, oid, index) => {
            let type = status === 3 ? "发货" : "取消";
            let tip = $("#"+oid).text().replace(/\s*/g, "");

            console.log(tip,type);
            if (type === "发货" && (tip === "已取消" || tip === "未付款")) {
                alert("该订单已取消或还未付款，无法发货！");
                return;
            }
            if (type === "取消" && tip === "交易成功") {
                alert("交易成功无法取消！");
                return;
            }
            if(type==="发货"&&(tip==="交易成功"||tip==="已发货")){
                alert("交易成功或已经发货！");
                return;
            }
            if (confirm("确认" + type + "吗？")) {
                //location.href = "${pageContext.request.contextPath}/web/orderServlet?method=setStatus&status="+status+"&oid="+oid;

                $.ajax(
                    {
                        url: "${pageContext.request.contextPath}/web/orderServlet",//请求的url ,后的servlet
                        async: false,//是否是异步请求，默认值: true。默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。
                        type: "GET",//使用get请求
                        data: {method: "setStatus", status: status, oid: oid},//请求参数，使用checkUser的方法。
                        dataType: "json",//响应回来的数据类型
                        cache: false,//是否缓存，默认值: true，dataType 为 script 和 jsonp 时默认为 false。设置为 false 将不缓存此页面。
                        success: function (data) {

                            $(".orderType").eq(index).text(data.orderType);

                        }
                    }
                );
            }

        };
    </script>
</head>

<body>
<p class="pLink">
    <a href="${pageContext.request.contextPath}/web/orderServlet?method=findCategoryOrder&status=1&pageNow=1">未付款</a> |
    <a href="${pageContext.request.contextPath}/web/orderServlet?method=findCategoryOrder&status=2&pageNow=1">已付款</a> |
    <a href="${pageContext.request.contextPath}/web/orderServlet?method=findCategoryOrder&status=3&pageNow=1">已发货</a> |
    <a href="${pageContext.request.contextPath}/web/orderServlet?method=findCategoryOrder&status=4&pageNow=1">交易成功</a> |
    <a href="${pageContext.request.contextPath}/web/orderServlet?method=findCategoryOrder&status=5&pageNow=1">已取消</a>
</p>
<div class="divMain">
    <div class="title">
        <div style="margin-top:7px;">
            <span style="margin-left:17%">商品信息</span>
            <span style="margin-left: 20%">下单时间</span>
            <span style="margin-left: 12%">金额</span>
            <span style="margin-left: 8%">订单状态</span>
            <span style="margin-left: 12%">操作</span>
        </div>
    </div>
    <br/>


    <table align="center" border="0" width="100%" cellpadding="0" cellspacing="0">

        <c:forEach items="${requestScope.pageBean.list}" var="order" varStatus="vs">
            <tr class="tt">
                <td width="35%" style="text-align: center">订单号：<a
                        href="${pageContext.request.contextPath}/web/orderServlet?method=showOrderItem&oid=${order.oid}">${order.oid}</a>
                </td>
                <td width="22%" style="text-align: center">${order.ordertime}</td>
                <td style="text-align: center" width="10%">
                    <span class="price_t">&yen;${order.total}</span>
                </td>
                <td width="13%" style="text-align: center" class="orderType" id="${order.oid}">
                    <c:choose>
                        <c:when test="${order.status == 1}">未付款</c:when>
                        <c:when test="${order.status == 2}">已付款</c:when>
                        <c:when test="${order.status == 3}">已发货</c:when>
                        <c:when test="${order.status == 4}">交易成功</c:when>
                        <c:when test="${order.status == 5}">已取消</c:when>
                    </c:choose>

                </td>
                <td width="20%" style="text-align: center">
                    <a href="${pageContext.request.contextPath}/web/orderServlet?method=showOrderItem&oid=${order.oid}">查看&nbsp;&nbsp;</a>
                    <a href="javascript:deliver(5,'${order.oid}','${vs.index}')">取消&nbsp;&nbsp;</a>
                    <a href="javascript:deliver(3,'${order.oid}','${vs.index}')">发货</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <%@include file="pager.jsp" %>
</div>
</body>
</html>

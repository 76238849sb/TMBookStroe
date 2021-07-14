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
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/web/adminjsps/admin/css/order/desc.css">
    <script src="https://s3.pstatp.com/cdn/expire-1-M/jquery/3.3.1/jquery.min.js"></script>
    <script>
        let deliver = (status, oid) => {
            let type = status === 3 ? "发货" : "取消";
            let tip = $("#orderType").text().replace(/\s*/g, "");
            console.log(type, tip);
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

                            $("#orderType").text(data.orderType);
                        }
                    }
                );
            }

        };
    </script>


</head>

<body>
<div class="divOrder">
		<span>订单号：${requestScope.order.oid}&nbsp;&nbsp;&nbsp;&nbsp;
            (<span id="orderType">

                 <c:choose>
                     <c:when test="${requestScope.order.status == 1}">未付款</c:when>
                     <c:when test="${requestScope.order.status == 2}">已付款</c:when>
                     <c:when test="${requestScope.order.status == 3}">已发货</c:when>
                     <c:when test="${requestScope.order.status == 4}">交易成功</c:when>
                     <c:when test="${requestScope.order.status == 5}">已取消</c:when>
                 </c:choose>
            </span>)

		　　　下单时间：${requestScope.order.ordertime}
		</span>
</div>
<div class="divRow">
    <div class="divContent">
        <dl>
            <dt>收货人信息</dt>
            <dd>${requestScope.order.address}</dd>
        </dl>
    </div>
    <div class="divContent">
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


                    <c:forEach items="${requestScope.orderItems}" var="orderItem">
                        <tr style="padding-top: 20px; padding-bottom: 20px;">
                            <td class="td" width="400px">
                                <div class="bookname">
                                    <img align="middle" width="70"
                                         src="${pageContext.request.contextPath}/web/bookServlet?method=drawImg&path=${orderItem.image_b}"/>
                                        ${orderItem.bname}
                                </div>
                            </td>
                            <td class="td">
                                <span>&yen;${orderItem.currPrice}</span>
                            </td>
                            <td class="td">
                                <span>${orderItem.quantity}</span>
                            </td>
                            <td class="td">
                                <span>&yen;${orderItem.subtotal}</span>
                            </td>
                        </tr>
                    </c:forEach>

                </table>
            </dd>
        </dl>
    </div>
    <div class="divBtn">
        <span class="spanTotal">合　　计：</span>
        <span class="price_t">&yen;${requestScope.order.total}</span><br/>

        <a id="deliver" href="javascript:deliver(3,'${requestScope.order.oid}')">发　　货</a>
        <a id="cancel" href="javascript:deliver(5,'${requestScope.order.oid}')">取　　消</a>

    </div>
</div>
</body>
</html>


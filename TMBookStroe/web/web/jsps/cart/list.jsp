<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>cartlist.jsp</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <script src="${pageContext.request.contextPath}/web/jquery/jquery-1.5.1.js"></script>
    <script src="${pageContext.request.contextPath}/web/js/round.js"></script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/jsps/css/cart/list.css">
    <script type="text/javascript">
        $(function () {
            // 给全选按钮添加点击事件
            $("#selectAll").click(function () {
                var flag = $(this).attr("checked");//获取全选的状态
                setAll(flag);//让所有条目复选框与全选同步
                setJieSuanStyle(flag);//让结算按钮与全选同步
            });

            // 给条目复选框添加事件
            $(":checkbox[name=checkboxBtn]").click(function () {
                var selectedCount = $(":checkbox[name=checkboxBtn][checked=true]").length;//被勾选复选框个数
                var allCount = $(":checkbox[name=checkboxBtn]").length;//所有条目复选框个数
                if (selectedCount === allCount) {//全选了
                    $("#selectAll").attr("checked", true);//勾选全选复选框
                    setJieSuanStyle(true);//使结算按钮可用
                } else if (selectedCount === 0) {//全撤消了
                    $("#selectAll").attr("checked", false);//撤消全选复选框
                    setJieSuanStyle(false);//使结算按钮不可用
                } else {//未全选
                    $("#selectAll").attr("checked", false);//撤消全选复选框
                    setJieSuanStyle(true);//使结算按钮可用
                }
            });

            $("#total").text(round(${requestScope.total}, 2));


        });


        // 设置所有条目复选框
        function setAll(flag) {
            $(":checkbox[name=checkboxBtn]").attr("checked", flag);//让所有条目的复选框与参数flag同步
        }

        // 设置结算按钮的样式
        function setJieSuanStyle(flag) {
            if (flag) {// 有效状态
                $("#jiesuan").removeClass("kill").addClass("jiesuan");//切换样式
                $("#jiesuan").unbind("click");//撤消“点击无效”
            } else {// 无效状态
                $("#jiesuan").removeClass("jiesuan").addClass("kill");//切换样式
                $("#jiesuan").click(function () {//使其“点击无效”
                    return false;
                });
            }
        }


        let deleteItem = (cartItemId) => {

            $.ajax({
                url: "${pageContext.request.contextPath}/face/faceCarServlet",  //请求的url，后台的servlet
                async: true,  //指定是否是异步请求
                type: 'GET',  //请求类型
                data: {method: "deleteCarItem", cartItemId: cartItemId}, //请求的数据
                dataType: 'json',  //返回的数据类型
                cache: false,  //是否缓存
                success: function (data) { //回调函数，把data渲染到页面中
                    console.log(data.subTotal);

                    if (data.result === "成功") {
                        $("#" + cartItemId).parents("tr").remove();
                        $("#msg").text("删除成功！")

                    } else {
                        $("#msg").text("删除失败！")
                    }

                    let total = round(data.subTotal, 2);
                    $("#total").text(round(total, 2));

                    setTimeout(function () {
                        $("#msg").text("")
                    }, 3000);

                }

            });


        };

        let deleteItems = () => {
            $("input[name='checkboxBtn']").each(function () {
                if ($(this).attr('checked')) {

                    deleteItem($(this).val());

                }
            });

        };

        let submitOrder = () => {
            let address = prompt("请输入收货地址：");
            address = address.replace(/\s*/g, "");
            if (address === "") {
                alert("不能填空值！")
                submitOrder();
            } else {
                location.href = "${pageContext.request.contextPath}/face/faceCarServlet?method=submitOrder&address=" + address + "&total=" + $("#total").text();
            }

        };

    </script>
</head>
<body>


<c:choose>
    <c:when test="${requestScope.total==0}">
        <table width="95%" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td align="right">
                    <img align="top" src="${pageContext.request.contextPath}/web/images/icon_empty.png"/>
                </td>
                <td>
                    <span class="spanEmpty">您的购物车中暂时没有商品</span>
                </td>
            </tr>
        </table>
    </c:when>
</c:choose>

<br/>
<br/>
<form id="form1" action="${pageContext.request.contextPath}/web/jsps/cart/showitem.jsp" method="post">
    <input type="hidden" name="cartItemIds" id="cartItemIds"/>
    <input type="hidden" name="method" value="loadCartItems"/>

    <table width="95%" align="center" cellpadding="0" cellspacing="0" id="form">
        <tr align="center" bgcolor="#efeae5">
            <td align="left" width="50px">
                <input type="checkbox" id="selectAll" checked="checked"/><label for="selectAll">全选</label>
            </td>
            <td colspan="2">商品名称</td>
            <td>单价</td>
            <td>数量</td>
            <td>小计</td>
            <td>操作</td>
        </tr>

        <c:forEach items="${requestScope.carList}" var="car">
            <tr align="center">
                <td align="left">
                    <input value="${car.cartItemId}" type="checkbox" name="checkboxBtn" checked="checked"/>
                </td>
                <td align="left" width="70px">
                    <a class="linkImage"
                       href="${pageContext.request.contextPath}/face/faceBookServlet?method=findBookInfo&bid=${car.bid}&quantity=${car.quantity}"><img
                            border="0"
                            width="54"
                            align="top"
                            src="${pageContext.request.contextPath}/face/faceBookServlet?method=drawImg&path=${car.image_b}"/></a>
                </td>
                <td align="left" width="400px">
                    <a href="${pageContext.request.contextPath}/face/faceBookServlet?method=findBookInfo&bid=${car.bid}&quantity=${car.quantity}"><span>${car.bname}</span></a>
                </td>
                <td><span>&yen;<span class="currPrice" id="12345CurrPrice">${car.currPrice}</span></span></td>
                <td>
                    <span class="quantity">${car.quantity}</span>
                </td>
                <td width="100px">
                    <span class="price_n">&yen;<span class="subTotal">${car.subtotal}</span></span>
                </td>
                <td>
                    <a class="delet" id="${car.cartItemId}" onclick="deleteItem('${car.cartItemId}')">删除</a>
                </td>
            </tr>
        </c:forEach>


        <tr>
            <td colspan="4" class="tdBatchDelete">
                <a href="javascript:deleteItems()">批量删除</a>
                <span id="msg" style="color: green;text-align: center"></span>
            </td>

            <td colspan="3" align="right" class="tdTotal">
                <span>总计：</span><span class="price_t">&yen;<span id="total"></span></span>
            </td>
        </tr>
        <tr>
            <td colspan="7" align="right">
                <a href="javascript:submitOrder()" id="jiesuan"
                   class="jiesuan"></a>
            </td>
            <br>
        </tr>
    </table>
</form>

</body>
</html>

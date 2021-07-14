<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>My JSP 'bookdesc.jsp' starting page</title>

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
          href="${pageContext.request.contextPath}/web/adminjsps/admin/css/book/add.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/jquery/jquery.datepick.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/web/jquery/jquery-1.5.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/web/jquery/jquery.datepick.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/web/jquery/jquery.datepick-zh-CN.js"></script>
    <script src="${pageContext.request.contextPath}/web/js/round.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#publishtime").datepick({dateFormat: "yy-mm-dd"});
            $("#printtime").datepick({dateFormat: "yy-mm-dd"});

            $("#btn").addClass("btn1");
            $("#btn").hover(
                function () {
                    $("#btn").removeClass("btn1");
                    $("#btn").addClass("btn2");
                },
                function () {
                    $("#btn").removeClass("btn2");
                    $("#btn").addClass("btn1");
                }
            );

            $("#btn").click(function () {
                var bname = $("#bname").val();
                var currPrice = $("#currPrice").val();
                var price = $("#price").val();
                var discount = $("#discount").val();
                var author = $("#author").val();
                var press = $("#press").val();
                var pid = $("#pid").val();
                var cid = $("#cid").val();
                var image_w = $("#image_w").val();
                var image_b = $("#image_b").val();

                if (!bname || !currPrice || !price || !discount || !author || !press || !pid || !cid || !image_w || !image_b) {
                    alert("图名、当前价、定价、折扣、作者、出版社、1级分类、2级分类、大图、小图都不能为空！");
                    return false;
                }

                if (isNaN(currPrice) || isNaN(price) || isNaN(discount)) {
                    alert("当前价、定价、折扣必须是合法小数！");
                    return false;
                }
                $("#form").submit();
            });
        });


        let changeOption = (pid) => {

            $.ajax(
                {
                    url: "${pageContext.request.contextPath}/web/bookServlet",//请求的url ,后的servlet
                    async: false,//是否是异步请求，默认值: true。默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。
                    type: "GET",//使用get请求
                    data: {method: "showBookCategory", pid: pid},//请求参数，使用checkUser的方法。
                    dataType: "json",//响应回来的数据类型
                    cache: false,//是否缓存，默认值: true，dataType 为 script 和 jsonp 时默认为 false。设置为 false 将不缓存此页面。
                    success: function (data) {
                        $("#cid").empty();
                        console.log(data.cname[0])
                        //请求成功后的回调函数，把date渲染到页面中
                        for (let i = 0; i < data.cid.length; i++) {
                            $("#cid").append("<option value='" + data.cid[i] + "'>" + data.cname[i] + "</option>");
                        }

                    }
                }
            );
        };


        let pri = () => {
            let price = $("#price").val();
            let currPrice = $("#currPrice").val();
            if (!isNaN(currPrice) && !isNaN(price)) {
                $("#discount").val(round(currPrice * 1.0 / price * 10, 1));
            }

        };

        let disc = () => {
            let discount = $("#discount").val() / 10;
            let price = $("#price").val();
            if (!isNaN(discount) && !isNaN(price)) {
                $("#currPrice").val(round(discount * price, 1));
            }

        };

    </script>
</head>

<body>
<div>
    <form action="${pageContext.request.contextPath}/web/addBookServlet" enctype="multipart/form-data" method="post"
          id="form">

        <div>
            <ul>
                <li>书名：　<input id="bname" type="text" name="bname" value="" style="width:500px;"/></li>
                <li>大图：　<input id="image_w" type="file" name="image_w" accept="image/*"/></li>
                <li>小图：　<input id="image_b" type="file" name="image_b" accept="image/*"/></li>
                <li>当前价：<input id="currPrice" onblur="pri()" type="number" step="0.1" min="0" name="currPrice" value=""
                               style="width:100px;"/>
                </li>
                <li>定价：　<input id="price" onblur="pri()" type="number" step="0.1" min="0" name="price" value=""
                               style="width:100px;"/>
                    折扣：<input id="discount" onblur="disc()" type="number" step="0.1" min="0" name="discount" value=""
                              style="width:80px;"/>折
                </li>
            </ul>
            <hr style="margin-left: 50px; height: 1px; color: #dcdcdc"/>
            <table>
                <tr>
                    <td colspan="3">
                        作者：　　<input type="text" id="author" name="author" value="" style="width:150px;"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        出版社：　<input type="text" name="press" id="press" value="" style="width:200px;"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">出版时间：<input type="text" id="publishtime" name="publishtime" value=""
                                                style="width:120px;"/></td>
                </tr>
                <tr>
                    <td>版次：　　<input type="number" min="1" name="edition" id="edition" value="" style="width:80px;"/>
                    </td>
                    <td>页数：　　<input type="number" min="1" name="pageNum" id="pageNum" value="" style="width:120px;"/>
                    </td>
                    <td>字数：　　<input type="number" min="1" name="wordNum" id="wordNum" value="" style="width:200px;"/>
                    </td>
                </tr>
                <tr>
                    <td width="250">印刷时间：<input type="text" name="printtime" id="printtime" value=""
                                                style="width:120px;"/></td>
                    <td width="250">开本：　　<input type="number" min="1" name="booksize" id="booksize" value="16"
                                                style="width:50px;"/></td>
                    <td>纸张：　　<input type="text" name="paper" id="paper" value="胶版纸" style="width:80px;"/></td>
                </tr>
                <tr>
                    <td>
                        一级分类：<select name="pid" id="pid" onchange="changeOption(this.value)">
                        <option value="">==请选择1级分类==</option>
                        <c:forEach items="${requestScope.allCategory}" var="category1">
                            <option value="${category1.cid}">${category1.cname}</option>
                        </c:forEach>


                    </select>
                    </td>
                    <td>
                        二级分类：<select name="cid" id="cid">
                        <option value="">==请选择2级分类==</option>

                        <c:forEach items="${requestScope.categoryTwo}" var="category2">

                            <option value="${category2.cid}">${category2.cname}</option>

                        </c:forEach>
                    </select>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <input type="button" id="btn" class="btn" value="新书上架">
                    </td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </div>
    </form>
</div>

</body>
</html>

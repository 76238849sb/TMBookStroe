<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>book_desc.jsp</title>

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
          href="${pageContext.request.contextPath}/web/adminjsps/admin/css/book/desc.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/jquery/jquery.datepick.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/web/jquery/jquery-1.5.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/web/jquery/jquery.datepick.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/web/jquery/jquery.datepick-zh-CN.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/web/adminjsps/admin/js/book/desc.js"></script>
    <script src="${pageContext.request.contextPath}/web/js/round.js"></script>

    <script type="text/javascript">

        $(function () {
            $("#box").attr("checked", false);
            $("#formDiv").css("display", "none");
            $("#show").css("display", "");
            // 操作和显示切换
            $("#box").click(function () {
                if ($(this).attr("checked")) {
                    $("#show").css("display", "none");
                    $("#formDiv").css("display", "");
                } else {
                    $("#formDiv").css("display", "none");
                    $("#show").css("display", "");
                }
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

        let delForm = (bid) => {
            location.href = "${pageContext.request.contextPath}/web/bookServlet?method=deleteBook&bid=" + bid;
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
<input type="checkbox" id="box"><label for="box">编辑或删除</label>
<br/>
<br/>
<div id="show">
    <div class="sm">${requestScope.bookInfo.bname}</div>
    <img align="top"
         src="${pageContext.request.contextPath}/web/bookServlet?method=drawImg&path=${requestScope.bookInfo.image_w}"
         class="tp"/>
    <div id="book" style="float:left;">
        <ul>
            <li>商品编号：${requestScope.bookInfo.bid}</li>
            <li>当前价：<span class="price_n">&yen;${requestScope.bookInfo.currPrice}</span></li>
            <li>定价：<span style="text-decoration:line-through;">&yen;${requestScope.bookInfo.price}</span>　
                折扣：<span style="color: #c30;">${requestScope.bookInfo.discount}</span>折
            </li>
        </ul>
        <hr style="margin-left: 50px; height: 1px; color: #dcdcdc"/>
        <table class="tab">
            <tr>
                <td colspan="3">
                    作者：${requestScope.bookInfo.author}著
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
                <td>开本：${requestScope.bookInfo.booksize}开</td>
                <td>纸张：${requestScope.bookInfo.paper}</td>
            </tr>
        </table>
    </div>
</div>


<div id='formDiv'>
    <div class="sm">&nbsp;</div>
    <form action="${pageContext.request.contextPath}/web/bookServlet" method="post" id="form">
        <input type="hidden" name="method" value="updateBookInfo"/>
        <input type="hidden" name="bid" value="${requestScope.bookInfo.bid}"/>
<%--        <input type="hidden" name="cid" value="${requestScope.bookInfo.cid}"/>--%>
        <img align="top"
             src="${pageContext.request.contextPath}/web/bookServlet?method=drawImg&path=${requestScope.bookInfo.image_w}"
             class="tp"/>
        <div style="float:left;">
            <ul>
                <li>${requestScope.bookInfo.bname}</li>
                <li>书名：　<input id="bname" type="text" name="bname"
                               value="${requestScope.bookInfo.bname}" style="width:500px;"/>
                </li>
                <li>当前价：<input id="currPrice" onblur="pri()" type="number" step="0.1" min="0" name="currPrice"
                               value="${requestScope.bookInfo.currPrice}"
                               style="width:100px;"/></li>
                <li>定价：　<input id="price" onblur="pri()" type="number" step="0.1" min="0" name="price"
                               value="${requestScope.bookInfo.price}"
                               style="width:100px;"/>
                    折扣：<input id="discount" onblur="disc()" type="number" step="0.1" min="0" name="discount"
                              value="${requestScope.bookInfo.discount}"
                              style="width:80px;"/>折
                </li>
            </ul>
            <hr style="margin-left: 50px; height: 1px; color: #dcdcdc"/>
            <table class="tab">
                <tr>
                    <td colspan="3">
                        作者：　　<input id="author" type="text" name="author" value="${requestScope.bookInfo.author}"
                                    style="width:150px;"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        出版社：　<input id="press" type="text" name="press" value="${requestScope.bookInfo.press}"
                                    style="width:200px;"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">出版时间：<input id="publishtime" type="text" name="publishtime"
                                                value="${requestScope.bookInfo.publishtime}" style="width:120px;"/></td>
                </tr>
                <tr>
                    <td>版次：　　<input id="edition" type="number" min="1" name="edition" value="${requestScope.bookInfo.edition}"
                                    style="width:50px;"/></td>
                    <td>页数：　　<input id="pageNum" type="number" min="1" name="pageNum" value="${requestScope.bookInfo.pageNum}"
                                    style="width:100px;"/></td>
                    <td>字数：　　<input id="wordNum" type="number" min="1" name="wordNum" value="${requestScope.bookInfo.wordNum}"
                                    style="width:120px;"/></td>
                </tr>
                <tr>
                    <td width="250px">印刷时间：<input id="printtime" type="text" name="printtime"
                                                  value="${requestScope.bookInfo.printtime}" style="width:100px;"/></td>
                    <td width="250px">开本：　　<input id="booksize" type="number" min="1" name="booksize"
                                                  value="${requestScope.bookInfo.booksize}" style="width:50px;"/></td>
                    <td>纸张：　　<input id="paper" type="text" name="paper" value="${requestScope.bookInfo.paper}"
                                    style="width:80px;"/></td>
                </tr>

                <tr>
                    <td>
                        一级分类：
                        <select name="pid" id="pid" onchange="changeOption(this.value)">
                            <option value="">==请选择1级分类==</option>
                            <c:forEach items="${requestScope.allCategory}" var="category1">
                                <c:choose>
                                    <c:when test="${category1.cid==requestScope.bookCategoryInfo.pid}">
                                        <option value="${category1.cid}" selected="selected">${category1.cname}</option>
                                    </c:when>

                                    <c:otherwise>
                                        <option value="${category1.cid}">${category1.cname}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                        </select>
                    </td>

                    <td>
                        二级分类：
                        <select name="cid" id="cid">
                            <option value="">==请选择2级分类==</option>

                            <c:forEach items="${requestScope.categoryTwo}" var="category2">
                                <c:choose>
                                    <c:when test="${category2.cid==requestScope.bookCategoryInfo.cid}">
                                        <option value="${category2.cid}" selected='selected'>${category2.cname}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${category2.cid}">${category2.cname}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                        </select>
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <input onclick="editForm()" type="submit" value="确认修改">
                        <input onclick="delForm('${requestScope.bookInfo.bid}')" type="button" value="删　　除">
                    </td>
                    <td></td>
                </tr>
            </table>
        </div>
    </form>
</div>

</body>
</html>

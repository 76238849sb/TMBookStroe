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
                    alert("????????????????????????????????????????????????????????????1????????????2?????????????????????????????????????????????");
                    return false;
                }

                if (isNaN(currPrice) || isNaN(price) || isNaN(discount)) {
                    alert("???????????????????????????????????????????????????");
                    return false;
                }
                $("#form").submit();
            });
        });


        let changeOption = (pid) => {

            $.ajax(
                {
                    url: "${pageContext.request.contextPath}/web/bookServlet",//?????????url ,??????servlet
                    async: false,//?????????????????????????????????: true??????????????????????????????????????????????????????????????????????????????????????????????????????????????? false???
                    type: "GET",//??????get??????
                    data: {method: "showBookCategory", pid: pid},//?????????????????????checkUser????????????
                    dataType: "json",//???????????????????????????
                    cache: false,//????????????????????????: true???dataType ??? script ??? jsonp ???????????? false???????????? false ????????????????????????
                    success: function (data) {
                        $("#cid").empty();
                        console.log(data.cname[0])
                        //????????????????????????????????????date??????????????????
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
                <li>????????????<input id="bname" type="text" name="bname" value="" style="width:500px;"/></li>
                <li>????????????<input id="image_w" type="file" name="image_w" accept="image/*"/></li>
                <li>????????????<input id="image_b" type="file" name="image_b" accept="image/*"/></li>
                <li>????????????<input id="currPrice" onblur="pri()" type="number" step="0.1" min="0" name="currPrice" value=""
                               style="width:100px;"/>
                </li>
                <li>????????????<input id="price" onblur="pri()" type="number" step="0.1" min="0" name="price" value=""
                               style="width:100px;"/>
                    ?????????<input id="discount" onblur="disc()" type="number" step="0.1" min="0" name="discount" value=""
                              style="width:80px;"/>???
                </li>
            </ul>
            <hr style="margin-left: 50px; height: 1px; color: #dcdcdc"/>
            <table>
                <tr>
                    <td colspan="3">
                        ???????????????<input type="text" id="author" name="author" value="" style="width:150px;"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        ???????????????<input type="text" name="press" id="press" value="" style="width:200px;"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">???????????????<input type="text" id="publishtime" name="publishtime" value=""
                                                style="width:120px;"/></td>
                </tr>
                <tr>
                    <td>???????????????<input type="number" min="1" name="edition" id="edition" value="" style="width:80px;"/>
                    </td>
                    <td>???????????????<input type="number" min="1" name="pageNum" id="pageNum" value="" style="width:120px;"/>
                    </td>
                    <td>???????????????<input type="number" min="1" name="wordNum" id="wordNum" value="" style="width:200px;"/>
                    </td>
                </tr>
                <tr>
                    <td width="250">???????????????<input type="text" name="printtime" id="printtime" value=""
                                                style="width:120px;"/></td>
                    <td width="250">???????????????<input type="number" min="1" name="booksize" id="booksize" value="16"
                                                style="width:50px;"/></td>
                    <td>???????????????<input type="text" name="paper" id="paper" value="?????????" style="width:80px;"/></td>
                </tr>
                <tr>
                    <td>
                        ???????????????<select name="pid" id="pid" onchange="changeOption(this.value)">
                        <option value="">==?????????1?????????==</option>
                        <c:forEach items="${requestScope.allCategory}" var="category1">
                            <option value="${category1.cid}">${category1.cname}</option>
                        </c:forEach>


                    </select>
                    </td>
                    <td>
                        ???????????????<select name="cid" id="cid">
                        <option value="">==?????????2?????????==</option>

                        <c:forEach items="${requestScope.categoryTwo}" var="category2">

                            <option value="${category2.cid}">${category2.cname}</option>

                        </c:forEach>
                    </select>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <input type="button" id="btn" class="btn" value="????????????">
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

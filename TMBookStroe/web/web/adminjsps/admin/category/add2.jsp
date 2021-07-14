<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>添加分类</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/web/jquery/jquery-1.5.1.js"></script>
    <script type="text/javascript">
        function checkForm() {
            if (!$("#cname").val()) {
                alert("分类名不能为空！");
                return false;
            }

            if (!$("#desc").val()) {
                alert("分类描述不能为空！");
                return false;
            }
            return true;
        }

        let checkCategoryTwoName = (val) => {

            $.ajax({
                url: "${pageContext.request.contextPath}/web/categoryServlet",  //请求的url，后台的servlet
                async: true,  //指定是否是异步请求
                type: 'GET',  //请求类型
                data: {method: "checkCategoryName", cname: val}, //请求的数据
                dataType: 'json',  //返回的数据类型
                cache: false,  //是否缓存
                success: function (data) { //回调函数，把data渲染到页面中

                    if (data.result !== "正确") {
                        $("#tip").text("该名称已存在");
                        $("#cname").focus();
                    }else {
						$("#tip").text("");

					}

                }

            });

        };


    </script>
    <style type="text/css">
        body {
            background: rgb(254, 238, 189);
        }
    </style>
</head>

<body>
<h3>添加2级分类</h3>
<form action="${pageContext.request.contextPath}/web/categoryServlet" method="post" onsubmit="return checkForm()">
    <input type="hidden" name="method" value='addTwoLevel'/>
    <input type="hidden" name="pid" value="${requestScope.pid}"/>
    分类名称：<input type="text" name="cname" onblur="checkCategoryTwoName(this.value)" id="cname"/>
    <span id="tip"></span>
    <br/>
    <br/>
    分类描述：<textarea rows="5" cols="50" name="desc" id="desc"></textarea><br/>
    <input type="submit" value="添加二级分类"/>
    <input type="button" value="返回" onclick="history.go(-1)"/>
</form>
</body>
</html>

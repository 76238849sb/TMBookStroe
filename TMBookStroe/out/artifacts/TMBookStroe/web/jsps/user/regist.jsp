<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>注册页面</title>
    <script src="https://s3.pstatp.com/cdn/expire-1-M/jquery/3.3.1/jquery.min.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/jsps/css/user/regist.css">


    <script type="text/javascript" src="${pageContext.request.contextPath}/web/jsps/js/user/regist.js"></script>

    <script>

        let tmp1 = true;
        let tmp2 = true;
        let tmp3 = true;
        let tmp4 = true;


        let checkEmail = () => {
            let reg = /^([\w_\-.])+@([\w_\-.])+\.([A-Za-z]{2,4})$/;
            let email = $("#email").val();
            if (!reg.test(email)) {
                $("#emailError").text("邮箱格式错误！");
                tmp1 = false;
            }

            //重复邮箱判断
            $.ajax({
                url: "${pageContext.request.contextPath}/face/faceUserServlet",  //请求的url，后台的servlet
                async: true,  //指定是否是异步请求
                type: 'GET',  //请求类型
                data: {method: "checkEmail", email: email}, //请求的数据
                dataType: 'json',  //返回的数据类型
                cache: false,  //是否缓存
                success: function (data) { //回调函数，把data渲染到页面中
                    if (data.result === "该邮箱已被注册！") {
                        $("#emailError").show();
                        $("#send").attr("disabled", "disabled");
                        $("#emailError").text(data.result);
                        tmp1 = false;
                    } else {
                        $("#emailError").hide();
                        $("#send").removeAttr("disabled");
                        tmp1 = true;
                    }
                }
            });
            $("#emailError").hide();
        };


        let checkPass = () => {
            let p1 = $("#loginpass").val();
            let p2 = $("#reloginpass").val();
            let reg = /^[a-zA-Z]\w{4,16}$/;
            if (!reg.test(p1)) {
                $("#loginpassError").show();
                $("#loginpassError").text("格式错误！长度4-16，字母开头,字母+数字");
                tmp2 = false;
            } else {
                $("#loginpassError").hide();
            }

            if (p1 !== p2) {
                $("#reloginpassError").show();
                $("#reloginpassError").text("两次密码不相同");
                tmp2 = false;
            } else {
                $("#reloginpassError").hide();
            }
            tmp2 = true;


        };


        let checkUserName = () => {
            val = $("#loginname").val().replace(/\s*/g, "");
            if (val === "") {
                $("#loginnameError").show();
                $("#loginnameError").text("用户名不能为空");
                tmp3 = false;
            } else {
                $("#loginnameError").hide();
            }

            //进行重复用户名的校验
            $.ajax({
                url: "${pageContext.request.contextPath}/face/faceUserServlet",  //请求的url，后台的servlet
                async: true,  //指定是否是异步请求
                type: 'GET',  //请求类型
                data: {method: "checkUserName", userName: val}, //请求的数据
                dataType: 'json',  //返回的数据类型
                cache: false,  //是否缓存
                success: function (data) { //回调函数，把data渲染到页面中
                    if (data.result === "错误") {
                        $("#loginnameError").show();
                        $("#loginnameError").text("该用户已存在！");
                        tmp3 = false;
                    } else {
                        $("#loginnameError").hide();
                        tmp3 = true;
                    }
                }
            });

        };

        /* let checkVCode = () => {
             let vcode = $("#vcode").val().toUpperCase();
             $.ajax({
                 url: "${pageContext.request.contextPath}/face/faceUserServlet",  //请求的url，后台的servlet
                async: true,  //指定是否是异步请求
                type: 'GET',  //请求类型
                data: {method: "checkCode", vcode: vcode}, //请求的数据
                dataType: 'json',  //返回的数据类型
                cache: false,  //是否缓存
                success: function (data) { //回调函数，把data渲染到页面中
                    if (data.result !== "正确") {
                        alert("验证码错误！");
                        return false;
                    }
                    return true;
                }
            });

        };*/

        let sendVCode = () => {
            let email = $("#email").val();

            let time = 30;
            let t = setInterval(function () {
                $("#send").val(time);
                $("#send").attr("disabled", "disabled");
                time -= 1;
                if (time <= 0) {
                    clearInterval(t);
                    $("#send").val("发送")
                    $("#send").removeAttr("disabled");
                }
            }, 1000);
            //发送验证码
            $.ajax({
                url: "${pageContext.request.contextPath}/face/faceUserServlet",  //请求的url，后台的servlet
                async: true,  //指定是否是异步请求
                type: 'GET',  //请求类型
                data: {method: "sendRegisterCode", email: email}, //请求的数据
                dataType: 'json',  //返回的数据类型
                cache: false,  //是否缓存
                success: function (data) { //回调函数，把data渲染到页面中
                    if (data.result === "成功") {
                        alert("发送成功！");
                    } else {
                        alert("发送失败！");
                    }
                }
            });

        };

        function onClickImg() {
            $("#vCode").attr("src", "${pageContext.request.contextPath}/face/validationCode?time=" + new Date().getTime())
        }

        let checkCode = () => {
            let code = $("#verifyCode").val();
            $.ajax({
                url: "${pageContext.request.contextPath}/face/faceUserServlet",  //请求的url，后台的servlet
                async: true,  //指定是否是异步请求
                type: 'GET',  //请求类型
                data: {method: "checkCode", verifyCode: code}, //请求的数据
                dataType: 'json',  //返回的数据类型
                cache: false,  //是否缓存
                success: function (data) { //回调函数，把data渲染到页面中
                    if (data.result !== "成功") {
                        $("#codeErr").text("验证码错误！");
                        tmp4 = false;
                    } else {
                        $("#codeErr").text("");
                        tmp4 = true;
                    }
                }
            });


        };


        let checkAll = () => {
            checkCode();checkEmail();checkUserName();checkPass();
            if (tmp1 && tmp2 && tmp3 && tmp4) {
                return true;
            } else {
                alert("请检查填写的数据是否符合要求！");
                return false;
            }
            /*console.log(checkEmail(),checkUserName(),checkPass(),checkCode());
            if(checkEmail()&&checkUserName()&&checkPass()&&checkCode()){
                let myDate = $("#form").serializeArray();
                let data = {};
                for (let i = 0; i < myDate.length; i++) {
                    data[myDate[i].name]=myDate[i]['value'];
                }
                data['method']='register';
                console.log(JSON.stringify(data));
                $.ajax({
                    url: "
            ${pageContext.request.contextPath}/face/faceUserServlet",  //请求的url，后台的servlet
                    async: true,  //指定是否是异步请求
                    type: 'GET',  //请求类型
                    contentType:"application/json",
                    data: JSON.stringify(data), //请求的数据
                    dataType: 'json',  //返回的数据类型
                    cache: false,  //是否缓存
                    success: function (data) { //回调函数，把data渲染到页面中
                        if (data.result === "成功") {
                            alert("请求提交成功！请查看邮箱并执行激活操作");
                        } else {
                            alert("请求提交失败！");
                        }
                    }
                });
            }*/


        };


    </script>

</head>

<body>
<div id="divMain">
    <div id="divTitle">
        <span id="spanTitle">新用户注册</span>
    </div>
    <div id="divBody">

        <form action="${pageContext.request.contextPath}/face/faceUserServlet" method="post" onsubmit="return checkAll()"
              id="form">
            <input type="hidden" name="method" value="register"/>
            <table id="tableForm">
                <tr>
                    <td class="tdText">用户名：</td>
                    <td class="tdInput">
                        <input class="inputClass" onblur="checkUserName()" type="text" name="loginname"
                               id="loginname"/>
                    </td>
                    <td class="tdError">
                        <label class="errorClass" id="loginnameError">用户名不能为空</label>
                    </td>
                </tr>
                <tr>
                    <td class="tdText">登录密码：</td>
                    <td>
                        <input class="inputClass" onblur="checkPass()" type="password" name="loginpass" id="loginpass"/>
                    </td>
                    <td>
                        <label class="errorClass" id="loginpassError">密码不能为空</label>
                    </td>
                </tr>
                <tr>
                    <td class="tdText">确认密码：</td>
                    <td>
                        <input class="inputClass" onblur="checkPass()" type="password" name="reloginpass"
                               id="reloginpass"/>
                    </td>
                    <td>
                        <label class="errorClass" id="reloginpassError">两次密码不相同</label>
                    </td>
                </tr>
                <tr>
                    <td class="tdText">Email：</td>
                    <td>
                        <input class="inputClass" onblur="checkEmail()" type="text" name="email" id="email"/>
                    </td>
                    <td>
                        <label class="errorClass" id="emailError">邮箱不能为空</label>
                    </td>
                </tr>

                <tr>
                    <td class="tdText">填写验证码：</td>
                    <td>
                        <input class="inputClass" onblur="checkCode()" style="width: 80px" type="text" name="verifyCode"
                               id="verifyCode"/>

                        <img id="vCode" onclick="onClickImg()"
                             style="width: 80px;height:32px;position: relative;top: 10px;"
                             src="${pageContext.request.contextPath}/face/validationCode"/>

                        <%--                        <input type="button" onclick="sendVCode()" value="发送" id="send"--%>
                        <%--                               style="width: 80px;height: 32px"/>--%>
                        <span id="codeErr"></span>

                    </td>
                    <td>
                        <label class="errorClass" id="verifyCodeError"></label>
                    </td>
                </tr>


                <tr>
                    <td></td>
                    <td>
                        <input type="submit" value=""
                               style="background-image: url('/TMBookStroe/web/images/regist1.jpg');width: 240px;height: 33px;"
                               id="submitBtn"/>
                    </td>
                    <td>
                        <label></label>
                    </td>
                </tr>
            </table>

        </form>


    </div>
</div>
</body>
</html>

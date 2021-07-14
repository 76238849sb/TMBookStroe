<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>登录</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/jsps/css/user/login.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/web/jquery/jquery-1.5.1.js"></script>
    <script src="${pageContext.request.contextPath}/web/js/common.js"></script>
    <script>


        function onClickImg() {
            $("#vCode").attr("src", "${pageContext.request.contextPath}/face/validationCode?time=" + new Date().getTime())
        }


        function checkUserNameValue(value) {
            if (value === "") {
                document.getElementById("loginnameError").innerHTML = "用户名不能为空";
            } else {
                document.getElementById("loginnameError").innerHTML = "";
            }
        }

        function checkUserPasswordValue(value) {
            if (value === "") {
                document.getElementById("loginpassError").innerHTML = "密码不能为空";
            } else {
                document.getElementById("loginpassError").innerHTML = "";
            }
        }

        function checkCode(value) {
            if (value === "") {
                document.getElementById("verifyCodeError").innerHTML = "验证码不能为空！"
            } else {
                document.getElementById("verifyCodeError").innerHTML = ""
            }

        }

    </script>


</head>

<body>
<div class="main">
    <div>
        <div class="imageDiv">
            <img class="img" src="${pageContext.request.contextPath}/web/images/zj.png"/></div>
        <div class="login1">
            <div class="login2">
                <div class="loginTopDiv">
                    <span class="loginTop">会员登录</span>
                    <span>
                <a href="${pageContext.request.contextPath}/web/jsps/user/regist.jsp" class="registBtn"></a>
              </span>
                </div>
                <div>


                    <form target="_top" action="${pageContext.request.contextPath}/face/faceUserServlet" method="post"
                          id="loginForm">
                        <input type="hidden" name="method" value="login"/>
                        <table>

                            <tr>
                                <td width="50"></td>
                                <td>
                                    <label class="error" id="msg">${requestScope.errinfo}</label>
                                </td>
                            </tr>

                            <tr>
                                <td width="50">用户名</td>
                                <td><input class="input" type="text" name="loginname" id="loginname"
                                           onblur="checkUserNameValue(this.value)"/></td>
                            </tr>

                            <tr>
                                <td height="20">&nbsp;</td>
                                <td>
                                    <span id="loginnameError" class="error"></span>
                                    <%--                                  <label id="loginnameError" class="error"></label>--%>
                                </td>
                            </tr>

                            <tr>
                                <td>密　码</td>
                                <td><input class="input" type="password" name="loginpass" id="loginpass"
                                           onblur="checkUserPasswordValue(this.value)"/></td>
                            </tr>
                            <tr>
                                <td height="20">&nbsp;</td>
                                <td>
                                    <span id="loginpassError" class="error"></span>
                                    <%--                                  <label id="loginpassError" class="error">密码不能为空</label>--%>
                                </td>
                            </tr>


                            <tr>
                                <td>验证码</td>
                                <td>
                                    <input class="input yzm" type="text" name="verifyCode" value=""
                                           onblur="checkCode(this.value)"/>
                                    <img id="vCode" onclick="onClickImg()"
                                         src="${pageContext.request.contextPath}/face/validationCode"/>
                                </td>
                            </tr>
                            <tr>
                                <td height="20px">&nbsp;</td>
                                <td>
                                    <span id="verifyCodeError" class="error">${requestScope.errinfo}</span>

                                    <%--                                  <label id="verifyCodeError" class="error">验证码不能为空！</label>--%>
                                </td>
                            </tr>


                            <tr>
                                <td>&nbsp;</td>
                                <td align="left">
                                    <input type="submit" value="登录" id="submit" class="loginBtn"/>
                                </td>
                            </tr>
                        </table>
                    </form>


                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
	
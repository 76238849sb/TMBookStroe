<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>pwd.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/css/css.css">

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/jsps/css/user/pwd.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/jquery/jquery-1.5.1.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/jsps/css/user/regist.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/web/jsps/js/user/regist.js"></script>
	<script src="${pageContext.request.contextPath}/web/js/common.js"></script>
	<script>


		let checkPass = () => {
			let p2 = $("#newLoginPass").val();
			let p3 = $("#reloginpass").val();
			let reg = /^[a-zA-Z]\w{4,16}$/;
			if (!reg.test(p2)) {
				$("#loginpassError").show();
				$("#loginpassError").text("格式错误！长度4-16，字母开头,字母+数字");
				return false;
			} else {
				$("#loginpassError").hide();
			}

			if (p2 !== p3) {
				$("#reloginpassError").show();
				$("#reloginpassError").text("两次密码不相同");
				return false;
			} else {
				$("#reloginpassError").hide();
			}
			return true;
		};



		let checkOldPass =() =>{
			let  p1 = $("#oldloginpass").val();
			if (p1===""){
				$("#loginpassError1").show();
				return false;
			}else {
				$("#loginpassError1").hide();
					$.ajax({
						url: "${pageContext.request.contextPath}/face/faceUserServlet",  //请求的url，后台的servlet
						async: true,  //指定是否是异步请求
						type: 'GET',  //请求类型
						data: {method: "checkOldPass", oldPass: p1}, //请求的数据
						dataType: 'json',  //返回的数据类型
						cache: false,  //是否缓存
						success: function (data) { //回调函数，把data渲染到页面中
							if (data.result === "正确") {
								$("#loginpassError1").hide();
								return true;
							} else {
								$("#loginpassError1").show();
								return false;
							}
						}
					});

			}
		}

		let checkVCode = () => {
			let vcode = $("#vcode").val().toUpperCase();
			$.ajax({
				url: "${pageContext.request.contextPath}/face/faceUserServlet",  //请求的url，后台的servlet
				async: true,  //指定是否是异步请求
				type: 'GET',  //请求类型
				data: {method: "checkChangePass", vcode: vcode}, //请求的数据
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

			return false;

		};

		let sendVCode = () => {

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
				data: {method: "sendNewPassCode"}, //请求的数据
				dataType: 'json',  //返回的数据类型
				cache: false,  //是否缓存
				success: function (data) { //回调函数，把data渲染到页面中
					if (data.result === "成功") {
						alert("发送成功");
					} else {
						alert("发送失败！");
					}
				}
			});

		};

		let checkAll = () => {
			return checkPass()  && checkVCode() && checkOldPass();
		};
	</script>

</head>

<body>
<div class="div0">
	<span>修改密码</span>
</div>

<div class="div1">
	<form action="${pageContext.request.contextPath}/face/faceUserServlet" method="post" target="_top">
		<input type="hidden" name="method" value="checkChangePass"/>
		<table>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>


			<tr>
				<td align="right">原密码:</td>
				<td><input class="inputClass"  onblur="checkOldPass()" type="password" name="loginpass" id="oldloginpass" value=""/></td>
				<td>
					<label class="errorClass" id="loginpassError1">密码不能为空</label>
				</td>
			</tr>

			<tr>
				<td class="tdText">新密码：</td>
				<td>
					<input class="inputClass" onblur="checkPass()" type="password" name="newLoginPass" id="newLoginPass"/>
				</td>
				<td>
					<label class="errorClass" id="loginpassError">新密码不能为空</label>
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
				<td class="tdText">填写验证码：</td>
				<td style="position: relative;left: -15px">
					<input class="inputClass" style="width: 140px" type="text" name="vcode"
						   id="vcode"/>
					<input type="button"  onclick="sendVCode()" value="发送" id="send"
						   style="width: 80px;height: 32px;margin-left: 15px"/>

				</td>
				<td>
					<label class="errorClass" id="verifyCodeError"></label>
				</td>
			</tr>

			<tr>
				<td align="right"></td>
				<td><input id="submit" type="submit" value="修改密码"/></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>

<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>pay.jsp</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web/jsps/css/order/pay.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/web/jquery/jquery-1.5.1.js"></script>

    <script type="text/javascript">
        $(function () {
            $("img").click(function () {
                $("#" + $(this).attr("name")).attr("checked", true);
            });

        });



        let sub = () => {
            $("#submit").css("cursor","wait");
            $("#submit").attr("href","#");
            $("#submit").text("请等待……");
            $.ajax({
                url: "${pageContext.request.contextPath}/face/faceCarServlet",  //请求的url，后台的servlet
                async: true,  //指定是否是异步请求
                type: 'GET',  //请求类型
                data: {method: "payOrder", oid: "${requestScope.order.oid}"}, //请求的数据
                dataType: 'json',  //返回的数据类型
                cache: false,  //是否缓存
                success: function (data) { //回调函数，把data渲染到页面中
                    if(data.result === "成功"){
                        $("#submit").text("支付成功");
                        let time = 5;
                        let t = setInterval(function () {
                            $("#tip").text("支付成功！页面将在"+time+"秒钟之后跳转……");
                            time--;
                            if(time<=0){
                                clearInterval(t);
                                location.href = "${pageContext.request.contextPath}/face/faceCarServlet?method=findAllOrder&pageNow=1";
                            }
                        },1000);
                    }
                    else {
                        alert("您已支付！");
                        $("#submit").text("确认支付");
                        $("#submit").css("cursor","pointer");
                        $("#submit").attr("href","javascript:sub();");
                    }

                }

            });

        };


    </script>
</head>

<body>
<div class="divContent">
    <span class="spanPrice">支付金额：</span><span class="price_t">&yen;${requestScope.order.total}</span>
    <span class="spanOid">编号：${requestScope.order.oid}</span>
</div>
<form action="" method="post" id="form1" target="_top">
    <input type="hidden" name="method" value=""/>
    <input type="hidden" name="oid" value=""/>
    <div class="divBank">
        <div class="divText">选择网上银行</div>
        <div style="margin-left: 20px;">
            <div style="margin-bottom: 20px;">
                <input id="ICBC-NET-B2C" type="radio" name="yh" value="ICBC-NET-B2C" checked="checked"/>
                <img name="ICBC-NET-B2C" align="middle" src="${pageContext.request.contextPath}/web/bank_img/icbc.bmp"/>

                <input id="CMBCHINA-NET-B2C" type="radio" name="yh" value="CMBCHINA-NET-B2C"/>
                <img name="CMBCHINA-NET-B2C" align="middle"
                     src="${pageContext.request.contextPath}/web/bank_img/cmb.bmp"/>

                <input id="ABC-NET-B2C" type="radio" name="yh" value="ABC-NET-B2C"/>
                <img name="ABC-NET-B2C" align="middle" src="${pageContext.request.contextPath}/web/bank_img/abc.bmp"/>

                <input id="CCB-NET-B2C" type="radio" name="yh" value="CCB-NET-B2C"/>
                <img name="CCB-NET-B2C" align="middle" src="${pageContext.request.contextPath}/web/bank_img/ccb.bmp"/>
            </div>
            <div style="margin-bottom: 20px;">
                <input id="BCCB-NET-B2C" type="radio" name="yh" value="BCCB-NET-B2C"/>
                <img name="BCCB-NET-B2C" align="middle" src="${pageContext.request.contextPath}/web/bank_img/bj.bmp"/>

                <input id="BOCO-NET-B2C" type="radio" name="yh" value="BOCO-NET-B2C"/>
                <img name="BOCO-NET-B2C" align="middle" src="${pageContext.request.contextPath}/web/bank_img/bcc.bmp"/>

                <input id="CIB-NET-B2C" type="radio" name="yh" value="CIB-NET-B2C"/>
                <img name="CIB-NET-B2C" align="middle" src="${pageContext.request.contextPath}/web/bank_img/cib.bmp"/>

                <input id="NJCB-NET-B2C" type="radio" name="yh" value="NJCB-NET-B2C"/>
                <img name="NJCB-NET-B2C" align="middle"
                     src="${pageContext.request.contextPath}/web/bank_img/nanjing.bmp"/>
            </div>

            <div style="margin-bottom: 20px;">
                <input id="CMBC-NET-B2C" type="radio" name="yh" value="CMBC-NET-B2C"/>
                <img name="CMBC-NET-B2C" align="middle" src="${pageContext.request.contextPath}/web/bank_img/cmbc.bmp"/>

                <input id="CEB-NET-B2C" type="radio" name="yh" value="CEB-NET-B2C"/>
                <img name="CEB-NET-B2C" align="middle"
                     src="${pageContext.request.contextPath}/web/bank_img/guangda.bmp"/>

                <input id="BOC-NET-B2C" type="radio" name="yh" value="BOC-NET-B2C"/>
                <img name="BOC-NET-B2C" align="middle" src="${pageContext.request.contextPath}/web/bank_img/bc.bmp"/>

                <input id="PINGANBANK-NET" type="radio" name="yh" value="PINGANBANK-NET"/>
                <img name="PINGANBANK-NET" align="middle"
                     src="${pageContext.request.contextPath}/web/bank_img/pingan.bmp"/>
            </div>

            <div style="margin-bottom: 20px;">
                <input id="CBHB-NET-B2C" type="radio" name="yh" value="CBHB-NET-B2C"/>
                <img name="CBHB-NET-B2C" align="middle" src="${pageContext.request.contextPath}/web/bank_img/bh.bmp"/>

                <input id="HKBEA-NET-B2C" type="radio" name="yh" value="HKBEA-NET-B2C"/>
                <img name="HKBEA-NET-B2C" align="middle" src="${pageContext.request.contextPath}/web/bank_img/dy.bmp"/>

                <input id="NBCB-NET-B2C" type="radio" name="yh" value="NBCB-NET-B2C"/>
                <img name="NBCB-NET-B2C" align="middle"
                     src="${pageContext.request.contextPath}/web/bank_img/ningbo.bmp"/>

                <input id="ECITIC-NET-B2C" type="radio" name="yh" value="ECITIC-NET-B2C"/>
                <img name="ECITIC-NET-B2C" align="middle" src="${pageContext.request.contextPath}/web/bank_img/zx.bmp"/>
            </div>


            <div style="margin-bottom: 20px;">
                <input id="SDB-NET-B2C" type="radio" name="yh" value="SDB-NET-B2C"/>
                <img name="SDB-NET-B2C" align="middle" src="${pageContext.request.contextPath}/web/bank_img/sfz.bmp"/>

                <input id="GDB-NET-B2C" type="radio" name="yh" value="GDB-NET-B2C"/>
                <img name="GDB-NET-B2C" align="middle" src="${pageContext.request.contextPath}/web/bank_img/gf.bmp"/>

                <input id="SHB-NET-B2C" type="radio" name="yh" value="SHB-NET-B2C"/>
                <img name="SHB-NET-B2C" align="middle" src="${pageContext.request.contextPath}/web/bank_img/sh.bmp"/>

                <input id="SPDB-NET-B2C" type="radio" name="yh" value="SPDB-NET-B2C"/>
                <img name="SPDB-NET-B2C" align="middle" src="${pageContext.request.contextPath}/web/bank_img/shpd.bmp"/>
            </div>


            <div style="margin-bottom: 20px;">
                <input id="POST-NET-B2C" type="radio" name="yh" value="POST-NET-B2C"/>
                <img name="POST-NET-B2C" align="middle" src="${pageContext.request.contextPath}/web/bank_img/post.bmp"/>

                <input id="BJRCB-NET-B2C" type="radio" name="yh" value="BJRCB-NET-B2C"/>
                <img name="BJRCB-NET-B2C" align="middle"
                     src="${pageContext.request.contextPath}/web/bank_img/beijingnongshang.bmp"/>

                <input id="HXB-NET-B2C" type="radio" name="yh" value="HXB-NET-B2C"/>
                <img name="HXB-NET-B2C" align="middle" src="${pageContext.request.contextPath}/web/bank_img/hx.bmp"/>

                <input id="CZ-NET-B2C" type="radio" name="yh" value="CZ-NET-B2C"/>
                <img name="CZ-NET-B2C" align="middle"
                     src="${pageContext.request.contextPath}/web/bank_img/zheshang.bmp"/>
            </div>
            <span id="tip" style="margin-left: 200px"></span>
        </div>
        <div style="margin: 40px;">
            <a href="javascript:sub();" class="linkNext" id="submit" >确认支付</a>
        </div>
    </div>
</form>
</body>
</html>

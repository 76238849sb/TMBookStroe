<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://s3.pstatp.com/cdn/expire-1-M/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">

    $(function () {
        $("#pageCode").keyup(function (event) {
            if (event.which === 13) {
                _go();
            }
        });
    });

    let _go = () => {
        let pc = $("#pageCode").val();//获取文本框中的当前页码

        if (!/^[1-9]\d*$/.test(pc)) {//对当前页码进行整数校验
            alert('请输入正确的页码！');
            return;
        }
        if (pc > ${requestScope.pageBean.totalPage}) {//判断当前页码是否大于最大页
            alert('请输入正确的页码！');
            return;
        }
        location = "${requestScope.pageBean.url}&pageNow=" + pc + "&cid=${requestScope.cid}";
    };
</script>


<div class="divBody">
    <div class="divContent">
        <c:choose>
            <c:when test="${requestScope.pageBean.pageNow <= 1}">
                <span class="spanBtnDisabled">上一页</span>
            </c:when>
            <c:otherwise>
                <a href="${requestScope.pageBean.url}&cid=${requestScope.cid}&pageNow=${requestScope.pageBean.pageNow-1}"
                   class="aBtn bold">上一页</a>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <%--  总页数小于等于6  --%>
            <c:when test="${requestScope.pageBean.totalPage <= 6}">
                <c:set var="begin" value="1"/>
                <c:set var="end" value="${requestScope.pageBean.totalPage}"/>
            </c:when>
            <%--  总页数大于6  --%>
            <c:otherwise>

                <%-- el表达式没有整除--%>
                <c:choose>
                    <c:when test="${requestScope.pageBean.pageNow % 6 == 0}">
                        <c:set var="begin"
                               value="${requestScope.pageBean.pageNow-5}"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="begin"
                               value="${1+(requestScope.pageBean.pageNow-requestScope.pageBean.pageNow%6)}"/>
                    </c:otherwise>
                </c:choose>


                <c:choose>
                    <c:when test="${begin+5 >= requestScope.pageBean.totalPage}">
                        <c:set var="end" value="${requestScope.pageBean.totalPage}"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="end" value="${begin + 5}"/>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>


        <c:forEach var="index" begin="${begin}" end="${end}" step="1">
            <c:choose>
                <c:when test="${index != requestScope.pageBean.pageNow}">
                    <a href="${requestScope.pageBean.url}&cid=${requestScope.pageBean.list.get(0).cid}&pageNow=${index}"
                       class="aBtn">${index}</a>
                </c:when>
                <c:otherwise>
                    <span class="spanBtnSelect">${index}</span>
                </c:otherwise>
            </c:choose>

        </c:forEach>
        <c:choose>
            <%--  当最后一页等于总页数且不够6页时不显示...--%>
            <c:when test="${end - begin >= 5}">
                <c:choose>
                    <c:when test="${end != requestScope.pageBean.totalPage}">
                        <span class="spanApostrophe">...</span>
                    </c:when>
                </c:choose>
            </c:when>
        </c:choose>

        <c:choose>
            <c:when test="${requestScope.pageBean.pageNow >= requestScope.pageBean.totalPage}">
                <span class="spanBtnDisabled">下一页</span>
            </c:when>
            <c:otherwise>
                <a href="${requestScope.pageBean.url}&cid=${requestScope.pageBean.list.get(0).cid}&pageNow=${requestScope.pageBean.pageNow+1}"
                   class="aBtn bold">下一页</a>
            </c:otherwise>
        </c:choose>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

        <span>共${requestScope.pageBean.totalPage}页</span>
        <span>到</span>
        <input type="number" min="1" max="${requestScope.pageBean.totalPage}" class="inputPageCode" id="pageCode"
               value="1"/>
        <span>页</span>
        <a href="javascript:_go();" class="aSubmit">确定</a>


    </div>
</div>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 顶部开始--%>
<div class="row">
    <div class="col-md-4">
        <%-- border:1px solid red; --%>
        <img alt="Java 博客系统" style="height: 80px" src="${pageContext.request.contextPath}/static/images/logo3.jpg">
    </div>
    <div class="col-md-8 pull-right">
        <div>
            <%--<p class="text-right">向右对齐文本</p>--%>
            <p class="text-right">
                <a href="${pageContext.request.contextPath}/login.jsp">进入后台</a>
            </p>

            <p class="text-right">您已经出生 ${passedDayNumBirth} 天。</p>
            <p class="text-right">您学习 Java 已经 ${passedDayNumJava} 天。</p>
        </div>

    </div>
</div>

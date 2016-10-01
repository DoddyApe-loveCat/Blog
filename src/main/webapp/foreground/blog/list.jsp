<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:forEach items="${blogList}" var="blog">
    <div class="panel panel-success">
        <div class="panel-heading">
            <b><span class="text-right"><fmt:formatDate value="${blog.releaseDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></span></b>
            <b>
                <h3 class="panel-title">
                    <a href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html">
                        ${blog.title}
                    </a>
                </h3>
            </b>
        </div>
        <div class="panel-body">
            摘要：${blog.summary}
            <br>
            <span class="text-right">
                阅读(${blog.clickHit}) 评论(${blog.replyHit})
            </span>
        </div>
    </div>
</c:forEach>

<%-- 分页的代码,由后端生成 --%>
${pageCode}
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
搜索 ${q} 的结果 (总共搜索到 ${resultTotal} 条记录,耗时  秒。)

<c:forEach items="${blogList}" var="blog">
    <div class="panel panel-success">
        <div class="panel-heading">
            <b><span class="text-right"><fmt:formatDate value="${blog.releaseDate}" type="date" pattern="yyyy 年 MM 月 dd 日"/></span></b>
            <b>
                <h3 class="panel-title">
                    <a href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html">
                            ${blog.title}
                    </a>
                </h3>
            </b>
        </div>
        <div class="panel-body">
            摘要：${blog.content}
            <p class="text-right">
                发表于 <fmt:formatDate value="${blog.releaseDate }" type="date" pattern="yyyy-MM-dd HH:mm"/> 阅读(${blog.clickHit}) 评论(${blog.replyHit})
            </p>
        </div>
    </div>
</c:forEach>
${pageCode}
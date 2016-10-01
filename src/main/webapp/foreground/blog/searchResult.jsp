<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
搜索 ${q} 的结果 (总共搜索到 ${resultTotal} 条记录,耗时 ${spendTime} 毫秒,您现在看到的是第 ${param.page == null ? 1 :param.page} 页的数据。)

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
                发表于 ${blog.releaseDateStr}
        </div>
    </div>
</c:forEach>
${pageCode}
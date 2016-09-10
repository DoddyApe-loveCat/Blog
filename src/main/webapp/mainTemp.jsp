<%--
  Created by IntelliJ IDEA.
  User: Liwei
  Date: 2016/8/1
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${pageTitle}</title>

    <%-- 引入 css 的时候，link 节点不要使用结束标记 --%>
    <link href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap-theme.min.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js" ></script>
    <style type="text/css">
        body{
            padding-top: 10px;
            padding-bottom:40px;
        }

        /* 修改默认的 Bootstrap 列表组组件的样式：把边框隐藏掉*/
        .list-group-item{
            border: 0px;
        }
    </style>
</head>
<body>
    <div class="container">

        <jsp:include page="/foreground/common/head.jsp"/>

        <jsp:include page="foreground/common/menu.jsp"/>
        <%-- 中间开始--%>
        <div class="row">
            <%-- 左边博客列表 --%>
            <div class="col-md-9">

                <%-- 这里是经常变化的，所以应该设置为变量 foreground/blog/list.jsp --%>
                <jsp:include page="${mainPage}"/>

            </div>
            <%-- 右边个人简介和友情链接 --%>
            <div class="col-md-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <span class="glyphicon glyphicon-list" aria-hidden="true"></span>
                            &nbsp;&nbsp;博主信息
                        </h3>
                    </div>
                    <div class="panel-body">
                        <img style="height: 235px;" src="${pageContext.request.contextPath}/static/userImages/liwei.jpg">
                        <div align="center">
                            ${blogger.nickName}
                        </div>
                        <div align="center">
                            （${blogger.sign}）
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <span class="glyphicon glyphicon-list" aria-hidden="true"></span>
                            &nbsp;&nbsp;按博客类别
                        </h3>
                    </div>
                    <div class="panel-body">
                        <ul class="list-group">
                            <c:forEach items="${blogTypeCountList}" var="blogType">
                                <li class="list-group-item">
                                    <span class="badge">${blogType.blogCount}</span>
                                    <a href="${pageContext.request.contextPath}/index.html?typeId=${blogType.id}">${blogType.typeName}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <span class="glyphicon glyphicon-list" aria-hidden="true"></span>
                            &nbsp;&nbsp;按博客日期
                        </h3>
                    </div>
                    <div class="panel-body">
                        <ul class="list-group">

                            <c:forEach items="${blogCountList}" var="blogCount">
                                <li class="list-group-item">
                                    <%-- 指定年份和月份博主发表的博客数量 --%>
                                    <span class="badge">${blogCount.blogCount}</span>
                                    <%-- 发布日期（只取年份和月份）--%>
                                    <a class="releaseDate" href="${pageContext.request.contextPath}/index.html?releaseDateStr=${blogCount.releaseDateStr}">${blogCount.releaseDateStr}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <span class="glyphicon glyphicon-list" aria-hidden="true"></span>
                            &nbsp;&nbsp;友情链接
                        </h3>
                    </div>
                    <div class="panel-body">
                        <%-- 请参考 Bootstrap 列表组 链接部分 --%>
                        <div class="list-group">
                            <!-- active 表示当前突出显示的链接 -->
                            <c:forEach var="link" items="${linkList}">
                                <a href="${link.linkUrl}" class="list-group-item" target="_blank">${link.linkName}</a>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="foreground/common/foot.jsp"/>

    </div>
    <script type="text/javascript">
        $(function () {
            $(".releaseDate").each(function(){
                var origin = $(this).text();
                var yearStr = origin.substring(0,4);
                var monthStr = origin.substring(5,7);
                var newStr = "";
                newStr = newStr.concat(yearStr + "年" + monthStr + "月");
                $(this).text(newStr);
            });
        });
    </script>
</body>
</html>

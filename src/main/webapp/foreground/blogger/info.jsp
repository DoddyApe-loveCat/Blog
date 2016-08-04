<%--
  Created by IntelliJ IDEA.
  User: Liwei
  Date: 2016/8/4
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="panel panel-success">
    <div class="panel-heading">
        <h3 class="panel-title">关于博主</h3>
    </div>
    <div class="panel-body">
        ${blogger.profile}
        <hr>
        <img style="width: 400px" src="${pageContext.request.contextPath}${blogger.imageName}">
    </div>
</div>

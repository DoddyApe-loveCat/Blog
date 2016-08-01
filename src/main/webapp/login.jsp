<%--
  Created by IntelliJ IDEA.
  User: Liwei
  Date: 2016/8/1
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>博客后台管理登录</title>
</head>
<body>
    <h1>请输入用户名和密码</h1>
    <form action="${pageContext.request.contextPath}/blogger/login.do"
    method="post">
        用户名：<input name="userName" type="text" value="${blogger.userName}"><br>
        密码：<input name="password" type="password" value="${blogger.password}"><br>
        <input type="submit" value="提交">
    </form>
</body>
</html>

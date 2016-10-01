<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Java 博客后台管理系统</title>
        <link href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap-theme.min.css" rel="stylesheet">
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js" ></script>
        <style type="text/css">
            *{
                margin: 0px;
                padding:0px;
            }

            body{
                overflow:hidden
            }
            <%-- 登录页面中间背景 --%>
            #login_middle_bg{
                background-color: #CC0033;
                height: 500px;
                margin-top: 10px;
            }
            #login_form_div{
                background-color: #ffffff;
                margin-top: 80px;
                padding-left: 50px;
                padding-right: 50px;
                padding-bottom: 50px;
            }
        </style>
    </head>
    <body>
        <div class="row">
            <div class="col-md-12" align="center">
                <%--<div style="margin-top: 60px;margin-bottom: 60px"></div>--%>
                <h1 style="font-family:微软雅黑">Java 博客后台管理系统</h1>
            </div>
        </div>

        <div class="row" id="login_middle_bg">

            <div class="container">

                <div class="col-md-8">
                </div>
                <div class="col-md-4">
                    <div id="login_form_div">
                        <%-- 表单部分请参考 Bootstrap 官网文档 表单部分 --%>
                        <form class="form-horizontal" action="${pageContext.request.contextPath}/login.do"
                              method="post">
                            <%--<div class="form-group">
                                <div class="input-group" align="center">
                                    <h1 style="font-family: 微软雅黑">账户登录</h1>
                                </div>
                            </div>--%>
                            <div align="center" style="padding-top: 10px;padding-bottom: 10px;">
                                <h1 style="font-family: 微软雅黑">账户登录</h1>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                                    </div>
                                    <input class="form-control" id="inputEmail3" placeholder="请输入用户名" name="userName" type="text" value="${blogger.userName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
                                    </div>
                                    <input type="password" class="form-control" id="inputPassword3" placeholder="请输入密码" name="password" type="password" value="${blogger.password}">
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-danger btn-block">提交</button>
                            </div>
                        </form>
                        <span style="color:red">${msg}</span>
                        演示账号:用户名:admin,密码:123456
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            注意事项:<br>
            1、添加博文、修改博文之前,请先确保博客的类型已经添加;<br>
            2、如果博客的类型添加以后,并未出现在博客类型列表中,请刷新缓存。
        </div>

    </body>
</html>

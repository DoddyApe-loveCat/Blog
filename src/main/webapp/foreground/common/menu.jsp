<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 导航条 --%>
<div class="row">
    <div class="col-md-12" style="padding-top: 10px;">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <%-- todo --%>
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/index.html"><strong>首页</strong></a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <%-- class="active" 表示这部分着重显示 --%>
                        <li><a href="${pageContext.request.contextPath}/blogger/aboutMe.html">关于我 <span class="sr-only">(current)</span></a></li>
                        <li><a href="${pageContext.request.contextPath}/srcDownload.html">我的 Github 和 开源中国 </a></li>

                        <%-- 下拉导航 --%>
                        <%--<li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Action</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">Separated link</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">One more separated link</a></li>
                            </ul>
                        </li>--%>
                    </ul>

                    <%-- 导航栏中的表单，浮动到右边 --%>
                    <form action="${pageContext.request.contextPath}/blog/q.html" class="navbar-form navbar-right" role="search">
                        <div class="form-group">
                            <input type="text" name="q" value="${q}" class="form-control" placeholder="请输入要查询的关键字">
                        </div>
                        <button type="submit" class="btn btn-default">搜索</button>
                    </form>

                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
    </div>
</div>

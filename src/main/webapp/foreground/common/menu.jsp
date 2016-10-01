<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row">
    <div class="col-md-12" style="padding-top: 10px;">

        <%-- BootStrap 导航条 --%>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
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
                        <%-- li 的 class="active" 表示这部分着重显示 --%>
                        <li><a href="${pageContext.request.contextPath}/blogger/aboutMe.html">博主简介 <span class="sr-only">(current)</span></a></li>
                        <li><a href="${pageContext.request.contextPath}/resume.html">个人简历</a></li>
                    </ul>

                    <%-- 导航栏中的表单，浮动到右边 --%>
                    <form action="${pageContext.request.contextPath}/blog/q.html" class="navbar-form navbar-right" role="search">
                        <div class="form-group">
                            <input type="text" name="q" value="${q}" class="form-control" placeholder="请输入要搜索的关键字">
                        </div>
                        <button type="submit" class="btn btn-default">搜索</button>
                    </form>

                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>

    </div>
</div>

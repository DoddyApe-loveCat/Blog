<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">
            <span class="glyphicon glyphicon-book" aria-hidden="true"></span> 博客信息
        </h3>
    </div>
    <div class="panel-body">
        <div align="center">
            <h3>${blog.title}</h3>
        </div>
        <div align="center">
            发布时间：<fmt:formatDate value="${blog.releaseDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
            &nbsp;&nbsp;
            博客类别：${blog.title}&nbsp;&nbsp;
            阅读(${blog.clickHit})&nbsp;&nbsp;
            评论(${blog.replyHit})&nbsp;&nbsp;
        </div>
        <div>
            <p>${blog.content}</p>
        </div>
        <div>
            <p><b>关键字</b>：${blog.keyword}</p>
        </div>
        <hr>
        ${pageCode}
    </div>
</div>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">
            <span class="glyphicon glyphicon-align-left" aria-hidden="true"></span> 评论信息
        </h3>

    </div>
    <div class="panel-body">

        <c:if test="${commentList == null}">
            评论列表为空
        </c:if>

        <c:if test="${commentList.size() == 0}">
            评论列表的长度为空
        </c:if>

        <c:if test="${commentList !=null and commentList.size()!=0}">
            <c:forEach items="${commentList}" var="comment" varStatus="status">
                <span class="commentList">
                <strong>${status.count}楼 &nbsp;&nbsp; ${comment.userIp} ：</strong>
                ${comment.content} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                [<fmt:formatDate value="${comment.commentDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>]
                <c:if test="${commentList.size() != status.count}">
                    <hr>
                </c:if>
                </span>
            </c:forEach>
            <c:if test="${commentList.size()>10}">
                <button id="showAllComments" type="button" class="btn btn-link">显示所有评论</button>
            </c:if>
        </c:if>
    </div>
</div>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">
            <span class="glyphicon glyphicon-comment" aria-hidden="true"></span> 发表评论
        </h3>

    </div>
    <div class="panel-body">

        <div class="form-group">
            <%-- 文本域 --%>
            <textarea placeholder="来说两句吧" class="form-control" rows="3" id="commentContent"></textarea>
        </div>

        <span style="float: left">验证码： </span>
        <input style="width:100px;float: left;" type="text" class="form-control input-sm" id="imageCodeInput">


        <%-- 为验证码图片绑定一个单击刷新事件 --%>
        <img id="imageCodeImg" style="margin-left:10px;float: left" src="/image.jsp">


        <button style="float: right;" type="submit" class="btn btn-default" id="commitComment">发表评论</button>


    </div>
</div>
<script type="text/javascript">
    $(function(){

        /**
         * 为提交评论按钮绑定单击事件
         */
        $("#commitComment").on("click",function(){
            var imageCode = $("#imageCodeInput").val();
            var commentContent = $("#commentContent").val();
            if(imageCode == null || imageCode == ''){
                alert("您还未输入验证码！");
                $("#imageCodeInput").focus();
            }else if(commentContent == null || commentContent == ''){
                alert("您还未输入评论的内容！")
                $("#commentContent").focus();
            }else {
                $.post("${pageContext.request.contextPath}/comment/save.do",{
                    "imageCode":imageCode,
                    "content":commentContent,
                    "blog.id":"${blog.id}"
                },function(data){
                    if(data.success){
                        alert("提交评论成功！");
                        location.reload(true);
                    }else {
                        alert(data.errorInfo);
                        $("#imageCodeInput").focus();
                    }
                    // 刷新验证码和用户输入框
                    $("#commentContent").val("");
                    $("#imageCodeInput").val("");
                    $("#imageCodeImg").trigger("click");
                },"json");
            }
        });

        /**
         * 为验证码图片绑定一个单击刷新事件
         */
        $("#imageCodeImg").on("click",function(){
            $(this).attr("src","${pageContext.request.contextPath}/image.jsp?"+ Math.random());
        });


        if(${commentList.size() > 10}){
            // 只显示前 10 条评论
            $(".commentList:gt(9)").hide();
        }

        $("#showAllComments").one("click",function(){
            $(".commentList").show();
            $(this).hide();
        });


    });
</script>
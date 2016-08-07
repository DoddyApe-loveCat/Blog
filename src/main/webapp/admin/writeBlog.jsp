<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>欢迎登录</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.5/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.5/themes/icon.css">
</head>
<body>
    <div class="easyui-panel" title="编写博客">
        <%-- cellspacing 属性规定单元格之间的空间。 --%>
        <table cellpadding="10">
            <tr>
                <td width="80px;">博客标题</td>
                <td>
                    <input id="title" type="text" style="width: 800px;" name="title" class="easyui-validatebox" data-options="required:true" >
                </td>
            </tr>
            <tr>
                <td>所属类别</td>
                <td>
                    <%-- 可以设置下拉选择框的高度和宽度自适应--%>
                    <select id="blogTypeId" class="easyui-combobox" name="blogType.id" panelWidth="auto" panelHeight="auto">
                            <option>请选择博客类型：</option>
                        <c:forEach items="${blogTypeCountList}" var="blogType">
                            <option value="${blogType.id}">${blogType.typeName}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <%-- 设置单元格的垂直居中方式为：顶部居中 --%>
                <td valign="top">博客内容</td>
                <td>
                    <!-- 加载编辑器的容器 -->
                    <script id="container" name="content" type="text/plain" style="width:1050px;height:800px">
                    </script>
                </td>
            </tr>
            <tr>
                <td>关键字：</td>
                <td>
                    <input type="text" class="easyui-textbox" style="width: 800px" name="keyword" id="keyword">
                    （多个关键字之间用空格隔开）
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <a id="publishBlog" href="#"  class="easyui-linkbutton" data-options="iconCls:'icon-publish-blog'">发表博客</a>
                </td>
            </tr>
        </table>

    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.5/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>


    <!-- 配置文件 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/ueditor/ueditor.all.js"></script>

    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/static/ueditor/lang/zh-cn/zh-cn.js"></script>

    <!-- 实例化编辑器 -->
    <script type="text/javascript">
        var ue = UE.getEditor('container');
    </script>

    <script type="text/javascript">
        $(function(){
           $("#publishBlog").on("click",function(){
               var title = $("#title").val();
               // 参考了官方的例子
               var blogTypeId = $("#blogTypeId").combobox('getValue');
               var content = UE.getEditor('container').getContent();
               var keyword = $("#keyword").val();


               if(title == null || title == ''){
                   alert("请填写博客标题！");
               }else if(blogTypeId == null || blogTypeId == ''){
                   alert("请选择博客类型！");
               }else if(content ==null || content == ''){
                   alert("请填写博客内容！");
               }else {

                   var summary = UE.getEditor('container').getContentTxt().substr(0,155);

                   $.post("${pageContext.request.contextPath}/admin/blog/save.do",{
                       "title":title,
                       "blogType.id":blogTypeId,
                       "content":content,
                       "summary":summary,
                       "keyword":keyword
                   },function(data){

                   },"json");
               }
           });
        });
    </script>
</body>

</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改博客</title>
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

    var modifyBlogId = "${param.id}";
    // alert("从上一个页面传递进来的参数 => " + modifyBlogId);

    // 此时应该通过 ajax 异步地从服务器获取数据填充在该页面的表单中
    // 参考 百度 ueditor 官方文档 API 模块的 ajax 部分

    // 这一行一定要写 ，编辑器才会被实例化
    var ue = UE.getEditor('container');

    // 文档地址：http://ueditor.baidu.com/doc/#UE.ajax
    //向sayhello.php发起一个异步的Ajax GET请求, 请求超时时间为10s， 请求完成后执行相应的回调。


    // 使用 ready 事件  1.2.6.1 编辑器准备就绪后会触发该事件 提示： render方法执行完成之后,会触发该事件

    ue.addListener( 'ready', function( editor ) {
        // editor.execCommand( 'focus' ); //编辑器家在完成后，让编辑器拿到焦点
        UE.ajax.request( '${pageContext.request.contextPath}/admin/blog/findById.do', {

            //请求方法。可选值： 'GET', 'POST'，默认值是'POST'
            method: 'GET',

            //超时时间。 默认为5000， 单位是ms
            timeout: 10000,

            //是否是异步请求。 true为异步请求， false为同步请求
            async: true,

            //请求携带的数据。如果请求为GET请求， data会经过stringify后附加到请求url之后。
            data: {
                id: modifyBlogId
            },

            //请求成功后的回调， 该回调接受当前的XMLHttpRequest对象作为参数。
            onsuccess: function ( xhr ) {
                console.log( xhr.responseText );
                // 重要方法，解析一个 json 字符串
                var data = eval("(" + xhr.responseText +")");
                // 解析好的数据进行填充
                $("#title").val(data.title);
                $("#blogTypeId").combobox('setValue',data.blogType.id);
                // 设置编辑器的内容
                ue.setContent(data.content);
                // alert(data.keyword);
                $("#keyword").textbox('setValue',data.keyword);
            },

            //请求失败或者超时后的回调。
            onerror: function ( xhr ) {
                alert( 'Ajax请求失败' );
            }

        } );
    } );




    //

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
                    "id":modifyBlogId,
                    "title":title,
                    "blogType.id":blogTypeId,
                    "content":content,
                    "summary":summary,
                    "keyword":keyword
                },function(data){
                    if(data.success){
                        $.messager.alert('提示','博客修改成功！');
                    }else {
                        $.messager.alert('提示',data.errorInfo);
                    }
                },"json");
            }
        });
    });





</script>

<script type="text/javascript">
    $(function(){





    });
</script>
</body>

</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.5/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.5/themes/icon.css">
    </head>
    <body>

        <table id="blogTypeManagerTable" class="easyui-datagrid">
            <thead>
                <tr>
                    <th field="ck" checkbox="true"></th>
                    <th field="id">编号</th>
                    <th field="typeName">博客类型名称</th>
                    <th field="orderNo">排序序号</th>
                </tr>
            </thead>
        </table>


        <div id="blogTypeOper">
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-modify',plain:true">修改</a>
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true">删除</a>
        </div>



        <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.5/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>

        <script type="text/javascript">
            $("#blogTypeManagerTable").datagrid({
                fit:true,
                toolbar:'#blogTypeOper',
                url:'${pageContext.request.contextPath}/admin/blogType/list.do',
                rownumbers:true,
                method:'get'
            });
        </script>
    </body>
</html>




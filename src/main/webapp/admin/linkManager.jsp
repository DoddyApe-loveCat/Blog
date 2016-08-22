<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.5/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.5/themes/icon.css">
    </head>
    <body>

        <table id="linkManagerTable" class="easyui-datagrid">
            <thead>
                <tr>
                    <%-- 【注意：这里 field="ck" 必须加上。】 --%>
                    <th field="ck"  checkbox="true"></th>
                    <th field="id">编号</th>
                    <th field="linkName">链接名称</th>
                    <th field="linkUrl">链接地址</th>
                    <th field="orderNo">序号</th>
                </tr>
            </thead>
        </table>
        <div id="linkManagerToolbar">
            <a id="a" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>
            <a id="b" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
            <a id="c" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
        </div>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.5/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript">
            $("#linkManagerTable").datagrid({
                "fit":true,
                "toolbar":"#linkManagerToolbar",
                "url":"${pageContext.request.contextPath}/admin/link/list.do",
                "rownumbers":true,
                "pagination":true,
                "method":"get"
            });
        </script>
    </body>
</html>



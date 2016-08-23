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
            <a id="openAddDialog" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>
            <a id="openUpdateDialog" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
            <a id="linkDelete" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
        </div>


        <div id="dialog" style="padding:5px;width:400px;height:200px;">
            <form id="linkFrom" method="post">

                <table>
                    <tr>
                        <td>链接名称：</td>
                        <td>
                            <input id="linkName" class="easyui-validatebox" type="text" name="linkName" data-options="required:true" />
                        </td>
                    </tr>
                    <tr>
                        <td>链接地址：</td>
                        <td>
                            <input id="linkUrl" class="easyui-validatebox" type="text" name="linkUrl" data-options="required:true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>排序序号：</td>
                        <td>
                            <input id="orderNo" class="easyui-validatebox" type="text" name="orderNo" data-options="required:true"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <div id="dialogToolbar">
            <a id="linkSave" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
            <a id="linkCancel" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
        </div>

        <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.5/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript">

            var url = "${pageContext.request.contextPath}/admin/link/save.do";

            $("#linkManagerTable").datagrid({
                "fit":true,
                "toolbar":"#linkManagerToolbar",
                "url":"${pageContext.request.contextPath}/admin/link/list.do",
                "rownumbers":true,
                "pagination":true,
                "method":"get"
            });


            $("#dialog").dialog({
                "title":"添加友情链接",
                "closable":true,
                "closed":true,
                "modal":true,
                "toolbar":"#dialogToolbar"
            });

            $("#openAddDialog").on("click",function(){
                url = "${pageContext.request.contextPath}/admin/link/save.do";
                $("#dialog").dialog('open');
            });

            $("#openUpdateDialog").on("click",function(){
                var selections = $("#linkManagerTable").datagrid("getSelections");
                var len = selections.length;
                if(len !=1){
                    $.messager.alert("系统提示","请只选择一条数据！");
                }else {
                    var row = selections[0];
                    url = "${pageContext.request.contextPath}/admin/link/save.do?id=" + row.id;
                    $("#dialog").dialog('open');
                    $('#linkFrom').form('load',row);
                }

            });

            $("#linkSave").on("click",function(){
                // submit the form
                $("#linkFrom").form("submit",{
                    "url":url,
                    "onSubmit":function(){

                    },
                    "success":function(data){
                        console.info(data);
                        data = eval("(" + data + ")");
                        if(data.success){
                            $.messager.alert("系统提示",data.successInfo);
                            // 关闭对话框
                            $("#dialog").dialog("close");
                            //
                            $("#linkManagerTable").datagrid("reload");

                            // reset
                            $("#linkName").val("");
                            $("#linkUrl").val("");
                            $("#orderNo").val("");
                        }else {
                            $.messager.alert("系统提示",data.errorInfo);
                        }
                    }
                });
            });

            $("#linkCancel").on("click",function(){
                // 关闭对话框
                $("#dialog").dialog("close");
            });


            $("#linkDelete").on("click",function(){
                var selections = $("#linkManagerTable").datagrid("getSelections");
                var len = selections.length;
                if(len == 0){
                    $.messager.alert("系统提示","请至少选择一条数据！");
                }else{
                    var ids = [];
                    for(var i = 0;i<len;i++){
                        ids.push(selections[i].id);
                    }
                    var idsStr = ids.join(",");
                    $.get("${pageContext.request.contextPath}/admin/link/delete.do",{"ids":idsStr},function(data){
                        if(data.success){
                            $.messager.alert("系统提示",data.successInfo);
                            $("#linkManagerTable").datagrid("reload");
                        }else {
                            $.messager.alert("系统提示",data.errorInfo);
                        }
                    });
                }
            });

        </script>
    </body>
</html>



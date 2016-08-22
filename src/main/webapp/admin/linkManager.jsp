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
            <a id="c" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
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
                $("#dialog").dialog('open');
            });

            $("#openUpdateDialog").on("click",function(){
                var selections = $("#linkManagerTable").datagrid("getSelections");
                var len = selections.length;
                if(len !=1){
                    $.messager.alert("系统提示","请只选择一条数据！");
                }else {
                    $("#dialog").dialog('open');
                    $('#linkFrom').form('load',selections[0]);
                }

            });


            $("#linkSave").on("click",function(){

                $('#linkFrom').form({
                    "url":"${pageContext.request.contextPath}/admin/link/save.do",
                    "onSubmit": function(){
                    },
                    "success":function(data){
                        alert(data)
                    }
                });
                // submit the form
                $('#linkFrom').submit();
            });


            function saveOrUpdate(state){

            };

        </script>
    </body>
</html>



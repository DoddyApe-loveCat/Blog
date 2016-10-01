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

        <%-- 表格上的工具栏图标 --%>
        <div id="blogTypeOper">
            <a id="addBlogtype" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
            <a id="modifyBlogType" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-modify',plain:true">修改</a>
            <a id="deleteBlogType" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true">删除</a>
        </div>

        <%-- closed : false 一开始显示，true ： 一开始不显示  --%>
        <div id="dialog" class="easyui-dialog" style="padding:5px;width:400px;height:200px;"
             title="My Dialog" iconCls="icon-ok" closed="true">
            <form id="blogTypeForm" method="post">
                <table>
                    <tr>
                        <td>博客类型名称：</td>
                        <td><input id="typeName" name="typeName" type="text" class="easyui-validatebox" required="true"/></td>
                    </tr>
                    <tr>
                        <td>博客类别排序：</td>
                        <td><input id="orderNo" name="orderNo" type="text" class="easyui-numberbox" required="true" style="width: 60px" data-options="min:0"/></td>
                    </tr>
                </table>
            </form>
        </div>
        <div id="dialogButton">
            <a id="saveBlogType" href="#" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
            <a id="cancel" href="#" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
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
                pagination:true,
                method:'get'
                // fitColumns: true
            });

            // 弹出对话框
            $("#addBlogtype").on("click",function(){
                $("#dialog").dialog("open").dialog("setTitle","添加博客类别信息");
            });


            var url = "${pageContext.request.contextPath}/admin/blogType/add.do";

            $("#saveBlogType").on("click",function(){

                $("#blogTypeForm").form("submit",{
                    url:url,
                    onSubmit:function(){
                        // 【重要】 onSubmit 在提交之前触发，返回false可以终止提交。
                        return $(this).form("validate");
                    },
                    success:function(data){
                        // change the JSON string to javascript object
                        var data = eval('(' + data + ')');

                        if(data.success){

                            // 提示用户
                            $.messager.alert("系统提示","保存成功！");

                            // 重置
                            $("#typeName").val("");
                            $("#orderNo").numberbox("reset");

                            // 关闭对话框
                            $("#dialog").dialog("close");

                            // 重新加载
                            // 重载行。等同于'load'方法，但是它将保持在当前页。
                            $("#blogTypeManagerTable").datagrid("reload");
                        }else {
                            $.messager.alert("系统提示",data.errorInfo);
                        }
                    }
                });

            });

            $("#dialog").dialog({
                "toolbar":"#dialogButton"
            });

            $("#cancel").on("click",function(){
                $("#dialog").dialog("close");
            });

            $("#modifyBlogType").on("click",function(){
                var selectedRows = $("#blogTypeManagerTable").datagrid("getSelections");
                // alert(selectedRows.length);
                if(selectedRows.length > 1){
                    $.messager.alert('系统提示','请选择一个须要修改的博客类型！');
                    // 【重要】退出函数
                    return;
                }
                var row = selectedRows[0];
                console.info(row);
                $("#dialog").dialog("open").dialog("setTitle","修改博客类别信息。");
                $('#blogTypeForm').form('load',row);
                // 【重要】 这里  url 是全局变量
                url = "${pageContext.request.contextPath}/admin/blogType/add.do?id=" + row.id;
            });

            $("#deleteBlogType").on("click",function(){
                var selections = $("#blogTypeManagerTable").datagrid("getSelections");
                if(selections.length == 0){
                    $.messager.alert("系统提示","请至少选择一条数据！");
                    return;
                }
                var deleteIds = [];
                for(var i=0;i<selections.length;i++){
                    deleteIds.push(selections[i].id);
                }


                $.messager.confirm("系统提示！","您确定要删除这几条数据吗？",function(r){
                   if(r){
                       $.get("${pageContext.request.contextPath}/admin/blogType/delete.do",{
                           "deleteIds":deleteIds
                       },function(data){
                            if(data.success){
                                $.messager.alert("系统提示！","删除成功！");
                                $("#blogTypeManagerTable").datagrid("reload");
                            }else {
                                $.messager.alert("系统提示！",data.errorInfo);
                            }
                       });
                   }
                });
            });
        </script>
    </body>
</html>




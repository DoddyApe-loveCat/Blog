<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 博客管理子选项卡，实现博客的修改与删除 --%>
<html>
    <head>
        <title>博客管理</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.5/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.5/themes/icon.css">
        <script type="text/javascript">

            /**
             *
             * @param value 表示原来这个单元格应该有的值
             * @param row 表示 value 这个单元格的上一级对象，因此通过  value.属性 可以获得同一级的属性值
             * @param index 下标，从 0 开始
             * @returns {string}
             */
            function formatTitle(value,row,index){
                return "<a target='_blank' href='${pageContext.request.contextPath}/blog/articles/" + row.id + ".html'>" + value + "</a>"
            }

            /**
             * 返回博客类型单元格格式化函数
             * @param value
             * @param row
             * @param index
             * @returns {string}
             */
            function formatBlogType(value,row,index){
                return value.typeName;
            }
        </script>
    </head>
    <body>
        <table id="blogManagerTable" class="easyui-datagrid">
            <thead>
                <tr>
                    <%-- 该列显示成一列复选框 checkbox="true" --%>
                    <th field="ck" checkbox="true"></th>
                    <th field="id" width="80">编号</th>
                    <%-- width 单位默认是 px --%>
                    <th field="title" width="400" formatter="formatTitle">标题</th>
                    <th field="releaseDate" width="200" align="center" >发布日期</th>
                    <th field="blogType" width="100" align="right" formatter="formatBlogType">博客类型</th>
                </tr>
            </thead>
        </table>

        <%-- 上面 datagrid 的工具栏--%>
        <div id="blogOper">
            <%-- plain boolean 为true时显示简洁效果。  --%>
            <a href="#" id="modifyOneBlog" class="easyui-linkbutton" data-options="iconCls:'icon-modify',plain:true">修改</a>
            <a href="#" id="deleteAnyBlog" class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true">删除</a>
        </div>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.5/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">

        $(function(){
            /**
             * 一些常见的选项 singleSelect:true 如果为true，则只允许选择一行。
             */
            $('#blogManagerTable').datagrid({
                fit:true, //当设置为 true 的时候面板大小将自适应父容器。
                title:"博客管理",
                rownumbers:true, // 如果为true，则显示一个行号列。
                pagination:true,
                toolbar:'#blogOper',
                url:'${pageContext.request.contextPath}/admin/blog/list.do',
                method:'get'
            });


            /**
             * 为修改按钮绑定了单击事件
             */
            $("#modifyOneBlog").on("click",function(data){
                // getChecked none 在复选框呗选中的时候返回所有行。
                var checkRows = $('#blogManagerTable').datagrid("getChecked");
                if(checkRows.length == 0){
                    $.messager.alert('温馨提示','请选择需要修改的博客！');
                }else if(checkRows.length > 1 ){
                    $.messager.alert('温馨提示','修改博客时所选的博客数量不能超过 1 ！');
                }else {
                    var checkRow = checkRows[0];
                    // 学习这种写法 todo
                    window.parent.openTab('修改博客','/admin/modifyBlog.jsp?id='+ checkRow.id,'icon-modify');
                }
            });

            $("#deleteAnyBlog").on("click",function(){
                var checkRows = $('#blogManagerTable').datagrid("getChecked");
                var willDelNum = checkRows.length;

                if(willDelNum == 0){
                    $.messager.alert('温馨提示','请选择需要删除的博客！','warning');
                }else if(willDelNum > 0 ) {
                    $.messager.confirm('确认对话框', '您想要删除这 <span style="color: red">' + willDelNum + '</span> 篇博客吗？', function(r){
                        if (r){
                            // $.messager.alert('温馨提示', '修改博客时所选的博客数量不能超过 1 ！');
                            var willDelIds = [];
                            for(var i=0;i<willDelNum;i++){
                                willDelIds.push(checkRows[i].id);
                            }
                            var ids=willDelIds.join(",");
                            $.get("${pageContext.request.contextPath}/admin/blog/deleteBlogList.do",{
                                "ids":ids
                            },function(data){
                                if(data.success){
                                    $.messager.alert('提示消息','删除成功！','info');
                                    // 重载行。等同于'load'方法，但是它将保持在当前页。
                                    // 注意：是表格重新加载，而不是删除按钮重新加载
                                    $('#blogManagerTable').datagrid('reload');
                                }else {
                                    $.messager.alert('提示消息',data.errorInfo,'error');
                                }
                            });
                        }
                    });
                }
            });

        });
    </script>
    </body>
</html>

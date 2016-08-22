<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.5/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.5/themes/icon.css">
    </head>
    <body>
        <table id="commentReviewTable" class="easyui-datagrid">
            <thead>
                <tr>
                    <th field="ck" checkbox="true" align="center"></th>
                    <th field="id">编号</th>
                    <th field="userIp">用户 IP</th>
                    <th field="blog" formatter="blogTitleFormatter">博客标题</th>
                    <th field="content">评论内容</th>
                    <th field="commentDate" formatter="dateFormatter">评论日期</th>
                    <th field="state" formatter="stateFormatter">评论状态</th>
                </tr>
            </thead>
        </table>

        <div id="commentReviewOper">
            <a id="reviewPass" class="easyui-linkbutton" iconCls="icon-ok">审核通过</a>
            <a id="reviewNoPass" class="easyui-linkbutton" iconCls="icon-no">审核不通过</a>

        </div>

        <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.5/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript">
            $("#commentReviewTable").datagrid({
                "fit":true,
                "toolbar":'#commentReviewOper',
                "url":"${pageContext.request.contextPath}/admin/comment/list.do",
                "rownumbers":true,
                "pagination":true,
                "method":"get",
                "queryParams":{
                    "state":'0'
                }
            });


            function dateFormatter(value,row,index){
                return getLocalTime(value);
            }

            function stateFormatter(value,row,index) {
                if(value === 0){
                    return "<font color='black'>未审核</font>";
                }
                if(value === 1){
                    return "<font color='green'>已审核</font>";
                }
                if(value === 2){
                    return "<font color='red'>审核不通过</font>";
                }
            }

            // 参考资料:http://www.jb51.net/article/43034.htm
            function getLocalTime(nS) {
                return new Date(parseInt(nS)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
            }

            function blogTitleFormatter(value,row,index) {
                if(value == null){
                    return "该评论所属的博客已经被删除。"
                }else {
                    return "<a target='_blank' href='${pageContext.request.contextPath}/blog/articles/"+value.id+".html'>"+value.title+"</a>";
                }
            }

            $("#reviewPass").on("click",function () {
                review(1);
            });

            $("#reviewNoPass").on("click",function () {
                review(2);
            });

            function review(state) {
                var selections = $("#commentReviewTable").datagrid("getSelections");
                var len = selections.length;

                if(len>0){
                    var idArray = [];
                    for(var i=0;i<len;i++){
                        idArray.push(selections[i].id);
                    }
                    var ids = idArray.join(",");
                    alert(ids);
                    $.messager.confirm("系统提示","您确定要审核这" + len +"条评论吗?",function (r) {
                        if(r){
                            $.get("${pageContext.request.contextPath}/admin/comment/review.do",{
                               "ids":ids,
                                "state":state
                            },function (data) {
                                if(data.success){
                                    alert(data.successInfo);
                                    $("#commentReviewTable").datagrid("reload");
                                }else {
                                    alert(data.errorInfo);
                                }

                            });
                        }
                    });
                }else {
                    $.messager.alert("系统提示","请至选择一条数据!");
                }
            }
        </script>
    </body>
</html>
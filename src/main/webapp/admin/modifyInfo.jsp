<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.5/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.5/themes/icon.css">
    </head>
    <body>
        <div id="info" class="easyui-panel">
            <form id="modifyInfo" method="post">
                <table cellpadding="10">
                    <tr>
                        <td style="width: 80px">用户名：</td>
                        <td>
                            <input id="id" name="id" type="hidden">
                            <input id="userName" name="userName" type="text" class="easyui-textbox" style="width: 400px">
                            (用户名不可修改)
                        </td>
                    </tr>
                    <tr>
                        <td>昵称：</td>
                        <td>
                            <input id="nickName" name="nickName" type="text" class="easyui-textbox" style="width: 400px">
                        </td>
                    </tr>
                    <tr>
                        <td>个性签名：</td>
                        <td>
                            <input id="sign" name="sign" type="text" class="easyui-textbox" style="width: 400px">
                        </td>
                    </tr>
                    <tr>
                        <td>个人头像：</td>
                        <td>
                            <input type="file" name="imageFile" id="imageFile">
                        </td>
                    </tr>
                    <tr>
                        <td>个人简介：</td>
                        <td>
                            <!-- 加载编辑器的容器 -->
                            <script id="profile" name="profile" type="text/plain" style="width:1050px;height:800px">

                            </script>

                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <a id="modifyInfoSubmit" class="easyui-linkbutton" data-options="iconCls:'icon-save',size:'large'" style="width: 100px">提交</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.5/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript">
            $("#info").panel({
                "title":"修改个人信息",
                "fit":true
            });

            // 设置用户名输入框是只读的
            $('#userName').attr('readonly',true);

            // 使用 ajax 的方式提交表单
            $("#modifyInfo").form({
                "url":"${pageContext.request.contextPath}/admin/blogger/save.do",
                "success":function(data){
                    if(data.success){
                        alert("修改个人信息成功!");
                    }
                }
            });

            $("#modifyInfoSubmit").on("click",function(){
                $('#modifyInfo').submit();
            });
        </script>

        <%-- 以下是 UEditor 编辑器脚本 --%>
        <!-- 配置文件 -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/ueditor/ueditor.config.js"></script>
        <!-- 编辑器源码文件 -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/ueditor/ueditor.all.js"></script>
        <!-- 实例化编辑器 -->
        <script type="text/javascript">
            var ue = UE.getEditor('profile');
            ue.addListener( 'ready', function( editor ) {
                //向sayhello.php发起一个异步的Ajax GET请求, 请求超时时间为10s， 请求完成后执行相应的回调。
                UE.ajax.request( '${pageContext.request.contextPath}/admin/blogger/find.do', {
                    //请求方法。可选值： 'GET', 'POST'，默认值是'POST'
                    method: 'GET',
                    //超时时间。 默认为5000， 单位是ms
                    timeout: 10000,
                    //是否是异步请求。 true为异步请求， false为同步请求
                    async: false,
                    //请求携带的数据。如果请求为GET请求， data会经过stringify后附加到请求url之后。
                    data: {
                        name: 'ueditor'
                    },

                    //请求成功后的回调， 该回调接受当前的XMLHttpRequest对象作为参数。
                    onsuccess: function ( xhr ) {
                        // console.log(xhr.responseText);
                        var data = eval("(" + xhr.responseText + ")");
                        $("#id").val(data.id);
                        $("#userName").textbox('setValue',data.userName);
                        $("#nickName").textbox('setValue',data.nickName);
                        $("#sign").textbox('setValue',data.sign);

                        ue.setContent(data.profile);

                    },

                    //请求失败或者超时后的回调。
                    onerror: function ( xhr ) {
                        alert( 'Ajax请求失败' );
                    }

                } );
            } );




        </script>
    </body>
</html>

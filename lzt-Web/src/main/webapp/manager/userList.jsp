<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="${ctx}/static/easyui/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/icon.css">
    <script type="text/javascript" src="${ctx}/static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/easyui/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" href="${ctx}/static/bootStrap/css/bootstrap.min.css">
    <script src="${ctx}/static/bootStrap/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function(){
            $('#dgUser').datagrid({
                onLoadSuccess: function (data) {
                    $("a[name='stopLogin']").linkbutton({text: '禁止登陆', plain: true, iconCls: 'icon_stopLogin'});
                    $("a[name='startLogin']").linkbutton({text: '开启登陆', plain: true, iconCls: 'icon_startLogin'});
                }
            });
        });

        function reloadUser(){
            $("#dgUser").datagrid("reload");
        }
        function headImg(value){
            return '<img class="img-circle" style="width: 100px;height: 100px" alt="头像" src="http://172.31.61.19:9091/lztWeb/'+value+'">';
        }
        function addUser(){
            $('#win').window({
                title:'添加管理员',
                width:450,
                height:280,
                modal:true,

            });
        }
        var regex1 = /^[A-Za-z0-9]{6,11}$/;
        var regex2 = /^[A-Za-z0-9.@!*&$#_]{6,13}$/;
        function addManager(){
            var InfoError = $("#InfoError");
            var userName = $("#username").val();
            var password = $("#password").val();
            if(userName.trim()=='' || password.trim()==''){
                InfoError.attr("style","display:inline;color:red");
                InfoError.text("用户名或者密码不能为空");
                return false;
            }
            if(checkUsername1()){
                if(checkPassword1()){
                    $.post("${ctx}/manager/user/addUser.do",{"username":userName,"password":password},
                        function(result){
                            if(result.code=="000000"){
                                reloadUser();
                                $("#win").window("close");
                            }else{
                                $.messager.alert('我的消息',result.message,"error");
                            }
                    },"json");
                }
            }
            return false;
        }
        function checkUsername1(){
            var InfoError = $("#InfoError");
            var userName = $("#username").val();
            var password = $("#password").val();
            if(userName.length>5){
                if(userName.match(regex1)==null){
                    InfoError.attr("style","display:inline;color:red");
                    InfoError.text("用户名只能是字母数字组合!");
                    return false;
                }
                InfoError.attr("style","display:none");
                return true;
            }else{
                InfoError.attr("style","display:inline;color:red");
                InfoError.text("用户名不能少于6位字符!");
                return false;
            }
        }
        function checkPassword1(){
            var InfoError = $("#InfoError");
            var userName = $("#username").val();
            var password = $("#password").val();
            if(userName.length>5){
                if(password.match(regex2)==null){
                    InfoError.attr("style","display:inline;color:red");
                    InfoError.text("密码符号错误!");
                    return false;
                }
                InfoError.attr("style","display:none");
                return true;
            }else{
                InfoError.attr("style","display:inline;color:red");
                InfoError.text("密码长度不能少于6位字符!");
                return false;
            }
        }

        function caozuoUser(value,row){
            var id = row.id;
            var status = row.status;
            var button;
            if(id==1){
                return "超级管理员";
            }
            if(status == 1){
                button = "<a href='javascript:void(0);' name='stopLogin' class='easyui-linkbutton' onclick='stopLogin("+id+")'>禁止登陆</a>";
            }else{
                button = "<a href='javascript:void(0);' name='startLogin' class='easyui-linkbutton' onclick='startLogin("+id+")'>开启登陆</a>";
            }
            return button;
        }

        function stopLogin(id){
            $.post("${ctx}/manager/user/StopOrStartLogin.do",{"id":id,"type":1},
                function(result){
                if(result.code=="000000"){
                    $.messager.alert('提示',result.message,'info');
                    reloadUser();
                }else{
                    $.messager.alert('警告',result.message,'error');
                }
            },"json");
        }

        function startLogin(id){
            $.post("${ctx}/manager/user/StopOrStartLogin.do",{"id":id,"type":2},
                function(result){
                    if(result.code=="000000"){
                        $.messager.alert('提示',result.message,'info');
                        reloadUser();
                    }else{
                        $.messager.alert('警告',result.message,'error');
                    }
                },"json");
        }

        function deleteUser(){
            var selects = $("#dgUser").datagrid("getSelections");
            if(selects.length < 1){
                $.messager.alert('警告','请选择一条记录','warning');
                return false;
            }
            $.messager.confirm('确认对话框', '您确定删除该管理员么？', function(r){
                if (r){
                    var id = selects[0].id;
                    if(id==1){
                        $.messager.alert('警告','不能删除超级管理员','warning');
                        return false;
                    }
                    $.post("${ctx}/manager/user/deleteUser.do",{"id":id},function(result){
                        if(result.code=="000000"){
                            reloadUser();
                        }else if(result.code=="000003"){
                            $.messager.alert('我的消息',result.message,"error");
                        }else{
                            $.messager.alert('我的消息',result.message,"error");
                        }
                    },'json')
                }
            });
        }
    </script>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table id="dgUser" class="easyui-datagrid" fit="true" fitColumns="true"
           singleSelect="true"   pagination="true" rownumbers="true" style="width: 100%" toolbar="#tb" url="${ctx}/manager/user/getUserList.do">
        <thead>
        <tr>
            <th data-options="field:'ck',checkbox:true"></th>
            <th data-options="field:'id'" width="5" align="center" halign="center" >管理员编号</th>
            <th data-options="field:'headImg'" width="15" align="center" halign="center" formatter="headImg">头像</th>
            <th data-options="field:'username'" width="10" align="center" halign="center" >用户名</th>
            <th data-options="field:'nickname'" width="10" align="center" halign="center">用户昵称</th>
            <th data-options="field:'description'" width="40" align="center" halign="center">个人说明</th>
            <th data-options="field:'createTime'" width="15" align="center" halign="center">创建时间</th>
            <th data-options="field:'updateTime'" width="15" align="center" halign="center">更新时间</th>
            <th data-options="field:'cz'" width="10" align="center" halign="center" formatter="caozuoUser">操作</th>
        </tr>
        </thead>
    </table>
    <div id="tb">
            <a href="javascript:void(0);" onclick="addUser()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"></a>
            <a href="javascript:void(0);" onclick="deleteUser()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"></a>
      <%--      <a href="javascript:void(0);" onclick="updateUser()" class="easyui-linkbutton" data-options="iconCls:'icon-update',plain:true"></a>--%>
    </div>
    <div id="win">
        <div class="form-group">
            <label for="username">用户名:</label>
            <input class="form-control" id="username" type="text" name="title" style="width:400px;" onblur="checkUsername1()"/>
        </div>
        <div class="form-group">
            <label for="password">密码:</label>
            <input class="form-control" id="password" type="text" name="title" style="width:400px;" onblur="checkPassword1()"/>
        </div>
        <span style="display: none" id="InfoError"></span>
        <button type="button" class="btn btn-info" style="float: right;margin-top: 25px" onclick="addManager()">添加</button>
    </div>
</body>
</html>

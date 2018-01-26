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
            $('#dgUrl').datagrid({
                onLoadSuccess: function (data) {
                    $("a[name='update']").linkbutton({text: '编辑', plain: true, iconCls: 'icon_lzt_edit'});
                    $("a[name='delete']").linkbutton({text: '删除', plain: true, iconCls: 'icon_cancel'});
                }
            });
        });
        function myUrl(value){
            return "<a target='view_window' href='"+value+"'>"+value+"</a>";
        }
        function czUrl(value,row){
            var btnHtml = "";
            btnHtml += "<a href='javascript:void(0);' class='easyui-linkbutton' name='update' onclick='update("+row.id+',"'+row.urlName+'"'+',"'+row.url+'"'+")'></a>";
            btnHtml += "<a href='javascript:void(0);' class='easyui-linkbutton' name='delete' onclick='deleteUrl("+row.id+',"'+row.urlName+'"'+")'></a>";
            return btnHtml;
        }

        function deleteUrl(id,urlName){
            $.messager.confirm('确认对话框', '您想要删除《'+urlName+'》链接么', function(r){
                if (r){
                    $.post("${ctx}/manager/url/deleteUrl.do",{"id":id},function(result){
                        if(result.code=="000000"){
                            $.messager.alert("成功","删除成功!","Info");
                            $("#dgUrl").datagrid("reload");
                        }else{
                            $.messager.alert("错误","删除失败","error");
                        }
                    },'json')
                }
            })
        }
        function update(id,urlName,url){
            $("#myId").val(id);
            $("#urlName").val(urlName);
            $("#url").val(url);
            addUrl();
        }

        function addUrl(){
            $("#addMyUrl").dialog({
                title:'添加更新链接',
                width:500,
                height:250,
                close:false,
                modal:true,
                buttons:[{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function() {
                        var urlName = $("#urlName").val().trim();
                        var url = $("#url").val().trim();
                        var id = $("#myId").val();
                        if(urlName==''){
                            $.messager.alert('警告','请输入链接名称！','warning');
                            return false;
                        }
                        if(url==''){
                            $.messager.alert('警告','请输入链接！','warning');
                            return false;
                        }
                        $.post("${ctx}/manager/url/addUrl.do",{"urlName":urlName,"url":url,"id":id},function(result){
                            $.messager.alert('我的消息','添加修改成功！','info');
                            $("#addMyUrl").dialog("close");
                            $("#dgUrl").datagrid("reload");
                        },'json');
                    }
                },{
                    text:'取消',
                    iconCls:'icon-no',
                    handler:function(){
                        $("#addMyUrl").dialog("close");
                    }
                }]
            })
        }
    </script>
</head>
<body>
<table id="dgUrl" class="easyui-datagrid" fit="true" fitColumns="true"
       singleSelect="true"  pagination="true" rownumbers="true" style="width: 100%" toolbar="#tb" url="${ctx}/manager/url/getUrlList.do">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'id'" width="5" align="center" halign="center">编号</th>
        <th data-options="field:'urlName'" width="20" align="center" halign="center">链接名称</th>
        <th data-options="field:'url'" width="40" align="center" halign="center" formatter="myUrl">链接</th>
        <th data-options="field:'cz'" width="10" align="center" halign="center" formatter="czUrl">操作</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <a href="javascript:void(0);" onclick="addUrl()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"></a>
</div>
<div id="addMyUrl">
    <input type="hidden" id="myId"/>
    <div class="form-group">
        <label for="urlName">链接名称:</label>
        <input class="form-control" id="urlName" type="text" name="urlName" style="width:450px;" placeholder="请输入链接名称"/>
    </div>
    <div class="form-group">
        <label for="url">链接:</label>
        <input class="form-control" id="url" type="text" name="url" style="width:450px;" placeholder="请输入链接"/>
    </div>
</div>
</body>
</html>

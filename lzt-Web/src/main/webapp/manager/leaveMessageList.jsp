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
            $('#dgLeave').datagrid({
                onLoadSuccess: function (data) {
                    $("a[name='show']").linkbutton({text: '关闭展示', plain: true, iconCls: 'icon_stopLogin'});
                    $("a[name='stop']").linkbutton({text: '开启展示', plain: true, iconCls: 'icon_startLogin'});
                }
            });
        });
        function reload(){
            $("#dgLeave").datagrid("reload");
        }

        function caoZuo(value,row){
            var isShow = row.isShow;
            var id= row.id;
            var button;
            if(isShow == "0"){
                button = "<a  href='javascript:void(0);' class='easyui-linkbutton' name='show' onclick='startShow("+id+","+isShow+")'></a>";
            }else{
                button = "<a  href='javascript:void(0);' class='easyui-linkbutton' name='stop' onclick='startShow("+id+","+isShow+")'></a>";
            }
            return button;
        }

        function startShow(id,isShow){
            $.post("${ctx}/manager/leaveMessage/updateLeave.do",{"id":id,"isShow":isShow},
                function(result){
                    if(result.code=="000000"){
                        $.messager.alert('提示',result.message,'info');
                        reload();
                    }else{
                        $.messager.alert('错误',result.message,'error');
                    }
            },"json")
        }

        function deleteMessage(){
            var selects = $("#dgLeave").datagrid("getSelections");
            if(selects.length < 1){
                $.messager.alert('警告','请选择一条记录','warning');
                return false;
            }
            var strids = [];
            for(var i=0;i<selects.length;i++){
                strids.push(selects[i].id);
            }
            var ids = strids.join(",");
            $.messager.confirm('确认对话框', '您确定删除该留言么？', function(r){
                if (r){
                    var id = selects[0].id;
                    $.post("${ctx}/manager/leaveMessage/deleteLeaveMessage.do",{"ids":ids},function(result){
                        if(result.code=="000000"){
                            reload();
                        }else if(result.code=="000003"){
                            $.messager.alert('我的消息',result.message,"error");
                        }else{
                            $.messager.alert('我的消息',result.message,"error");
                        }
                    },'json')
                }
            });
        }
        function showList(type){
            $("#dgLeave").datagrid('load',{
                "type":type
            });
        }
    </script>
</head>
<body>
<table id="dgLeave" class="easyui-datagrid" fit="true" fitColumns="true"
       pagination="true" rownumbers="true" style="width: 100%" toolbar="#tb" url="${ctx}/manager/leaveMessage/getLiuyanManagerList.do">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'id'" width="5" align="center" halign="center">编号</th>
        <th data-options="field:'kIP'" width="10" align="center" halign="center">留言IP</th>
        <th data-options="field:'message'" width="30" align="center" halign="center">留言内容</th>
        <th data-options="field:'createDate'" width="10" align="center" halign="center">留言时间</th>
        <th data-options="field:'updateDate'" width="10" align="center" halign="center">操作时间</th>
        <th data-options="field:'caozuo'" width="10" align="center" halign="center" formatter="caoZuo">操作</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <a href="javascript:void(0);" onclick="deleteMessage()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"></a>
    <a href="javascript:void(0);" onclick="showList(1)" class="easyui-linkbutton" data-options="iconCls:'icon-showList'">展示列表</a>
    <a href="javascript:void(0);" onclick="showList(0)" class="easyui-linkbutton" data-options="iconCls:'icon-stopList'">未展示列表</a>
    <a href="javascript:void(0);" onclick="showList(2)" class="easyui-linkbutton" data-options="iconCls:'icon-allList'">全部</a>
</div>
</body>
</html>

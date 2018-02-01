<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>发展列表</title>
    <script type="text/javascript" src="${ctx}/static/easyui/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/icon.css">
    <script type="text/javascript" src="${ctx}/static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/easyui/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" href="${ctx}/static/bootStrap/css/bootstrap.min.css">
    <script src="${ctx}/static/bootStrap/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function(){
            $('#dgDev').datagrid({
                onLoadSuccess: function (data) {
                    $("a[name='devUpdate']").linkbutton({text: '编辑', plain: true, iconCls: 'icon_lzt_edit'});
                    $("a[name='delete']").linkbutton({text: '删除', plain: true, iconCls: 'icon_cancel'});
                }
            });
        })
        function devCz(value,row){
            var id = row.id;
            var button = "<a onclick='updateDev("+id+',"'+row.webContent+'"'+',"'+row.webTitle+'"'+")' name='devUpdate' class='easyui-linkbutton' href='javascript:void(0);'></a>";
            button += "<a href='javascript:void(0);' class='easyui-linkbutton' name='delete' onclick='deleteDev("+row.id+")'></a>";
            return button;
        }
        function updateDev(id,webContent,webTitle){
            window.parent.openTab('修改网站发展'+id,'updateDevelop.jsp?id='+id+'&webContent='+webContent+'&webTitle='+webTitle,'icon-modifyPassword');
        }
        function deleteDev(id){
            $.messager.confirm('确认对话框', '您想要删除这条记录么', function(r){
                $.post("${ctx}/manager/devlop/deleteDev.do",{"id":id},
                    function(result){
                        if(result.code=="000000"){
                            $.messager.alert('成功','删除成功!','info');
                        }else{
                            $.messager.alert('失败',result.message,'error');
                        }
                    },'json');
            })
        }

        function devContent(value,row){
            if(value.length>30){
                return "<a href='javascript:void(0)' onclick='showDevContent("+row.id+',"'+row.webContent+'"'+")'>"+value.substring(0,30)+"..."+"</a>";
            }else{
                return value;
            }
        }
        function showDevContent(id,data){
            $("#Devtext").text(data);
            $("#MyDev").dialog({
                title:'查看全部内容',
                width:600,
                height:400,
                close:false,
                modal:true,
                buttons:[{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function() {
                        $("#MyDev").dialog("close");
                    }
                },{
                    text:'取消',
                    iconCls:'icon-no',
                    handler:function(){
                        $("#MyDev").dialog("close");
                    }
                }]
            })
        }
    </script>
</head>
<body>
    <table id="dgDev" class="easyui-datagrid" fit="true" fitColumns="true"
           singleSelect="true"  pagination="true" rownumbers="true" style="width: 100%" toolbar="#tb" url="${ctx}/manager/devlop/getDevList.do">
        <thead>
        <tr>
            <th data-options="field:'ck',checkbox:true"></th>
            <th data-options="field:'id'" width="5" align="center" halign="center">编号</th>
            <th data-options="field:'webTitle'" width="20" align="center" halign="center">发展标题</th>
            <th data-options="field:'webContent'" width="40" align="center" halign="center" formatter="devContent">发展内容</th>
            <th data-options="field:'webCreateDate'" width="10" align="center" halign="center">创建时间</th>
            <th data-options="field:'webUpdateDate'" width="10" align="center" halign="center">更新时间</th>
            <th data-options="field:'cz'" width="10" align="center" halign="center" formatter="devCz">操作</th>
        </tr>
        </thead>
    </table>
</body>
<div id="MyDev">
    <span id="Devtext"></span>
</div>
</html>

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

    <script type="text/javascript">
        function reload(){
            $("#dgType").datagrid("reload");
        }

        function addType(){
            $('#win').window({
                title:'分类管理',
                width:450,
                height:280,
                modal:true,

            });
            $('#sl').slider({
                mode: 'h',
                min:0,
                max:20,
                rule:[0,'|',4,'|',8,'|',12,'|',16,'|',20],
                showTip:true,
                step:1

            });
            $('#sl').slider("setValue",0);
            $("#type").val("");
            $("#typeId").val("");
        }

        function update(){
            var selects = $("#dgType").datagrid("getSelections");
            if(selects.length != 1){
                $.messager.alert('警告','请选择一条记录','warning');
                return false;
            }
            addType();
            var sort = selects[0].sort;
            var typeName = selects[0].typeName;
            var id = selects[0].id;
            $('#sl').slider("setValue",sort);
            $("#type").val(typeName);
            $("#typeId").val(id);
        }

        function add(){
            var sortValue = $("#sl").slider("getValue");
            var type = $("#type").val();
            var id = $("#typeId").val();
            $.post("${ctx}/manager/type/saveOrUpdate.do",{"id":id,"typeName":type,"sort":sortValue},function(result){
                if(result.code=="000000"){
                    reload();
                    $("#win").window("close");
                }else{
                    $.messager.alert('我的消息',"保存失败!","error");
                }
            },'json')
        }

        function deleteType(){
            var selects = $("#dgType").datagrid("getSelections");
            if(selects.length < 1){
                $.messager.alert('警告','请选择一条记录','warning');
                return false;
            }
            $.messager.confirm('确认对话框', '您确定删除该类型么？', function(r){
                if (r){
                    var id = selects[0].id;
                    $.post("${ctx}/manager/type/deleteType.do",{"id":id},function(result){
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
    </script>
</head>
<body>
<table id="dgType" class="easyui-datagrid" fit="true" fitColumns="true"
       singleSelect="true"  pagination="true" rownumbers="true" style="width: 100%" toolbar="#tb" url="${ctx}/manager/type/getTypeList.do">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'id'" width="5" align="center" halign="center">编号</th>
        <th data-options="field:'typeName'" width="20" align="center" halign="center">类别名称</th>
        <th data-options="field:'createDate'" width="10" align="center" halign="center">创建时间</th>
        <th data-options="field:'updateDate'" width="10" align="center" halign="center">更新时间</th>
        <th data-options="field:'sort'" width="5" align="center" halign="center">排序</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <a href="javascript:void(0);" onclick="addType()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"></a>
    <a href="javascript:void(0);" onclick="deleteType()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"></a>
    <a href="javascript:void(0);" onclick="update()" class="easyui-linkbutton" data-options="iconCls:'icon-update',plain:true"></a>
</div>

<div id="win">
    <input type="hidden" id="typeId"/>
    <div class="form-group">
        <label for="type">分类名称:</label><span style="color: red;display: none;float: right">该分类已经存在</span>
        <input class="form-control" id="type" type="text" name="title" style="width:400px;"/>
    </div>
    <div class="form-group">
        <label for="ss">排序:</label>
        <div id="ss" style="height: 15px"></div>
        <div id="sl" style="height:100px;"></div>
    </div>
    <button type="button" class="btn btn-info" style="float: right;margin-top: 25px" onclick="add()">保存</button>
</div>
</body>
</html>

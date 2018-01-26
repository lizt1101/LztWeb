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
            $('#dgBj').datagrid({
                onLoadSuccess: function (data) {
                    $("a[name='stop']").linkbutton({text: '禁用', plain: true, iconCls: 'icon_stopLogin'});
                    $("a[name='start']").linkbutton({text: '启用', plain: true, iconCls: 'icon_startLogin'});
                    $("a[name='edit']").linkbutton({text: '编辑', plain: true, iconCls: 'icon_lzt_edit'});
                }
            });
        });
        function bjImg(value,row){
            var bj = 'http://172.31.61.19:9091/lztWeb/bj/'+value;
            if(row.isPu=="0"){
                return 'background:url('+bj+');height:150px';
            }else{
                return "background:url("+bj+") 0 50% no-repeat;background-size: 100% 100%;height:150px";
            }
        }
        function bjValue(){
            return null;
        }
        function yangshi(value){
            if(value=="0"){
                return "平铺";
            }else{
                return "拉伸";
            }
        }
        function caozuoBj(value,row){
            var btnHtml = '';
            if(row.isDelete=='0'){
                btnHtml += "<a href='javascript:void(0);' class='easyui-linkbutton' name='stop' onclick='updateBjStatus("+row.id+",1"+")'></a>&nbsp;&nbsp;&nbsp;";
            }else{
                btnHtml += "<a href='javascript:void(0);' class='easyui-linkbutton' name='start' onclick='updateBjStatus("+row.id+",0"+")'></a>&nbsp;&nbsp;&nbsp;";
            }
            btnHtml += "<a href='javascript:void(0);' class='easyui-linkbutton' name='edit' onclick='updateBj("+row.id+")'></a>";
            return btnHtml;
        }
        function updateBj(id){
            window.parent.openTab('修改背景详情'+id,'UpdateBjImage.jsp?id='+id,'icon-modifyPassword');
        }
        function updateBjStatus(id,status){
            var str;
            if(status==1){
                str = '禁用';
            }else{
                str = '启用';
            }
            $.messager.confirm('确认对话框', '您要'+str+'该背景么？', function(r){
                if (r){
                    $.post("${ctx}/manager/image/updateImageStatus.do",{"id":id,"status":status},
                        function(data){
                            if(data.code=="000000"){
                                $.messager.alert('成功',data.message,'success');
                                $("#dgBj").datagrid("reload");
                            }else{
                                $.messager.alert('错误',data.message,'error');
                            }
                        },'json');
                }
            });
        }
        function showbj(status){
            $("#dgBj").datagrid('load',{
                "status":status
            });
        }
    </script>
<head>
    <title>背景管理</title>
</head>
<body>
<table id="dgBj" class="easyui-datagrid" fit="true" fitColumns="true"
         pagination="true" rownumbers="true" style="width: 100%" toolbar="#tb" url="${ctx}/manager/image/getImageList.do">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'id'" width="2" align="center" halign="center">编号</th>
        <th data-options="field:'bjName'" width="10" align="center" halign="center">背景名称</th>
        <th data-options="field:'bjUrl'" width="20" align="center" halign="center" styler="bjImg" formatter="bjValue">背景图</th>
        <th data-options="field:'isPu'" width="5" align="center" halign="center" formatter="yangshi">样式</th>
        <th data-options="field:'createDate'" width="10" align="center" halign="center">创建时间</th>
        <th data-options="field:'updateDate'" width="10" align="center" halign="center">更新时间</th>
        <th data-options="field:'sort'" width="5" align="center" halign="center">排序</th>
        <th data-options="field:'isDelete'" width="5" align="center" halign="center" hidden="true"></th>
        <th data-options="field:'cz'" width="10" align="center" halign="center" formatter="caozuoBj">操作</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <a href="javascript:void(0);" onclick="showbj(0)" class="easyui-linkbutton" data-options="iconCls:'icon-showList'">启用列表</a>
    <a href="javascript:void(0);" onclick="showbj(1)" class="easyui-linkbutton" data-options="iconCls:'icon-stopList'">禁用列表</a>
    <a href="javascript:void(0);" onclick="showbj(2)" class="easyui-linkbutton" data-options="iconCls:'icon-allList'">全部</a>
</div>
</body>
</html>

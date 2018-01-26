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
            $('#dgLb').datagrid({
                onLoadSuccess: function (data) {
                    $("a[name='stop']").linkbutton({text: '禁用', plain: true, iconCls: 'icon_stopLogin'});
                    $("a[name='start']").linkbutton({text: '启用', plain: true, iconCls: 'icon_startLogin'});
                    $("a[name='delete']").linkbutton({text: '删除', plain: true, iconCls: 'icon_cancel'});
                }
            });
        });
        function showLb(status){
            $("#dgLb").datagrid('load',{
                "status":status
            });
        }
        function lunboType(value){
            if(value=='0'){
                return '文章';
            }else{
                return '链接';
            }
        }
        function artUrl(value){
            if(value!="" && value!=null){
                return "<a target='view_window' href='${ctx}/article/getArtDetails/"+value+".do'>"+value+"</a>";
            }else{
                return '无';
            }
        }
        function qtUrl(value){
            if(value==""){
                return '无';
            }else{
                return "<a target='view_window' href='"+value+"'>"+value+"</a>";
            }
        }
        function LbCz(value,row){
            var btnHtml = '';
            if(row.lbStatus=='0'){
                btnHtml += "<a href='javascript:void(0);' class='easyui-linkbutton' name='stop' onclick='updateLbStatus("+row.id+",1"+")'></a>&nbsp;&nbsp;&nbsp;";
            }else{
                btnHtml += "<a href='javascript:void(0);' class='easyui-linkbutton' name='start' onclick='updateLbStatus("+row.id+",0"+")'></a>&nbsp;&nbsp;&nbsp;";
            }
            btnHtml += "<a href='javascript:void(0);' class='easyui-linkbutton' name='delete' onclick='updateLbStatus("+row.id+",2"+")'></a>";
            return btnHtml;
        }
        function LbTu(value){
            var lb = 'http://172.31.61.19:9091/lztWeb/lunbo/'+value;
            return "background:url("+lb+") 50% 0 no-repeat;background-size: 100% 100%;height:150px";
        }
        function Lbtu(value){
            return null;
        }
        function updateLbStatus(id,status){
            var str;
            if(status==1){
                str = '禁用';
            }else if(status==0){
                str = '启用';
            }else{
                str = '删除';
            }
            $.messager.confirm('确认对话框', '您要'+str+'该轮播图么？', function(r){
                if (r){
                    $.post("${ctx}/manager/image/updateLbImageStatus.do",{"id":id,"status":status},
                        function(data){
                            if(data.code=="000000"){
                                $.messager.alert('成功',data.message,'success');
                                $("#dgLb").datagrid("reload");
                            }else{
                                $.messager.alert('错误',data.message,'error');
                            }
                        },'json');
                }
            });
        }
    </script>
</head>
<body>
<table id="dgLb" class="easyui-datagrid" fit="true" fitColumns="true"
       pagination="true" rownumbers="true" style="width: 100%" toolbar="#tb" url="${ctx}/manager/image/getLbImageList.do">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'id'" width="2" align="center" halign="center">编号</th>
        <th data-options="field:'lbName'" width="10" align="center" halign="center">轮播名称</th>
        <th data-options="field:'lbTu'" width="20" align="center" halign="center" styler="LbTu" formatter="Lbtu">轮播图</th>
        <th data-options="field:'lbAid'" width="5" align="center" halign="center" formatter="artUrl">文章编号</th>
        <th data-options="field:'lbUrl'" width="20" align="center" halign="center" formatter="qtUrl">链接</th>
        <th data-options="field:'lbType'" width="5" align="center" halign="center" formatter="lunboType">轮播类型</th>
        <th data-options="field:'lbSort'" width="5" align="center" halign="center">轮播顺序</th>
        <th data-options="field:'lbStatus'" width="5" align="center" halign="center" hidden="true"></th>
        <th data-options="field:'cz'" width="10" align="center" halign="center" formatter="LbCz">操作</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <a href="javascript:void(0);" onclick="showLb(0)" class="easyui-linkbutton" data-options="iconCls:'icon-showList'">启用列表</a>
    <a href="javascript:void(0);" onclick="showLb(1)" class="easyui-linkbutton" data-options="iconCls:'icon-stopList'">禁用列表</a>
    <a href="javascript:void(0);" onclick="showLb(2)" class="easyui-linkbutton" data-options="iconCls:'icon-allList'">全部</a>
</div>
</body>
</html>

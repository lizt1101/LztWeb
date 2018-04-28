<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="${ctx}/static/easyui/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/icon.css">
    <script type="text/javascript" src="${ctx}/static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/easyui/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" href="${ctx}/static/bootStrap/css/bootstrap.min.css">
    <script type="text/javascript" src="${ctx}/static/bootStrap/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function(){
            $('#dgtu').datagrid({
                onLoadSuccess: function (data) {
                    $("a[name='stop']").linkbutton({text: '禁用', plain: true, iconCls: 'icon_stopLogin'});
                    $("a[name='start']").linkbutton({text: '启用', plain: true, iconCls: 'icon_startLogin'});
                    $("a[name='delete']").linkbutton({text: '删除', plain: true, iconCls: 'icon_cancel'});
                }
            });
        });
        function type(value){
            if(value=='1'){
                return '页头';
            }else if(value=='2'){
                return '主页正文头';
            }else if(value=='3'){
                return '主页侧边图';
            }else if(value=='4'){
                return '主页时间框';
            }else{
                return '详情正文页头';
            }
        }
        function status(value){
            if(value=='0'){
                return '展示';
            }else{
                return '禁用';
            }
        }
        function caoZuoTu(value,row){
            var id = row.id;
            var btnHtml = '';
            if(row.tuStatus=='0'){
                btnHtml += "<a href='javascript:void(0);' class='easyui-linkbutton' name='stop' onclick='updateQtStatus("+row.id+",1"+","+row.tuType+")'>禁用</a>&nbsp;";
            }else{
                btnHtml += "<a href='javascript:void(0);' class='easyui-linkbutton' name='start' onclick='updateQtStatus("+row.id+",0"+","+row.tuType+")'>启用</a>&nbsp;";
            }
            btnHtml += "<a href='javascript:void(0);' class='easyui-linkbutton' name='delete' onclick='updateQtStatus("+row.id+",2"+","+row.tuType+")'>删除</a>";
            return btnHtml;
        }

        function updateQtStatus(id,status,type){
            var str;
            if(status==1){
                str = '禁用';
            }else if(status==0){
                str = '启用';
            }else{
                str = '删除';
            }
            $.messager.confirm('确认对话框', '您要'+str+'该图么？', function(r){
                if (r){
                    $.post("${ctx}/manager/image/updateQtImageStatus.do",{"id":id,"status":status,"type":type},
                        function(data){
                            if(data.code=="000000"){
                                $.messager.alert('成功',data.message,'success');
                                $("#dgtu").datagrid("reload");
                            }else{
                                $.messager.alert('错误',data.message,'error');
                            }
                        },'json');
                }
            });
        }
        function showQt(status){
            $("#dgtu").datagrid('load',{
                "status":status
            });
        }
        function serachType(){
            var type= $("#cc").combobox("getValue");
            $("#dgtu").datagrid('load',{
                "type":type
            });
        }
        function tu(value,row){
            var tu = 'http://172.31.61.25:9091/lztWeb/style/'+value;
            if(row.tuType=='4' || row.tuType=='3'){
                return "background:url("+tu+") 0 50% no-repeat;background-size:100% 100%;height:100px";
            }else{
                return "background:url("+tu+") 0 50% no-repeat;background-size:100%;height:70px";
            }

        }
        function typeTu(value){
            return null;
        }
    </script>
</head>
<body>
<table id="dgtu" class="easyui-datagrid" fit="true" fitColumns="true"
       pagination="true" rownumbers="true" style="width: 100%" toolbar="#tb" url="${ctx}/manager/image/getQtImageList.do">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'id'" width="4" align="center" halign="center">编号</th>
        <th data-options="field:'tuName'" width="10" align="center" halign="center">图名称</th>
        <th data-options="field:'tuUrl'" width="30" align="center" halign="center" styler="tu" formatter="typeTu">图</th>
        <th data-options="field:'tuType'" width="10" align="center" halign="center" formatter="type">图部位</th>
        <th data-options="field:'tuStatus'" width="5" align="center" halign="center" formatter="status">图状态</th>
        <th data-options="field:'tuCreate'" width="5" align="center" halign="center">创建时间</th>
        <th data-options="field:'tuUpdate'" width="10" align="center" halign="center">更新时间</th>
        <th data-options="field:'tuCreateBy'" width="5" align="center" halign="center">创建者编号</th>
        <th data-options="field:'tuUpdateBy'" width="5" align="center" halign="center">更新者编号</th>
        <th data-options="field:'cz'" width="10" align="center" halign="center" formatter="caoZuoTu">操作</th>
    </tr>
    </thead>
</table>
<div id="tb">
    &nbsp&nbsp<span>部&nbsp&nbsp位&nbsp:</span>&nbsp;&nbsp;<select id="cc" class="easyui-combobox" name="dept" style="width:200px;">
    <option value="0">请选择</option>
    <option value="1">页头</option>
        <option value="2">主页正文头</option>
        <option value="3">主页侧边图</option>
        <option value="4">主页时间框</option>
        <option value="5">详情正文页头</option>
    </select>
    <a id="btn" href="javascript:void(0);" onclick="serachType()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">确定</a><br><br>
    <a href="javascript:void(0);" onclick="showQt(0)" class="easyui-linkbutton" data-options="iconCls:'icon-showList'">启用列表</a>
    <a href="javascript:void(0);" onclick="showQt(1)" class="easyui-linkbutton" data-options="iconCls:'icon-stopList'">禁用列表</a>
    <a href="javascript:void(0);" onclick="showQt(2)" class="easyui-linkbutton" data-options="iconCls:'icon-allList'">全部</a>
</div>
</body>
</html>

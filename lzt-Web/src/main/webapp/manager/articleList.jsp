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
     $(document).ready(function(){
         $('#dg').datagrid({
             onLoadSuccess: function (data) {
                 $("a[name='artUpdate']").linkbutton({text: '编辑', plain: true, iconCls: 'icon_lzt_edit'});
             }
         });
      })

      function shuaxin(){
          $("#dg").datagrid("reload");
      }
        function openTab1(text,url,icon){
            window.parent.openTab(text,url,icon);
        }
        function subContent(value){
            if(value.length>25){
                return value.substring(0,25)+"。。。。。。";
            }
            return value;
        }

        function lookOrUpdate(value){
            var id = value;
            var button = "<a onclick='update("+id+")' name='artUpdate' class='easyui-linkbutton' href='javascript:void(0);'></a>";
            return button;
        }

      function look(id){
            alert(id);
            window.parent.openTab('查看文章'+id,'LookArticle.jsp?id='+id,'icon-modifyPassword');
        }
        function update(id){
            window.parent.openTab('修改文章'+id,'UpdateArticle.jsp?id='+id,'icon-modifyPassword');
        }
        function remove(){
            var selects = $("#dg").datagrid("getSelections");
            if(selects.length<1){
                $.messager.alert('警告','请选择一条记录','warning');
                return false;
            }
            var strids = [];
            for(var i=0;i<selects.length;i++){
                strids.push(selects[i].id);
            }
            var ids = strids.join(",");
            $.messager.confirm('确认对话框', '您想要删除这些数据么', function(r){
                if (r){
                    $.post("${ctx}/manager/article/deleteArt.do",{"ids":ids},function(result){
                        if(result.code=="000000"){
                            $.messager.alert("成功","删除成功!","Info");
                            $("#dg").datagrid("reload");
                        }else{
                            $.messager.alert("错误","删除失败","error");
                        }
                    },'json')
                }
            });
        }
        function reload(){
            $("#dg").datagrid("reload");
        }
        function detailsBytitle(value,row){
            return "<a target='view_window' href='${ctx}/article/getArtDetails/"+row.id+".do'>"+value+"</a>";
        }
        function serach(){
            var key = $("#key").val().trim();
            if(key != ""){
                $("#dg").datagrid('load',{
                    "key":key
                })
            }
        }
    </script>
</head>
<body>
<table id="dg" class="easyui-datagrid" fit="true" fitColumns="true"
       checkOnSelect="false"  pagination="true" rownumbers="true" style="width: 100%" toolbar="#tb" url="${ctx}/manager/article/getList.do">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'title'" width="20" align="center" halign="center" formatter="detailsBytitle">标题</th>
        <th data-options="field:'content'" width="50" align="center" halign="center" formatter="subContent">内容</th>
        <th data-options="field:'userName'" width="10" align="center" halign="center">作者</th>
        <th data-options="field:'createTime'" width="20" align="center" halign="center">发表时间</th>
        <th data-options="field:'updateBy'" width="10" align="center" halign="center">更新者</th>
        <th data-options="field:'updateTime'" width="20" align="center" halign="center">更新时间</th>
        <th data-options="field:'typeName'" width="10" align="center" halign="center">类别</th>
        <th data-options="field:'id'" width="10" align="center" halign="center" formatter="lookOrUpdate">操作</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <input class="form-control" id="key" type="text" name="key" style="width:200px;display: inline" placeholder="请输入关键字进行搜索"/>
    <a href="javascript:serach();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a><br>
    <a href="javascript:void(0);" onclick="openTab1('发表文章','writeArticle.jsp','icon-writeblog')" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"></a>
    <a href="javascript:void(0);" onclick="remove()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"></a>
</div>

</body>
</html>

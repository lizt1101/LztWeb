
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>评论列表</title>
    <script type="text/javascript" src="${ctx}/static/easyui/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/icon.css">
    <script type="text/javascript" src="${ctx}/static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/easyui/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" href="${ctx}/static/bootStrap/css/bootstrap.min.css">
    <script src="${ctx}/static/bootStrap/js/bootstrap.min.js"></script>
    <script>
        function check(value,row){
            var status = row.status;
            var id= row.id;
            var aid = row.aid;
            var button;
            if(status == "0"){
                button = "<a style='cursor:pointer;text-decoration: none' href='javascript:void(0);' class='easyui-linkbutton' onclick='piCheck("+id+","+aid+")'>审核</a>";
            }else if(status == "1"){
                button = "<a style='cursor:pointer;text-decoration: none' href='javascript:void(0);' class='easyui-linkbutton' onclick='reply("+id+")'>回复</a>";
                /*button = "已审核";*/
            }else{
                button = "已回复"
            }
            return button;
        }
        function checkUser(value){
            if(value == null){
                return '暂无';
            }
            return value;
        }

        function showReplyContent(value,row){
            if(value == null){
                return '暂未回复';
            }
            if(value.length>20){
                return  "<a href='javascript:void(0)' onclick='showComment("+row.id+","+1+")'>"+value.substring(0,20)+"..."+"</a>";
            }
            return value;
        }
        function reply(id){
            $("#reply").dialog({
                title:'评论回复',
                width:600,
                height:400,
                close:false,
                modal:true,
                buttons:[{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function() {
                        var reply = $("#replyText").val();
                        $.post("${ctx}/manager/comment/replyComment.do",{"id":id,"reply":reply},function(result){
                            if(result.code=="000000"){
                                $.messager.alert("成功",result.message,"info");
                                $("#dgComment").datagrid("reload");
                                $("#reply").dialog("close");
                            }else{
                                $.messager.alert("错误",result.message,"error");
                            }
                        },'json')
                        $("#reply").dialog("close");
                    }
                },{
                    text:'取消',
                    iconCls:'icon-no',
                    handler:function(){
                        $("#reply").dialog("close");
                    }
                }]
            })
        }

        function piCheck(id,aid){
            var ids;
            var aids;
            if(id==0){
                var selects = $("#dgComment").datagrid("getSelections");
                if(selects.length<1){
                    $.messager.alert('警告','请选择一条记录','warning');
                    return false;
                }
                var strids = [];
                var straids =[];
                for(var i=0;i<selects.length;i++){
                    strids.push(selects[i].id);
                    straids.push(selects[i].aid);
                }
                ids = strids.join(",");
                aids = straids.join(",");
            }else{
                ids = id;
                aids = aid;
            }
            $.messager.confirm('确认对话框', '您想要审核这些评论么', function(r){
                if (r){
                    $.post("${ctx}/manager/comment/checkComment.do",{"ids":ids,"aids":aids},function(result){
                        if(result.code=="000000"){
                            $.messager.alert("成功",result.message,"info");
                            $("#dgComment").datagrid("reload");
                        }else{
                            $.messager.alert("错误",result.message,"error");
                        }
                    },'json')
                }
            });
        }

        function MyCheck(status){
            $("#dgComment").datagrid('load',{
                "status":status
            });
        }
        function aidUrl(value){
            return "<a target='view_window' href='${ctx}/article/getArtDetails/"+value+".do'>"+value+"</a>";
        }
        function showAllComment(value,row){
            if(value.length>20){
                return  "<a href='javascript:void(0)' onclick='showComment("+row.id+","+2+")'>"+value.substring(0,20)+"..."+"</a>";
            }
            return value;
        }

        function showComment(id,type){
            $.post("${ctx}/manager/comment/getComment.do",{"id":id,"type":type},function(result){
                $("#Commenttext").text(result.data);
            },'json');
            $("#Mycomment").dialog({
                title:'查看全部内容',
                width:600,
                height:400,
                close:false,
                modal:true,
                buttons:[{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function() {
                       $("#Mycomment").dialog("close");
                    }
                },{
                    text:'取消',
                    iconCls:'icon-no',
                    handler:function(){
                        $("#Mycomment").dialog("close");
                    }
                }]
            })
        }
        // Firefox, Google Chrome, Opera, Safari, Internet Explorer from version 9
        function OnInput (event) {
            /* alert ("The new content1: " + event.target.value);*/
            var len = event.target.value.length;
            $("#re").text(500-len);
        }
        // Internet Explorer
        function OnPropChanged (event) {
            if (event.propertyName.toLowerCase () == "value") {
                var len = event.target.value.length;
                $("#re").text(500-len);
                /* alert ("The new content2: " + event.srcElement.value);*/
            }
        }
    </script>
</head>
<body>
<table id="dgComment" class="easyui-datagrid" fit="true" fitColumns="true"
       pagination="true" rownumbers="true" style="width: 100%" toolbar="#tb" url="${ctx}/manager/comment/commentList.do">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'id'" width="5" align="center" halign="center" hidden="true">编号</th>
        <th data-options="field:'aid'" width="5" align="center" halign="center" formatter="aidUrl">文章编号</th>
        <th data-options="field:'name'" width="5" align="center" halign="center">评论代号</th>
        <th data-options="field:'createBy'" width="10" align="center" halign="center">评论者IP</th>
        <th data-options="field:'comment'" width="30" align="center" halign="center" formatter="showAllComment">评论内容</th>
        <th data-options="field:'createDate'" width="10" align="center" halign="center">评论时间</th>
        <th data-options="field:'updateBy'" width="5" align="center" halign="center" formatter="checkUser">审核人编号</th>
        <th data-options="field:'updateDate'" width="10" align="center" halign="center">审核时间</th>
        <th data-options="field:'reply'" width="20" align="center" halign="center" formatter="showReplyContent">回复内容</th>
        <th data-options="field:'caozuo'" width="5" align="center" halign="center" formatter="check">操作</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <a href="javascript:void(0);" onclick="deleteMessage()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"></a>
    <a href="javascript:void(0);" onclick="piCheck(0,0)" class="easyui-linkbutton" data-options="plain:true">批量审核</a>
    <a href="javascript:void(0);" onclick="MyCheck(0)" class="easyui-linkbutton" data-options="plain:true">未审核</a>
    <a href="javascript:void(0);" onclick="MyCheck(1)" class="easyui-linkbutton" data-options="plain:true">已审核</a>
    <a href="javascript:void(0);" onclick="MyCheck(3)" class="easyui-linkbutton" data-options="plain:true">已回复</a>
    <a href="javascript:void(0);" onclick="MyCheck(2)" class="easyui-linkbutton" data-options="plain:true">全部</a>
</div>
<div id="Mycomment">
   <span id="Commenttext"></span>
</div>
<div id="reply">
    <textarea id="replyText" placeholder="请输入回复。。。" style="width: 90%;height: 90%;font-size: 20px" maxlength="500" oninput="OnInput(event)" onpropertychange="OnPropChanged(event)">

    </textarea><br>
    还可输入<span id="re">500</span>个字符
    <%--<input style="width: 90%;height: 40px;font-size: 20px;" maxlength="50" class="form-control"/>--%>
</div>
</body>
</html>

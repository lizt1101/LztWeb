<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>添加发展</title>
    <script type="text/javascript" src="${ctx}/static/easyui/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/icon.css">
    <script type="text/javascript" src="${ctx}/static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/easyui/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" href="${ctx}/static/bootStrap/css/bootstrap.min.css">
    <script src="${ctx}/static/bootStrap/js/bootstrap.min.js"></script>
    <script>
        // Firefox, Google Chrome, Opera, Safari, Internet Explorer from version 9
        function OnInput (event) {
            /* alert ("The new content1: " + event.target.value);*/
            var len = event.target.value.length;
            $("#zf").text(1024-len);
        }
        // Internet Explorer
        function OnPropChanged (event) {
            if (event.propertyName.toLowerCase () == "value") {
                var len = event.target.value.length;
                $("#zf").text(1024-len);
                /* alert ("The new content2: " + event.srcElement.value);*/
            }
        }
        function addDev(){
            $('#dev').form('submit', {
                url:"${ctx}/manager/devlop/addDevlop.do",
                onSubmit: function(){
                    if($("#webTitle").val()==''){
                        $.messager.alert('警告','请输入发展标题！','warning');
                        return false;
                    }
                    if($("#webContent").val()==''){
                        $.messager.alert('警告','请输入发展内容！','warning');
                        return false;
                    }
                },
                success:function(data){
                    var data = eval('(' + data + ')');
                    if(data.code=="000000"){
                        clearDev();
                        $.messager.alert('成功',data.message,'success');
                    }else{
                        $.messager.alert('错误',data.message,'error');
                    }

                }
            })
        }
        function clearDev(){
            $("#webTitle").val("");
            $("#webContent").val("");
        }
    </script>
</head>
<body>
<form id="dev" method="post">
    <div class="form-group">
        <label for="webTitle">发展标题:</label>
        <input class="form-control" id="webTitle" type="text" name="webTitle" style="width:400px;" placeholder="请输入标题(可选)"/>
    </div>
    <div class="form-group">
        <label for="webContent">发展内容:</label>
        <textarea class="form-control" id="webContent" type="text" rows="17" maxlength="1024" oninput="OnInput(event)" onpropertychange="OnPropChanged(event)" name="webContent" style="width:900px;height: 400px;font-size: 20px" placeholder="请输入发展内容"></textarea>
        还可输入<span id="zf">1024</span>个字符
    </div>
</form>
<button onclick="addDev()" class="btn btn-info">发表</button>
</body>
</html>

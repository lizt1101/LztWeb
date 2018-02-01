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
            $.post("${ctx}/manager/common/getContent.do",{"id":"1"},function(result){
                $("#webP").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+result.data);
            },'json');
        });
        function editWeb(){
            $("#webP").attr("style","display:none");
            $("#text").attr("style","width: 100%;height: 100%;font-size: 20px;");
            $("#bj").attr("style","display:none");
            $("#qx").attr("style","float: right;margin-left: 10px");
            $("#qd").attr("style","float: right");
            $("#text").val($("#webP").text());
        }
        function qxWeb(){
            $("#webP").attr("style","width: 100%;height: 100%;font-size: 20px;");
            $("#text").attr("style","display:none");
            $("#bj").attr("style","float: right");
            $("#qx").attr("style","display:none");
            $("#qd").attr("style","display:none");
        }
        function qdWeb(){
            var newContent = $("#text").val();
            $.post("${ctx}/manager/common/updateContent.do",{"webContent":newContent,"id":1},function(result){
                $.messager.alert('我的消息','修改成功！','info');
                qxWeb();
                $("#webP").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+newContent);
            },'json');

        }
    </script>
</head>
<body>
    <div style="width: 80%;height: 500px;margin: 20px auto;">
        <div style="width: 80%;height: 80%;margin: 0 auto;">
            <p style="font-size: 20px" id="webP">
            </p>
            <textarea id="text" style="width: 100%;height: 100%;font-size: 20px;display: none" maxlength="500"></textarea>
        </div>
        <div style="width: 80%;height: 20%;margin: 0 auto;">
            <button id="bj" type="button" class="btn btn-info" style="float: right" onclick="editWeb()">编辑</button>
            <button id="qx" type="button" class="btn btn-info" style="float: right;display: none" onclick="qxWeb();">取消</button>
            <button id="qd" type="button" class="btn btn-info" style="float: right;display: none" onclick="qdWeb()">确定</button>

        </div>
    </div>
</body>
</html>

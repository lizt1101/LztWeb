<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
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
    <style>
        body{
            width: 100%;
            height: 800px;
        }
    </style>
    <script src="${ctx}/static/bootStrap/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function(){
            $.post("${ctx}/manager/image/getImage.do",{"id": ${param.id}},
                function(result){
                    $("#name").val(result.data.bjName);
                    $("#sort").slider("setValue",result.data.sort)
                    if(result.data.isPu=="0"){
                        $("#isPu").attr("checked","checked");
                        $("body").attr("style","background:url(http://172.31.61.19:9091/lztWeb/bj/"+result.data.bjUrl+");padding: 10px");
                    }else{
                        $("body").attr("style","background:url(http://172.31.61.19:9091/lztWeb/bj/"+result.data.bjUrl+") 0 50% no-repeat;background-size: 100% 100%;padding: 10px");
                    }
            },'json');
        });
        function mySubmit(){
            $('#ffbj').form('submit', {
                url:"${ctx}/manager/image/updateImage.do",
                onSubmit: function(param){
                    if($("#name").val().trim()==''){
                        $.messager.alert('警告','请输入背景名称！','warning');
                        return false;
                    }
                    param.id = ${param.id};
                },
                success:function(data){
                    var data = eval('(' + data + ')');
                    if(data.code=="000000"){
                        $.messager.alert('成功',data.message,'success');
                    }else{
                        $.messager.alert('错误',data.message,'error');
                    }

                }
            });
        }
        function bj(){
            if($("#isPu").is(":checked")){
                var backStyle = $("body").attr("style");
                var start = backStyle.indexOf("(");
                var end = backStyle.indexOf(")");
                var objUrl = backStyle.substring(parseInt(start)+1,end);
                $("body").attr("style","background:url("+objUrl+");padding: 10px");
            }else{
                var backStyle = $("body").attr("style");
                var start = backStyle.indexOf("(");
                var end = backStyle.indexOf(")");
                var objUrl = backStyle.substring(parseInt(start)+1,end);
                $("body").attr("style","background:url("+objUrl+") 0 50% no-repeat;background-size: 100% 100%;padding: 10px");
            }
        }
    </script>
</head>
<body style="padding: 10px">
<form method="post" id="ffbj">
    <h4>背景名称:</h4>
    <input type="text" id="name" name="bjName" class="form-control" placeholder="请输入背景名称。。。" style="width: 300px">
    <h4>排序:</h4><br>
    <input class="easyui-slider" value="1"  style="width:600px;margin-left: 10px" name="sort" id="sort"
           data-options="showTip:true,rule:[1,'|',5,'|',9,'|',13,'|',17,'|',21],min:1,max:21,step:1" /><br><br>
    <input type="checkbox" name="isPu" onchange="bj()" id="isPu">平铺
</form>
<button onclick="mySubmit()" class="btn btn-info">更改</button>

</body>
</html>

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
    <script src="${ctx}/static/bootStrap/js/bootstrap.min.js"></script>
    <style>
        body{
            width: 100%;
            height: 800px;
        }
    </style>
    <script>
        function mySubmit(){
            $('#ff').form('submit', {
                    url:"${ctx}/manager/image/uploadBj.do",
                onSubmit: function(){
                    if($("#name").val().trim()==''){
                        $.messager.alert('警告','请输入背景名称！','warning');
                        return false;
                    }
                    if($("#file0").val()==''){
                        $.messager.alert('警告','请选择背景图片！','warning');
                        return false;
                    }
            },
            success:function(data){
                var data = eval('(' + data + ')');
                if(data.code=="000000"){
                    clear2();
                        $.messager.alert('成功',data.message,'success');
                    }else{
                        $.messager.alert('错误',data.message,'error');
                    }

            }
        });

        }

        function clear2(){
            $("#name").val("");
            $("body").attr("style","padding:10px");
            $("#sort").slider("setValue",1);
        }

        function bj(){
            if($("#isPu").is(":checked") && $("#file0").val()!=''){
                var backStyle = $("body").attr("style");
                var start = backStyle.indexOf("(");
                var end = backStyle.indexOf(")");
                var objUrl = backStyle.substring(parseInt(start)+1,end);
                $("body").attr("style","background:url("+objUrl+");padding: 10px");
            }
            if(!$("#isPu").is(":checked") && $("#file0").val()!=''){
                var backStyle = $("body").attr("style");
                var start = backStyle.indexOf("(");
                var end = backStyle.indexOf(")");
                var objUrl = backStyle.substring(parseInt(start)+1,end);
                $("body").attr("style","background:url("+objUrl+") 0 50% no-repeat;background-size: 100% 100%;padding: 10px");
            }
        }
    </script>

<body style="padding: 10px">
    <form method="post" enctype="multipart/form-data" id="ff">
        <h4>背景名称:</h4>
        <input type="text" id="name" name="bjName" class="form-control" placeholder="请输入背景名称。。。" style="width: 300px">
        <h4>请选择背景图片文件：JPG/GIF/PNG</h4>
        <input type="file" name="uploadFile" id="file0"/>
        <script>
            $("#file0").change(function(){
                var objUrl = getObjectURL(this.files[0]);
                if (objUrl && $("#isPu").is(":checked")) {
                    $("body").attr("style","background:url('"+objUrl+"');padding: 10px");
                }else{
                    $("body").attr("style","background:url("+objUrl+") 0% 50% no-repeat;background-size: 100% 100%;padding: 10px");
                }
            }) ;
            //建立一個可存取到該file的url
            function getObjectURL(file) {
                var url = null ;
                if (window.createObjectURL!=undefined) { // basic
                    url = window.createObjectURL(file) ;
                } else if (window.URL!=undefined) { // mozilla(firefox)
                    url = window.URL.createObjectURL(file) ;
                } else if (window.webkitURL!=undefined) { // webkit or chrome
                    url = window.webkitURL.createObjectURL(file) ;
                }
                return url ;
            }
        </script>
        <h4>排序:</h4><br>
        <input class="easyui-slider" value="1"  style="width:600px;margin-left: 10px" name="sort" id="sort"
               data-options="showTip:true,rule:[1,'|',5,'|',9,'|',13,'|',17,'|',21],min:1,max:21,step:1" /><br><br>
        <input type="checkbox" name="isPu" checked="checked" onchange="bj()" id="isPu">平铺
    </form>
    <button onclick="mySubmit()" class="btn btn-info">添加</button>
</body>
</html>

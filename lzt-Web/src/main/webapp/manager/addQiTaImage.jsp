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
    <style>
       /* #showTu{
            width: 100%;
            height: 100px;
            !*border: 1px solid red;*!
        }
        #showTime{
            width: 300px;
            height: 300px;
          !*  border: 1px solid red;*!
        }*/
    </style>
    <script>
        function QtImage(){
            $('#Qt').form('submit', {
                url:"${ctx}/manager/image/uploadImage.do",
                onSubmit: function(){
                    if($("#Tu").val()==''){
                        $.messager.alert('警告','请选择图片！','warning');
                        return false;
                    }
                },
                success:function(data){
                    var data = eval('(' + data + ')');
                    if(data.code=="000000"){
                        $("#tuName").val("");
                        $("#tuType").val("1");
                        $("#showTu").attr("style","display:none");
                        $.messager.alert('成功',data.message,'success');
                    }else{
                        $.messager.alert('错误',data.message,'error');
                    }

                }
            })
        }
        function Mytype(){
            if($("#showTu").attr("style")!="display:none"){
                var tu = $("#tuType").val();
                var backStyle = $("#showTu").attr("style");
                var start = backStyle.indexOf("(");
                var end = backStyle.indexOf(")");
                var objUrl = backStyle.substring(parseInt(start)+1,end);
                if(tu=='4' || tu=='3'){
                    $("#showTu").attr("style","width:300px;height:300px;background: url("+objUrl+");background-size: 100% 100%;");
                }else{
                    $("#showTu").attr("style","width:100%;height:100px;background:url("+objUrl+") 0 50% no-repeat;background-size:100%");
                }
            }
        }
    </script>
</head>;
<body>
<div id="showTu" style="display:none">
</div>
<%--<div style="display:none" id="showTime">

</div>--%>
<form id="Qt" method="post" enctype="multipart/form-data">
    <div class="form-group">
        <label for="tuName">图名称:</label>
        <input class="form-control" id="tuName" type="text" name="tuName" style="width:400px;" placeholder="请输入图名称(可选)"/>
    </div>
    <div class="form-group">
        <label for="Tu">选择图片:</label>
        <input class="form-control" id="Tu" type="file" name="file" style="width:400px;"/>
        <script>
            $("#Tu").change(function(){
                var objUrl = getObjectURL(this.files[0]);
                var tu = $("#tuType").val();
                if (objUrl) {
                    if(tu=='4' || tu=='3'){
                        $("#showTu").attr("style","width:300px;height:300px;background:url('"+objUrl+"');background-size: 100% 100%;");
                    }else{
                        $("#showTu").attr("style","width:100%;height:100px;background:url('"+objUrl+"') 0 50% no-repeat;background-size:100%");
                    }

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
    </div>
    <div class="form-group">
        <label for="tuType">图片部位选择:</label><br>
        <select class="form-control" id="tuType" style="width: 400px" name="tuType" onchange="Mytype()">
            <option value="1">页头</option>
            <option value="2">主页正文头</option>
            <option value="3">主页侧边图</option>
            <option value="4">主页时间框</option>
            <option value="5">详情正文页头</option>
        </select>
    </div>
</form>
<button onclick="QtImage()" class="btn btn-info">添加</button>
</body>
</html>

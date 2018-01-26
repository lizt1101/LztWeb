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
        #showTu{
            width: 100%;
            height: 400px;
            /*border: 1px solid red;*/
        }
    </style>
    <script>
        function art(){
            $("#aid").removeAttr("style");
            $("#url").attr("style","display:none");
        }
        function url(){
            $("#url").removeAttr("style");
            $("#aid").attr("style","display:none");
        }
        function LbImage(){
            $('#Lb').form('submit', {
                url:"${ctx}/manager/image/uploadLunBo.do",
                onSubmit: function(){
                    if($("#lbTu").val()==''){
                        $.messager.alert('警告','请选择图片！','warning');
                        return false;
                    }
                    if($("input[name='lbType']:checked").val()==null){
                        $.messager.alert('警告','请选择轮播类型！','warning');
                        return false;
                    }else{
                        if($("input[name='lbType']:checked").val()=='0' & $("#lbAid").val().trim()==''){
                            $.messager.alert('警告','请输入文章编号！','warning');
                            return false;
                        }
                        if($("input[name='lbType']:checked").val()=='1' & $("#lbUrl").val().trim()==''){
                            $.messager.alert('警告','请输入链接地址！','warning');
                            return false;
                        }
                    }
                },
                success:function(data){
                    var data = eval('(' + data + ')');
                    if(data.code=="000000"){
                        clear1();
                        $.messager.alert('成功',data.message,'success');
                    }else{
                        $.messager.alert('错误',data.message,'error');
                    }

                }
            })
        }
        function clear1(){
            $("#lbName").val("");
            $("#lbType").attr("checked",false);
            $("#lbType1").attr("checked",false);
            $("#lbSort").slider("setValue",1);
            $("#lbAid").val("");
            $("#lbUrl").val("");
            $('#showTu').attr("style","display:none");
        }
        function yzwz(){
            var aid = $("#lbAid").val().trim();
            if(isNaN(parseInt(aid,10))){
                $.messager.alert('警告','请输入纯数字！','warning');
                return false;
            }else{
                $.post("${ctx}/manager/article/getDetail.do",{"id":aid},
                    function(result){
                        if(result==null){
                            $.messager.alert('错误',"该文章不存在",'error');
                            $("#lbAid").val("");
                            return false;
                        }else{
                            $.post("${ctx}/manager/image/isAddImage.do",{"id":aid},function(result){
                                if(result.code!="000000"){
                                    $.messager.alert('错误',result.message,'error');
                                    $("#lbAid").val("");
                                    return false;
                                }
                            },'json')
                        }
                },'json')
                return true;
            }
        }

    </script>
</head>
<body style="padding: 10px">
<div id="showTu" style="display: none">

</div>
    <form id="Lb" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="lbName">轮播图名称:</label>
            <input class="form-control" id="lbName" type="text" name="lbName" style="width:400px;" placeholder="请输入轮播图名称(可选)"/>
        </div>
        <div class="form-group">
            <label for="lbTu">选择图片:</label>
            <input class="form-control" id="lbTu" type="file" name="file" style="width:400px;"/>
            <script>
                $("#lbTu").change(function(){
                    var objUrl = getObjectURL(this.files[0]);
                    if (objUrl) {
                        $("#showTu").attr("style","background:url('"+objUrl+"') 50% 0 no-repeat;");
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
            <label for="lbType">类型选择:</label><br>
            <input type="radio" name="lbType" id="lbType" value="0" onclick="art()">文章&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="lbType" id="lbType1" value="1" onclick="url()">链接
        </div>
        <div class="form-group" id="aid" style="display: none">
            <label for="lbAid">文章编号:</label>
            <input class="form-control" id="lbAid" type="text" name="lbAid" style="width:400px;" placeholder="请输入有效的文章编号" onblur="yzwz()"/>
        </div>
        <div class="form-group" id="url" style="display: none">
            <label for="lbUrl">链接地址:</label>
            <input class="form-control" id="lbUrl" type="text" name="lbUrl" style="width:400px;" placeholder="请输入有效的链接地址"/>
        </div>
        <div class="form-group">
            <label for="lbSort">轮播次序:</label>
            <input id="lbSort" class="easyui-slider" value="1"  style="width:400px;margin-left: 10px" name="lbSort"
                   data-options="showTip:true,rule:[1,'|',3,'|',5,'|',7,'|',9],min:1,max:9,step:1" />
        </div>
    </form>
<button onclick="LbImage()" class="btn btn-info">添加</button>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/icon.css">
    <script type="text/javascript" src="${ctx}/static/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/easyui/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" href="${ctx}/static/bootStrap/css/bootstrap.min.css">
    <script src="${ctx}/static/bootStrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/static/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/static/ueditor/ueditor.all.min.js"> </script>

    <script src="${ctx}/static/uploadImage/js/amazeui.min.js" charset="utf-8"></script>
    <script src="${ctx}/static/uploadImage/js/cropper.min.js" charset="utf-8"></script>
    <script src="${ctx}/static/uploadImage/js/custom_up_img.js" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/uploadImage/css/font-awesome.4.6.0.css">
    <link rel="stylesheet" href="${ctx}/static/uploadImage/css/amazeui.min.css">
    <link rel="stylesheet" href="${ctx}/static/uploadImage/css/amazeui.cropper.css">
    <link rel="stylesheet" href="${ctx}/static/uploadImage/css/custom_up_img.css">
    <style type="text/css">
        .up-img-cover {width: 100px;height: 100px;}
        .up-img-cover img{width: 100%;}
    </style>

    <style type="text/css">
        table tr{
            height: 60px;
        }
        .info,.info1{
            font-size: 15px;
        }
        textarea{
            rows:3;
            cols:20;
            margin-top: 30px;
        }
    </style>
    <script>
        function setImg(imgName){
            $("#up-img-touch img").attr("src","http://172.31.61.25:9091/lztWeb/${CurrentUser.username}/"+imgName);
        }

       function updateInfo(){
           $(".show input").removeAttr("readonly");
           $(".show textarea").removeAttr("readonly");
           $("#save").attr("style","display:inline");
           $("#cancel").attr("style","display:inline");
           $("#update").attr("style","display:none");
        }
        function cancelInfo(){
            $(".show input").attr("readonly","readonly");
            $(".show textarea").attr("readonly","readonly");
            $("#save").attr("style","display:none");
            $("#cancel").attr("style","display:none");
            $("#update").attr("style","display:inline");
        }
        function saveInfo(){
            var nickName = $("#nickname").val();
            var description = $("#description").val();
            $.post("${ctx}/manager/user/updateInfo.do",{"nickname":nickName,"description":description},function(result){
                if(result.code==000000){
                    cancelInfo();
                    $.messager.alert('我的消息','修改成功！','info');
                }else{
                    $.messager.alert('我的消息','修改失败！','error');
                }
            },"json");
        }
    </script>
</head>
<body>
<%--用户名、头像、昵称、创建时间、完善时间、个人说明、--%>
    <div style="margin: 80px auto;width: 40%">
       <table style="width: 100%">
           <tr>
               <td style="font-size: 15px;font-weight: bold;text-align: center;height: 120px">头像:</td>
               <%--  <td class="info">${CurrentUser.headImg}</td>--%>
               <td>
                   <div class="up-img-cover"  id="up-img-touch" >
                       <img class="am-circle" alt="点击图片上传" src="http://172.31.61.25:9091/lztWeb/${CurrentUser.headImg}" >
                   </div>
                  <%-- <div><a style="text-align: center; display: block;"  id="pic"></a></div>--%>
                   <!--图片上传框-->
                   <div class="am-modal am-modal-no-btn up-frame-bj " tabindex="-1" id="doc-modal-1">
                       <div class="am-modal-dialog up-frame-parent up-frame-radius">
                           <div class="am-modal-hd up-frame-header">
                               <label>修改头像</label>
                               <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
                           </div>
                           <div class="am-modal-bd  up-frame-body">
                               <div class="am-g am-fl">
                                   <div class="am-form-group am-form-file">
                                       <div class="am-fl">
                                           <button type="button" class="am-btn am-btn-default am-btn-sm">
                                               <i class="am-icon-cloud-upload"></i> 选择要上传的文件</button>
                                       </div>
                                       <input type="file" id="inputImage">
                                   </div>
                               </div>
                               <div class="am-g am-fl" >
                                   <div class="up-pre-before up-frame-radius">
                                       <img alt="" src="" id="image" >
                                   </div>
                                   <div class="up-pre-after up-frame-radius">
                                   </div>
                               </div>
                               <div class="am-g am-fl">
                                   <div class="up-control-btns">
                                       <span class="am-icon-rotate-left"  onclick="rotateimgleft()"></span>
                                       <span class="am-icon-rotate-right" onclick="rotateimgright()"></span>
                                       <span class="am-icon-check" id="up-btn-ok" url="admin/user/upload.action"></span>
                                   </div>
                               </div>

                           </div>
                       </div>
                   </div>
                   <!--加载框-->
                   <div class="am-modal am-modal-loading am-modal-no-btn" tabindex="-1" id="my-modal-loading">
                       <div class="am-modal-dialog">
                           <div class="am-modal-hd">正在上传...</div>
                           <div class="am-modal-bd">
                               <span class="am-icon-spinner am-icon-spin"></span>
                           </div>
                       </div>
                   </div>

                   <!--警告框-->
                   <div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
                       <div class="am-modal-dialog">
                           <div class="am-modal-hd">信息</div>
                           <div class="am-modal-bd"  id="alert_content">
                               成功了
                           </div>
                           <div class="am-modal-footer">
                               <span class="am-modal-btn">确定</span>
                           </div>
                       </div>
                   </div>

               </td>
           </tr>
           <tr>
               <td style="font-size: 15px;font-weight: bold;text-align: center">用户名:</td>
               <td class="info">${CurrentUser.username}</td>
           </tr>
           <tr>
               <td style="font-size: 15px;font-weight: bold;text-align: center">昵称:</td>
               <td  class="show">
                   <input readonly="readonly" class="form-control" id="nickname" type="text" name="nickname" value="${CurrentUser.nickname}"/>
               </td>
           </tr>
           <tr>
               <td style="font-size: 15px;font-weight: bold;text-align: center;height: 300px">签名:</td>
               <td style="display: none" class="show" style="text-align: center">
                    <textarea readonly="readonly" style="height: 300px" class="form-control" id="description" type="text" name="description">${CurrentUser.description}</textarea>
                </td>
           </tr>
           <tr>
               <td style="font-size: 15px;font-weight: bold;text-align: center">创建时间:</td>
               <td class="info">
                   <span><fmt:formatDate value="${CurrentUser.createTime}" pattern="yyyy年MM月dd日 HH:mm:ss"/></span>
               </td>
           </tr>
           <tr>
               <td style="font-size: 15px;font-weight: bold;text-align: center">完善时间:</td>
               <td class="info">
                   <span id="updateDate"><fmt:formatDate value="${CurrentUser.updateTime}" pattern="yyyy年MM月dd日 HH:mm:ss"/></span>
               </td>
           </tr>
           <tr>
               <td style="font-size: 15px;font-weight: bold;text-align: center">
               </td>
               <td class="info" style="text-align: right">
                   <button type="button" class="btn btn-info" id="update" onclick="updateInfo()">编辑</button>
                   <button style="display: none" type="button" class="btn btn-info" id="save" onclick="saveInfo()">保存</button>
                   <button style="display: none" type="button" class="btn btn-info" id="cancel" onclick="cancelInfo()">取消</button>
               </td>
           </tr>
       </table>
    </div>
</div>
</body>
</html>

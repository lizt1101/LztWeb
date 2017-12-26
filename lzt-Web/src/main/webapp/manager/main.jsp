<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/icon.css">
    <script type="text/javascript" src="${ctx}/static/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/easyui/locale/easyui-lang-zh_CN.js"></script>

    <title>管理</title>
    <style>
        body{
            padding: 0;
            margin: 0;
        }
        a span{
            float: left;
        }
        #saletyOff{
            color: black;
        }
        #saletyOff:hover{
            color: red;
        }
    </style>
<script type="text/javascript">

	function openTab(text,url,icon){
		if($("#tabs").tabs("exists",text)){
			$("#tabs").tabs("select",text);
		}else{
			var content = "<iframe name='child' scrolling='auto' frameborder='0' style='width:100%;height:100%' src='"+url+"'></iframe>";
			$("#tabs").tabs('add',{
				title:text,
				iconCls:icon,
				closable:true,
				content:content
            })
         /*   $("#tabs").tabs({
                onBeforeClose: function(title){
                    return confirm('您确认想要关闭 ' + title);
                }
            })*/
		}
	}
	function closeTab(text){
        if($("#tabs").tabs("exists",text)){
            $("#tabs").tabs("close",text);
        }
    }

    function logout(){
        $.messager.confirm("系统提示","您确定要退出系统吗？",function(r){
            if(r){
                window.location.href='${ctx}/Login/logout.do';
            }
        });
    }

    var regex2 = /^[A-Za-z0-9.@!*&$#_]{6,13}$/;
    function checkPassword(password,mesg){
        if(password.length>5){
            if(password.match(regex2)==null){
                $("#warmError").attr("style","display:block;color:red");
                $("#warmError").text(mesg+"密码符号错误!");
                return false;
            }
        }else{
            $("#warmError").attr("style","display:block;color:red");
            $("#warmError").text(mesg+"密码长度不能少于6位字符!");
            return false;
        }
        return true;
    }

  function updatePassword(){
        $("#updatePassword").dialog({
            title:'修改密码',
            width:400,
            height:200,
            close:false,
            modal:true,
            buttons:[{
                text:'确定',
                iconCls:'icon-ok',
                handler:function(){
                    var oldPass = $("#oldPass").val();
                    var txtNewPass = $("#txtNewPass").val();
                    var txtRePass = $("#txtRePass").val();
                    var warmError = $("#warmError");
                    if(oldPass.trim()=='' || txtNewPass.trim()=='' || txtRePass.trim()==''){
                        warmError.attr("style","display:block;color:red");
                        warmError.text("请输入密码!");
                        return;
                    }else{
                       if(checkPassword(oldPass,"原")){
                           if(checkPassword(txtNewPass,"新")){
                               if( checkPassword(txtRePass,"确认")){
                                   if(rePassword()){
                                       warmError.attr("style","display:none;color:red");
                                       $.post("${ctx}/Login/updatePassword.do",{"oldPassword":oldPass,"newPassword":txtNewPass},function(result){
                                           if(result.code=="000000"){
                                               $.messager.alert("系统提示","修改成功,请重新登陆!",'info',function(){
                                                   window.location.href='${ctx}/Login/logout.do';
                                               });
                                               $("#oldPass").val("");
                                               $("#txtNewPass").val("");
                                               $("#txtRePass").val("");
                                               $("#updatePassword").dialog("close");
                                           }else{
                                               warmError.attr("style","display:block;color:red");
                                               warmError.text(result.message);
                                           }
                                       },'json');
                                   }
                               }
                           }
                       }
                       return;
                    }
                }
            },{
                text:'取消',
                iconCls:'icon-no',
                handler:function(){
                    $("#updatePassword").dialog("close");
                }
            }]
        })
    }
    function rePassword(){
        var txtNewPass = $("#txtNewPass").val();
        var txtRePass = $("#txtRePass").val();
        if(txtNewPass.trim() != txtRePass.trim()){
            $("#warmError").attr("style","display:block;color:red");
            $("#warmError").text("新密码不一致");
            return false;
        }
        $("#warmError").attr("style","display:none");
        return true;
    }
</script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:50px">
    <div style="width: 30%;height: 40px;float: right;line-height: 40px">
        <span style="font-size: 17px;float: right">您好,${CurrentUser.username}&nbsp&nbsp&nbsp&nbsp&nbsp
            <a id="saletyOff" href="javascript:logout()" style="cursor: hand;text-decoration: none;">安全退出</a>
        </span>
    </div>
</div>
<div data-options="region:'south',split:true" style="height:50px;"></div>
<div data-options="region:'west',title:'菜单',split:true" style="width:150px;">
    <div id="ea" class="easyui-accordion" fit="true">
        <div title="文章管理" data-options="iconCls:'icon-home'" style="overflow:auto;padding:10px;">
            <a href="javascript:openTab('发表文章','writeArticle.jsp','icon-writeblog')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-writeblog'" style="width: 150px">发表文章</a>
            <a href="javascript:openTab('文章列表','articleList.jsp','icon-bkgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-bkgl'" style="width: 150px">文章列表</a>
            <a href="javascript:openTab('文章分类','typeList.jsp','icon-bklb')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-bklb'" style="width: 150px">文章分类</a>
        </div>
        <div title="用户管理" data-options="iconCls:'icon-yh'" style="padding:10px;">
<shiro:hasRole name="superManager">
            <a href="javascript:openTab('管理员列表','userList.jsp','icon-grxx')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-grxx'" style="width: 150px">用户列表</a>
</shiro:hasRole>
    <a href="javascript:openTab('我的信息','myInfo.jsp','icon-grxx')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-grxx'" style="width: 150px">我的信息</a>
        </div>
        <div title="图片管理" data-options="iconCls:'icon-imggm'" style="padding:10px;">
            <a href="javascript:openTab('背景管理','imageList.jsp','icon-imglist')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-imglist'" style="width: 150px">背景管理</a>
            <a href="javascript:openTab('添加背景','addBjImage.jsp','icon-imgadd')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-imgadd'" style="width: 150px">添加背景</a>
        </div>
        <div title="系统管理" data-options="iconCls:'icon-item'" style="padding:10px;">
            <a href="javascript:updatePassword()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-modifyPassword'" style="width: 150px">修改密码</a>
        </div>
    </div>
</div>
<div data-options="region:'center'" style="padding:5px;background:#eee;">
    <div id="tabs" class="easyui-tabs" fit="true" border="false">
        <div title="我的主页" style="padding:20px;overflow:hidden; color:red; " data-options="iconCls:'icon-home'">
        </div>
    </div>
</div>
<div id="updatePassword">
    <table cellpadding=3>
        <tr>
            <td>原密码：</td>
            <td><input id="oldPass" type="Password" style="height: 20px"/></td>
        </tr>
        <tr>
            <td>新密码：</td>
            <td><input id="txtNewPass" type="Password" style="height: 20px"/></td>
        </tr>
        <tr>
            <td>确认密码：</td>
            <td><input id="txtRePass" type="Password" style="height: 20px" onblur="rePassword()"/></td>
        </tr>
    </table>
    <span id="warmError" style="display:none;color:red"></span>
</div>
</body>
</html>
























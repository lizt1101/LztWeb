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
    </style>
<script type="text/javascript">

   /* $(document).ready(function(){
        $("#tabs").tabs({
            onSelect:function(title){
                if(title=="文章列表"){
                    child.window.shuaxin();
                }
            }
        });
    })*/

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

</script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:100px;"></div>
<div data-options="region:'south',split:true" style="height:50px;"></div>
<div data-options="region:'west',title:'菜单',split:true" style="width:150px;">
    <div id="ea" class="easyui-accordion" fit="true">
        <div title="文章管理" data-options="iconCls:'icon-home'" style="overflow:auto;padding:10px;">
            <a href="javascript:openTab('发表文章','writeArticle.jsp','icon-writeblog')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-writeblog'" style="width: 150px">发表文章</a>
            <a href="javascript:openTab('文章列表','articleList.jsp','icon-bkgl')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-bkgl'" style="width: 150px">文章列表</a>
            <a href="javascript:openTab('文章分类','typeList.jsp','icon-bklb')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-bklb'" style="width: 150px">文章分类</a>
        </div>
        <div title="用户管理" data-options="iconCls:'icon-yh'" style="padding:10px;">
            <a href="javascript:openTab('用户列表','writeArticle.jsp','icon-grxx')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-grxx'" style="width: 150px">用户列表</a>
        </div>
        <div title="系统管理" data-options="iconCls:'icon-item'" style="padding:10px;">
            <a href="javascript:openTab('修改密码','writeArticle.jsp','icon-modifyPassword')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-modifyPassword'" style="width: 150px">修改密码</a>
        </div>
    </div>
</div>
<div data-options="region:'center'" style="padding:5px;background:#eee;">
    <div id="tabs" class="easyui-tabs" fit="true" border="false">
        <div title="我的主页" style="padding:20px;overflow:hidden; color:red; " data-options="iconCls:'icon-home'">

        </div>
    </div>
</div>
</body>
</html>
























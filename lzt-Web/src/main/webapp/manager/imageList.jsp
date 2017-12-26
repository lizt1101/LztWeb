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
<head>
    <title>背景管理</title>
</head>
<body>
<table id="dgType" class="easyui-datagrid" fit="true" fitColumns="true"
       singleSelect="true"  pagination="true" rownumbers="true" style="width: 100%" toolbar="#tb" url="">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'id'" width="5" align="center" halign="center">编号</th>
        <th data-options="field:'bjName'" width="10" align="center" halign="center">背景名称</th>
        <th data-options="field:'bjUrl'" width="20" align="center" halign="center">背景图</th>
        <th data-options="field:'createDate'" width="10" align="center" halign="center">创建时间</th>
        <th data-options="field:'updateDate'" width="10" align="center" halign="center">更新时间</th>
        <th data-options="field:'sort'" width="5" align="center" halign="center">排序</th>
    </tr>
    </thead>
</table>
</body>
</html>

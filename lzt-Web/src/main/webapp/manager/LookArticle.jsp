<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>
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
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${ctx}/static/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript">
       $(document).ready(function(){
            $.post("${ctx}/type/typeList.do",
                function(result){
                    var select = $("#type");
                    var optionHtml='';
                    for (x in result.data.typeList)
                    {
                        optionHtml += "<option value='"+result.data.typeList[x].id+"'>"+result.data.typeList[x].typeName+"</option>";
                    }
                    select.append(optionHtml);
                },'json');
        });

       function closeTab(id){
           window.parent.closeTab("查看文章"+id);
       }
    </script>
</head>
<body>
    <input type="hidden" id="aid" name="Aid">
    <div class="form-group">
        <label for="title">标题:</label>
        <input class="form-control" id="title" type="text" name="title" style="width:400px;"/>
    </div>
    <div class="form-group">
        <label for="type">分类:</label>
        <select class="form-control" id="type" name="typeId" style="width:200px;">
            <option value="0">请选择类型</option>
        </select>
    </div>
   <div class="form-group">
        <label for="editor">文章正文:</label>
       <script id="editor" name="content" type="text/plain" style="width:1024px;height:500px;"></script>
    </div>
    <div class="form-group">
    <label for="sign">关键词(以逗号分隔):</label>
     <input class="form-control" id="sign" type="text" name="sign" style="width:400px;"/>
    </div>
    <button type="button" class="btn btn-info" onclick="publish()">修改</button>
            <%--<button type="button" class="btn btn-info" onclick="closeTab(${param.id})">关闭</button> --%>
</body>
<script type="text/javascript">
   /* 实例化编辑器*/
    var ue = UE.getEditor('editor');
    var id = ${param.id};
           if(id != null){
               ue.addListener("ready",function(){
                   UE.ajax.request("${ctx}/manager/article/getDetail.do",
                       {
                           method:"post",
                           async : false,
                           data:{"id":id},
                           onsuccess:function(result){
                               result = eval("(" + result.responseText + ")");
                               $("#aid").val(id);
                               $("#title").val(result.title);
                               $("#sign").val(result.sign);
                               $("#type").val(result.typeId);
                              UE.getEditor('editor').setContent(result.content);
                           }
                       }
                   )
               });
           }
 </script>

</html>









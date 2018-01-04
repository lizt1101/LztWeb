<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	function down(a){
		alert(a);
		$.ajax({
			type : "POST",
			async : false,
			dataType : "JSON",
			cache : false,
			url : "${pageContext.request.contextPath}/down.do",
			data :{url:a},
			success : function(data) {
				if (data.flag) {
					
				} else {
					alert(data.msg);
				}
			}
		});
	}
</script>
</head>

<body>
<h1>hello</h1>
	<%--<form action="upload.do"  method="post" enctype="multipart/form-data">
		<input type="file" name="uploadFile">
		<input type="submit" value="上传">
	</form>--%>
<%--http://172.31.61.19:9091/lztWeb/1513839273878lzt.jpg--%>
<img src="http://172.31.61.19:9091/lztWeb/admin1/1513839273878lzt.jpg"/>
	<img  src="http://172.31.61.19:9091/java/1.jpg" style="width:200px;height:200px"/>
	<a href="#" onclick="down('1.jpg')">下载</a>
	
	<form action="serach.do"  method="post" >
		<input type="text" name="keyword"/>
		<input type="submit" value="搜索">
	</form>
</body>
</html>





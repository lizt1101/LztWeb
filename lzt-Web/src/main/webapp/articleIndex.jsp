<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>文章主页</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootStrap/css/bootstrap.min.css">
<script
	src="${pageContext.request.contextPath}/static/bootStrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div>
		<c:forEach var="art" items="${artList}">
			<li style="margin-top: 30px"><span>${art.title }</span><br /> <span>${art.content }</span><br />
				<span>${art.createTime }</span></li>
		</c:forEach>
	</div>
	<div>
		<ul class="pagination pagination-lg">
		${pageCode }
		</ul>
	</div>

</body>
</html>
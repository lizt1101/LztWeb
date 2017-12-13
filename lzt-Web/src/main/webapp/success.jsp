<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
${article.content}
	<%--<div>
		<c:forEach var="art" items="${articleResultList}">
			<li>
				<span>${art.my_title }</span>
				<span>${art.my_content }</span>
				<span>${art.create_time }</span>
			</li>
		</c:forEach>
	</div>--%>
</body>
</html>
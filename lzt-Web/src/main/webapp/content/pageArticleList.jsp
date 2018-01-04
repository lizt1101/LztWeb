<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/taglib.jsp"%>
<c:forEach var="art" items="${artList}">
    <div style="width: 100%;height: 200px;">
        <div style="width: 73%;height: 100%;float: left;">
            <div style="width: 100%;height: 85%">
                <span style="font-weight: bold;font-size: 18px"><a target="view_window" style="text-decoration: none" href="${ctx}/article/getArtDetails/${art.id}.do">${art.title}</a></span><br><br>
                <span style="font-size: 14px"><strong>作者:</strong>${art.userName}</span><br><br>
                <span><strong>摘要:</strong>${art.content}</span>
            </div>
            <div style="width: 100%;height: 15%;">
                <span><fmt:formatDate value="${art.createTime}" pattern="yyyy年MM月dd日 HH:mm:ss"/></span>
                <span style="float: right"><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/eye.png"> 阅读(${art.readCount})&nbsp&nbsp
                    <img src="${ctx}/static/bootStrap/MyImage/xiaoImg/user_edit.png"> 评论(1)</span>
            </div>
        </div>
        <c:if test="${art.imgUrl!=null}">
            <div style="width: 27%;height: 100%;float: right;padding: 15px">
                <img src="${art.imgUrl}" style="width: 100%;height: 100%"/>
            </div>
        </c:if>
    </div>
    <div class="xuxian"></div>
</c:forEach>

<div style="margin-top: 30px;margin-bottom: 20px">
    <nav style="margin: 0 auto">
        <ul class="pagination pagination-lg">
            ${pageBean }
        </ul>
    </nav>
</div>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/taglib.jsp"%>
<c:forEach var="art" items="${artList}">
    <div style="width: 100%;height: 220px;">
        <div style="width: 100%;height: 85%;">
            <span style="font-weight: bold;font-size: 18px"><a target="view_window" style="text-decoration: none" class="aText" href="${ctx}/article/getArtDetails/${art.id}.do">${art.my_title}</a></span><br>
            <c:if test="${art.sign!=null}">
                <span><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/key.png">&nbsp;&nbsp;${art.sign}</span><br>
            </c:if>
            <c:if test="${art.sign==null}">
                <span><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/key.png">&nbsp; 无</span><br>
            </c:if>
            <span><strong>摘要</strong><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/tag_blue.png">&nbsp;&nbsp;&nbsp;
                    ${art.my_content}
            </span>
        </div>
        <div style="width: 100%;height: 15%;margin-bottom: 10px">
            <span style="float: right"><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/date.png">&nbsp;发表时间:${art.create_time}</span>
        </div>
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
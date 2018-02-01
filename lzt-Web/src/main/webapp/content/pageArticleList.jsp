<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/taglib.jsp"%>
<c:forEach var="art" items="${artList}">
    <div style="width: 100%;height: 250px;">
        <c:if test="${art.imgUrl!=null}">
            <div style="width: 73%;height: 100%;float: left;">
                <div style="width: 100%;height: 85%">
                    <span style="font-weight: bold;font-size: 18px;">
                        <a target="view_window" class="aText" href="${ctx}/article/getArtDetails/${art.id}.do">${art.title}</a>
                    </span><br><br>
                    <span style="font-size: 14px"><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/user_suit.png">&nbsp;&nbsp;${art.nickName}</span><br>
                    <span><strong>摘要</strong><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/tag_blue.png">&nbsp;&nbsp;&nbsp;${art.content}</span>
                </div>
                <div style="width: 100%;height: 15%;">
                    <span><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/date.png">&nbsp;<fmt:formatDate value="${art.createTime}" pattern="yyyy年MM月dd日 HH:mm:ss"/></span>
                    <span style="float: right"><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/66-eye.png" style="width:24px;height: 24px"> 阅读(${art.readCount})&nbsp&nbsp
                    <img src="${ctx}/static/bootStrap/MyImage/xiaoImg/user_edit.png"> 评论(${art.pingCount})</span>
                </div>
            </div>
            <div style="width: 27%;height: 90%;float: right;padding: 10px">
                <img src="${art.imgUrl}" style="width: 100%;height: 100%"/>
            </div>
        </c:if>
       <c:if test="${art.imgUrl==null}">
           <div style="width: 100%;height: 100%;float: left;">
               <div style="width: 100%;height: 85%">
                   <span style="font-weight: bold;font-size: 18px"><a target="view_window" class="aText" href="${ctx}/article/getArtDetails/${art.id}.do">${art.title}</a></span><br><br>
                   <span style="font-size: 14px"><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/user_suit.png">&nbsp;&nbsp;${art.nickName}</span><br>
                   <span><strong>摘要</strong><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/tag_blue.png">&nbsp;&nbsp;&nbsp;${art.content}</span>
               </div>
               <div style="width: 100%;height: 15%;">
                   <span><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/date.png">&nbsp;<fmt:formatDate value="${art.createTime}" pattern="yyyy年MM月dd日 HH:mm:ss"/></span>
                   <span style="float: right"><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/66-eye.png" style="width:24px;height: 24px"> 阅读(${art.readCount})&nbsp&nbsp
                    <img src="${ctx}/static/bootStrap/MyImage/xiaoImg/user_edit.png"> 评论(${art.pingCount})</span>
               </div>
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
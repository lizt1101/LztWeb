<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <script src="${ctx}/static/bootStrap/js/jquery.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/bootStrap/js/jquery.cookie.js" type="text/javascript"></script>
    <script src="${ctx}/static/bootStrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${ctx}/static/bootStrap/css/bootstrap.min.css">
    <title></title>
    <style>
        *{
            margin: 0;
            padding: 0;
        }
        .box{
            position:relative;
            border:2px solid #ccc;
        }
        .box::before{
            content:attr(title);
            position:absolute;
            left:50%;
            transform:translateX(-50%);
            -webkit-transform:translate(-50%,-50%);
            padding:0 10px;
            background-color:#fff;
        }
        #Myhead{
            width:100%;
            height: 70px;
            /* border: 1px solid #4c4c4c;*/
            position: fixed;
            z-index: 99;
            /*text-align:center;*/
            background-color: #000;
            left:0; top:0;
        }
        .head-li{
            display:inline;
        }
        .head-li li{
            display: block;
            float: right;
            margin-right: 30px;
            margin-top: 30px;
        }
        .head-li li a{
            text-decoration: none;
            font-size: 18px;
            color: white;
        }
        .head-li li:hover{
            position: relative;
            left: -1px;
            top: -1px;
        }
        #ul li:HOVER {
            position: relative;
            left: -2px;
            top: -2px;
        }
        #ul li{
            background-color:#fff;
            border:3px solid #fff;
            padding:10px;
            margin:10px;
            float: left;
        }
        #ul{
            /* /!* border:1px solid red;
             width: 780px;
             margin: 20px auto;
             /!*  去掉列表符号*!/*/
            list-style-type: none;
            width: 100%;height: 500px;
        }
        p,span{
            font-family: '微软雅黑', '文泉驿正黑', '宋体';
            line-height: 30px;
        }
        #plUl li{
            border: 1px solid #1b6d85;
            width: 100%;
            height: 80px;
            list-style-type: none;
        }
    </style>
    <style>
        #Myhead{
            width:100%;
            height: 70px;
            /* border: 1px solid #4c4c4c;*/
            position: fixed;
            z-index: 99;
            /*text-align:center;*/
            background-color: #000;
        }
        .head-li{
            display:inline;
        }
        .head-li li{
            display: block;
            float: right;
            margin-right: 30px;
            margin-top: 30px;
        }
        .head-li li a{
            text-decoration: none;
            font-size: 18px;
            color: white;
        }
        .head-li li:hover{
            position: relative;
            left: -1px;
            top: -1px;
        }
        #ul li:HOVER {
            position: relative;
            left: -2px;
            top: -2px;
        }
        #ul li{
            background-color:#fff;
            border:3px solid #fff;
            padding:10px;
            margin:10px;
            float: left;
        }
        #ul{
            /* /!* border:1px solid red;
             width: 780px;
             margin: 20px auto;
             /!*  去掉列表符号*!/*/
            list-style-type: none;
            width: 100%;height: 500px;
        }
        #ul li p{
            /* border:1px solid red;*/
            text-align: center;
            font-family:"微软雅黑","文泉驿正黑","黑体";
            line-height:2em;
            width:120px;
            height:30px;
            overflow: auto;

        }
    </style>
    <script>
        $(document).ready(function(){
            var bj = $.cookie('bj');
            if(bj==null){
                var li = $("#ul li:eq("+1+")");
                li.attr("style","border:3px solid #ccc");
                $("body").attr("background","${ctx}/static/bootStrap/MyImage/bj/bj2.jpg");
            }else{
                var li = $("#ul li:eq("+(bj-1)+")");
                li.attr("style","border:3px solid #ccc");
                $("body").attr("background","${ctx}/static/bootStrap/MyImage/bj/bj"+bj+".jpg");
            }
        })
        window.onscroll= function(){
            //变量t是滚动条滚动时，距离顶部的距离
            if(t >= 70 && t<470){
                $("#Myhead").attr("style","background-color:rgba(255,255,255,0.3);");
                $(".head-li li a").attr("style","color:#fff");
                $("#title").attr("src","${ctx}/static/bootStrap/MyImage/title.png")
            }else if(t>470){
                $("#Myhead").attr("style","background-color:rgba(255,255,255,0.8);");
                $(".head-li li a").attr("style","color:#000");
                $("#title").attr("src","${ctx}/static/bootStrap/MyImage/title1.png")
            }else{          //恢复正常
                $("#Myhead").attr("style","background-color:#000000");
                $(".head-li li a").attr("style","color:#fff");
                $("#title").attr("src","${ctx}/static/bootStrap/MyImage/title.png")
            }
        }
        function createcookie(img){
            $.cookie('bj',img,{ expires: 1,path:'/',secure:false,raw:false});
            $("body").attr("background","${ctx}/static/bootStrap/MyImage/bj/bj"+img+".jpg");
        }
        function xz(a){
            var allLi = $("#ul li");
            allLi.attr("style","");
            var li = $("#ul li:eq("+(a-1)+")");
            li.attr("style","border:3px solid #ccc");
            createcookie(a);
        }
    </script>
</head>
<body>
<!--背景动画start-->
<script src="js/canvas-nest.min.js"></script>
<!--背景动画end-->
<jsp:include page="/common/head.jsp"></jsp:include>

<div class="container" style="margin-top: 100px">
    <div class="row">
        <div class="col-md-1 col-xs-1" style=""></div>
        <div class="col-md-10 col-xs-10" id="body_content" style="background-color: rgba(255,255,255,0.6);box-shadow: 0px 0px 15px #000">
            <div style="width: 100%;height: 80px">
                <div style="width: 90%;height: 100%;margin: 0 auto;line-height: 80px;text-align: center;font-size: 25px">
                    <span><strong>${article.title}</strong></span>
                </div>
            </div>
            <div style="width: 100%;height: 30px">
                <div style="width: 50%;height: 100%;margin: 0 auto;line-height: 30px;text-align: center;font-size: 15px">
                    <span>分类&nbsp:&nbsp&nbsp${typeName}</span>
                </div>
            </div>
            <div style="width: 90%;height: 50px;margin: 0 auto">
                <div style="width: 30%;height: 100%;margin: 0 auto;line-height: 50px;text-align: left;font-size: 16px;float: left">
                    <!-- <div class="bdsharebuttonbox">
                         <a href="#" class="bds_more" data-cmd="more"></a>
                         <a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
                         <a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
                         <a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a>
                         <a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网">
                         </a><a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
                     </div>
                     <script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},
                         "share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='' +
                             'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>-->
                </div>
                <div style="width: 40%;height: 100%;margin: 0 auto;line-height: 50px;text-align: center;font-size: 16px;float: left">
                    <span>发表时间&nbsp:&nbsp&nbsp<fmt:formatDate value="${article.createTime}" pattern="yyyy年MM月dd日 HH:mm:ss"></fmt:formatDate></span>
                </div>
                <div style="width: 30%;height: 100%;margin: 0 auto;line-height: 50px;text-align: right;font-size: 16px;float: left">
                    <img src="${ctx}/static/bootStrap/MyImage/xiaoImg/yd.png" style="width: 20px;height: 20px">&nbsp<span style="font-size: 12px">${reads}人阅读&nbsp&nbsp</span>
                </div>
            </div>
            <div style="width: 90%;margin: 20px auto; font-size: 16px;">
               ${article.content}
            </div>
            <div style="width: 90%;height: 30px;margin: 0 auto">
                <span><strong>关键字:</strong>
                    <c:forEach var="key" items="${keys}">
                        ${key}&nbsp;&nbsp;
                    </c:forEach>
                </span>
            </div>
            <hr style="height:1px;border:none;border-top:1px solid #ccc;width: 90%;margin: 5px auto" />
            <div style="width: 90%;height: 50px;margin: 0 auto">
               ${pageBean}
            </div>
            <div style="width: 90%;height: 100px"></div>
            <!-- <div style="width: 90%;margin: 20px auto">
                 <div class="box" title="评论列表">
                    <ul id="plUl">
                        <li>123</li>
                        <li>123</li>
                        <li>123</li>
                        <li>123</li>
                        <li>123</li>
                    </ul>
                 </div>
             </div>-->
        </div>
        <div class="col-md-1 col-xs-1"></div>
    </div>
</div>

</body>
</html>















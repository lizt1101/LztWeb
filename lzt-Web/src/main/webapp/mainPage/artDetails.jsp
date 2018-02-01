<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="${ctx}/lztlogo1.ico" type="image/x-icon" />
    <script src="${ctx}/static/bootStrap/js/jquery.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/bootStrap/js/jquery.cookie.js" type="text/javascript"></script>
    <script src="${ctx}/static/bootStrap/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/alertKuang/js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="${ctx}/static/bootStrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/alertKuang/css/xcConfirm.css"/>

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
       /* p,span{
            font-family: '微软雅黑', '文泉驿正黑', '宋体';
            line-height: 30px;
        }*/
        .xuxian{
            width:90%;
            height:0;
            border-bottom:#000000 1px dashed;
            margin-top: 10px;
            float: right;
        }
    </style>
    <script>
        window.onscroll= function(){
            //变量t是滚动条滚动时，距离顶部的距离
            var t = document.documentElement.scrollTop||document.body.scrollTop;
            if(t >= 70){
                $("#Myhead").attr("style","background-color:rgba(255,255,255,0.3);");
               /* $(".head-li li a").attr("style","color:#fff");
                $("#title").attr("src","${ctx}/static/bootStrap/MyImage/title.png");*/
            }else{          //恢复正常
                $("#Myhead").attr("style","background-color: rgba(255,255,255,0.4);");
            /*    $(".head-li li a").attr("style","color:#000");
                $("#title").attr("src","${ctx}/static/bootStrap/MyImage/title1.png");*/
            }
        }
        function tijiaoComment(){
            var commentext = $("#commentext").val();
            var code = $("#c_code").val();
            if(commentext.trim()==''){
                var txt=  "请输入评论！！！";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.warning);
                $(".c_code").attr("src","${ctx}/Login/getCommentCode.do?x="+Math.random());
                return false;
            }
            if(code.trim()==''){
                var txt=  "请输入验证码！！！";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.warning);
                $(".c_code").attr("src","${ctx}/Login/getCommentCode.do?x="+Math.random());
                return false;
            }
            $.post("${ctx}/Comment/comment.do",{"code":code,"comment":commentext,"aid":${article.id},"name":$("#name").val()},
                function(result){
                    if(result.code=="000000"){
                        window.wxc.xcConfirm(result.message, window.wxc.xcConfirm.typeEnum.success);
                        $("#commentext").val("");
                        $("#c_code").val("");
                        $(".c_code").attr("src","${ctx}/Login/getCommentCode.do?x="+Math.random());
                    }else{
                        window.wxc.xcConfirm(result.message, window.wxc.xcConfirm.typeEnum.error);
                        $("#c_code").val("");
                        $(".c_code").attr("src","${ctx}/Login/getCommentCode.do?x="+Math.random());
                    }
            },'json');
        }
        // Firefox, Google Chrome, Opera, Safari, Internet Explorer from version 9
        function OnInput (event) {
            /* alert ("The new content1: " + event.target.value);*/
            var len = event.target.value.length;
            $("#zf").text(500-len);
        }
        // Internet Explorer
        function OnPropChanged (event) {
            if (event.propertyName.toLowerCase () == "value") {
                var len = event.target.value.length;
                $("#zf").text(500-len);
                /* alert ("The new content2: " + event.srcElement.value);*/
            }
        }
        function pinglun(){
            $("#showPing").attr("style","display:none");
            $(".hideComment1").attr("style","width: 90%;height: 40px;margin:0 auto;");
            $(".hideComment2").attr("style","width: 90%;height: 50px;margin:0 auto;");
            $(".hideComment3").attr("style","width: 90%;margin: 0 auto;");
            $.post("${ctx}/Comment/commentList.do",{"id":${article.id}},
                function(result){
                    var html = '';
                    var hideComment3 = $(".hideComment3");
                    for(x in result.data){
                        var y =  parseInt(x)+parseInt(1);
                        html += "<div style='width: 100%;'>" +
                                    "<div style='width: 100%;margin-top: 20px;'>" +
                                        "<div style='width: 10%;height: 80px;float: left;margin-top: 20px'> " +
                                            "<img class='img-circle' alt='图片' style='border:1px solid #ccc;width: 100%;height: 100%' src='http://172.31.61.19:9091/lztWeb/phead/"+result.data[x].headImg+"' >" +
                                        "</div>" +
                                        "<div style='width:90%;height: 80px;float: left;line-height: 80px;margin-top: 20px'>" +
                                            "<span style='margin-left: 10px;font-weight: bold;font-size: 18px;'>"+result.data[x].name+"</span>" +
                                            "<span style='float: right;font-size: 14px;margin-top: 20px'>"+y+"楼&nbsp;&nbsp;&nbsp;"+result.data[x].createDate+"&nbsp;发表</span>" +
                                        "</div>" +
                                    "</div>" +
                                    "<div style='width: 90%;float: right'> " +
                                        "<div style='width: 100%;float: left;font-size: 16px;'>" +
                                            "<span style='margin-left: 10px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+result.data[x].comment+"</span>" +
                                        "</div> " +
                                    "</div> ";
                        if(result.data[x].reply!=null){
                            html += "<div style='width: 90%;float: right'> " +
                                "<span style='font-size: 16px'><strong>作者回复:</strong>"+result.data[x].reply+"</span> " +
                                "</div>" +
                                "<div style='width: 100%;'> " +
                                "<span style='font-size: 16px;float: right;font-size: 14px'>回复时间:"+result.data[x].updateDate+"</span> " +
                                "</div>";
                        }
                        html +="</div><div class='xuxian'></div>";
                    }
                    hideComment3.append(html);
            },'json');
        }
    </script>
    <style>
        #top{
            text-align: center;
            line-height: 25px;
            position: fixed;
            bottom: 400px;
            right: 10%;
            width: 40px;
            height: 80px;
        }
        #top div:hover{
            background-color: #ccc;
            position: relative;
            left: -1px;
            top: -1px;
        }
        .edit{
            width: 100%;
            height: 48%;
            background-color: #fff;
            margin-top:5px;

        }
      /*   body{
            position: relative;
            min-height: 100%;
        }*/
    </style>
</head>
<body>
<!--背景动画start-->
<script src="${ctx}/static/bootStrap/js/canvas-nest.min.js"></script>
<!--背景动画end-->
    <jsp:include page="/common/head.jsp"></jsp:include>
<div class="container" style="margin-top: 100px;">
    <div class="row">
        <div class="col-md-1 col-xs-1" style=""></div>
        <div class="col-md-10 col-xs-10" id="body_content" style="background-color: rgba(255,255,255,0.6);box-shadow: 0px 0px 15px #fff;padding: 0">
            <div style="width: 100%;box-shadow: 0 0 70px 30px rgb(249, 249, 249) inset;
            <c:set var="t" value="false" />
            <c:forEach var="tu" items="${tuList}">
            <c:if test="${tu.tuType=='5'}">background: url('http://172.31.61.19:9091/lztWeb/style/${tu.tuUrl}') 0 50% no-repeat;background-size: 100% 100%;
                <c:set var="t" value="true" />
            </c:if>
            </c:forEach>
            <c:if test="${t==false}"> background: url('${ctx}/static/bootStrap/MyImage/detailstou.jpg') 0 50% no-repeat;background-size: 100% 100%;</c:if>">

                <div style="width: 100%;height: 80px">
                    <div style="width: 90%;height: 100%;margin: 0 auto;line-height: 80px;text-align: center;font-size: 25px">
                        <span><strong>${article.title}</strong></span>
                    </div>
                </div>
                <div style="width: 100%;height: 30px">
                    <div style="width: 50%;height: 100%;margin: 0 auto;line-height: 30px;text-align: center;font-size: 15px">
                        <span><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/categories.png" />&nbsp;&nbsp;${article.typeName}</span>
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
                        <span><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/date.png">&nbsp;发表时间&nbsp;&nbsp;<fmt:formatDate value="${article.createTime}" pattern="yyyy年MM月dd日 HH:mm:ss"></fmt:formatDate></span>
                    </div>
                    <div style="width: 30%;height: 100%;margin: 0 auto;line-height: 50px;text-align: right;font-size: 16px;float: left">
                        <img src="${ctx}/static/bootStrap/MyImage/xiaoImg/yd.png" style="width: 20px;height: 20px">&nbsp<span style="font-size: 12px">${article.reads}人阅读&nbsp&nbsp</span>
                    </div>
                </div>
            </div>
            <div style="width: 90%;margin: 20px auto; font-size: 16px;">
                ${article.content}
            </div>
            <div style="width: 90%;height: 30px;margin: 0 auto">
            <span><strong>关键字:</strong>
              <c:if test="${fn:length(article.keys)==0}">
                    无
                </c:if>
                <c:if test="${fn:length(article.keys)>0}">
                    <c:forEach var="key" items="${keys}">
                        ${key}&nbsp;&nbsp;
                    </c:forEach>
            </c:if>
            </span>
            </div>
            <hr style="height:1px;border:none;border-top:1px solid #ccc;width: 90%;margin: 5px auto" />
            <div style="width: 90%;height: 50px;margin: 0 auto">
                ${pageBean}
            </div>
            <div style="width: 90%;height: 30px;">
            </div>
            <div style="width: 90%;height: 50px;margin: 40px auto" id="showPing">
                <c:if test="${article.pings!=0}">
                    <button type="button" onclick="pinglun()" class="btn btn-info" style="width: 100%;color: #000;height: 100%;font-size: 18px;border: 0;box-shadow: 0px 0px 15px #90d7ec;background-color: rgba(255,255,255,0.5)">点击查看${article.pings}条评论</button>
                </c:if>
                <c:if test="${article.pings==0}">
                    <button type="button" data-toggle="modal" data-target="#comment" class="btn btn-info" style="width: 100%;color: #000;height: 100%;font-size: 18px;border: 0;box-shadow: 0px 0px 15px #90d7ec;background-color: rgba(255,255,255,0.5)">快来评论我吧!</button>
                </c:if>
            </div>
            <div style="width: 90%;height: 30px;margin:0 auto;display: none" class="hideComment2">
                <img src="${ctx}/static/bootStrap/MyImage/bian1.png" style="width: 100%;height: 100%">
            </div>
            <div style="width: 90%;height: 40px;display: none" class="hideComment1">
                <div style="height: 100%;width: 20%;margin: 0 auto">
                    <img src="${ctx}/static/bootStrap/MyImage/comment.png" style="width: 100%;height: 100%">
                </div>
                <%-- <div style="height: 100%;width: 5%;float: right">
                     <img src="${ctx}/static/bootStrap/MyImage/bi.png" style="width: 90%;height: 90%">
                 </div>--%>
            </div>
           <%-- <div style="width: 90%;height: 30px;margin:0 auto;display: none" class="hideComment2">
                <img src="${ctx}/static/bootStrap/MyImage/bian1.png" style="width: 100%;height: 100%">
            </div>--%>
            <div style="width: 90%;margin: 0px auto;display: none" class="hideComment3">
            </div>
            <div style="width: 90%;height: 50px;float: right;"></div>
        </div>
        <div class="col-md-1 col-xs-1"></div>
    </div>
</div>
<div id="foot" style="font-size:10px;;width: 100%;height: 30px;text-align: center;line-height: 30px;margin-top: 10px;">
    <span>Copyright © 2018 LiZiTao lztizfl.com All Rights Reserved.</span>
</div>
<div id="top">
    <div class="edit">
        <a href="#" data-toggle="modal" data-target="#comment"><img src="${ctx}/static/bootStrap/MyImage/bi.png" style="width: 80%;height: 80%"></a>
    </div>
    <div class="edit">
        <a href="#"><img src="${ctx}/static/bootStrap/MyImage/daosan.png" style="width: 80%;height: 80%"></a>
    </div>
</div>
<div class="modal fade" id="comment" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background:url('${ctx}/static/bootStrap/MyImage/pinglun2.png') 0% 50% no-repeat;background-size: 50%">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h3 class="modal-title" id="myModalLabel">
                </h3>
            </div>
            <div class="modal-body" id="fg" style="background:url('${ctx}/static/bootStrap/MyImage/commentbj.png') 0% 50% no-repeat;background-size: 100%">
                <div style="width: 100%;height: 45px;margin-bottom: 10px">
                    <div style="width: 40%;height: 100%;float: left">
                        <input id="c_code" type="text" maxlength="4" class="form-control" placeholder="请输入验证码。。。" style="height: 100%;background-color: rgba(255,255,255,0.5);font-size: 18px">
                    </div>
                    <div style="width: 40%;height: 100%;float: left;padding-left: 10px">
                        <img class="c_code" src="${ctx}/Login/getCommentCode.do" onclick="this.setAttribute('src','${ctx}/Login/getCommentCode.do?x='+Math.random());"
                             alt="验证码" title="点击更换"/>
                    </div>
                    <div style="width: 20%;height: 100%;float: right;">
                        <button type="button" class="btn btn-info" style="float: right;width: 100%;height: 100%;font-size: 18px" onclick="tijiaoComment()">提交</button>
                    </div>
                </div>
                <textarea oninput="OnInput(event)" onpropertychange="OnPropChanged(event)" class="form-control" maxlength="500" rows="17" placeholder="请输入评论。。。" style="font-size: 18px;background-color: rgba(255,255,255,0.5)" id="commentext"></textarea>
                <div style="width: 100%;height: 20px;text-align: right;margin-bottom: 10px">
                    还可输入<span id="zf">500</span>个字符
                </div>
                <div style="width: 100%;height: 30px;margin-bottom: 10px">
                    <input id="name" type="text" maxlength="20" class="form-control" placeholder="请输入代号(可选)。。。" style="height: 100%;background-color: rgba(255,255,255,0.5);font-size: 18px">
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>















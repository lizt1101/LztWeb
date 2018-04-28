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
    <script>window.jQuery || document.write('<script src="${ctx}/static/bootStrap/js/jquery.min.js"><\/script>')</script>
    <script type="text/javascript" src="${ctx}/static/bootStrap/js/jquery.flexslider-min.js"></script>
    <script src="${ctx}/static/bootStrap/js/bootstrap.min.js"></script>
    <script src="${ctx}/js/base64.js"></script>
    <script src="${ctx}/static/alertKuang/js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="${ctx}/static/bootStrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/static/bootStrap/css/Index.css">
    <link rel="stylesheet" href="${ctx}/static/bootStrap/css/search-form.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bootStrap/css/zzsc.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/alertKuang/css/xcConfirm.css"/>

    <title></title>
    <style>
        a,img{border:0;}
        /* flexslider */
        .flexslider{position:relative;height:400px;overflow:hidden;background:url(Index/images/loading.gif) 50% no-repeat;}
        .slides{position:relative;z-index:1;}
        .slides li{height:400px;}
        .flex-control-nav{position:absolute;bottom:10px;z-index:2;width:100%;text-align:center;}
        .flex-control-nav li{display:inline-block;width:14px;height:14px;margin:0 5px;*display:inline;zoom:1;}
        .flex-control-nav a{display:inline-block;width:14px;height:14px;line-height:40px;overflow:hidden;background:url(Index/images/dot.png) right 0 no-repeat;cursor:pointer;}
        .flex-control-nav .flex-active{background-position:0 0;}

        .flex-direction-nav{position:absolute;z-index:3;width:100%;top:45%;}
        .flex-direction-nav li a{display:block;width:50px;height:50px;overflow:hidden;cursor:pointer;position:absolute;}
        .flex-direction-nav li a.flex-prev{left:40px;background:url(Index/images/prev.png) center center no-repeat;}
        .flex-direction-nav li a.flex-next{right:40px;background:url(Index/images/next.png) center center no-repeat;}
        /*#serachBt{
            cursor: pointer;
        }
        #serachInput{
            width: 70%;
            height: 80%;
            margin: 7px auto;
            box-shadow: 0px 0px 15px #90d7ec;
            border:1px solid #90d7ec;
            !*border: 1px solid #fff*!
        }
        #serachBt{
            width: 20%;
            height: 100%;
            float: right;
            text-align: center;
            padding:10px 0;
            background-color:#90d7ec;
        }*/
        /*弹幕开始*/
        .dm{width:100%;height:600px;position:absolute;top:520px;left:0;display:none}
        .dm .d_screen
        .d_del{width:38px;height:38px;background:#600;display:block;text-align:center;line-height:38px;
            text-decoration:none;font-size:20px;color:#fff;border-radius:19px;border:1px solid #fff;position:absolute;top:10px;right:20px;z-index:3;}
        .dm .d_screen .d_del:hover{background:#f00;}
        .dm .d_screen .d_mask{width:100%;height:100%;background:#000;position:absolute;top:0;left:0;opacity:0.5;
            filter:alpha(opacity=50) z-index:1;}
        .dm .d_screen .d_show{position: relative;z-index:2;}
        .dm .d_screen .d_show div{font-size:22px;line-height:36px;font-weight:500;color:#fff;position:absolute;left:0;top:0;}
        /*end d_scree*/

        /*.send start*/
        .send{width:100%;height:60px;position:absolute;bottom:0;left:0;}
        .send .s_fiter{width:100%;height:76px;background:#000;position:bottom:0;left:0;opacity:0.8;filter:alpha(opacity=80);}
        .send .s_con{width:100%;height:76px;position:absolute;top:0;left:0;z-index:2;text-align:center;line-height:76px;}
        .send .s_con
        .s_txt{width:605px;height:36px;border-radius:4px 0 0 4px;outline:none;border:1px solid #5bba32;}
        .send .s_con .s_sub{width:100px;height:36px;background:#65c33d;border:0;outline:none;font-size:14px;color:#fff;font-family:"微软雅黑";cursor:pointer;border-radius:0 4px 4px 0;border:1px solid #5bba32;}
        .send .s_con .s_sub:hover{background:#3eaf0e;}
        /*弹幕结束*/

    </style>
    <script>
        $(document).ready(function(){
            var int = self.setInterval("serachK1()",5000);
            $('.flexslider').flexslider({
                directionNav: true,
                pauseOnAction: false,
                slideshowSpeed: 3000
            });

            var mywidth = window.screen.width;
            $("#mycanvas").attr("width",mywidth);
        })

       /* $(window).resize(function () {
            /!*alert("侧边宽度"+$("#jkDiv").width());*!/
            var width = $("#jkDiv").width();
            $("#sjWidth").val(width);

        })*/

        window.onscroll= function(){
            //变量t是滚动条滚动时，距离顶部的距离
            var t = document.documentElement.scrollTop||document.body.scrollTop;
            if(t >= 70 && t<470){
                $("#Myhead").attr("style","background-color:rgba(255,255,255,0.3);");
                /*$(".head-li li a").attr("style","color:#fff");*/
              /*  $("#title").attr("src","${ctx}/static/bootStrap/MyImage/title1.png")*/
            }else if(t>470){
                $("#Myhead").attr("style","background-color:rgba(255,255,255,0.6);");
               /* $(".head-li li a").attr("style","color:#fff");*/
               /* $("#title").attr("src","${ctx}/static/bootStrap/MyImage/title.png")*/
            }else{          //恢复正常
                $("#Myhead").attr("style","background-color:rgba(255,255,255,0.4)");
               /* $(".head-li li a").attr("style","color:#000");*/
               /* $("#title").attr("src","${ctx}/static/bootStrap/MyImage/title1.png")*/
            }
        }
        function dianji(){
            $(".head_div2").slideToggle("slow");
            if($("#san img").attr("src")=="${ctx}/static/bootStrap/MyImage/daosan.png"){
                $("#san img").attr("src","${ctx}/static/bootStrap/MyImage/san.png");
            }else{
                $("#san img").attr("src","${ctx}/static/bootStrap/MyImage/daosan.png");
                var mywidth = window.screen.width;
                var canvas = document.getElementById('mycanvas');
                var ctx = canvas.getContext('2d');
                ctx.font = "18px serif";
                ctx.textBaseline = "ideographic";
                ctx.clearRect(0,0,mywidth,500);
                $.post("${ctx}/LeaveMessage/getLiuyanShowList.do",{"start":"0","pageSize":"30"},
                    function (result) {
                        for(x in result.list){
                            var color = getColor();
                            ctx.fillStyle = color;
                            drawText(ctx,result.list[x].message,Math.random()*mywidth,Math.random()*500,Math.random()*150+50);
                        }
                    },"json");
            }

        }

        function drawText(ctx,t,x,y,w){

            var chr = t.split("");
            var temp = "";
            var row = [];

            for(var a = 0; a < chr.length; a++){
                if( ctx.measureText(temp).width < w ){
                    ;
                }
                else{
                    row.push(temp);
                    temp = "";
                }
                temp += chr[a];
            }

            row.push(temp);

            for(var b = 0; b < row.length; b++){
                ctx.fillText(row[b],x,y+(b+1)*20);
            }
        }


        function liuyan1(){
            var liuyan = $("#s_txt").val();
            var liuyancode = $("#y_code").val();
            if(liuyancode.trim()==''){
                var txt=  "请输入验证码！！！";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.warning);
                $(".imgcode").attr("src","${ctx}/Login/getLiuYanCode.do?x="+Math.random());
                return false;
            }
            if(liuyan.trim()==''){
                var txt=  "请输入留言！！！";
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.warning);
                return false;
            }
            var mywidth = window.screen.width;
            var canvas = document.getElementById('mycanvas');
            var color = getColor();
            var ctx = canvas.getContext('2d');
            ctx.font = "20px serif";
            ctx.textBaseline = "ideographic";
            ctx.fillStyle = color;
            drawText(ctx,liuyan,Math.random()*mywidth,Math.random()*500,500);
            $.post("${ctx}/LeaveMessage/liuyan.do",{"message":liuyan,"kIP":"172.31.61.19","code":liuyancode},function(result){
                if(result.code=="000000"){
                    var txt=  "留言成功！！！";
                    window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success);
                    $("#s_txt").val("");
                    $("#y_code").val("");
                    $(".imgcode").attr("src","${ctx}/Login/getLiuYanCode.do?x="+Math.random());
                }else{
                    window.wxc.xcConfirm(result.message, window.wxc.xcConfirm.typeEnum.error);
                    $("#y_code").val("");
                    $(".imgcode").attr("src","${ctx}/Login/getLiuYanCode.do?x="+Math.random());
                }
            },"json");
        }

        //随机获取颜色值
        function getReandomColor(){
            return '#'+(function(h){
                    return new Array(7-h.length).join("0")+h
                })((Math.random()*0x1000000<<0).toString(16))
        }

        function huan(){
            var tu = Math.ceil(Math.random()*15);
            $("body").attr("background","${ctx}/static/bootStrap/MyImage/bj"+tu+".jpg");
        }
        function createcookie(img){
            $.cookie('bj',img,{ expires: 1,path:'/',secure:false,raw:false});
            $("body").attr("background","${ctx}/static/bootStrap/MyImage/bj/bj"+img+".jpg");
        }
        function xz(a){
            var allLi = $("#ul li");
            allLi.attr("style","");
            var li = $(".bj"+a);
            li.attr("style","border:3px solid #ccc");
            createcookie(a);

        }


        function serachK1(){
            var r =  Math.ceil(Math.random()*255);
            var g = Math.ceil(Math.random()*255);
            var b = Math.ceil(Math.random()*255);
            var color = getColor1();
         /*   $("#serachInput").attr("style","border:1px solid "+color+";box-shadow: 0px 0px 15px "+color);
            $("#serachBt").attr("style","background-color:"+color);*/
            $(".liuyan").attr("style","box-shadow: 0px 0px 35px "+color+";background-color:"+color);
        }

        function getColor1(){
            var colorValue="0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f";
            var colorArray = colorValue.split(",");
            var color="#";
            for(var i=0;i<6;i++){
                color+=colorArray[Math.floor(Math.random()*16)];
            }
            return color;
        }

        function liuyan(){
            $("#showliu").attr("style","width: 100%;height: 100%;margin: 0 auto;");
            $("#liuyan").attr("style","display:none;");
            $("#liuyan1").attr("style","text-decoration: none;font-size: 24px;color:#fff");
        }

    </script>
    <script>
        function getTypeList(){
            var ul = $("#Li");
            var lis = $("#Li li");
            if(lis.length>0){
                return false;
            }else{
                $.post("${ctx}/type/typeList.do",
                    function(result){
                        var optionHtml='';
                        for (x in result.data.typeList)
                        {
                            var b = new Base64();
                            var str = b.encode("lzt"+result.data.typeList[x].id);
                            var str1 = b.encode("lzt"+result.data.typeList[x].typeName);
                            optionHtml += "<li>&nbsp;&nbsp;&nbsp;&nbsp;<a href=${ctx}/toIndex.do?type="+str+"&typeName="+str1+">"+result.data.typeList[x].typeName+"&nbsp&nbsp("+result.data.typeList[x].count+")</a></li>"
                        }
                        ul.append(optionHtml);
                    },'json');
            }
        }

        function getTimeList(){
            var ul = $("#timeLi");
            var lis = $("#timeLi li");
            if(lis.length>0){
                return false;
            }else{
                $.post("${ctx}/type/timeList.do",
                    function(result){
                        var optionHtml='';
                        for (x in result.data.timeList)
                        {
                            var b = new Base64();
                            var str = b.encode("lzt"+result.data.timeList[x].time);
                            optionHtml += "<li>&nbsp;&nbsp;&nbsp;&nbsp;<a href=${ctx}/toIndex.do?time="+str+">"+result.data.timeList[x].time+"&nbsp&nbsp("+result.data.timeList[x].count+")</a></li>"
                        }
                        ul.append(optionHtml);
                    },'json');
            }
        }

        function aboutMe(){
            var web = $("#web");
            var webHead = $("#webHead");
            var webContentHtml = '';
            if(web.text()!=''){
                return false;
            }
            $.post("${ctx}/aboutWeb.do",function(result){
                web.text(result.data.description);
                webContentHtml += "<img class='am-circle' style='width: 100%;height: 100%' alt='头像' src='http://172.31.61.25:9091/lztWeb/"+result.data.headImg+"'/>";
                webHead.append(webContentHtml);
            },'json');
        }

        function readCount(){
            var ul = $("#readLi");
            var lis = $("#readLi li");
            if(lis.length>0){
                return false;
            }else{
                $.post("${ctx}/read.do",function(result){
                    var optionHtml='';
                    for (x in result.data)
                    {
                        optionHtml += "<li><img style='width:25px;height:20px' src='${ctx}/static/image/"+(parseInt(x)+1)+".png'>&nbsp;<a target='view_window' href=${ctx}/article/getArtDetails/"+result.data[x].id+".do>"+result.data[x].title+"&nbsp&nbsp("+result.data[x].look+")</a></li>"
                    }
                    ul.append(optionHtml);
                },'json');
            }
        }

        function pingCount(){
            var ul = $("#pingLi");
            var lis = $("#pingLi li");
            if(lis.length>0){
                return false;
            }else{
                $.post("${ctx}/ping.do",function(result){
                    var optionHtml='';
                    for (x in result.data)
                    {
                        optionHtml += "<li><img style='width:25px;height:20px' src='${ctx}/static/image/"+(parseInt(x)+1)+".png'>&nbsp;<a target='view_window' href=${ctx}/article/getArtDetails/"+result.data[x].id+".do>"+result.data[x].title+"&nbsp&nbsp("+result.data[x].ping+")</a></li>"
                    }
                    ul.append(optionHtml);
                },'json');
            }
        }

        function serachArt(){
            var key = $("#key").val();

        }


        function Myurl(){
            var ul = $("#urlLi");
            var lis = $("#urlLi li");
            if(lis.length>0){
                return false;
            }else{
                $.post("${ctx}/getUrl.do",function(result){
                    if(result.code=="000000"){
                        var optionHtml='';
                        for (x in result.data)
                        {
                            optionHtml += "<li>&nbsp;&nbsp;&nbsp;&nbsp;<a target='view_window' href="+result.data[x].url+">"+result.data[x].urlName+"</a></li>"
                        }
                        ul.append(optionHtml);
                    }else{
                        ul.append("<li>"+result.message+"</li>");
                    }
                },'json');
            }
        }
    </script>
</head>
<body>
<!--背景动画start-->
<script src="${ctx}/static/bootStrap/js/canvas-nest.min.js"></script>
<!--背景动画end-->
<jsp:include page="/common/head.jsp"></jsp:include>
<div class="container" style="padding: 0">
    <div class="row">
        <div class="col-md-12 col-xs-12">
            <div id="head">
                <%--<div id="Myhead">
                      <!-- <button onclick="huan()" style="">风格</button>-->
                      <div style="float: right;width: 40%;height: 100%;">
                          <ul class="head-li">
                              <li><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/504263.png">&nbsp;<a href="#" data-toggle="modal" data-target="#myModal">风格</a></li>
                              <li><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/award_star_silver_1.png">&nbsp;<a href="#">分类</a></li>
                              <li><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/award_star_gold_3.png">&nbsp;<a href="#ph">排行</a></li>
                              <li><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/504223.png">&nbsp;<a href="#">首页</a></li>
                          </ul>
                      </div>
                      <div style="float: right;width: 20%;height: 100%">
                          <img src="${ctx}/static/bootStrap/MyImage/title.png" style="width: 100%;height: 100%" id="title"/>
                      </div>
                      <div style="float: right;width: 40%;height: 100%">
                      </div>
                </div>--%>
                <div id="lunbo">
                    <div class="jq22-container">
                        <div class="flexslider">
                            <ul class="slides">
                                <c:forEach var="lb" items="${LbList}">
                                    <%--<a target='view_window' href='${ctx}/article/getArtDetails/${lb.lbAid}.do'></a>--%>
                                    <li style="background:url(http://172.31.61.25:9091/lztWeb/lunbo/${lb.lbTu}) 50% 0 no-repeat;">
                                        <c:if test="${lb.lbType==1}">
                                            <a target='view_window' href='${lb.lbUrl}'>
                                                <div style="width: 100%;height: 100%;"></div>
                                            </a>
                                        </c:if>
                                        <c:if test="${lb.lbType==0}">
                                            <a target='view_window' href='${ctx}/article/getArtDetails/${lb.lbAid}.do'>
                                                <div style="width: 100%;height: 100%;"></div>
                                            </a>
                                        </c:if>
                                    </li>
                                </c:forEach>
                                <%--<li style="background:url(http://172.31.61.25:9091/lztWeb/lunbo/lzt1516588666552.png) 50% 0 no-repeat;"></li>
                                <li style="background:url(${ctx}/static/bootStrap/MyImage/images/img2.png) 50% 0 no-repeat;"></li>
                                <li style="background:url(${ctx}/static/bootStrap/MyImage/images/img3.png) 50% 0 no-repeat;"></li>
                                <li style="background:url(${ctx}/static/bootStrap/MyImage/images/img4.png) 50% 0 no-repeat;"></li>
                                <li style="background:url(${ctx}/static/bootStrap/MyImage/images/img5.png) 50% 0 no-repeat;"></li>--%>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div style="width: 100%;">
    <div class="container" style="padding: 0">
        <div class="row">
            <div class="col-md-12 col-xs-12">
                <div style="text-align: center;" id="san2">
                    <img src="${ctx}/static/bootStrap/MyImage/san.png" onclick="dianji()" style="width: 100px;height: 50px;cursor:pointer">
                </div>
                <div class="head_div2">
                    <div style="width: 100%;height: 600px;position: absolute;">
                        <div style="width: 30%;height: 50%;margin: 0 auto;background:url('${ctx}/static/bootStrap/MyImage/liuyanban.png') 0 50% no-repeat;background-size: 100%">

                        </div>
                        <div style="width: 100%;height: 60px;margin-top: 230px">
                            <div style="width: 82%;height: 100%;float: left;" >
                                <div style="width: 100%;height: 100%;margin: 0 auto;display: none;" id="showliu">
                                    <div style="width: 75%;height:100%;float: left;">
                                        <input type="text" id="s_txt"  maxlength="30" class="form-control" placeholder="请输入留言(最多输入30个字符)" style="height: 100%;background-color: rgba(255,255,255,0.1);font-size: 20px">
                                    </div>
                                    <div style="width: 10%;height: 100%;float: left;">
                                        <img style="width: 100%;height: 100%" src="${ctx}/Login/getLiuYanCode.do" onclick="this.setAttribute('src','${ctx}/Login/getLiuYanCode.do?x='+Math.random());"
                                             alt="验证码" title="点击更换" class="imgcode"/>
                                    </div>
                                    <div style="width: 15%;height: 100%;float: left;">
                                        <input id="y_code" type="text" maxlength="4" class="form-control" placeholder="请输入验证码。。。" style="height: 100%;background-color: rgba(255,255,255,0.1);font-size: 20px">
                                    </div>
                                </div>
                            </div>
                            <div class="liuyan">
                                <a onclick="liuyan()" style="text-decoration: none;font-size: 18px;color: #fff;" id="liuyan" href="javascript:void(0);">留&nbsp言&nbsp&nbsp(点我呀!!!)</a>
                                <a onclick="liuyan1()" style="text-decoration: none;font-size: 24px;display: none" id="liuyan1" href="javascript:void(0);">留&nbsp&nbsp言</a>
                            </div>
                        </div>
                    </div>
                    <div style="width: 100%;height: 550px;position: absolute">
                        <canvas id="mycanvas" height="550">
                        </canvas>
                    </div>
                </div>
                <div style="text-align: center" id="san">
                    <img src="${ctx}/static/bootStrap/MyImage/san.png" onclick="dianji()" style="width: 100px;height: 50px;cursor:pointer">
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-xs-8" id="body_content" style="box-shadow: 0px 0px 15px #fff;">
                <div style="width: 100%;height: 50px;margin: 0 auto">
                    <marquee direction=left behavior=scroll
                             scrollamount=10 scrolldelay=10 align=top bgcolor=#ffffff
                             hspace=20 vspace=10 onmouseover=this.stop() onmouseout=this.start()>
                        <span style="color: #000;font-size: 20px">${gunText}</span>
                    </marquee>
                </div>
                ${tishi}
                <hr style="height:1px;border:none;border-top:1px solid #ccc;" />
                <div class="content" id="content">
                    <jsp:include page="${content}"></jsp:include>
                </div>
            </div>
            <div class="col-md-3 col-xs-3" id="body_ce">
                <div class="row">
                    <!--background: url('timebj.jpg');border: 1px solid red;background-size: 100% 100%;-->
                    <div id="jkDiv" class="col-md-12 col-xs-12" style="height:280px;
                    <c:set var="l" value="false" />
                    <c:forEach var="tu" items="${tuList}">
                        <c:if test="${tu.tuType=='4'}">background: url('http://172.31.61.25:9091/lztWeb/style/${tu.tuUrl}');background-size: 100% 100%;
                        <c:set var="l" value="true" />
                        </c:if>
                    </c:forEach>
                    <c:if test="${l==false}">background: url('${ctx}/static/bootStrap/MyImage/time.png');background-size: 100% 100%;</c:if>">
                        <div class="zzsc-content">
                            <canvas id="clock7_" width="250px" height="250px">
                            </canvas>
                        </div>
                        <script src="${ctx}/static/bootStrap/js/canvas_clock.js"></script>
                        <script>
                            clockd7_={
                                "indicate": true,
                                "indicate_color": "#222",
                                "dial1_color": "#666600",
                                "dial2_color": "#81812e",
                                "dial3_color": "#9d9d5c",
                                "time_add": 1,
                                "time_24h": true,
                                "date_add":1,
                                "date_add_color": "#999",
                                "bg_color":"rgba(255, 255, 255, 0)",
                            };
                            var c = document.getElementById('clock7_');
                            cns7_ = c.getContext('2d');
                            clock_dots(235,cns7_,clockd7_);
                        </script>
                    </div>
                    <div class="col-md-12 col-xs-12" style="margin-top: 30px;padding: 0">
                        <div id="ph"></div>
                        <div class="panel-group" id="accordion">
                            <div class="panel panel-default" style="background-color:rgba(255,255,255,0.7);">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a class="a_ce" data-toggle="collapse" data-parent="#accordion"
                                           href="#collapseZero" style="text-decoration: none" onclick="aboutMe()">
                                            <img src="${ctx}/static/bootStrap/MyImage/xiaoImg/award_star_gold_1.png">
                                            <span><strong>&nbsp&nbsp关&nbsp于&nbsp我</strong></span>
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseZero" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <div style="width: 60%;height: 150px;margin: 20px auto" id="webHead">

                                        </div>
                                        <strong><span style="font-size: 15px">签&nbsp;名&nbsp;:</span></strong>&nbsp;<span id="web"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default" style="background-color:rgba(255,255,255,0.7);">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a class="a_ce" data-toggle="collapse" data-parent="#accordion"
                                           href="#collapseOne" style="text-decoration: none" onclick="getTypeList()">
                                            <img src="${ctx}/static/bootStrap/MyImage/xiaoImg/award_star_silver_1.png">
                                            <span><strong>&nbsp&nbsp类&nbsp型&nbsp分&nbsp类</strong></span>
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseOne" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <ul class="Li" id="Li">
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default" style="background-color:rgba(255,255,255,0.7);">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a class="a_ce" data-toggle="collapse" data-parent="#accordion"
                                           href="#collapsefive" style="text-decoration: none" onclick="getTimeList()">
                                            <img src="${ctx}/static/bootStrap/MyImage/xiaoImg/award_star_gold_3.png">
                                            <span><strong>&nbsp&nbsp时&nbsp间&nbsp分&nbsp类</strong></span>
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapsefive" class="panel-collapse collapse">
                                    <div class="panel-body" style="height: 300px;overflow-x:auto">
                                        <ul class="Li" id="timeLi">
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default" style="background-color:rgba(255,255,255,0.7);">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a class="a_ce" data-toggle="collapse" data-parent="#accordion"
                                           href="#collapseTwo" style="text-decoration: none" onclick="readCount()">
                                            <img src="${ctx}/static/bootStrap/MyImage/xiaoImg/award_star_silver_2.png">
                                            <span><strong>&nbsp&nbsp阅&nbsp读&nbsp排&nbsp行</strong></span>
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseTwo" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <ul class="Li" id="readLi">

                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default" style="background-color:rgba(255,255,255,0.7);">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a class="a_ce" data-toggle="collapse" data-parent="#accordion"
                                           href="#collapseThree" style="text-decoration: none" onclick="pingCount()">
                                            <img src="${ctx}/static/bootStrap/MyImage/xiaoImg/award_star_silver_3.png">
                                            <span><strong>&nbsp&nbsp评&nbsp论&nbsp排&nbsp行</strong></span>
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseThree" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <ul class="Li" id="pingLi">
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default" style="background-color:rgba(255,255,255,0.7);">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a class="a_ce" data-toggle="collapse" data-parent="#accordion"
                                           href="#collapsefour" style="text-decoration: none" onclick="Myurl()">
                                            <img src="${ctx}/static/bootStrap/MyImage/xiaoImg/award_star_gold_2.png">
                                            <span><strong>&nbsp&nbsp推&nbsp荐&nbsp链&nbsp接</strong></span>
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapsefour" class="panel-collapse collapse">
                                    <div class="panel-body">
                                       <ul class="Li" id="urlLi">

                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12 col-xs-12" style="height: 300px;margin-top: 30px;padding: 0;">
                        <c:forEach var="tu" items="${tuList}">
                            <c:if test="${tu.tuType=='3'}">
                                <img src="http://172.31.61.25:9091/lztWeb/style/${tu.tuUrl}" style="width: 100%;height: 100%">
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="foot" style="font-size:10px;width: 100%;height: 30px;text-align: center;border-top: 2px solid #ccc;line-height: 30px;margin-top: 10px">
        <span>Copyright © 2018 LiZiTao lztizfl.com All Rights Reserved.</span>
    </div>
</div>
</body>
</html>
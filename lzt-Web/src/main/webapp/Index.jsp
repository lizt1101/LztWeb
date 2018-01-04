<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <script src="${ctx}/static/bootStrap/js/jquery.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/bootStrap/js/jquery.cookie.js" type="text/javascript"></script>
    <script>window.jQuery || document.write('<script src="${ctx}/static/bootStrap/js/jquery.min.js"><\/script>')</script>
    <script type="text/javascript" src="${ctx}/static/bootStrap/js/jquery.flexslider-min.js"></script>
    <script src="${ctx}/static/bootStrap/js/bootstrap.min.js"></script>
    <script src="${ctx}/js/base64.js"></script>
    <link rel="stylesheet" href="${ctx}/static/bootStrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/static/bootStrap/css/Index.css">
    <link rel="stylesheet" href="${ctx}/static/bootStrap/css/search-form.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/bootStrap/css/zzsc.css">
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
        #serachBt{
            cursor: pointer;
        }
        #serachInput{
            width: 80%;
            height: 65%;
            margin: 3% auto;
            box-shadow: 0px 0px 15px #90d7ec;
            border:1px solid #90d7ec;
            /*border: 1px solid #fff*/
        }
        #serachBt{
            width: 20%;
            height: 100%;
            float: right;
            text-align: center;
            padding:10px 0;
            background-color:#90d7ec;
        }
        /*弹幕开始*/
        .dm{width:100%;height:800px;position:absolute;top:520px;left:0;display:none}
        .dm .d_screen
        .d_del{width:38px;height:38px;background:#600;display:block;text-align:center;line-height:38px;
            text-decoration:none;font-size:20px;color:#fff;border-radius:19px;border:1px solid #fff;position:absolute;top:10px;right:20px;z-index:3;display:none;}
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
            var int = self.setInterval("serachK()",5000);
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
            $('.flexslider').flexslider({
                directionNav: true,
                pauseOnAction: false,
                slideshowSpeed: 3000
            });

        })

        window.onscroll= function(){
            //变量t是滚动条滚动时，距离顶部的距离
            var t = document.documentElement.scrollTop||document.body.scrollTop;
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
        function dianji(){
            if($("#san img").attr("src")=="${ctx}/static/bootStrap/MyImage/daosan.png"){
                $("#san img").attr("src","${ctx}/static/bootStrap/MyImage/san.png");
            }else{
                $("#san img").attr("src","${ctx}/static/bootStrap/MyImage/daosan.png");
            }
            $(".head_div2").slideToggle("slow");
            init_screen();
            $(".dm").toggle(1000);

        }
        function publish(){
            var text=$("#s_txt").val();
            var div="<div>"+text+"</div>";
            $(".d_show").append(div);
            init_screen();
        }
        function init_screen(){
            var _top=0;
            $(".d_show").find("div").show().each(function(){
                var _left=$(window).width()-$(this).width();
                var _height=$(window).height();

                _top=_top+76;
                if(_top>=_height-100){
                    _top=0;
                }

                $(this).css({left:_left,top:_top,color:getReandomColor()});
                var time=10000;
                if($(this).index()%2==0){
                    time=15000;
                }
                $(this).animate({left:"-"+_left+"px"},time,function(){


                });
            });
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
            var li = $("#ul li:eq("+(a-1)+")");
            li.attr("style","border:3px solid #ccc");
            createcookie(a);

        }


        function serachK(){
            var r =  Math.ceil(Math.random()*255);
            var g = Math.ceil(Math.random()*255);
            var b = Math.ceil(Math.random()*255);
            var color = getColor();
            $("#serachInput").attr("style","border:1px solid "+color+";box-shadow: 0px 0px 15px "+color);
            $("#serachBt").attr("style","background-color:"+color);
        }

        function getColor(){
            var colorValue="0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f";
            var colorArray = colorValue.split(",");
            var color="#";
            for(var i=0;i<6;i++){
                color+=colorArray[Math.floor(Math.random()*16)];
            }
            return color;
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
                            optionHtml += "<li><a href=${ctx}/toIndex.do?type="+str+"&typeName="+str1+">"+result.data.typeList[x].typeName+"&nbsp&nbsp("+result.data.typeList[x].count+")</a></li>"
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
                            optionHtml += "<li><a href=${ctx}/toIndex.do?time="+str+">"+result.data.timeList[x].time+"&nbsp&nbsp("+result.data.timeList[x].count+")</a></li>"
                        }
                        ul.append(optionHtml);
                    },'json');
            }
        }
    </script>
</head>
<body background="${ctx}/static/bootStrap/MyImage/bj/bj15.jpg">
<!--背景动画start-->
<script src="${ctx}/static/bootStrap/js/canvas-nest.min.js"></script>
<!--背景动画end-->
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
  <jsp:include page="/common/head.jsp"></jsp:include>
  <div id="lunbo">
        <div style="width: 40%;height: 90px; top: 100px; left: 30%;position: absolute; z-index: 3;">
            <div id="serachInput" boder="1px solid rgba(255,255,255,0.6)">
                <div style="width: 80%;height: 100%;float: left;">
                    <div style="width: 12%;height: 100%;float: left;background-color: rgba(255,255,255,0.1)">
                        <img src="${ctx}/static/bootStrap/MyImage/sousuo.png" style="width: 100%;height: 100%">
                    </div>
                    <div style="width: 88%;height: 100%;float: left">
                        <input type="text" class="form-control" placeholder="请输入关键字" style="height: 100%;background-color: rgba(255,255,255,0.1);border: 0;font-size: 20px">
                    </div>
                </div>
                <div id="serachBt">
                    <span style="color: #fff;font-size: 22px;">搜&nbsp&nbsp索</span>
                </div>
            </div>
        </div>
        <div class="jq22-container">
            <div class="flexslider">
                <ul class="slides">
                    <li style="background:url(${ctx}/static/bootStrap/MyImage/images/img1.png) 50% 0 no-repeat;"></li>
                    <li style="background:url(${ctx}/static/bootStrap/MyImage/images/img2.png) 50% 0 no-repeat;"></li>
                    <li style="background:url(${ctx}/static/bootStrap/MyImage/images/img3.png) 50% 0 no-repeat;"></li>
                    <li style="background:url(${ctx}/static/bootStrap/MyImage/images/img4.png) 50% 0 no-repeat;"></li>
                    <li style="background:url(${ctx}/static/bootStrap/MyImage/images/img5.png) 50% 0 no-repeat;"></li>

                </ul>
            </div>
        </div>
  </div>
</div>
<div style="width: 100%;">
    <div style="text-align: center;" id="san2">
        <img src="${ctx}/static/bootStrap/MyImage/san.png" onclick="dianji()" style="width: 100px;height: 50px;cursor:pointer">
    </div>
    <div class="head_div2">
        <div class="dm">
            <!--d_screen start-->
            <div class="d_screen">
                <div class="d_mask"></div>
                <div class="d_show">
                    <div>1</div>
                    <div>2</div>
                    <div>3</div>
                    <div>4</div>
                    <div>5</div>
                </div>
            </div>
            <!--end d_screen-->

            <!--send start-->
            <div class="send">
                <div style="width: 50%;height:100%;margin:0 auto">
                    <div style="width: 80%;height:100%;float: left">
                        <input type="text" id="s_txt" class="form-control" placeholder="请输入留言。。。" style="height: 100%;background-color: rgba(255,255,255,0.1);font-size: 20px">
                    </div>
                    <div style="width: 20%;height:100%;background-color: rgba(255,255,255,0.1);float: right;">
                        <button type="button" class="btn btn-primary btn-lg btn-block" style="height: 100%" onclick="publish()">发表留言</button>
                    </div>
                </div>
            </div>
            <!--end send-->
        </div>
    </div>
    <div style="text-align: center" id="san">
        <img src="${ctx}/static/bootStrap/MyImage/san.png" onclick="dianji()" style="width: 100px;height: 50px;cursor:pointer">
    </div>
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-xs-8" id="body_content">
                <div style="width: 100%;height: 50px;margin: 0 auto">
                    <marquee direction=left behavior=scroll
                             scrollamount=10 scrolldelay=10 align=top bgcolor=#ffffff
                             hspace=20 vspace=10 onmouseover=this.stop() onmouseout=this.start()>
                        <span style="color: #000;font-size: 20px">此处输入滚动内容</span>
                    </marquee>
                </div>
                ${tishi}
                <hr style="height:1px;border:none;border-top:1px solid #ccc;" />
                <div id="content">
                    <jsp:include page="${content}"></jsp:include>
                </div>
            </div>
            <div class="col-md-3 col-xs-3" id="body_ce">
                <div class="row">
                    <!--background: url('timebj.jpg');border: 1px solid red;background-size: 100% 100%;-->
                    <div class="col-md-12 col-xs-12" style="height:300px;background: url('${ctx}/static/bootStrap/MyImage/time.png');background-size: 100% 100%;">
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
                            clock_dots(250,cns7_,clockd7_);
                        </script>
                    </div>
                    <div class="col-md-12 col-xs-12" style="height: 450px;margin-top: 30px;padding: 0">
                        <div id="ph"></div>
                        <div class="panel-group" id="accordion">
                            <div class="panel panel-default" style="background-color:rgba(255,255,255,0.7);">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a class="a_ce" data-toggle="collapse" data-parent="#accordion"
                                           href="#collapseZero" style="text-decoration: none">
                                            <img src="${ctx}/static/bootStrap/MyImage/xiaoImg/award_star_gold_1.png">
                                            <span><strong>&nbsp&nbsp关&nbsp于&nbsp本&nbsp站</strong></span>
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseZero" class="panel-collapse collapse">
                                    <div class="panel-body">
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
                                           href="#collapseTwo" style="text-decoration: none">
                                            <img src="${ctx}/static/bootStrap/MyImage/xiaoImg/award_star_silver_2.png">
                                            <span><strong>&nbsp&nbsp阅&nbsp读&nbsp排&nbsp行</strong></span>
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseTwo" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <ul class="Li">
                                            <li>标题标题标题 &nbsp&nbsp(17)</li>
                                            <li>标题标题标题 &nbsp&nbsp(16)</li>
                                            <li>标题标题标题 &nbsp&nbsp(14)</li>
                                            <li>标题标题标题 &nbsp&nbsp(12)</li>
                                            <li>标题标题标题 &nbsp&nbsp(11)</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default" style="background-color:rgba(255,255,255,0.7);">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a class="a_ce" data-toggle="collapse" data-parent="#accordion"
                                           href="#collapseThree" style="text-decoration: none">
                                            <img src="${ctx}/static/bootStrap/MyImage/xiaoImg/award_star_silver_3.png">
                                            <span><strong>&nbsp&nbsp评&nbsp论&nbsp排&nbsp行</strong></span>
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseThree" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <ul class="Li">
                                            <li>标题标题标题 &nbsp&nbsp(17)</li>
                                            <li>标题标题标题 &nbsp&nbsp(16)</li>
                                            <li>标题标题标题 &nbsp&nbsp(14)</li>
                                            <li>标题标题标题 &nbsp&nbsp(12)</li>
                                            <li>标题标题标题 &nbsp&nbsp(11)</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default" style="background-color:rgba(255,255,255,0.7);">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a class="a_ce" data-toggle="collapse" data-parent="#accordion"
                                           href="#collapsefour" style="text-decoration: none">
                                            <img src="${ctx}/static/bootStrap/MyImage/xiaoImg/award_star_gold_2.png">
                                            <span><strong>&nbsp&nbsp推&nbsp荐&nbsp文&nbsp章</strong></span>
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapsefour" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <ul class="Li">
                                            <li>标题标题标题 &nbsp&nbsp(17)</li>
                                            <li>标题标题标题 &nbsp&nbsp(16)</li>
                                            <li>标题标题标题 &nbsp&nbsp(14)</li>
                                            <li>标题标题标题 &nbsp&nbsp(12)</li>
                                            <li>标题标题标题 &nbsp&nbsp(11)</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="foot" style="width: 100%;height: 30px;text-align: center;border-top: 2px solid #ccc;line-height: 30px;margin-top: 10px">
        <span>小错的博客</span>
    </div>
</div>
</body>
</html>
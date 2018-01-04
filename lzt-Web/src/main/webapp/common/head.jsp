<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>
<%--<head>
    <script src="${ctx}/static/bootStrap/js/jquery.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/bootStrap/js/jquery.cookie.js" type="text/javascript"></script>
    <script src="${ctx}/static/bootStrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${ctx}/static/bootStrap/css/bootstrap.min.css">
    <style>
        *{
            margin: 0;
            padding: 0;
        }
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
        p{
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
        })
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
</head>--%>

<div id="Myhead">
    <div style="float: right;width: 40%;height: 100%;">
        <ul class="head-li">
            <li><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/504263.png">&nbsp;<a href="#" data-toggle="modal" data-target="#myModal">风格</a></li>
            <li><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/award_star_silver_1.png">&nbsp;<a href="#">分类</a></li>
            <li><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/award_star_gold_3.png">&nbsp;<a href="#ph">排行</a></li>
            <li><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/504223.png">&nbsp;<a href="${ctx}/toIndex.do">首页</a></li>
        </ul>
    </div>
    <div style="float: right;width: 20%;height: 100%">
        <img src="${ctx}/static/bootStrap/MyImage/title.png" style="width: 100%;height: 100%" id="title"/>
    </div>
    <div style="float: right;width: 40%;height: 100%">
    </div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    风格
                </h4>
            </div>
            <div class="modal-body" id="fg">
                <ul style="overflow: auto" id="ul">
                    <li onclick="xz(1)">
                        <div style="width: 120px;height:120px;background: url('${ctx}/static/bootStrap/MyImage/bj/bj1.jpg')"></div>
                        <p>123</p>
                    </li>
                    <li onclick="xz(2)">
                        <div style="width: 120px;height:120px;background: url('${ctx}/static/bootStrap/MyImage/bj/bj2.jpg')"></div>
                        <p>123</p>
                    </li>
                    <li onclick="xz(3)">
                        <div style="width: 120px;height:120px;background: url('${ctx}/static/bootStrap/MyImage/bj/bj3.jpg')">
                        </div>
                        <p>123</p>
                    </li>
                    <li onclick="xz(4)">
                        <div style="width: 120px;height:120px;background: url('${ctx}/static/bootStrap/MyImage/bj/bj4.jpg')"></div>
                        <p>123</p>
                    </li>
                    <li onclick="xz(5)">
                        <div style="width: 120px;height:120px;background: url('${ctx}/static/bootStrap/MyImage/bj/bj5.jpg')"></div>
                        <p>123</p>
                    </li>
                    <li onclick="xz(6)">
                        <div style="width: 120px;height:120px;background: url('${ctx}/static/bootStrap/MyImage/bj/bj6.jpg')"></div>
                        <p>123</p>
                    </li>
                    <li onclick="xz(7)">
                        <div style="width: 120px;height:120px;background: url('${ctx}/static/bootStrap/MyImage/bj/bj7.jpg')"></div>
                        <p>123</p>
                    </li>
                    <li onclick="xz(8)">
                        <div style="width: 120px;height:120px;background: url('${ctx}/static/bootStrap/MyImage/bj/bj8.jpg')"></div>
                        <p>123</p>
                    </li>
                    <li onclick="xz(9)">
                        <div style="width: 120px;height:120px;background: url('${ctx}/static/bootStrap/MyImage/bj/bj9.jpg')"></div>
                        <p>123</p>
                    </li>
                    <li onclick="xz(10)">
                        <div style="width: 120px;height:120px;background: url('${ctx}/static/bootStrap/MyImage/bj/bj10.jpg')"></div>
                        <p>123</p>
                    </li>
                    <li onclick="xz(11)">
                        <div style="width: 120px;height:120px;background: url('${ctx}/static/bootStrap/MyImage/bj/bj11.jpg')"></div>
                        <p>123</p>
                    </li>
                    <li onclick="xz(12)">
                        <div style="width: 120px;height:120px;background: url('${ctx}/static/bootStrap/MyImage/bj/bj12.jpg')"></div>
                        <p>123</p>
                    </li>
                    <li onclick="xz(13)">
                        <div style="width: 120px;height:120px;background: url('${ctx}/static/bootStrap/MyImage/bj/bj13.jpg')"></div>
                        <p>123</p>
                    </li>
                    <li onclick="xz(14)">
                        <div style="width: 120px;height:120px;background: url('${ctx}/static/bootStrap/MyImage/bj/bj14.jpg')"></div>
                        <p>123</p>
                    </li>
                    <li onclick="xz(15)">
                        <div style="width: 120px;height:120px;background: url('${ctx}/static/bootStrap/MyImage/bj/bj15.jpg')"></div>
                        <p>123</p>
                    </li>

                </ul>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>
<style>
    #Myhead{
        width:100%;
        height: 70px;
        position: fixed;
        z-index: 99;
        left:0; top:0;
        <c:forEach var="tu" items="${tuList}">
            <c:if test="${tu.tuType=='1'}">
                background: url("http://172.31.61.19:9091/lztWeb/style/${tu.tuUrl}") 0 50% no-repeat;background-size: 100%;
            </c:if>
        </c:forEach>
       /* border: 1px solid red;*/
       /* background: url("${ctx}/static/qitatu/head1.png") 0 50% no-repeat;background-size: 100%;*/
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
        background-color: rgba(255,255,255,0);
        border:3px solid rgba(255,255,255,0);
        padding:10px;
        margin:10px;
        float: left;
    }
    #ul{
        /*
         /!*  去掉列表符号*!/*/
        list-style-type: none;
        width: 100%;height: 500px;
    }
    #ul li p{
        /* border:1px solid red;*/
        color: #fff;
        text-align: center;
        font-family:"微软雅黑","文泉驿正黑","黑体";
        line-height:2em;
        width:120px;
        height:30px;
        overflow: auto;

    }
    p,span{
        font-family: '微软雅黑', '文泉驿正黑', '宋体';
        line-height: 30px;
    }
</style>
<script>
        $(document).ready(function(){
            var gundong = document.documentElement.clientHeight < (document.documentElement.offsetHeight-4);
            var bj = $.cookie('bj');
            var a = $.cookie('index');
            var isPu = $.cookie('isPu');
            if(bj==null){
                var li = $(".bj2");
                li.attr("style","border:3px solid #ccc");
                $("body").attr("style","background:url(http://172.31.61.19:9091/lztWeb/bj/bj2.jpg)");
            }else{
                var li = $(".bj"+a);
                li.attr("style","border:3px solid #ccc");
                if(isPu=="0"){
                    $("body").attr("style","background:url(http://172.31.61.19:9091/lztWeb/bj/"+bj+")");
                }else{
                   if(gundong){
                        $("body").attr("style","background:url(http://172.31.61.19:9091/lztWeb/bj/"+bj+") 0 50% no-repeat;background-size: 100% 100%");
                    }else{
                        $("body").attr("style","height:"+window.screen.availHeight+"px;background:url(http://172.31.61.19:9091/lztWeb/bj/"+bj+") 0 50% no-repeat;background-size: 100% 100%");
                    }

                }
            }
        })
        function createcookie(a,isPu,url){
            var gundong = document.documentElement.clientHeight < (document.documentElement.offsetHeight-4);
            $.cookie('index',a,{ expires: 1,path:'/',secure:false,raw:false});
            $.cookie('bj',url,{ expires: 1,path:'/',secure:false,raw:false});
            $.cookie('isPu',isPu,{ expires: 1,path:'/',secure:false,raw:false});
            if(isPu=="0"){
                $("body").attr("style","height:"+window.screen.availHeight+";background:url(http://172.31.61.19:9091/lztWeb/bj/"+url+")");
            }else{
               if(gundong){
                    $("body").attr("style","background:url(http://172.31.61.19:9091/lztWeb/bj/"+url+") 0 50% no-repeat;background-size: 100% 100%");
                }else{
                    $("body").attr("style","height:"+window.screen.availHeight+"px;background:url(http://172.31.61.19:9091/lztWeb/bj/"+url+") 0 50% no-repeat;background-size: 100% 100%");
              }
            }

        }
        function xz(a,isPu,url){
            var allLi = $("#ul li");
            allLi.attr("style","");
            var li = $(".bj"+a);
            li.attr("style","border:3px solid #ccc");
            createcookie(a,isPu,url);

        }
    </script>
<div id="Myhead" style="background-color: #000">
    <div style="float: right;width: 40%;height: 100%;">
        <ul class="head-li">
            <li><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/504263.png">&nbsp;<a href="#" data-toggle="modal" data-target="#myModal">风格</a></li>
            <%--<li><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/award_star_silver_1.png">&nbsp;<a href="#">分类</a></li>
            <li><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/award_star_gold_3.png">&nbsp;<a href="#ph">排行</a></li>--%>
            <li><img src="${ctx}/static/bootStrap/MyImage/xiaoImg/504223.png">&nbsp;<a href="${ctx}/toIndex.do">首页</a></li>
        </ul>
    </div>
    <div style="float: right;width: 20%;height: 100%">
        <img src="${ctx}/static/bootStrap/MyImage/title.png" style="width: 100%;height: 100%" id="title"/>
    </div>
    <div style="float: right;width: 40%;height: 100%;">

    </div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="background:url('${ctx}/static/bootStrap/MyImage/headbj.png') 0% 50% no-repeat;background-size: 100%">
            <div class="modal-header" style="text-align: center">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="color: red">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel" style="color: #fff">
                    风格
                </h4>
            </div>
            <div class="modal-body" id="fg">
                <ul style="overflow: auto" id="ul">
                    <c:forEach var="bj" items="${bjList}">
                        <li onclick="xz(${bj.id},${bj.isPu},'${bj.bjUrl}')" class="bj${bj.id}">
                            <c:if test="${bj.isPu=='0'}">
                                <div style="width: 120px;height:120px;background: url('http://172.31.61.19:9091/lztWeb/bj/${bj.bjUrl}')"></div>
                            </c:if>
                            <c:if  test="${bj.isPu=='1'}">
                                <div style="width: 120px;height:120px;background: url('http://172.31.61.19:9091/lztWeb/bj/${bj.bjUrl}') 0 50% no-repeat;background-size: 100% 100%;"></div>
                            </c:if>
                            <p class="p">${bj.bjName}</p>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

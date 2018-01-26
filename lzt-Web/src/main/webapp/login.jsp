<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>登陆</title>
    <link rel="shortcut icon" href="${ctx}/lztlogo1.ico" type="image/x-icon" />
    <script type="text/javascript" src="${ctx}/static/easyui/jquery.min.js"></script>
    <link rel="stylesheet" href="${ctx}/static/bootStrap/css/bootstrap.min.css">
    <script src="${ctx}/static/bootStrap/js/bootstrap.min.js"></script>
    <script src="${ctx}/js/login.js"></script>
    <style>
        #MyDiv{
            margin: 0;
            padding: 0;
            background:url(${ctx}/static/image/bj.jpg) no-repeat;
            width:100%;
            height:100%;
            background-size:100% 100%;
            position:absolute;
            filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='${ctx}/static/image/bj.jpg',sizingMethod='scale');

        }
        #loginDiv{
            position:absolute;
            left:50%;
            top:50%;
            margin:-225px 0 0 -200px;
            width: 400px;
            height: 450px;
            background-color:rgba(255,255,255,0.4)
        }
        div span{
            float: right;
            font-size: 14px;
        }
    </style>
</head>

<body style="width:100%;height:100%;">
<div id="MyDiv">
    <div id="loginDiv">
        <div style="margin: 50px auto;width: 350px;height: 325px;">
            <form action="${ctx}/Login/login.do"  method="post" onsubmit="return check(this) && checkUsername() && checkPassword() && checkCode()">
                <div class="form-group">
                    <label for="username" style="font-size: 18px;color: white">用户名</label><span class="remind1" style="display: none"></span>
                    <input type="text" name="userName" value="${param.userName}" maxlength="11" class="form-control" style="height: 50px;font-size: 16px;background-color:rgba(255,255,255,0.4)" id="username" placeholder="请输入6-11位字符" onblur="checkUsername()">
                </div>
                <div class="form-group">
                    <label for="password" style="font-size: 18px;color: white">密码</label><span class="remind2" style="display: none"></span>
                    <input type="password" name="password" value="${param.password}" maxlength="13" class="form-control" style="height: 50px;font-size: 16px ;background-color:rgba(255,255,255,0.4)" id="password" placeholder="请输入密码" onblur="checkPassword()">
                </div>
                <div class="form-group">
                    <label for="code" style="font-size: 18px;color: white">验证码</label><span class="remind3" style="display: none"></span><br>
                    <input type="text" name="code" class="form-control" value="${param.code}" maxlength="4" style="display: inline;height: 50px;width:250px;font-size: 16px;background-color:rgba(255,255,255,0.4)" id="code" placeholder="请输入验证码" onblur="checkCode()">
                    <img src="${ctx}/Login/getCode.do" onclick="this.setAttribute('src','${ctx}/Login/getCode.do?x='+Math.random());"
                         alt="验证码" title="点击更换" />
                </div>
                <span style="color: red;font-size: 14px">${error}</span><br>
                <button type="submit" class="btn btn-info" style="float: right;font-size: 17px">登&nbsp&nbsp陆</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>

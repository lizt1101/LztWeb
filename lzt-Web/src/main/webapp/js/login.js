/**
 * Created by admin on 2017/12/22.
 */
var regex1 = /^[A-Za-z0-9]{6,11}$/;
var regex2 = /^[A-Za-z0-9.@!*&$#_]{6,13}$/;
function check(form){
    if(form.userName.value.trim()=='') {
        form.userName.focus();
        $(".remind1").attr("style", "color:red");
        $(".remind1").text("请输入用户名");
        return false;
    }
    if(form.password.value.trim()==''){
        form.password.focus();
        $(".remind2").attr("style","color:red");
        $(".remind2").text("请输入登录密码");
        return false;
    }
    if(form.code.value.trim()==''){
        form.password.focus();
        $(".remind3").attr("style","color:red");
        $(".remind3").text("请输入验证码");
        return false;
    }
    return true;
}
function checkUsername(){
    if($("#username").val().length>5){
        if($("#username").val().match(regex1)==null){
            $(".remind1").attr("style","color:red");
            $(".remind1").text("用户名只能是字母数字组合!");
            return false;
        }
        $(".remind1").attr("style","display:none");
        return true;
    }else{
        $(".remind1").attr("style","color:red");
        $(".remind1").text("用户名不能少于6位字符!");
        return false;
    }
}
function checkPassword(){
    if($("#password").val().length>5){
        if($("#password").val().match(regex2)==null){
            $(".remind2").attr("style","color:red");
            $(".remind2").text("密码符号错误!");
            return false;
        }
        $(".remind2").attr("style","display:none");
        return true;
    }else{
        $(".remind2").attr("style","color:red");
        $(".remind2").text("密码长度不能少于6位字符!");
        return false;
    }
}
function checkCode(){
    if($("#code").val().length==4){
        $(".remind3").attr("style","display:none");
        return true;
    }else{
        $(".remind3").attr("style","color:red");
        $(".remind3").text("请输入四位验证码!");
        return false;
    }

}
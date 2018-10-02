
function login(){
    var username = $("#username").val();
    var passwprd = $("#password").val()
    if(username == "" || passwprd == ""){
        $("#msg").text("请输入账号或密码");
        return;
    }
    $("#msg").text("");
    $.ajax({
        type:"POST",
        url:"/user/login.do",
        data:{
            "username": $("#username").val(),
            "password": $("#password").val()
        },
        dataType:"json",
        success:function (result) {
            if(result.status === 0){
                $("#msg").text("");
                if(result.msg === "TEACHER")
                    location.href="manage.html";
                else
                    location.href="index.html";
            }
            else if(result.status === 12){
                $("#msg").text("账号或密码错误");
            }
            else if(result.status === 5){
                alert("检测到已有账号登录，若无法进入页面，请安装最新版chrome浏览器后尝试");
            }
            else{
                alert("服务器发生错误，请重试")
            }
        },
        error:function (result) {
            alert("向服务器请求数据失败" + result);
        }
    }); 
}



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
                    location.href="indexex.html";
                else
                    location.href="index.html";
            }
            else{
                $("#msg").text("账号或密码错误");
            }
        },
        error:function (result) {
            alert("向服务器请求数据失败" + result);
        }
    }); 
}


function login(){
    $.ajax({
        type:"post",
        url:"http://127.0.0.1/user/login.do",
        data:{
            "username": $("#username").val(),
            "password": $("#password").val()
        },
        dataType:"json",
        success:function (result) {
            if(result.status === 0){
                alert("成功");
                $("#msg").text("");
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


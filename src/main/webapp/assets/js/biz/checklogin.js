$.ajax({
    type:"POST",
    url:"/user/login_pwd.do",
    data:{
        username: $("#"),
        password: $("#")
    },
    dataType:"json", 
    success:function (result) {
        //准备
    },
    error:function (result) {
        alert("服务器错误，请稍后重试");
        window.location.href = "index.html";
    }
});
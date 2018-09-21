function logout() {
    $.ajax({
        type:"GET",
        url:"/user/logout.do",
        dataType:"json",
        success:function (res) {
            location.href = "login.html";
        },
        error:function () {
            alert("向服务器请求数据失败");
        }
    });
}
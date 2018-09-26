function logout() {
    if(confirm("确认退出吗？")) {
        $.ajax({
            type: "GET",
            url: "/user/logout.do",
            dataType: "json",
            success: function (res) {
                window.location.replace("login.html")
            },
            error: function () {
                alert("向服务器请求数据失败");
            }
        });
    }
}
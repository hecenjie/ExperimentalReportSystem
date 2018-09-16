$(function () {
    $.ajax({
        type:"get",
        url:"/user/isLogin.do",
        dataType:"json",
        success:function(res){
            var strUrl=location.href;
            var arrUrl=strUrl.split("/");
            var strPage=arrUrl[arrUrl.length-1];
            //如果已登陆，则在登陆或注册页面将跳转到主页
            if(res.status === 5){
                if(strPage === "login.html" || strPage === "register.html") {
                    location.href="index.html"
                }
            }
            //如果未登陆，且不在登陆和注册页面，则跳转到登陆页面
            if(res.status === 6){
                if(strPage !== "login.html" && strPage !== "register.html") {
                    location.href="login.html"
                }
            }
        }, error:function() {
            alert("向服务器请求数据失败")
        }
    })
})
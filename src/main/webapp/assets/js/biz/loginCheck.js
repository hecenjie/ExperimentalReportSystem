$(function () {
    $.ajax({
        type:"get",
        url:"/user/get_user_role.do",
        dataType:"json",
        success:function(res){
            var strUrl = location.href;
            var arrUrl = strUrl.split("/");
            var strPage = arrUrl[arrUrl.length - 1];

            //用户未登陆
            if(res.status === 2) {
                if (strPage !== "login.html" && strPage !== "register.html" && strPage !== "") {
                    location.href = "login.html"
                }
            } else if(res.status === 14) {
                //如果登陆用户为老师
                if (strPage === "login.html" || strPage === "register.html" || strPage === "") {
                    window.location.replace("manage.html")
                }
                else if(strPage === "index.html" || strPage === "PhotoeletricExperiment.html"){
                    window.location.replace("manage.html")
                }
            } else if(res.status === 13) {
                //如果登陆用户为学生
                if (strPage === "login.html" || strPage === "register.html" || strPage === "") {
                    window.location.replace("index.html")
                }
                else if(strPage === "manage.html" || strPage === "search-class.html" || strPage === "search-student.html"
                    || strPage === "manage-exp.html"){
                    window.location.replace("index.html")
                }
            }
        }, error:function() {
            alert("向服务器请求数据失败")
        }
    })
})
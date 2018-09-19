$(function () {
    $.ajax({
        type:"get",
        url:"/user/get_basic_info.do",
        dataType:"json",
        success:function(res){
            if(res.status === 0){
                $("#user_name").text(res.data.stuName)
                $("#user_id").text(res.data.id)
            } else if(res.status === 2){
                location.href = "login.html";
            } else{
                alert("获取个人信息时错误");
            }
        }, error:function() {
            alert("向服务器请求数据失败")
        }
    })
})
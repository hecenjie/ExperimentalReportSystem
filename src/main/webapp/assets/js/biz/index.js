$(function () {
    $.ajax({
        type:"get",
        url:"/user/get_stu_basic_info.do",
        dataType:"json",
        success:function(res){
            if(res.status === 0){
                $("#stu_num").text(res.data.stuNum)
                $("#stu_class").text(res.data.stuClass)
                $("#major").text(res.data.majorName)
                $("#stu_num").text(res.data.stuNum)
                $("#stu_class").text(res.data.stuClass)
                $("#stu_name").text(res.data.stuName)
            } else{

            }
        }, error:function() {
            alert("向服务器请求数据失败")
        }
    })
})
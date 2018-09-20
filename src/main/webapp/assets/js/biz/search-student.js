$(function () {
    $.ajax({
        type:"get",
        url:"/exp/get_all_exps.do",
        dataType:"json",
        success:function(res){
            if(res.status === 0){
                $.each(res.data, function (idx, val) {
                    str = "<option value='"+val.id+"'>"+val.name+"</option>"
                    $("#exp").append(str)
                })
            }
        }, error:function() {
            alert("向服务器请求数据失败")
        }
    })
})

function searchStu(){
    $("#stu_score").empty();
    var stuNum = $("#stu_num").val();
    var expId = $("#exp").find("option:selected").val();
    $.ajax({
        type:"get",
        url:"/exp/search_stu.do",
        dataType:"json",
        data:{
            stuNum: stuNum,
            expId: expId
        },
        success:function(res){
            if(res.status === 0){
                alert("成功");
            }
        }, error:function() {
            alert("向服务器请求数据失败")
        }
    })
}
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

    $.ajax({
        type:"get",
        url:"/major/get_all_majors.do",
        dataType:"json",
        success:function(res){
            if(res.status === 0){
                $.each(res.data, function (idx, val) {
                    str = "<option value='"+val.id+"'>"+val.name+"</option>"
                    $("#major").append(str)
                })
            }
        }, error:function() {
            alert("向服务器请求数据失败")
        }
    })
})

function search(orderBy){
    $("#stu_score").empty();
    var stuClass = $("#stu_class").val();
    var majorId = $("#major").find("option:selected").val();
    var expId = $("#exp").find("option:selected").val();

    alert(stuClass)
    alert(majorId)
    alert(expId)
    alert(orderBy)

    // $.ajax({
    //     type:"get",
    //     url:"/exp/search_stu.do",
    //     dataType:"json",
    //     data:{
    //         stuNum: stuNum,
    //         expId: expId
    //     },
    //     success:function(res){
    //         if(res.status === 0){
    //             alert("成功");
    //         }
    //     }, error:function() {
    //         alert("向服务器请求数据失败")
    //     }
    // })
}

$("#score_desc").click(function () {
    search("score_desc")
})

$("#score_asc").click(function () {
    search("score_asc")
})
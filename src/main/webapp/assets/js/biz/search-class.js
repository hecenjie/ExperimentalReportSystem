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

function search(orderBy, isExport){
    $("#stu_score").empty();
    var stuClass = $("#stu_class").val();
    var majorId = $("#major").find("option:selected").val();
    var expId = $("#exp").find("option:selected").val();

    if(isExport === 0) {
        $("#stu_score").empty();
    }

    $.ajax({
        type:"get",
        url:"/manage/score/get_scorelist_major.do",
        dataType:"json",
        data:{
            majorId: majorId,
            stuClass: stuClass,
            expId: expId,
            orderBy: orderBy,
            isExport: isExport
        },
        success:function(res){
            if(res.status === 0){
                $.each(res.data, function (idx, val) {
                    var id = val.stuId+"_"+val.expId;
                    var str = "                                    <tr id="+id+">\n" +
                        "                                        <td>"+val.stuNum+"</td>\n" +
                        "                                        <td>"+val.stuName+"</td>\n" +
                        "                                        <td>"+val.majorName+val.stuClass+"</td>\n" +
                        "                                        <td>"+val.expName+"</td>\n" +
                        "                                        <td>"+val.score+"</td>\n" +
                        "                                        <td><a href=\"javascript:void(0)\" onclick='deleteScore("+val.stuId+","+val.expId+")'>删除</a></td>\n" +
                        "                                    </tr>"
                    $("#stu_score").append(str)
                })
            }
        }, error:function() {
            alert("向服务器请求数据失败")
        }
    })
}

$("#score_desc").click(function () {
    search("score_desc", 0)
})

$("#score_asc").click(function () {
    search("score_asc", 0)
})

function deleteScore(stuId, expId){
    if(confirm("确认删除吗？")) {
        $.ajax({
            type: "post",
            url: "/manage/score/delete_score.do",
            dataType: "json",
            data: {
                stuId: stuId,
                expId: expId,
            },
            success: function (res) {
                if (res.status === 0) {
                    var id = stuId+"_"+expId;
                    alert("删除成功");
                    $("#"+id).remove();
                } else{
                    alert("删除失败");
                }
            }, error: function () {
                alert("向服务器请求数据失败")
            }
        })
    }
}


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

function searchStu(isExport){
    $("#stu_score").empty();
    var stuNum = $("#stu_num").val();
    var expId = $("#exp").find("option:selected").val();
    $.ajax({
        type:"get",
        url:"/manage/score/get_scorelist_stunum.do",
        dataType:"json",
        data:{
            stuNum: stuNum,
            expId: expId,
            isExport: isExport
        },
        success:function(res){
            if(res.status === 0){
                $.each(res.data, function (idx, val) {
                    var str = "                                    <tr>\n" +
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
                    alert("删除成功");
                    searchStu(0);
                } else{
                    alert("删除失败");
                    searchStu(0);
                }
            }, error: function () {
                alert("向服务器请求数据失败")
            }
        })
    }
}

$("#search").click(function () {
    searchStu(0)
})

$("#export").click(function () {
    searchStu(1)
})


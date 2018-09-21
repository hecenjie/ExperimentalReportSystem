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
                        "                                        <td>"+val.expName+"</td>\n" +
                        "                                        <td>"+val.score+"</td>\n" +
                        "                                        <td><a href=\"#\">删除</a></td>\n" +
                        "                                    </tr>"
                    $("#stu_score").append(str)
                })
            }
        }, error:function() {
            alert("向服务器请求数据失败")
        }
    })
}

$("#search").click(function () {
    searchStu(0)
})

$("#export").click(function () {
    searchStu(1)
})
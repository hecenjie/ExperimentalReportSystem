function submit() {
    var selectval = new Array();
    var num;
    for(var i = 1; i <= 11; i++){
        if(i <= 9)
            num = "0" + i + "";
        else
            num = i;
        selectval[i - 1] = $("#choice_" + num).val();
    }

    $.ajax({
        type:"POST",
        url:"/exp/submit_Exp.do",
        data:{
            selectval:selectval
        },
        dataType:"json",
        success:function (result) {
            alert("提交成功");
        },
        error:function (result) {
            alert("向服务器请求数据失败" + result);
        }
    });

}
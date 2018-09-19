function openExp(expId){
    $.ajax({
        type:"post",
        url:"/manage/exp/open_exp.do",
        dataType:"json",
        data:{
            expId: expId
        },
        success:function(res){
            if(res.status === 0){
                alert("开放实验成功");
            } else if(res.status === 2){
                location.href = "login.html";
            } else{
                alert("开放实验失败");
                location.reload();
            }
        }, error:function() {
            alert("向服务器请求数据失败")
        }
    })
}

function closeExp(expId){
    $.ajax({
        type:"post",
        url:"/manage/exp/close_exp.do",
        dataType:"json",
        data:{
            expId: expId
        },
        success:function(res){
            if(res.status === 0){
                alert("关闭实验成功");
            } else if(res.status === 2){
                location.href = "login.html";
            } else{
                alert("关闭实验失败");
                location.reload();
            }
        }, error:function() {
            alert("向服务器请求数据失败")
        }
    })
}
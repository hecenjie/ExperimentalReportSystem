function register() {
    $.ajax({
        type:"get",
        url:"http://www.nanwulife.com/good/good_detail.do",
        dataType:"json",
        data: {
            "username": $("#form-username").val,
            "password": $("#form-password").val,
            "passwordCheck": $("#form-passwordCheck").val,
            "majorId": $("#form-majorId").val,
            "stuClass": $("#form-class").val,
        },
        success:function(res){

        }, error:function() {
            alert("向服务器请求数据失败")
        }
    })
}
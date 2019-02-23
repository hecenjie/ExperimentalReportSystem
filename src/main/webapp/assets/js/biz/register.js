$(function () {
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

function register() {
    $.ajax({
        type:"post",
        url:"/user/register.do",
        dataType:"json",
        data: {
            "username": $("#form-username").val(),
            "password": $("#form-password").val(),
            "passwordCheck": $("#form-passwordCheck").val(),
            "majorId": $("#major").find("option:selected").val(),
            "stuClass": $("#form-class").val(),
            "stuName": $("#form-name").val(),
        },
        success:function(res){
            if(res.status === 0){
                alert("注册成功！")
                location.href="index.html"
            } else{
                if(res.status === 4){
                    alert("两次密码输入不正确")
                } else if(res.status === 7){
                    alert("该用户已存在")
                } else if(res.status === 3){
                    alert("注册信息格式有误")
                } else{
                    alert("注册失败")
                }
            }

        }, error:function() {
            alert("向服务器请求数据失败")
        }
    })
}

function edit() {
    $.ajax({
        type:"post",
        url:"/user/edit.do",
        dataType:"json",
        data: {
            "id": $("#form-id").val(),
            "role": $("#form-role").val(),
            "username": $("#form-username").val(),
            "username1": $("#form-username1").val(),
            "password": $("#form-password").val(),
            "passwordCheck": $("#form-passwordCheck").val(),
            "majorId": $("#major").find("option:selected").val(),
            "stuClass": $("#form-class").val(),
            "stuName": $("#form-name").val()
        },
        success:function(res){
            if(res.status === 0){
                alert("修改成功！");
                location.href="/index.html"
            } else{
                if(res.status === 4){
                    alert("两次密码输入不正确")
                } else if(res.status === 7){
                    alert("该用户已存在")
                } else if(res.status === 3){
                    alert("修改信息格式有误")
                } else{
                    alert("修改失败")
                }
            }

        }, error:function() {
            alert("向服务器请求数据失败")
        }
    })
}

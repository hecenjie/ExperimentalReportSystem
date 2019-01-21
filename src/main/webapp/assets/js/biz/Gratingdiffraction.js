function submit() {
    var selectval = new Array();
    var table = new Array();
    var blank = new Array();

    for (var i = 1; i <= 13; i++) {
        selectval[i - 1] = $("#choice_" + i + "").val();
    }

    for (var i = 1; i <= 16; i++) {
        table[i - 1] = $("#table_" + i + "").val();
    }

    for (var i = 1; i <= 12; i++) {
        blank[i - 1] = $("#blank_" + i + "").val();
    }

    $.ajax({
        type: "POST",
        url: "/sub/Exp_03.do",
        data: {
            selectval: selectval,
            blank: blank,
            table: table
        },
        async: false,
        dataType: "json",
        success: function (result) {
            if (result.status === 15)
                alert("请勿多次提交试验");
            else {
                alert("提交成功");
                location.href="../index.html"
            }
        },
        error: function (result) {
            alert("向服务器请求数据失败" + result);
        }
    });
}



function submitAll() {
    if (confirm("为避免数据丢失，提交前请先将实验数据截图，确认提交吗？")) {
        submit();
    }
}

$(function () {
        $.ajax({
            type: "GET",
            url: "/exp/get_exp_status.do",
            data: {
                expId: 3
            },
            dataType: "json",
            success: function (result) {
                if (result.status === 10) {
                    alert("实验已关闭，请联系实验老师");
                    location.href = "../index.html";
                }
            },
            error: function (result) {
                alert("向服务器请求数据失败" + result);
            }
        });

        $.ajax({
            type: "GET",
            url: "/score/is_stu_have_score.do",
            data: {
                expId: 3
            },
            dataType: "json",
            success: function (res) {
                if(res.status === 0){
                    //用户未提交过此实验
                } else if (res.status === 2) {
                    location.href = "../login.html";
                } else if(res.status === 15){
                    alert("您已提交过此实验，如有疑问请联系实验老师");
                    location.href = "../index.html";
                } else{
                    alert("服务器发生错误");
                }
            },
            error: function (res) {
                alert("向服务器请求数据失败" + res);
            }
        });
    }
)
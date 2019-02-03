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
            else if (result.status === 10)
                alert("实验已关闭,如有疑问请联系实验老师");
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

function autoGenera1() {
    yanshejiao(4, 5, 12, 13, 1);
    yanshejiao(1, 8, 9, 16, 2);
    yanshejiao(3, 6, 11, 14, 3);
    yanshejiao(2, 7, 10, 15, 4);
}

function yanshejiao(i1, i2, i3, i4, j){
    var x1 = parseFloat($("#table_" + i1).val());
    var x2 = parseFloat($("#table_" + i2).val());
    var x3 = parseFloat($("#table_" + i3).val());
    var x4 = parseFloat($("#table_" + i4).val());
    var left = Math.abs(x1 - x2) > 300 ?  360 - Math.abs(x1 - x2) :  Math.abs(x1 - x2);
    var right = Math.abs(x3 - x4) > 300 ?  360 - Math.abs(x3 - x4) :  Math.abs(x3 - x4);
    var res = (left + right) / 4;
    $("#blank_" + j).val(res.toFixed(2));
}

function autoGenera2() {
    dg1();
    dg2();
    d();
    E1();
    ly1();
    E2();
    ly2()
    E3();
}

function dg1() {
    var up = 546.1;
    var g = parseFloat($("#blank_1").val());
    var down = Math.sin(g * 0.017453293);
    var res = up / down;
    $("#blank_5").val(res.toFixed(2));
}

function dg2() {
    var up = 546.1 * 2;
    var g = parseFloat($("#blank_2").val());
    var down = Math.sin(g * 0.017453293);
    var res = up / down;
    $("#blank_6").val(res.toFixed(2));
}

function d() {
    var up = parseFloat($("#blank_5").val()) +  parseFloat($("#blank_6").val());
    var res = up / 2;
    $("#blank_7").val(res.toFixed(2));
}

function E1() {
    var d = parseFloat($("#blank_7").val());
    var up = Math.abs(d - 3300) * 100;
    var down = 3300;
    var res = up / down;
    $("#blank_8").val(res.toFixed(2));
}

function ly1() {
    var d = parseFloat($("#blank_7").val());
    var y = parseFloat($("#blank_3").val());
    var res = d * Math.sin(y * 0.017453293);
    $("#blank_9").val(res.toFixed(2));
}

function E2() {
    var y = parseFloat($("#blank_9").val());
    var up = Math.abs(y - 577) * 100;
    var res = up / 577;
    $("#blank_10").val(res.toFixed(2));
}

function ly2() {
    var d = parseFloat($("#blank_7").val());
    var y = parseFloat($("#blank_4").val());
    var res = d * Math.sin(y * 0.017453293);
    $("#blank_11").val(res.toFixed(2));
}

function E3() {
    var y = parseFloat($("#blank_11").val());
    var up = Math.abs(y - 579.1) * 100;
    var res = up / 579.1;
    $("#blank_12").val(res.toFixed(2));
}
function autoGenera(){
    for(var i = 1; i <= 21; i += 4){
        var j = i + 1;
        Di(i, j);
    }
    for(var i = 24; i <= 33; i += 3){
        var j = i + 1;
        Di(i, j);
    }
    Di5Di();
    Di5DiAvg();
    R();
    O();
}

function Di(i, j){
    var xl = $("#table_"+i).val();
    var xr = $("#table_"+j).val();
    var di = Math.abs(xl - xr);
    var index = j + 1;
    $("#table_"+ index).val(di.toFixed(3));
}

function Di5Di(){
    var d5 = $("#table_3").val();
    var d6 = $("#table_7").val();
    var d7 = $("#table_11").val();
    var d8 = $("#table_15").val();
    var d9 = $("#table_19").val();
    var d10 = $("#table_23").val();
    var d11 = $("#table_26").val();
    var d12 = $("#table_29").val();
    var d13 = $("#table_32").val();
    var d14 = $("#table_35").val();

    var d10d5 = d10 * d10 - d5 * d5;
    var d11d6 = d11 * d11 - d6 * d6;
    var d12d7 = d12 * d12 - d7 * d7;
    var d13d8 = d13 * d13 - d8 * d8;
    var d14d9 = d14 * d14 - d9 * d9;

    $("#table_4").val(d10d5.toFixed(3));
    $("#table_8").val(d11d6.toFixed(3));
    $("#table_12").val(d12d7.toFixed(3));
    $("#table_16").val(d13d8.toFixed(3));
    $("#table_20").val(d14d9.toFixed(3));
}

function Di5DiAvg(){
    var d10d5 = parseFloat($("#table_4").val());
    var d11d6 = parseFloat($("#table_8").val());
    var d12d7 = parseFloat($("#table_12").val());
    var d13d8 = parseFloat($("#table_16").val());
    var d14d9 = parseFloat($("#table_20").val());

    var up = d10d5 + d11d6 + d12d7 + d13d8 + d14d9;
    var avg = up / 5;

    // console.log(d10d5);
    // console.log(d11d6);
    // console.log(d12d7);
    // console.log(d13d8);
    // console.log(d14d9);
    // console.log(up);
    // console.log(avg);

    $("#blank_1").val(avg.toFixed(3));
}

function R() {
    var avg = parseFloat($("#blank_1").val());
    var r = avg / 11.786;
    $("#blank_2").val(r.toFixed(2));
}

function O() {
    var D = parseFloat($("#blank_1").val());
    var D5 = parseFloat($("#table_4").val());
    var D6 = parseFloat($("#table_8").val());
    var D7 = parseFloat($("#table_12").val());
    var D8 = parseFloat($("#table_16").val());
    var D9 = parseFloat($("#table_20").val());

    var up = (D5 - D) * (D5 - D) + (D6 - D) * (D6 - D) + (D7 - D) * (D7 - D) + (D8 - D) * (D8 - D) + (D9 - D) * (D9 - D);
    var res = Math.sqrt(up / 4);
    $("#blank_3").val(res.toFixed(2));
}




$(function () {
        $.ajax({
            type: "GET",
            url: "/exp/get_exp_status.do",
            data: {
                expId: 7
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
                expId: 7
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


function submitAll() {
    if (confirm("为避免数据丢失，提交前请先将实验数据截图，确认提交吗？")) {
        submit();
    }
}

function submit() {
    var choice = new Array();
    var blank = new Array();
    var table = new Array();


    for (var i = 1; i <= 18; i++) {
        choice[i-1] = $("#choice_" + i + "").val();
    }

    for (var i = 1; i <= 3; i++) {
        blank[i-1] = $("#blank_" + i + "").val();
    }

    for (var i = 1; i <= 35; i++) {
        table[i-1] = $("#table_" + i + "").val();
    }

    $.ajax({
        type: "POST",
        url: "/sub/Exp_07.do",
        data: {
            choice: choice,
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
            alert("服务器请求失败，请确认数据填写正确且完整后重试" + result);
        }
    });
}

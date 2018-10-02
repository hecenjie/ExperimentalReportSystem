var chart1;
var chart2;
var chart3;
var chart4;

var table1_x1=50;
var table1_x2=50;
var table1_x3=50;
var table1_x4=50;
var table1_x5=50;
var table1_x6=50;
var table1_x7=50;
var table1_x8=50;
var table1_x9=50;
var table1_x10=50;
var table1_x11=50;
var table1_x12=50;
var table1_x13=50;
var table1_x14=50;
var table1_x15=50;
var table1_x16=50;
var table1_x17=50;
var table1_x18=50;
var table1_x19=50;
var table1_x20=50;
var table1_x21=50;

var table2_x1=0.5;
var table2_x2=1.0;
var table2_x3=1.5;
var table2_x4=2.0;
var table2_x5=2.5;
var table2_x6=3.0;
var table2_x7=3.5;
var table2_x8=4.0;
var table2_x9=4.5;
var table2_x10=5.0;
var table2_x11=50;
var table2_x12=50;
var table2_x13=50;
var table2_x14=50;
var table2_x15=50;
var table2_x16=50;
var table2_x17=50;
var table2_x18=50;
var table2_x19=50;
var table2_x20=50;
var table2_x21=50;
var table2_x22=50;
var table2_x23=50;
var table2_x24=50;
var table2_x25=50;
var table2_x26=50;
var table2_x27=50;

function generateChart1() {
}

function generateChart2() {
}

function generateChart3() {
}

function generateChart4() {
}

function uploadChart(chart, index) {
    // 获取Canvas的编码。
    var imgData = document.getElementById(chart).toDataURL("image/png");

    // 上传到后台。
    $.ajax({
        type: "post",
        url: "/exp/upload_chart.do",
        data: {
            image: imgData.substring(22),
            expId: 1,
            index: index
        },
        async: false,
        success: function (res) {
            // alert(res.status);
        },
        error: function (res) {
            alert("向服务器请求数据失败" + res.msg)
        }

    })
}


$(function () {
        $.ajax({
            type: "GET",
            url: "/exp/get_exp_status.do",
            data: {
                expId: 1
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
                expId: 1
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
var chart1;
var chart2;
var chart3;
var chart4;

var table1_x1=50;
var table1_x2=100;
var table1_x3=200;
var table1_x4=300;
var table1_x5=400;
var table1_x6=500;
var table1_x7=600;
var table1_x8=700;
var table1_x9=800;
var table1_x10=900;
var table1_x11=1000;
var table1_x12=1100;
var table1_x13=1200;
var table1_x14=1300;
var table1_x15=1400;
var table1_x16=1500;
var table1_x17=1600;
var table1_x18=1700;
var table1_x19=1800;
var table1_x20=1900;
var table1_x21=2000;

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
var table2_x11=5.1;
var table2_x12=5.2;
var table2_x13=5.3;
var table2_x14=5.4;
var table2_x15=5.5;
var table2_x16=5.6;
var table2_x17=5.7;
var table2_x18=5.8;
var table2_x19=5.9;
var table2_x20=6.0;
var table2_x21=6.1;
var table2_x22=6.2;
var table2_x23=6.3;
var table2_x24=6.4;
var table2_x25=6.5;
var table2_x26=6.7;
var table2_x27=6.8;

function generateChart1() {
    var table1_u1 = $("#table1_01").val();
    var table1_u2 = $("#table1_03").val();
    var table1_u3 = $("#table1_05").val();
    var table1_u4 = $("#table1_07").val();
    var table1_u5 = $("#table1_09").val();
    var table1_u6 = $("#table1_11").val();
    var table1_u7 = $("#table1_13").val();
    var table1_u8 = $("#table1_15").val();
    var table1_u9 = $("#table1_17").val();
    var table1_u10 = $("#table1_19").val();
    var table1_u11 = $("#table1_21").val();
    var table1_u12 = $("#table1_23").val();
    var table1_u13 = $("#table1_25").val();
    var table1_u14 = $("#table1_27").val();
    var table1_u15 = $("#table1_29").val();
    var table1_u16 = $("#table1_31").val();
    var table1_u17 = $("#table1_33").val();
    var table1_u18 = $("#table1_35").val();
    var table1_u19 = $("#table1_37").val();
    var table1_u20 = $("#table1_39").val();
    var table1_u21 = $("#table1_41").val();

    var table1_y1=pu2r(table1_u1, table1_x1);
    var table1_y2=pu2r(table1_u2, table1_x2);
    var table1_y3=pu2r(table1_u3, table1_x3);
    var table1_y4=pu2r(table1_u4, table1_x4);
    var table1_y5=pu2r(table1_u5, table1_x5);
    var table1_y6=pu2r(table1_u6, table1_x6);
    var table1_y7=pu2r(table1_u7, table1_x7);
    var table1_y8=pu2r(table1_u8, table1_x8);
    var table1_y9=pu2r(table1_u9, table1_x9);
    var table1_y10=pu2r(table1_u10, table1_x10);
    var table1_y11=pu2r(table1_u11, table1_x11);
    var table1_y12=pu2r(table1_u12, table1_x12);
    var table1_y13=pu2r(table1_u13, table1_x13);
    var table1_y14=pu2r(table1_u14, table1_x14);
    var table1_y15=pu2r(table1_u15, table1_x15);
    var table1_y16=pu2r(table1_u16, table1_x16);
    var table1_y17=pu2r(table1_u17, table1_x17);
    var table1_y18=pu2r(table1_u18, table1_x18);
    var table1_y19=pu2r(table1_u19, table1_x19);
    var table1_y20=pu2r(table1_u20, table1_x20);
    var table1_y21=pu2r(table1_u21, table1_x21);

    $("#table1_02").val(table1_y1);
    $("#table1_04").val(table1_y2);
    $("#table1_06").val(table1_y3);
    $("#table1_08").val(table1_y4);
    $("#table1_10").val(table1_y5);
    $("#table1_12").val(table1_y6);
    $("#table1_14").val(table1_y7);
    $("#table1_16").val(table1_y8);
    $("#table1_18").val(table1_y9);
    $("#table1_20").val(table1_y10);
    $("#table1_22").val(table1_y11);
    $("#table1_24").val(table1_y12);
    $("#table1_26").val(table1_y13);
    $("#table1_28").val(table1_y14);
    $("#table1_30").val(table1_y15);
    $("#table1_32").val(table1_y16);
    $("#table1_34").val(table1_y17);
    $("#table1_36").val(table1_y18);
    $("#table1_38").val(table1_y19);
    $("#table1_40").val(table1_y20);
    $("#table1_42").val(table1_y21);

    var ctx = document.getElementById('chart1').getContext('2d');
    chart1 = new Chart(ctx, {
        type: 'line',
        data: {
            labels: [
                table1_x1, table1_x2, table1_x3, table1_x4, table1_x5, table1_x6, table1_x7, table1_x8, table1_x9, table1_x10,
                table1_x11, table1_x12, table1_x13, table1_x14, table1_x15, table1_x16, table1_x17, table1_x18, table1_x19, table1_x20,
                table1_x21
            ],
            datasets: [{
                label: "P=U^2/R",
                backgroundColor: 'rgb(255, 99, 132)',
                borderColor: 'rgb(255, 99, 132)',
                data: [
                    table1_y1, table1_y2, table1_y3, table1_y4, table1_y5, table1_y6, table1_y7, table1_y8, table1_y9, table1_y10,
                    table1_y11, table1_y12, table1_y13, table1_y14, table1_y15, table1_y16, table1_y17, table1_y18, table1_y19, table1_y20,
                    table1_y21
                ],
                fill: false,
            }]
        },
        options: {
            responsive: true,
            title: {
                display: true,
                text: 'P-R曲线图'
            },
            tooltips: {
                mode: 'index',
                intersect: false,
            },
            hover: {
                mode: 'nearest',
                intersect: true
            },
            scales: {
                xAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'R'
                    }
                }],
                yAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'P'
                    }
                }]
            }
        }
    });

}

function generateChart2() {
}

function generateChart3() {
    var table3_u1 = $("#table3_01").val();
    var table3_u2 = $("#table3_03").val();
    var table3_u3 = $("#table3_05").val();
    var table3_u4 = $("#table3_07").val();
    var table3_u5 = $("#table3_09").val();
    var table3_u6 = $("#table3_11").val();
    var table3_u7 = $("#table3_13").val();
    var table3_u8 = $("#table3_15").val();
    var table3_u9 = $("#table3_17").val();
    var table3_u10 = $("#table3_19").val();
    var table3_u11 = $("#table3_21").val();
    var table3_u12 = $("#table3_23").val();
    var table3_u13 = $("#table3_25").val();
    var table3_u14 = $("#table3_27").val();
    var table3_u15 = $("#table3_29").val();
    var table3_u16 = $("#table3_31").val();
    var table3_u17 = $("#table3_33").val();
    var table3_u18 = $("#table3_35").val();
    var table3_u19 = $("#table3_37").val();
    var table3_u20 = $("#table3_39").val();
    var table3_u21 = $("#table3_41").val();

    var table3_y1=pu2r(table3_u1, table1_x1);
    var table3_y2=pu2r(table3_u2, table1_x2);
    var table3_y3=pu2r(table3_u3, table1_x3);
    var table3_y4=pu2r(table3_u4, table1_x4);
    var table3_y5=pu2r(table3_u5, table1_x5);
    var table3_y6=pu2r(table3_u6, table1_x6);
    var table3_y7=pu2r(table3_u7, table1_x7);
    var table3_y8=pu2r(table3_u8, table1_x8);
    var table3_y9=pu2r(table3_u9, table1_x9);
    var table3_y10=pu2r(table3_u10, table1_x10);
    var table3_y11=pu2r(table3_u11, table1_x11);
    var table3_y12=pu2r(table3_u12, table1_x12);
    var table3_y13=pu2r(table3_u13, table1_x13);
    var table3_y14=pu2r(table3_u14, table1_x14);
    var table3_y15=pu2r(table3_u15, table1_x15);
    var table3_y16=pu2r(table3_u16, table1_x16);
    var table3_y17=pu2r(table3_u17, table1_x17);
    var table3_y18=pu2r(table3_u18, table1_x18);
    var table3_y19=pu2r(table3_u19, table1_x19);
    var table3_y20=pu2r(table3_u20, table1_x20);
    var table3_y21=pu2r(table3_u21, table1_x21);

    $("#table3_02").val(table3_y1);
    $("#table3_04").val(table3_y2);
    $("#table3_06").val(table3_y3);
    $("#table3_08").val(table3_y4);
    $("#table3_10").val(table3_y5);
    $("#table3_12").val(table3_y6);
    $("#table3_14").val(table3_y7);
    $("#table3_16").val(table3_y8);
    $("#table3_18").val(table3_y9);
    $("#table3_20").val(table3_y10);
    $("#table3_22").val(table3_y11);
    $("#table3_24").val(table3_y12);
    $("#table3_26").val(table3_y13);
    $("#table3_28").val(table3_y14);
    $("#table3_30").val(table3_y15);
    $("#table3_32").val(table3_y16);
    $("#table3_34").val(table3_y17);
    $("#table3_36").val(table3_y18);
    $("#table3_38").val(table3_y19);
    $("#table3_40").val(table3_y20);
    $("#table3_42").val(table3_y21);

    var ctx = document.getElementById('chart3').getContext('2d');
    chart3 = new Chart(ctx, {
        type: 'line',
        data: {
            labels: [
                table1_x1, table1_x2, table1_x3, table1_x4, table1_x5, table1_x6, table1_x7, table1_x8, table1_x9, table1_x10,
                table1_x11, table1_x12, table1_x13, table1_x14, table1_x15, table1_x16, table1_x17, table1_x18, table1_x19, table1_x20,
                table1_x21
            ],
            datasets: [{
                label: "P=U^2/R",
                backgroundColor: 'rgb(255, 99, 132)',
                borderColor: 'rgb(255, 99, 132)',
                data: [
                    table3_y1, table3_y2, table3_y3, table3_y4, table3_y5, table3_y6, table3_y7, table3_y8, table3_y9, table3_y10,
                    table3_y11, table3_y12, table3_y13, table3_y14, table3_y15, table3_y16, table3_y17, table3_y18, table3_y19, table3_y20,
                    table3_y21
                ],
                fill: false,
            }]
        },
        options: {
            responsive: true,
            title: {
                display: true,
                text: 'P-R曲线图'
            },
            tooltips: {
                mode: 'index',
                intersect: false,
            },
            hover: {
                mode: 'nearest',
                intersect: true
            },
            scales: {
                xAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'R'
                    }
                }],
                yAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'P'
                    }
                }]
            }
        }
    });

}

function generateChart4() {
}

function pu2r(u, r) {
    var p;
    p = (u * u) /r;
    return p.toFixed(4);
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
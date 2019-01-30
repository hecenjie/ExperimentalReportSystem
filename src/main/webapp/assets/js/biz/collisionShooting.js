function theoreticalH(){
    var x = 20.4;
    var y = $("#table_1_2").val();
    var th = (x * x) / (4 * y);
    return th;
}

function addedH(){
    var x = 20.4;
    var y = $("#table_1_2").val();
    var avgX = $("#table_1_4").val();
    var ah = x * (x - avgX) / (2 * y);
    return ah;
}

function autoGenera1(){
    var th = theoreticalH();
    var ah = addedH();
    $("#table_1_3").val(th);
    $("#table_1_5").val(ah);
}

function autoGenera2(){
    var th = $("#table_1_3").val();
    var rh = $("#table_1_6").val();
    var m = $("#table_1_1").val();
    var deltaE = m * 9.8 * (rh - th) * 1E-5;
    var deltaE2 = (rh - th) * 100 / th;
    $("#blank_1").val(deltaE);
    $("#blank_2").val(deltaE2);
}

// 沟通中，暂时取消
// function generateChart1() {
//     // var x1 = $("#table_2_1").val()-20.4;
//     // var x2 = $("#table_2_2").val()-20.4;
//     // var x3 = $("#table_2_3").val()-20.4;
//     // var x4 = $("#table_2_4").val()-20.4;
//     // var x5 = $("#table_2_5").val()-20.4;
//     // var x6 = $("#table_2_6").val()-20.4;
//
//     var x1 = $("#table_2_1").val();
//     var x2 = $("#table_2_2").val();
//     var x3 = $("#table_2_3").val();
//     var x4 = $("#table_2_4").val();
//     var x5 = $("#table_2_5").val();
//     var x6 = $("#table_2_6").val();
//
//     var z1 = $("#table_2_7").val();
//     var z2 = $("#table_2_8").val();
//     var z3 = $("#table_2_9").val();
//     var z4 = $("#table_2_10").val();
//     var z5 = $("#table_2_11").val();
//     var z6 = $("#table_2_12").val();
//
//     console.log(x1);
//     console.log(x2);
//     console.log(x3);
//     console.log(x4);
//     console.log(x5);
//     console.log(x6);
//
//
//     var ctx = document.getElementById('chart1').getContext('2d');
//
//     chart1 = new Chart(ctx, {
//         type: 'line',
//         data: {
//             // labels: [x1, x2, x3, x4, x5, x6],
//             // labels: [-0.8, -0.7, -0.6, -0.5, -0.4, -0.3, -0.2, -0.1, 0, 0.1, 0.2, 0.3],
//             labels: [12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22],
//             datasets: [{
//                 label: "拟合直线",
//                 backgroundColor: 'rgb(255, 99, 132)',
//                 borderColor: 'rgba(255, 255, 255, 0)',
//                 data: [
//                     {x: x1, y: z1}, {x: x2, y: z2},{x: x3, y: z3},{x: x4, y: z4}, {x: x5, y: z5},{x: x6, y: z6}
//                 ],
//                 fill: false,
//             }]
//         },
//         options: {
//             responsive: true,
//             title: {
//                 display: true,
//                 text: '实验曲线图'
//             },
//             tooltips: {
//                 mode: 'index',
//                 intersect: false,
//             },
//             hover: {
//                 mode: 'nearest',
//                 intersect: true
//             },
//             scales: {
//                 xAxes: [{
//                     display: true,
//                     scaleLabel: {
//                         display: true,
//                         labelString: 'x'
//                     }
//                 }],
//                 yAxes: [{
//                     display: true,
//                     scaleLabel: {
//                         display: true,
//                         labelString: 'z'
//                     }
//                 }]
//             }
//         }
//     });
// }

function submitAll() {
    if (confirm("为避免数据丢失，提交前请先将实验数据截图，确认提交吗？")) {
        submit();
    }
}

function submit(){
    var choice = new Array();
    var table1 = new Array();
    var table2 = new Array();
    var blank = new Array();


    for (var i = 1; i <= 10; i++) {
        choice[i - 1] = $("#choice_" + i + "").val();
    }

    for (var i = 1; i <= 6; i++) {
        table1[i - 1] = $("#table_1_" + i + "").val();
    }

    for (var i = 1; i <= 12; i++) {
        table2[i - 1] = $("#table_2_" + i + "").val();
    }

    for (var i = 1; i <= 2; i++) {
        blank[i - 1] = $("#blank_" + i + "").val();
    }

    $.ajax({
        type: "POST",
        url: "/sub/Exp_06.do",
        data: {
            choice: choice,
            blank: blank,
            table1: table1,
            table2: table2
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


$(function () {
        $.ajax({
            type: "GET",
            url: "/exp/get_exp_status.do",
            data: {
                expId: 6
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
                expId: 6
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
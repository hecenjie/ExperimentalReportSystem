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

function generateChart1() {
    var x1 = $("#table_2_1").val();
    var x2 = $("#table_2_2").val();
    var x3 = $("#table_2_3").val();
    var x4 = $("#table_2_4").val();
    var x5 = $("#table_2_5").val();
    var x6 = $("#table_2_6").val();

    var z1 = $("#table_2_7").val();
    var z2 = $("#table_2_8").val();
    var z3 = $("#table_2_9").val();
    var z4 = $("#table_2_10").val();
    var z5 = $("#table_2_11").val();
    var z6 = $("#table_2_12").val();

    var ctx = document.getElementById('chart1').getContext('2d');

    chart1 = new Chart(ctx, {
        type: 'line',
        data: {
            labels: [x1, x2, x3, x4],
            datasets: [{
                label: "拟合直线",
                backgroundColor: 'rgb(255, 99, 132)',
                borderColor: 'rgb(255, 99, 132)',
                data: [
                    {x: x1, y: z1}, {x: x2, y: z2},{x: x3, y: z3}
                ],
                fill: false,
            }, {
                label: "原始数据",
                backgroundColor: 'rgb(105,105,105)',
                borderColor: 'rgba(255, 255, 255, 0)',
                data: [
                    {x: x4, y: z4}, {x: x5, y: z5},{x: x6, y: z6}
                ],
                fill: false,
            }]
        },
        options: {
            responsive: true,
            title: {
                display: true,
                text: '实验曲线图'
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
                        labelString: 'd^2 mm'
                    }
                }],
                yAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: '转动惯量 J'
                    }
                }]
            }
        }
    });
}
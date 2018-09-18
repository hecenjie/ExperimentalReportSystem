function generateChart1() {
    // window.chart.update();

    var pic1BeginY = parseFloat($("#chart1_index1").val());
    // var pic1BeginY = 0.47;
    console.info('pic1BeginY = '+pic1BeginY);
    var pic1IndexY2 = parseFloat($("#chart1_index2").val());
    // var pic1IndexY2 = 0.58;
    console.info('pic1IndexY2 = '+pic1IndexY2);
    var pic1IndexY3 = parseFloat($("#chart1_index3").val());
    // var pic1IndexY3 = 1.15;
    console.info('pic1IndexY3 = '+pic1IndexY3);
    var pic1IndexY4 = parseFloat($("#chart1_index4").val());
    // var pic1IndexY4 = 1.412;
    console.info('pic1IndexY4 = '+pic1IndexY4);
    var pic1EndY = parseFloat($("#chart1_index5").val());
    // var pic1EndY = 1.723;
    console.info('pic1EndY = '+pic1EndY);
    var res = fitting(pic1BeginY, pic1IndexY2, pic1IndexY3, pic1IndexY4, pic1EndY);
    var k = res[0];
    var b = res[1];
    console.info("k = " + k);
    console.info("b = " + b);
    var fitBeginY = k * pic1BeginX + b;
    var fitEndY = k * pic1EndX + b;
    console.info("fitBeginY = " + fitBeginY);
    console.info("fitEndY = " + fitEndY);
    var ctx = document.getElementById('chart1').getContext('2d');

    var chart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: [pic1BeginX, pic1IndexX2, pic1IndexX3, pic1IndexX4, pic1EndX],
            datasets: [{
                label: "拟合直线",
                backgroundColor: 'rgb(255, 99, 132)',
                borderColor: 'rgb(255, 99, 132)',
                data: [
                    {x: pic1BeginX, y: fitBeginY}, {x: pic1EndX, y: fitEndY}
                ],
                fill: false,
            }, {
                label: "原始数据",
                backgroundColor: 'rgb(0, 0, 0)',
                borderColor: 'rgb(255, 255, 255)',
                data: [
                    pic1BeginY, pic1IndexY2, pic1IndexY3, pic1IndexY4, pic1EndY
                ],
                fill: false,
            }]
        },
        options: {
            responsive: true,
            title:{
                display:true,
                text:'实验曲线图'
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
                        labelString: '频率V（10E+14Hz）'
                    }
                }],
                yAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: '截止电压Uo（V）'
                    }
                }]
            }
        }
    });
}

var pic1BeginX = 5.196;
var pic1IndexX2 = 5.490;
var pic1IndexX3 = 6.879;
var pic1IndexX4 = 7.408
var pic1EndX = 8.214;

function fitting(u1, u2, u3, u4, u5){
    var v1 = pic1BeginX;
    var v2 = pic1IndexX2;
    var v3 = pic1IndexX3;
    var v4 = pic1IndexX4;
    var v5 = pic1EndX;

    var sumV = v1 + v2 + v3 + v4 + v5;
    console.info("sumV = " + sumV)
    var sumU = u1 + u2 + u3 + u4 + u5;
    console.info("sumU = " + sumU)
    var avgV = sumV / 5;
    console.info("avgV = " + avgV)
    var avgU = sumU / 5;
    console.info("avgU = " + avgU)
    var avgMulUV = (v1*u1 + v2*u2 + v3*u3 + v4*u4 + v5*u5) / 5;
    console.info("avgMulUV = " + avgMulUV)
    var avgMulVV = (v1*v1 + v2*v2 + v3*v3 + v4*v4 + v5*v5) / 5;
    console.info("avgMulVV = " + avgMulVV)

    var k = (avgV*avgU -avgMulUV) / (avgV*avgV - avgMulVV);

    var b = u1 - k * v1;

    return [k, b];
}

function uploadChart(chart){
    // 获取Canvas的编码。
    var imgData = document.getElementById(chart).toDataURL("image/jpeg");
}




function submit() {
    var selectval = new Array();
    var num;
    for(var i = 1; i <= 11; i++){
        if(i <= 9)
            num = "0" + i + "";
        else
            num = i;
        selectval[i - 1] = $("#choice_" + num).val();
    }

    $.ajax({
        type:"POST",
        url:"/exp/submit_Exp.do",
        data:{
            selectval:selectval
        },
        dataType:"json",
        success:function (result) {
            alert("提交成功");
        },
        error:function (result) {
            alert("向服务器请求数据失败" + result);
        }
    });
}
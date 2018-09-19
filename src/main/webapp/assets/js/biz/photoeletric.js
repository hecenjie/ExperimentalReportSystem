var chart1;
var chart2;

function generateChart1() {
    // if(chart1 !== undefined){
    //     chart1.update();
    // }

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

    chart1 = new Chart(ctx, {
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
                backgroundColor: 'rgb(105,105,105)',
                borderColor: 'rgba(255, 255, 255, 0)',
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

function generateChart2() {

    // if(chart2 !== undefined){
    //     window.chart2.update();
    // }

    var table2_01 = parseFloat($("#table2_01").val());
    var table2_02 = parseFloat($("#table2_02").val());
    var table2_03 = parseFloat($("#table2_03").val());
    var table2_04 = parseFloat($("#table2_04").val());
    var table2_05 = parseFloat($("#table2_05").val());
    var table2_06 = parseFloat($("#table2_06").val());
    var table2_07 = parseFloat($("#table2_07").val());
    var table2_08 = parseFloat($("#table2_08").val());
    var table2_09 = parseFloat($("#table2_09").val());
    var table2_10 = parseFloat($("#table2_10").val());
    var table2_11 = parseFloat($("#table2_11").val());
    var table2_12 = parseFloat($("#table2_12").val());
    var table2_13 = parseFloat($("#table2_13").val());
    var table2_14 = parseFloat($("#table2_14").val());
    var table2_15 = parseFloat($("#table2_15").val());
    var table2_16 = parseFloat($("#table2_16").val());
    var table2_17 = parseFloat($("#table2_17").val());
    var table2_18 = parseFloat($("#table2_18").val());
    var table2_19 = parseFloat($("#table2_19").val());
    var table2_20 = parseFloat($("#table2_20").val());
    var table2_21 = parseFloat($("#table2_21").val());
    var table2_22 = parseFloat($("#table2_22").val());

    var table3_01 = parseFloat($("#table3_01").val());
    var table3_02 = parseFloat($("#table3_02").val());
    var table3_03 = parseFloat($("#table3_03").val());
    var table3_04 = parseFloat($("#table3_04").val());
    var table3_05 = parseFloat($("#table3_05").val());
    var table3_06 = parseFloat($("#table3_06").val());
    var table3_07 = parseFloat($("#table3_07").val());
    var table3_08 = parseFloat($("#table3_08").val());
    var table3_09 = parseFloat($("#table3_09").val());
    var table3_10 = parseFloat($("#table3_10").val());
    var table3_11 = parseFloat($("#table3_11").val());
    var table3_12 = parseFloat($("#table3_12").val());
    var table3_13 = parseFloat($("#table3_13").val());
    var table3_14 = parseFloat($("#table3_14").val());
    var table3_15 = parseFloat($("#table3_15").val());
    var table3_16 = parseFloat($("#table3_16").val());
    var table3_17 = parseFloat($("#table3_17").val());
    var table3_18 = parseFloat($("#table3_18").val());
    var table3_19 = parseFloat($("#table3_19").val());
    var table3_20 = parseFloat($("#table3_20").val());
    var table3_21 = parseFloat($("#table3_21").val());
    var table3_22 = parseFloat($("#table3_22").val());

    var table4_01 = parseFloat($("#table4_01").val());
    var table4_02 = parseFloat($("#table4_02").val());
    var table4_03 = parseFloat($("#table4_03").val());
    var table4_04 = parseFloat($("#table4_04").val());
    var table4_05 = parseFloat($("#table4_05").val());
    var table4_06 = parseFloat($("#table4_06").val());
    var table4_07 = parseFloat($("#table4_07").val());
    var table4_08 = parseFloat($("#table4_08").val());
    var table4_09 = parseFloat($("#table4_09").val());
    var table4_10 = parseFloat($("#table4_10").val());
    var table4_11 = parseFloat($("#table4_11").val());
    var table4_12 = parseFloat($("#table4_12").val());
    var table4_13 = parseFloat($("#table4_13").val());
    var table4_14 = parseFloat($("#table4_14").val());
    var table4_15 = parseFloat($("#table4_15").val());
    var table4_16 = parseFloat($("#table4_16").val());
    var table4_17 = parseFloat($("#table4_17").val());
    var table4_18 = parseFloat($("#table4_18").val());
    var table4_19 = parseFloat($("#table4_19").val());
    var table4_20 = parseFloat($("#table4_20").val());
    var table4_21 = parseFloat($("#table4_21").val());
    var table4_22 = parseFloat($("#table4_22").val());

    var ctx = document.getElementById('chart2').getContext('2d');

    chart2 = new Chart(ctx, {
        type: 'line',
        data: {
            labels: [pic2BeginX, pic2IndexX2, pic2IndexX3, pic2IndexX4, pic2IndexX5, pic2IndexX6, pic2IndexX7, pic2IndexX8
                , pic2IndexX9, pic2IndexX10, pic2IndexX11, pic2IndexX12, pic2IndexX13, pic2IndexX14, pic2IndexX15, pic2IndexX16
                , pic2IndexX17, pic2IndexX18, pic2IndexX19, pic2IndexX20, pic2IndexX21, pic2EndX],
            datasets: [{
                label: "r=40cm, 4mm",
                backgroundColor: 'rgb(255, 99, 132)',
                borderColor: 'rgb(255, 99, 132)',
                data: [
                    table2_01,table2_02,table2_03,table2_04,table2_05,table2_06,table2_07,table2_08,table2_09,
                    table2_10,table2_11,table2_12, table2_13,table2_14,table2_15,table2_16,table2_17,table2_18,
                    table2_19,table2_20,table2_21, table2_22
                ],
                fill: false,
            }, {
                label: "r=40cm, 2mm",
                backgroundColor: 'rgb(126, 202, 256)',
                borderColor: 'rgb(126, 202, 256)',
                data: [
                    table3_01,table3_02,table3_03,table3_04,table3_05,table3_06,table3_07,table3_08,table3_09,
                    table3_10,table3_11,table3_12, table3_13,table3_14,table3_15,table3_16,table3_17,table3_18,
                    table3_19,table3_20,table3_21, table3_22
                ],
                fill: false,
            }, {
                label: "r=30cm, 2mm",
                backgroundColor: 'rgb(105,105,105)',
                borderColor: 'rgb(105,105,105)',
                data: [
                    table4_01,table4_02,table4_03,table4_04,table4_05,table4_06,table4_07,table4_08,table4_09,
                    table4_10,table4_11,table4_12, table4_13,table4_14,table4_15,table4_16,table4_17,table4_18,
                    table4_19,table4_20,table4_21, table4_22
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
                        labelString: 'U'
                    }
                }],
                yAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'I'
                    }
                }]
            }
        }
    });
}

var pic1BeginX = 5.196;
var pic1IndexX2 = 5.490;
var pic1IndexX3 = 6.879;
var pic1IndexX4 = 7.408;
var pic1EndX = 8.214;

var pic2BeginX = -1;
var pic2IndexX2 = -0.5;
var pic2IndexX3 = 0;
var pic2IndexX4 = 2;
var pic2IndexX5 = 4;
var pic2IndexX6 = 6;
var pic2IndexX7 = 8;
var pic2IndexX8 = 10;
var pic2IndexX9 = 12;
var pic2IndexX10 = 14;
var pic2IndexX11 = 16;
var pic2IndexX12 = 18;
var pic2IndexX13 = 20;
var pic2IndexX14 = 22;
var pic2IndexX15 = 24;
var pic2IndexX16 = 26;
var pic2IndexX17 = 28;
var pic2IndexX18 = 30;
var pic2IndexX19 = 32;
var pic2IndexX20 = 34;
var pic2IndexX21 = 36;
var pic2EndX = 38;

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

function uploadChart(chart, index){
    // 获取Canvas的编码。
    var imgData = document.getElementById(chart).toDataURL("image/png");

    // 上传到后台。
    $.ajax({
        type: "post",
        url:"/exp/upload_chart.do",
        data: {
            image : imgData.substring(22),
            expId: 1,
            index: index
        },
        async: false,
        success: function (res) {
            alert(res.status);
        },
        error: function(res) {
            alert("向服务器请求数据失败")
        }

    })
}




function submit() {
    var selectval = new Array();
    var chart1 = new Array();
    var result = new Array();
    var table2 = new Array();
    var table3 = new Array();
    var table4 = new Array();
    var num;
    for(var i = 1; i <= 11; i++){
        if(i <= 9)
            num = "0" + i + "";
        else
            num = i;
        selectval[i - 1] = $("#choice_" + num).val();
    }

    for(var i = 1; i <= 3; i++){
        result[i - 1] = $("#blank_02_0" + i).val();
    }

    for(var i = 1; i <= 5; i++){
        chart1[i - 1] = $("#chart1_index" + i).val();
    }

    for(var i = 1; i <= 22; i++){
        if(i <= 9)
            num = "0" + i + "";
        else
            num = i;
        table2[i - 1] = $("#table2_" + num).val();
    }

    for(var i = 1; i <= 22; i++){
        if(i <= 9)
            num = "0" + i + "";
        else
            num = i;
        table3[i - 1] = $("#table3_" + num).val();
    }

    for(var i = 1; i <= 22; i++){
        if(i <= 9)
            num = "0" + i + "";
        else
            num = i;
        table4[i - 1] = $("#table4_" + num).val();
    }
    
    
    $.ajax({
        type:"POST",
        url:"/sub/Exp_01.do",
        data:{
            selectval:selectval,
            chart1:chart1,
            result:result,
            table2:table2,
            table3:table3,
            table4:table4
        },
        async: false,
        dataType:"json",
        success:function (result) {
            alert("提交成功");
        },
        error:function (result) {
            alert("向服务器请求数据失败" + result);
        }
    });
}

function submitAll() {
    if(confirm("确认上传吗")) {
        uploadChart("chart1", 1);
        uploadChart("chart2", 2);
        submit();
    }
}
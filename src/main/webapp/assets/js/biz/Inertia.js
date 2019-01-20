function autoGenera1(){
    beltaJ(1);
    beltaJ(2);
    var j0 = $("#table_1_19").val();
    var j = $("#table_2_19").val();
    var js = j - j0;
    $("#blank_3").val(js);
}

function autoGenera2(){
    var Ro = $("#blank_4").val();
    var Ri = $("#blank_5").val();
    var M = $("#blank_6").val();
    var js = $("#blank_3").val();
    var jl = M * (Ro*Ro + Ri*Ri) / 2;
    var E = (js - jl) * 100 / jl;
    $("#blank_7").val(jl);
    $("#blank_8").val(E);
}

function beltaJ(table){
    var lt2 = parseFloat($("#table_" + table + "_1").val());
    var lt3 = parseFloat($("#table_" + table + "_2").val());
    var lt4 = parseFloat($("#table_" + table + "_3").val());
    var lt5 = parseFloat($("#table_" + table + "_4").val());
    var lt6 = parseFloat($("#table_" + table + "_5").val());
    var lt7 = parseFloat($("#table_" + table + "_6").val());
    var lt8 = parseFloat($("#table_" + table + "_7").val());
    var lt9 = parseFloat($("#table_" + table + "_8").val());

    var rt2 = parseFloat($("#table_" + table + "_10").val());
    var rt3 = parseFloat($("#table_" + table + "_11").val());
    var rt4 = parseFloat($("#table_" + table + "_12").val());
    var rt5 = parseFloat($("#table_" + table + "_13").val());
    var rt6 = parseFloat($("#table_" + table + "_14").val());
    var rt7 = parseFloat($("#table_" + table + "_15").val());
    var rt8 = parseFloat($("#table_" + table + "_16").val());
    var rt9 = parseFloat($("#table_" + table + "_17").val());

    // console.log("table1 lt2 lt3 : " + lt2 + " " + lt3);
    // console.log("table1 res1 : " + res1(lt2, lt3));
    // console.log("table2 res1 : " + res1(rt2, rt6));
    var belta3 = (res1(lt2, lt6) + res2(lt3, lt7) + res3(lt4, lt8) + res4(lt5, lt9)) / 4;
    var belta4 = (res1(rt2, rt6) + res2(rt3, rt7) + res3(rt4, rt8) + res4(rt5, rt9)) / 4;
    $("#table_" + table + "_9").val(belta3);
    $("#table_" + table + "_18").val(belta4);
    console.log("table_" + table + " belta3: " + belta3);
    console.log("table_" + table + " belta4: " + belta4);


    var m = parseFloat($("#blank_1").val());
    var r = parseFloat($("#blank_2").val());
    var g = 9.8;
    var on = m * r * (g - belta4 * r);
    var down = belta4 - belta3;

    console.log(on);
    console.log(down);

    var j = on / down;
    $("#table_" + table + "_19").val(j);
}

function res1(t1, t2){
    var on = 2 * 3.14 * (6 * t1 - 2 * t2);
    // console.log("on: " + on);
    var down = t1 * t2 * (t2 - t1);
    // console.log("down: " + down);
    // console.log("on/down: " + on/down);
    return on / down;
}

function res2(t1, t2){
    var on = 2 * 3.14 * (7 * t1 - 3 * t2);
    var down = t1 * t2 * (t2 - t1);
    return on / down;
}

function res3(t1, t2){
    var on = 2 * 3.14 * (8 * t1 - 4 * t2);
    var down = t1 * t2 * (t2 - t1);
    return on / down;
}

function res4(t1, t2){
    var on = 2 * 3.14 * (9 * t1 - 5 * t2);
    var down = t1 * t2 * (t2 - t1);
    return on / down;
}


var chart1;

function generateChart1() {
    var x1 = 45 * 45;
    var x2 = 60 * 60;
    var x3 = 75 * 75;
    var x4 = 90 * 90;

    var y1 = $("#table_3_19").val();
    var y2 = $("#table_4_19").val();
    var y3 = $("#table_5_19").val();
    var y4 = $("#table_6_19").val();

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
                    {x: x1, y: y1}, {x: x4, y: y4}
                ],
                fill: false,
            }, {
                label: "原始数据",
                backgroundColor: 'rgb(105,105,105)',
                borderColor: 'rgba(255, 255, 255, 0)',
                data: [
                    y1, y2, y3, y4
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
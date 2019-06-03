/**
 * 图形生成
 */
///////////////////////////////////////
// 基于准备好的dom，初始化echarts实例
function becomeEcharts(optionNum, mainId, outdataUhs, myRegression) {
    var myChart = echarts.init(document.getElementById(mainId));


    option = {};
    var option1 = {

        title: {
            text: "U——I关系曲线",
            x: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        /*legend: {
            data: ['拟合曲线', '观察点'],
            x: "left",
            y: '20',
            orient: 'vertical',
        },*/
        toolbox: {
            show: true,
            orient: 'vertical',      // 布局方式，默认为水平布局，可选为：
            // 'horizontal' ¦ 'vertical'
            x: 'right',                // 水平安放位置，默认为全图右对齐，可选为：
                                       // 'center' ¦ 'left' ¦ 'right'
                                       // ¦ {number}（x坐标，单位px）
            y: 'top',                  // 垂直安放位置，默认为全图顶端，可选为：
            // 工具箱内边距，单位px，默认各方向内边距为5，
            showTitle: true,
            feature: {
                saveAsImage: {
                    show: true,
                    title: '保存为图片',
                    type: 'jpeg',
                    lang: ['点击本地保存']
                }
            }
        },
        calculable: false,

        yAxis: [
            {
                // min: -0.5,
                // max: 5.5
                axisLabel: {
                    formatter: '{value} '
                },
                name: "U/mV",
            }
        ],
        xAxis: [
            {
                min: 1,
                max: 8,
                interval: 1,
                axisLabel: {
                    formatter: '{value} '
                },
                name: "I/A",

            }
        ],
        series: [{
            // 线条图
            type: 'line',
            name: '拟合曲线',
            data: myRegression.points,
            marker: {
                enabled: false
            },
            states: {
                hover: {
                    lineWidth: 0
                }
            },
            enableMouseTracking: false
        }, {
            name: '观察点',
            data: outdataUhs,
            type: 'scatter',
        }
        ]
    };

    var option3 = {
        title: {
            text: "B——x关系曲线",
            x: 'center'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        toolbox: {
            show: true,
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['-13', '-12', '-11', '-10', '-9', '-8', '-7', '-6', '-5', '-4', '-3', '-2', '-1', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13'],
            name: 'X/cm'
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value} '
            },
            axisPointer: {
                snap: true
            },
            name: 'B/T'
        },
        series: [
            {
                name: 'B——x关系曲线',
                type: 'line',
                smooth: true,
                data: outdataUhs
            }
        ]
    };

    if (optionNum === 1) {
        option = option1;
    } else if (optionNum === 2) {
        option = option1;
    } else if (optionNum === 3) {
        option = option3;
    }

    window.addEventListener("resize", function () {
        myChart.resize();
    });

    return myChart;

}

function getChart(optionNum, outdataUhs, myRegression) {
    var mainId = "main" + optionNum + "";
    if (optionNum === 3) {
        myRegression = {
            points: []
        };
    }
    var myChart = becomeEcharts(optionNum, mainId, outdataUhs, myRegression);
    myChart.setOption(option, true);
    //给canvas添加id
    $("#" + mainId + " canvas:first-child").attr('id', "chart" + optionNum);


}

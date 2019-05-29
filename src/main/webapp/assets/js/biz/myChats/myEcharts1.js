// 基于准备好的dom，初始化echarts实例
// var myChart = echarts.init(document.getElementById('main1'));
var myChart2 = echarts.init(document.getElementById('main2'));
var myChart3 = echarts.init(document.getElementById('main3'));
option = {

    title:{
      text:"xxxxxxxxxxxxxxxx图形形状待定",
        x:'center'
    },
    tooltip : {
        trigger: 'item'
    },
    legend: {
        data:['拟合曲线','观察点'],
        x:"left",
        y: '20',
        orient: 'vertical',
    },
    toolbox: {
        show : true,
        orient: 'vertical',      // 布局方式，默认为水平布局，可选为：
        // 'horizontal' ¦ 'vertical'
        x: 'right',                // 水平安放位置，默认为全图右对齐，可选为：
                                   // 'center' ¦ 'left' ¦ 'right'
                                   // ¦ {number}（x坐标，单位px）
        y: 'top',                  // 垂直安放位置，默认为全图顶端，可选为：
        // 工具箱内边距，单位px，默认各方向内边距为5，
        showTitle: true,
        feature : {
            saveAsImage : {
                show : true,
                title : '保存为图片',
                type : 'jpeg',
                lang : ['点击本地保存']
            }
        }
    },
    calculable : false,

    xAxis : [
        {
            // min: -0.5,
            // max: 5.5
            axisLabel: {
                formatter:'{value} A'
            }

        }
    ],
    yAxis : [
        {
            axisLabel:{
                formatter:'{value} mV'
            }

        }
    ],
    series : [{
        // 线条图
        type: 'line',
        name: '拟合曲线',
        data: [[0, 1.11], [5, 4.51]],
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
        // 散点图
        type: 'scatter',
        name: '观察点',
        data: [1, 1.5, 2.8, 3.5, 3.9],
        marker: {
            radius: 4
        }
    }
    ]
};

/*myChart.setOption(option, true);*/
myChart2.setOption(option, true);
myChart3.setOption(option, true);
window.addEventListener("resize", function () {
    /*myChart.resize();*/
    myChart2.resize();
    myChart3.resize();
});

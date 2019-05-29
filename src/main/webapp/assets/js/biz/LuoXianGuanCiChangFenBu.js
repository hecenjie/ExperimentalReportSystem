$(function () {
        $.ajax({
            type: "GET",
            url: "/exp/get_exp_status.do",
            data: {
                expId: 11
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
                expId: 11
            },
            dataType: "json",
            success: function (res) {
                if (res.status === 0) {
                    //用户未提交过此实验
                } else if (res.status === 2) {
                    location.href = "../login.html";
                } else if (res.status === 15) {
                    alert("您已提交过此实验，如有疑问请联系实验老师");
                    location.href = "../index.html";
                } else {
                    alert("服务器发生错误");
                }
            },
            error: function (res) {
                alert("向服务器请求数据失败" + res);
            }
        });
    }
);


function submitAll() {

    $("canvas").each(function () {
        $(this).attr('id', "chart" + $(this).index());
    });

    if (confirm("为避免数据丢失，提交前请先将实验数据截图，确认提交吗？")) {
        uploadChart("chart0", 1);
        submit();
    }
}



function submit() {
    var choice = new Array();
    var blank = new Array();
    var table = new Array();
    var table_out = new Array();
    var chart1 = new Array();


    for (var i = 1; i <= 15; i++) {
        choice[i - 1] = $("#choice_" + i + "").val();
    }

    for (var i = 1; i <= 5; i++) {
        blank[i - 1] = $("#blank_" + i + "").val();
    }

    for (var i = 1; i <= 172; i++) {
        table[i - 1] = $("#table_" + i + "").val();
    }

    for (var i = 1; i <= 70; i++) {
        table_out[i - 1] = $("#table_out_" + i + "").val();
    }

    /**
     * 获取canvas
     */
    for (var i = 1; i <= $("canvas").length; i++) {
        chart1[i - 1] = $("#chart" + i + "").val();
        alert(chart1[i-1]);//---------------------------------------------------------------测试
    }

    alert(chart1);//---------------------------------------------------------------测试

    $.ajax({
        type: "POST",
        url: "/sub/Exp_11.do",
        data: {
            choice: choice,
            blank: blank,
            table: table,
            table_out:table_out,
            chart1: chart1,
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
                location.href = "../index.html"
            }
        },
        error: function (result) {
            alert("服务器请求失败，请确认数据填写正确且完整后重试" + result);
        }
    });
}

function productDataAndPicture1() {
    productDataAndPicture();
}


function productDataAndPicture2() {

}

/**
 * 生成数据和图像
 */

var outdataUhs = new Array();
var myRegression;

function productDataAndPicture() {

    var table = new Array();
    var table_out = new Array();

    /**
     * 获取表单数据
     */
    for (var i = 1; i <= 32; i++) {
        table[i - 1] = $("#table_" + i + "").val();
    }

    /**
     * 将表单数据放入输出变量中
     */
    var tableOutData = 0;
    var j=0;
    for (var i = 0; i < table.length; i++) {
        tableOutData += Math.abs(table[i]);
        if (((i + 1) % 4 === 0)) {
            table_out[j++] = tableOutData/4;
            tableOutData =0;
        }

    }

    /**
     * 输出表单变量值
     */
    for (var i = 1; i <= table_out.length; i++) {
        var tableOutId = "#table_out_" + i;
        $(tableOutId).attr("value",table_out[i-1]);
    }

    /**
     * 点数据
     */

    for (var i = 1; i <= table_out.length; i++) {
        var tableOutId = "#table_out_" + i;
        var data1  = new Array();
        data1[0] = i;
        data1[1] = parseFloat($(tableOutId).val());
        outdataUhs[i-1] = data1;
    }




    /**
     * 调用函数生成回归曲线
     */
    myRegression = ecStat.regression('linear', outdataUhs);

    /**
     *计算残差平方和
     */
    var R2 = getR2(outdataUhs, myRegression);


    /**
     * 图像自动生成
     */
    getChart(outdataUhs,myRegression);
    /**给图像添加id*/
    // $("canvas").attr("id", "chart1");

    /**
     * 赋值表达式和参数
     */
    $("#blank_1").attr("value",myRegression.expression );
    $("#blank_2").attr("value",R2.toFixed(2) );
}

function getR2(outdataUhs,myRegression) {
    var CanChaPingFangHe = 0.0;
    var ZongPingFangHe = 0.0;
    var r2 = 0.0;


    for (var i = 0; i < outdataUhs.length; i++) {
        /**
         * 曲线计算参数
         */
        var Y = myRegression.parameter.gradient * outdataUhs[i][0] + myRegression.parameter.intercept;
        /**
         * 实际参数
         */
        var y = outdataUhs[i][1];
        CanChaPingFangHe += (y - Y) * (y - Y);
        ZongPingFangHe += y * y;
        r2 = (ZongPingFangHe - CanChaPingFangHe) / ZongPingFangHe;
    }
    return r2;
}

/**
 * 提交图片
 * @param chart
 * @param index
 */
function uploadChart(chart, index) {
    // 获取Canvas的编码。
    var imgData = document.getElementById(chart).toDataURL("image/png");  /** 这里-报错×******/

    // 上传到后台。
    $.ajax({
        type: "post",
        url: "/exp/upload_chart.do",
        data: {
            image: imgData.substring(22),
            expId: 11,
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

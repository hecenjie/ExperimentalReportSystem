function autoGenera1(){
    var s00 = $("#table_2_1").val();
    var s01 = $("#table_2_2").val();
    var s10 = $("#table_2_3").val();
    var s11 = $("#table_2_4").val();
    var s20 = $("#table_2_5").val();
    var s21 = $("#table_2_6").val();
    var s30 = $("#table_2_7").val();
    var s31 = $("#table_2_8").val();
    var s40 = $("#table_2_9").val();
    var s41 = $("#table_2_10").val();
    var s50 = $("#table_2_11").val();
    var s51 = $("#table_2_12").val();
    var s60 = $("#table_2_13").val();
    var s61 = $("#table_2_14").val();
    var s70 = $("#table_2_15").val();
    var s71 = $("#table_2_16").val();

    autoGenera1Helper(s00, s01, 0);
    autoGenera1Helper(s10, s11, 1);
    autoGenera1Helper(s20, s21, 2);
    autoGenera1Helper(s30, s31, 3);
    autoGenera1Helper(s40, s41, 4);
    autoGenera1Helper(s50, s51, 5);
    autoGenera1Helper(s60, s61, 6);
    autoGenera1Helper(s70, s71, 7);

    var a1 = $("#answer2_r4_1").text();
    var a2 = $("#answer2_r5_1").text();
    var a3 = $("#answer2_r6_1").text();
    var a4 = $("#answer2_r7_1").text();
    var ans2 = (parseFloat(a1) + parseFloat(a2) + parseFloat(a3) + parseFloat(a4)) / 400;
    $("#answer2").text(ans2);
}

function autoGenera1Helper(a, b, row){
    if(a == null || a === undefined || a === "") a = 0;
    if(b == null || b === undefined || b === "") b = 0;
    var ans1 = (parseFloat(a) + parseFloat(b)) / 2;
    $("#answer2_r" + row).text(ans1);
    // alert(ans1);
    if(row >= 4){
        var fr = row -4;
        var ans2 = $("#answer2_r" + row).text() - $("#answer2_r" + fr).text();
        $("#answer2_r" + row + "_1").text(ans2);
    }

}

function autoGenera2(){
    var a1 = parseFloat($("#table_1_1").val());
    var a2 = parseFloat($("#table_1_2").val());
    var a3 = parseFloat($("#table_1_3").val());
    var a4 = parseFloat($("#table_1_4").val());
    var a5 = parseFloat($("#table_1_5").val());
    var a6 = parseFloat($("#table_1_6").val());
    var ans = (a1 + a2 + a3 + a4 + a5 + a6) / 6000;
    var d0 = parseFloat($("#blank_2").val());
    $("#answer1").text(ans-d0/1000);
}

function autoGenera3(){
    var L = parseFloat($("#blank_6").val());
    var D = parseFloat($("#blank_5").val());
    var F = parseFloat($("#blank_3").val());
    var d = parseFloat($("#answer1").text());
    var b = parseFloat($("#blank_4").val());
    var s = parseFloat($("#answer2").text());
    console.log("L = " + L);
    console.log("D = " + D);
    console.log("F = " + F);
    console.log("d = " + d);
    console.log("b = " + b);
    console.log("s = " + s);
    var on = 8 * L * D * F;
    console.log("on = " + on);
    var down = 3.14 * d * d * b * s;
    console.log("down = " + down)
    var e = on / down;
    console.log("e = " + e);
    $("#blank_7").val(num2e(e));
}

function num2e(num){
    var p = Math.floor(Math.log(num)/Math.LN10);
    var n = num * Math.pow(10, -p);
    return n.toFixed(3) + 'e' + p;
}

function submitAll() {
    if (confirm("为避免数据丢失，提交前请先将实验数据截图，确认提交吗？")){
        submit();
    }
}


function submit() {
    var choice = new Array();
    var blank = new Array();
    var table1 = new Array();
    var table2 = new Array();
    var answer = new Array();


    for (var i = 1; i <= 17; i++) {
        choice[i-1] = $("#choice_" + i + "").val();
    }

    for (var i = 1; i <= 7; i++) {
        blank[i-1] = $("#blank_" + i + "").val();
    }

    for (var i = 1; i <= 6; i++) {
        table1[i-1] = $("#table_1_" + i + "").val();
    }

    for (var i = 1; i <= 16; i++){
        table2[i-1] = $("#table_2_" + i + "").val();
    }

    answer[0] = $("#answer1").text();
    answer[1] = $("#answer2_r0").text();
    answer[2] = $("#answer2_r1").text();
    answer[3] = $("#answer2_r2").text();
    answer[4] = $("#answer2_r3").text();
    answer[5] = $("#answer2_r4").text();
    answer[6] = $("#answer2_r4_1").text();
    answer[7] = $("#answer2_r5").text();
    answer[8] = $("#answer2_r5_1").val();
    answer[9] = $("#answer2_r6").text();
    answer[10] = $("#answer2_r6_1").text();
    answer[11] = $("#answer2_r7").text();
    answer[12] = $("#answer2_r7_1").text();
    answer[13] = $("#answer2").text();

    $.ajax({
        type: "POST",
        url: "/sub/Exp_04.do",
        data: {
            choice: choice,
            blank: blank,
            table1: table1,
            table2: table2,
            answer: answer
        },
        async: false,
        dataType: "json",
        success: function (result) {
            if (result.status === 14)
                alert("请勿多次提交试验");
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
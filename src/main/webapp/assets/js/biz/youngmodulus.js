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

}

function autoGenera1Helper(a, b, row){
    if(a == null || a === undefined || a === "") a = 0;
    if(b == null || b === undefined || b === "") b = 0;
    var ans1 = (parseFloat(a) + parseFloat(b)) / 2;
    $("#answer2_r" + row).text(ans1);
    // alert(ans1);
    if(row >= 4){

        var ans2 = $("#answer2_r" + row).text() - $("#answer2_r" + row - 4).text();
        $("#answer2_r" + row + "_1").text(ans2);
    }

}
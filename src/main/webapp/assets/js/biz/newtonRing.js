function autoGenera(){
    for(var i = 1; i <= 21; i += 4){
        var j = i + 1;
        Di(i, j);
    }
    for(var i = 24; i <= 33; i += 3){
        var j = i + 1;
        Di(i, j);
    }
    Di5Di();
}

function Di(i, j){
    var xl = $("#table_"+i).val();
    var xr = $("#table_"+j).val();
    var di = Math.abs(xl - xr);
    var index = j + 1;
    $("#table_"+ index).val(di);
}

function Di5Di(){
    var d5 = $("#table_3").val();
    var d6 = $("#table_7").val();
    var d7 = $("#table_11").val();
    var d8 = $("#table_15").val();
    var d9 = $("#table_19").val();
    var d10 = $("#table_23").val();
    var d11 = $("#table_26").val();
    var d12 = $("#table_29").val();
    var d13 = $("#table_32").val();
    var d14 = $("#table_35").val();

    var d10d5 = d10 * d10 - d5 * d5;
    var d11d6 = d11 * d11 - d6 * d6;
    var d12d7 = d12 * d12 - d7 * d7;
    var d13d8 = d13 * d13 - d8 * d8;
    var d14d9 = d14 * d14 - d9 * d9;

    $("#table_4").val(d10d5);
    $("#table_8").val(d11d6);
    $("#table_12").val(d12d7);
    $("#table_16").val(d13d8);
    $("#table_20").val(d14d9);
}
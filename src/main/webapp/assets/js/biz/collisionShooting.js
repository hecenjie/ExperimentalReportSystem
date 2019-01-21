function theoreticalH(){
    var x = 20.4;
    var y = $("#").val();
    var th = (x * x) / (4 * y);
}

function addedH(){
    var x = 20.4;
    var y = $("#").val();
    var avgX = $("#").val();
    var ah = x * (x - avgX) / (2 * y);
}
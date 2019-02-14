/**页面加载时就执行*/
$(function(){
    /**
     * 初始化链表状态
     */
    getState(1);
    getState(2);
})


function openExp(expId){

    if(confirm("确认要开启该实验吗？")) {
        $.ajax({
            type: "post",
            url: "/manage/exp/open_exp.do",
            dataType: "json",
            data: {
                expId: expId
            },
            success: function (res) {
                if (res.status === 0) {
                    alert("开放实验成功");
                } else if (res.status === 2) {
                    location.href = "login.html";
                } else {
                    alert("开放实验失败");
                    location.reload();
                }
            }, error: function () {
                alert("向服务器请求数据失败")
            }
        })
    }
}

function closeExp(expId){
    if(confirm("确认要关闭该实验吗？")) {
        $.ajax({
            type: "post",
            url: "/manage/exp/close_exp.do",
            dataType: "json",
            data: {
                expId: expId
            },
            success: function (res) {
                if (res.status === 0) {
                    alert("关闭实验成功");
                } else if (res.status === 2) {
                    location.href = "login.html";
                } else {
                    alert("关闭实验失败");
                    location.reload();
                }
            }, error: function () {
                alert("向服务器请求数据失败")
            }
        })
    }

}

/**
 * 操作开放关闭状态
 * @param obj
 * @param expId
 */
function getExp(obj,expId){
    $.ajax({
        type: "post",
        url: "/manage/exp/get_exp.do",
        dataType: "json",
        data: {
            expId: expId
        },
        success: function (res) {
                if (res.status === 9){
                    closeExp(expId);
                    obj.innerText="关闭";
                }else if (res.status === 10) {
                    openExp(expId);
                    obj.innerText="开放";
                }
        }, error: function () {
            alert("向服务器请求数据失败")
        }
    })
}




/**
 * 查看开放状态并初始化链表状态
 * @param expId
 */
function getState(expId){
    $.ajax({
        type: "post",
        url: "/manage/exp/get_exp.do",
        dataType: "json",
        data: {
            expId: expId
        },
        success: function (res) {
            var aOpenExp = document.getElementsByClassName("aOpenExp");
            for (var i=0;i<aOpenExp.length;i++){
                if (res.status === 9){
                    aOpenExp[i].innerText="关闭";
                }else if (res.status === 10) {
                    aOpenExp[i].innerText="开放";
                }
            }
        }, error: function () {
            alert("向服务器请求数据失败")
        }
    })
}

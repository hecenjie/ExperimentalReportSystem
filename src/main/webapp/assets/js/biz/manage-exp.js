/**页面加载时就执行*/
$(function(){
    getState("a1",1);
    getState("a2",2);
})

function openExp(obj,expId){

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
                    window.location.reload();
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

function closeExp(obj,expId){
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
                    window.location.reload();
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
                    closeExp(obj,expId);

                }else if (res.status === 10) {
                    openExp(obj,expId);

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
function getState(id,expId){
    $.ajax({
        type: "post",
        url: "/manage/exp/get_exp.do",
        dataType: "json",
        data: {
            expId: expId
        },
        success: function (res) {
            var aOpenExp = document.getElementById(id);
                if (res.status === 9){
                    aOpenExp.innerText="关闭";
                }else if (res.status === 10) {
                    aOpenExp.innerText="开放";
                }

        }, error: function () {
            alert("向服务器请求数据失败")
        }
    })
}

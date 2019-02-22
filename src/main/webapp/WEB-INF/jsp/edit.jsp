<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
    <meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT">


    <title>用户修改</title>

    <!-- CSS -->
    <!--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/form-elements.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/assets/js/html5shiv.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/respond.min.js"></script>
    <![endif]-->

    <!-- Favicon and touch icons -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${pageContext.request.contextPath}/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${pageContext.request.contextPath}/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${pageContext.request.contextPath}/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/assets/ico/apple-touch-icon-57-precomposed.png">

</head>

<body>

<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1><strong>南京农业大学</strong>实验报告提交及管理系统</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top">
                        <div class="form-top-left">
                            <h3>用户修改</h3>
                            <p>请输入您的学生信息进行修改，为防止遗忘，密码请与教务处保持相同：</p>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-key"></i>
                        </div>
                    </div>
                    <div class="form-bottom">
                        <form role="form" action="#" method="post" class="login-form" onsubmit="return false" id="edit">
                            <div class="form-group">
                                <label class="sr-only" for="form-username">Username</label>
                                <input type="text" name="id"style="display:none" id="form-id"  value="${user.id}">
                                <input type="text" name="role"style="display:none" id="form-role"  value="${user.role}">
                                <input type="text" name="username" disabled="disabled" placeholder="学号" value="${user.stuNum}" class="form-username form-control" id="form-username">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-name">Name</label>
                                <input type="text" name="stuName" placeholder="姓名" value="${user.stuName}"  class="form-name form-control" id="form-name">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">Password</label>
                                <input type="password" name="password" placeholder="密码"  class="form-password form-control" id="form-password">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-passwordCheck">Password</label>
                                <input type="password" name="passwordCheck" placeholder="确认密码"   class="form-password form-control" id="form-passwordCheck">
                            </div>
                            <div class="form-group">
                                <select class="form-control" id="major">
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-class">Class</label>
                                <input type="text" name="password" value="${user.stuClass}" placeholder="班级 (例如:174)" onkeyup="value=value.replace(/\D/g,'')" maxlength="5" class="form-class form-control" id="form-class">
                            </div>
                            <%--<p><a href="login.html">账号登陆</a></p>--%>
                            <button type="submit" class="btn" onclick="edit()">修改</button>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<!-- Javascript -->
<script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery.backstretch.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/scripts.js"></script>
<!--登陆状态校验-->
<script src="${pageContext.request.contextPath}/assets/js/biz/loginCheck.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/biz/register.js"></script>

</body>

</html>

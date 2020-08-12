<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="/asset/images/favicon.ico" type="images/x-icon"/>
    <link rel="stylesheet" href="/asset/css/login.css"/>
    <style>
        html {
            background: url(asset/images/main3.png) no-repeat center center fixed;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
            background-size: cover;
        }
    </style>
    <title>login</title>
</head>
<body>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
    $(function () {
        var responseMessage = "<c:out value="${message}" />";
        if (responseMessage != "") {
            alert(responseMessage)
        }
    })
</script>

<div class="login-page">

    <div class="form">
        <img src="/asset/images/georlocklogo.png">
        <%--로그인폼--%>
        <form class="login-form" name="login" action="login" method="post">
            <input type="text" placeholder="ID" name="empNo" class="inputcontrol"/>
            <input type="password" placeholder="PASSWORD" name="userPw" class="inputcontrol"/>
            <div class="checkbox">
                <label class="checklabel">
                    <input type="checkbox" name="autoLogin" class="inp_radio">자동 로그인
                </label>
            </div>
            <button type="submit">login</button>
        </form>
</body>
</html>
</html>
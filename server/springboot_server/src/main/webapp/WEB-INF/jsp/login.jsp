<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/asset/css/login.css"/>
<title>login</title>
</head>
<body>
	<div class="login-page">

        <div class="form">
            <img src="/asset/images/georlocklogo.png">
<%--로그인폼--%>
                <form class="login-form" name="loginForm" action="record" method="post">
                    <input type="text" placeholder="ID"  name = "empNo" class="inputcontrol"/>
                    <input type="password" placeholder="PASSWORD" name = "userPw" class="inputcontrol"/>
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
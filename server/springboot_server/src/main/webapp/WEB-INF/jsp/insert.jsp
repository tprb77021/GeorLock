<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="/asset/css/insert.css"/>
    <title>회원권한등록</title>
</head>
<body>
<header>
    <p>
        출입권한등록
    </p>
</header>
<main>


    <form class="insert-form" name="insert" action="accessinsert" method="post">
        사번<br /><input type="text" name="empNo" /><br />
        비밀번호<br /><input type="text" name="userPw" /><br />
        이름<br /><input type="text" name="username" /><br />
        출입가능시간<br />
        <input type="time" name="intime" />
        <input type="time" name="outtime" />
        <br />
        유저타입(1:사용자 2:관리자)<br /><input type="text" name="usertype" /><br />
        <input type="submit" value="등록" />
    </form>
</main>
</body>
</html>

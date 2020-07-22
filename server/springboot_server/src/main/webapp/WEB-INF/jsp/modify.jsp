<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>출입권하수정</title>
</head>

<body>
<header>
    <p>
        출입권한수정
    </p>
</header>

<main>
    <form class="modify-form" name="modify" action="accessupdate" method="post">
        사번 : ${empNo}<br /><input type="hidden" name="empNo" value=${empNo} />

        이름 : ${username} <br />

        출입가능시간<br />
        <input type="time" name="intime" />
        <input type="time" name="outtime" />
        <br />

        <input type="submit" value="수정" onclick="x()"/>
    </form>
</main>
<script>
    function x() {
        self.submit();
    }


</script>
</body>
</html>

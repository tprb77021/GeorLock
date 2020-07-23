<!DOCTYPE html>
<html lang="en">
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
    <form class="insert-form" name="insert" action="access" method="post">
        사번<br /><input type="text" name="empNo" /><br />
        이름<br /><input type="text" name="name" /><br />
        출입가능시간<br />
        <input type="time" name="intime" />
        <input type="time" name="outtime" />
        <br />
        <input type="submit" value="등록" />
    </form>
</main>
</body>
</html>

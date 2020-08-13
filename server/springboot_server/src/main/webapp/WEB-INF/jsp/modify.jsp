<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Document</title>
    <style>
        @import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&family=Roboto&display=swap");

        header .headerDiv {
            width: 80%;
            padding: 2px;
            margin-top: 20px;
        }

        header p {
            font-size: 30px;
        }

        .headerDiv p {
            margin-left: 25px;
            font-family: "Noto Sans KR", sans-serif;
            font-size: 17px;
        }

        header {
            margin-bottom: 30px;
        }

        .jsp-div {
            width: 98%;

            padding: 10px 20px;

            margin: 8px auto;

            border: 1px solid #ccc;

            border-radius: 4px;

            box-sizing: border-box;
        }

        .input-div {
            display: flex;
            justify-content: space-between;
            padding: 10px 5px;
            width: 98%;
        }

        body {
            font-family: "Noto Sans KR", sans-serif;
        }

        input[type="submit"] {
            width: 100%;

            background-color: #2497f4;

            color: white;

            padding: 14px 20px;

            margin: 30px 0px 0px 0px;

            border: none;

            border-radius: 4px;

            cursor: pointer;

            font-size: 15px;

            font-family: "Noto Sans KR", sans-serif;
        }

        input[type="time"] {
            width: 49%;

            display: inline-block;

            border: 1px solid #ccc;

            padding: 12px 10px;

            border-radius: 4px;

            box-sizing: border-box;
        }

        input[type="submit"]:hover {
            background-color: #f0f38b;
            color: black;
        }
    </style>
</head>

<body>
<header>
    <p>
        출입권한수정
    </p>
</header>

<main>
    <form
            class="modify-form"
            name="modify"
            action="accessUpdate"
            method="post"
    >
        사번 :
        <div class="jsp-div">${empNo}</div>
        이름 :
        <div class="jsp-div">${username}</div>
        출입가능시간:
        <div class="input-div">
            <input type="time" name="intime"/>
            <input type="time" name="outtime"/>
        </div>

        <input type="hidden" name="empNo" value="${empNo}"/>
        <input type="submit" value="수정"/>
    </form>
</main>
</body>
</html>
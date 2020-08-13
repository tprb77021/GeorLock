<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="/asset/images/favicon.ico" type="images/x-icon"/>
    <link rel="stylesheet" href="/asset/css/access.css"/>
    <title>출입권한등록</title>
    <script
            src="https://kit.fontawesome.com/a962b14940.js"
            crossorigin="anonymous"
    ></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<%--header--%>
<header>
    <div class="logoImg">
        <a href="record" title="record">
            <img src="/asset/images/georlocklogo.png">
        </a>
    </div>
    <%--로그아웃--%>
    <div class="logout">
        <a href="logout" title="logout">
            <span>Logout</span>
        </a>
    </div>
</header>

<div id="main" >
    <nav>
        <div class="nav-item">
            <ul>
                <li>
                    <a href="record" title="record">
                        <span>출입기록</span>
                    </a>
                </li>
                <li>
                    <a href="insert_btn"title="insert">
                        <span>출입권한등록</span>
                    </a>
                </li>
                <li>
                    <a href="access"title="access">
                        <span>출입권한설정</span>
                    </a>
                </li>
            </ul>
        </div>
    </nav>
    <article class="main-item">
        <p class="mainComment">등록하기</p>

        <%--검색창--%>
                <form id="searchForm" action="empSearch" method="post">
                    <div class="search">
                        <input type="text"  name="textSearch" class="search_txt" placeholder="Search..."/>
                        <button type="submit" value="검색">
                            <i class="fas fa-search"></i>
                        </button>
                    </div>
                </form>

        <%--테이블--%>
        <div class="paginationBox">
            <ul class="pagination">
                <li class="page-item">
                </li>
            </ul>
        </div>

        <%--페이징--%>
        <table class="table">
            <colgroup>
                <col width="20%"/>
                <col width="20%"/>
                <col width="40%"/>
                <col width="20%"/>
            </colgroup>
            <tr>
                <thead>
                <th>사번</th>
                <th>이름</th>
                <th>등록</th>
                </thead>
            </tr>



            <c:forEach items="${empuser}" var="list">
                <tr>
                    <td>${list.empNo}</td>
                    <td>${list.username}</td>
                    <td>
                        <div class="btn-group">
                            <button type="button" id="modity_btn" >
                                <p class="modify"><a href=/accessInsert?empNo=${list.empNo}&username=${list.username}>등록</a></p>
                            </button>
                        </div>
                    </td>
                </tr>
            </c:forEach>


        </table>
        <a class="upscroll" href="#" title="맨 위로"><i class="fas fa-angle-double-up" fa-sm></i></a>
    </article>

    <%--navside--%>

</div>

</body>
</html>





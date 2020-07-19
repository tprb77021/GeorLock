<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/asset/css/record.css"/>
	<title>출입기록</title>
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
		<article class="main-item">
			<p>출입기록</p>

<%--검색창--%>
			<form id="searchForm" action="record" method="get">
				<div class="search">
					<input type = "date" name="startDate"/> ~
					<input type = "date" name="endDate"/>
					<input type="text"  name="textSearch" class="search_txt" placeholder="Search..."/>
				</div>
			</form>

<%--테이블--%>
		<table class="table">
			<colgroup>
				<col width="20%"/>
				<col width="20%"/>
				<col width="40%"/>
				<col width="20%"/>
			</colgroup>
			<tr>
				<thead>
				<th>NO</th>
				<th>출입시간</th>
				<th>사원번호</th>
				<th>이름</th>
				</thead>
			</tr>
			<tr>
				<td>1</td>
				<td>1</td>
				<td>1</td>
				<td>1</td>
			</tr>
		</table>

<%--페이징--%>
		<div class="paginationBox">
			<ul class="pagination">
				<li class="page-item">
				</li>
			</ul>
		</div>
	</article>

<%--navside--%>
		<nav>
			<div class="nav-item">
				<ul>
					<li>
						<a href="record" title="record">
							<span>출입기록</span>
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
</div>
</body>
</html>
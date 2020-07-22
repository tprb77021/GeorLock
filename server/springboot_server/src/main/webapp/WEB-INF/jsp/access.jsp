<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/asset/css/access.css"/>
	<title>출입권한설정</title>
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
			<p>출입권한설정</p>

<%--검색창--%>
			<form id="searchForm" action="accessSearch" method="post">
				<div class="search">
					<input type="text"  name="textSearch" class="search_txt" placeholder="Search..."/>
					<input type="submit" value="검색">
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
					<th>출입가능시간</th>
					<th>수정/삭제</th>
					</thead>
				</tr>



				<c:forEach items="${userlist}" var="list">
					<tr>
						<td>${list.empNo}</td>
						<td>${list.username}</td>
						<td>${list.intime} ~ ${list.outtime}</td>
				<td>
					<div class="btn-group">
						<button type="button" id="modity_btn"  onClick="window.open('/accessmodity?empNo=${list.empNo}&username=${list.username}', '_blank', 'width=500px,height=400px,toolbars=no,scrollbars=no'); return false;">
							<p class="modify">수정</p>
						</button>
						<button type="submit" id="delete_btn" onclick="view_confirm(${list.empNo})">
							<p class="delete">삭제</p>
						</button>
					</div>
				</td>
				</tr>
				</c:forEach>


			</table>
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

<script type="text/javascript">
	function view_confirm(empNo)
	{
		var ans = confirm( "삭제 하시겠습니까?" );
		if ( ans ) {
			location.href = "/accessdelete?empNo="+empNo;
		}
	}
</script>
</body>
</html>
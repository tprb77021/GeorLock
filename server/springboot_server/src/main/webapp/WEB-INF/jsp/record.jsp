<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출입기록</title>


<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&family=Roboto&display=swap');
body {
  margin: 0 auto;	
 
}
button {
  border: 0;
  background: 0;
  cursor: pointer;
}
button, input, a {
  outline: none;
}
a {
  text-decoration: none;
  color: #FFF;
  font-family: 'Roboto', sans-serif;
  
}

ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
}

header{
	height: 20%;
	border-bottom: 1px solid #E7E6E6;
	justify-content: space-between;
	display: flex;
	font-family: 'Roboto', sans-serif;
}

.logo img{
	padding-top:10px;
	padding-left:40px;
	width: 190px;
	height: 50px;
	
}

.logout{
	margin-right: 12px;
	font-size: 20px;
	color: #7F7F7F;
	margin-top: 25px;
	margin-right: 30px;
}

.main{
	margin-left: 260px;
	padding-top: 10px;
	font-family: 'Noto Sans KR', sans-serif;	
}

.main h1{
	margin-left:25px;
	color: #053863;
}


.sidenav {
  width: 250px;
  height: 100%;
  background: #2497F4;
  position: fixed;
  text-align: center;
 
}

.container aside {
  margin: 10px;
}
.container aside ul li a {
  padding: 15px 0px;
  margin: 40px 0px;
  
  transition: 0.2s;
  font-family: 'Noto Sans KR', sans-serif;
  
}
.container aside ul li a:hover {
  background: #053863;
}
.container aside ul li a span {
  font-size: 20px;
}

aside a{
	display: block;
}

.search{
	margin: 20px 0px 0px 30px;
	display: inline-block;
	
		
}

.search input[type="date"]{
	height: 27px;
}

.search a{
	color: #053863;
}

.search input[type="text"]{
	width: 250px;
	height: 27px;
}

.table{
margin-top: 30px;
border: 1px solid #7F7F7F;
width: 90%;
text-align: center;
margin-left: auto; 
margin-right: auto;


}






</style>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
</head>
<body>
	<header>
		<div class="logo">
	     	<a href="record.jsp" title="record">	
	        	<img src="images/georlocklogo.png">
	        </a>
	    </div>
	    <div class="logout">
	    	<span>Logout</span>
	    </div>
	</header>

	<div class = "container">
		<aside class="sidenav">
		    <ul>
		      <li>
		        <a href="record.jsp" title="record">
		          <span>출입기록</span>          
		        </a>
		      </li>
		      <li>
		        <a href="access.jsp"title="access">
		          <span>출입권한설정</span>         
		        </a>
		      </li> 
		      <li>
		        <a href="#" title="request">
		          <span>출입요청목록</span>         
		        </a>
		      </li> 
		    </ul>
 		</aside>
	 <main class="main">
			<h1>출입기록</h1>
		 <div class="search">			
			 	<input type = "date"/> ~
			 	<input type = "date"/>			
			    <input type="text" class="search_txt" id="" placeholder="Search..."/>		
			    <a class="search_btn" href="#"><i class="fas fa-search"></i></a>
		</div>
		<table class="table">
		  <tr>
		    <th>NO</th>
		    <th>출입시간</th>
		    <th>사원번호</th>
		    <th>이름</th>
		  </tr>
		  <tr>
		  	<td></td>
		  	<td></td>
		  	<td></td>
		  	<td></td>
		  </tr>
		</table>
	 </main>
	</div>
  </body>
</html>
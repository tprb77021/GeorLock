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
  margin: 0px;	

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
  display: block;
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

.side-panel {

  width: 250px;
  height: 100%;
  background: #2497F4;
  position: fixed;
  text-align: center;
 
}

.side-panel nav {
  margin: 10px;
}
.side-panel nav ul li a {
  padding: 15px 0px;
  margin: 40px 0px;
  border-radius: 2px;
  transition: 0.2s;
  font-family: 'Noto Sans KR', sans-serif;
  
}
.side-panel nav ul li a:hover {
  background: #053863;
}

.side-panel nav ul li a span {
  font-size: 20px;
}

</style>
</head>
<body>
<header>
	<div class="logo">
       <img src="images/georlocklogo.png">
    </div>
    <div class="logout">
    	<span>Logout</span>
    </div>
</header>
<section class='side-panel'>
  <nav>
    <ul>
      <li>
        <a href='#' title='record'>
          <span>출입기록</span>          
        </a>
      </li>
      <li>
        <a href='#' title='access'>
          <span>출입권한설정</span>         
        </a>
      </li> 
    </ul>
  </nav>
</section>
  </body>
</html>
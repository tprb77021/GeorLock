<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>

<style type="text/css">

@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&family=Roboto&display=swap');
body {
 font-family: 'Roboto', sans-serif;
}
	

.login-page {
  width: 400px;
  padding: 8% 0 0;
  margin: auto;
}

.form {
  position:relative;
  max-width: 360px;
  margin: 0 auto 100px;
  padding: 45px;
  text-align: center;
 
}
.form img{
	width: 100%;
	margin-bottom: 30px;
}



.form input[type=text],
.form input[type=password] {
  outline: 0;
  background: #f2f2f2;
  width: 100%;
  border: 0;
  margin: 0 0 15px;
  padding: 15px;
  box-sizing: border-box;
  font-size: 14px;
}
.form button {
 
  text-transform: uppercase;
  outline: 0;
  background: #2497F4;;
  width: 100%;
  border: 0;
  padding: 15px;
  color: #FFFFFF;
  font-size: 14px;
  cursor: pointer;
}
.form button:hover{
  background: #ed7d31;
}



.checkbox{
float: left;
margin-bottom: 10px;
font-family: 'Noto Sans KR', sans-serif;
}

	
</style>
</head>
<body>
	<div class="login-page">
  <div class="form">
    <img src="/WEB-INF/images/georlocklogo.png">
    <form class="login-form">
      	<input type="text" placeholder="ID"  name = "userid" class="inputcontrol"/>
      	<input type="password" placeholder="PASSWORD" name = "userpass" class="inputcontrol"/>   
      	  <div class="checkbox">
          <label class="checklabel">
            <input type="checkbox" value="remember-me" class="inp_radio">자동 로그인
          </label>
        </div>
     	<button type="submit">login</button>
    </form>
  </div>
</div>
</body>
</html>
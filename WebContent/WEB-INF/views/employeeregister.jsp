<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
* {
  box-sizing: border-box;
}
form
{
 	box-sizing: border-box;
 	border: 1px solid #ccc;
    border-radius: 4px;
    width:60%;
    background-color:lightgrey;
  }
h2 {
    text-align:center;
    font-size:30px;
    color:darken(#e5e5e5, 50%);
  }
input[type=text],input[type=password], select, textarea {
  width: 50%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
}
input[type=submit]{
    width: 50%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
    background-color:green;
}
</style>
</head>
<body>
 <div align="center">
  <h1>Employee Register Form</h1>
  <form action="<%= request.getContextPath() %>/register" method="post">
   	<h2>Sign Up</h2>
   	<p>
		<input id="username" name="username" type="text" placeholder="USERNAME">
	</p>
	<p>
		<input id="mobile" name="mobile" type="text" placeholder="MOBILE">
	</p>
	<p>
		<input id="email" name="email" type="text" placeholder="EMAIL">
	</p>
	<p>
		<input id="password" name="password" type="password" placeholder="PASSWORD">
	</p>
	<p>
		<input type="submit" value="SUBMIT" id="submit">
	</p>
	<p>
		Already a member ? <a href="login">Login</a>
	</p>
  </form>
 </div>
</body>
</html>
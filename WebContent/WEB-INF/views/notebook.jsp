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
  <form action="<%= request.getContextPath() %>/notebookser" method="post">
   	<h2>NEW NOTEBOOK</h2>
   	<p>
		<input id="username" name="username" type="text" placeholder="USERNAME">
	</p>
	<p>
		<input id="notebookname" name="notebookname" type="text" placeholder="NOTEBOOK-NAME">
	</p>
	<p>
		<input type="submit" value="SAVE" id="submit">
	</p>
  </form>
 </div>
</body>
</html>
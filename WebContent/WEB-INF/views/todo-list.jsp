<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>

<head>

<title>User Management Application</title>

<link rel="stylesheet"
 href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
 integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
 crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
.notification {
  background-color: #555;
  color: white;
  text-decoration: none;
  padding: 15px 26px;
  position: relative;
  display: inline-block;
  border-radius: 2px;
}

.notification:hover {
  background: red;
}

.notification .badge {
  position: absolute;
  top: -10px;
  right: -10px;
  padding: 5px 10px;
  border-radius: 50%;
  background: red;
  color: white;
}
.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 260px;
  text-align:center;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  padding: 12px 16px;
  z-index: 1;
}

.dropdown:hover .dropdown-content {
  display: block;
}
 </style>
 
</head>

<body style="background-color: #E8E8E8">

 <header>
  
  <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #404040">
   
   <div>
    <a href="" class="navbar-brand"> Notifier App</a>
   </div>
   
   <ul class="navbar-nav">
    <li><a href="<%=request.getContextPath()%>/notebook" class="nav-link">Notebooks</a></li>
   </ul>

   <ul class="navbar-nav">
    <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Notes</a></li>
   </ul>
   
   <ul class="navbar-nav">
    <li><a href="<%=request.getContextPath()%>/edituser" class="nav-link">Edit User</a></li>
   </ul>
   
   <ul class="navbar-nav navbar-collapse justify-content-end">
    
     <div class="dropdown">
  		 <a class="notification" href="<%=request.getContextPath()%>/notify">
   		 <i class="fa fa-bell"></i>
   		 <span class="badge"><c:out value="${fn:length(listnotification)}" /></span>
   		 </a>
     	<div class="dropdown-content">
			<c:forEach var="notification" items="${listnotification}">
      			<p style="color:Blue;text-transform: uppercase;"> <c:out value="${notification.title}" /> <p>
       			<p> ( <c:out value="${notification.startDate}" /> ) </p> 
    		</c:forEach>
   		</div>
    </div>
   
    <li><a href="<%=request.getContextPath()%>/newnotebook" class="nav-link">New Notebook</a></li>

    <li><a href="<%=request.getContextPath()%>/logout" class="nav-link">Logout</a></li>
   
   </ul>
  
  </nav>
 
 </header>

 <div class="row" style="background-color: #E8E8E8">

  <div class="container">
   <h3 class="text-center">All Notes</h3>
   <hr>
   
   <div class="container text-left">
     <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add Note</a>
   </div>
   
   <br> 
     
     
    <c:forEach var="todo" items="${listTodo}">
    <div style="background-color: #B8B8B8; width:95%; border-radius: 5px; padding: 10px; margin: 5px; justify-content: space-between; text-align:justify">
    
       <strict style="font-weight:bold;"> <c:out value="${todo.title}" /> </strict>
        Started on : <c:out value="${todo.startDate}" /> 
       <p style="margin-left :70%;">
       <a href="showdetails?id=<c:out value='${todo.id}' />"><i class="fa fa-plus">View</i></a>
        &nbsp;&nbsp;&nbsp;&nbsp;
       <a href="edit?id=<c:out value='${todo.id}' />"><i class="fa fa-edit">edit</i></a>
        &nbsp;&nbsp;&nbsp;&nbsp; 
        <a href="delete?id=<c:out value='${todo.id}' />"><i class="fa fa-trash">delete</i></a>
        <p>
    </div>
    </c:forEach>
    
    <c:forEach var="notebook" items="${listnotebook}">
    <div style="background-color: #B8B8B8; width:95%; border-radius: 5px; padding: 10px; margin: 5px; justify-content: space-between; text-align:justify">
    
       <strict style="font-weight:bold;"> <c:out value="${notebook.notebook_name}" /> </strict><br>
       <c:out value="${notebook.notes_count}" /> Notes
       
       <p style="margin-left :70%;">
       <a href="new" /><i class="fa fa-plus"></i></a>
        &nbsp;&nbsp;&nbsp;&nbsp;
       <a href="editnotebook?id=<c:out value='${notebook.id}' />" ><i class="fa fa-edit">edit</i></a>
        &nbsp;&nbsp;&nbsp;&nbsp; 
       <a href="deletenotebook?id=<c:out value='${notebook.id}' />" ><i class="fa fa-trash">delete</i></a>
        <p>
    </div>
    </c:forEach>
    
    
    
     <c:forEach var="todos" items="${showdetail}">
      <div style="background-color: white; width:95%; border-radius: 5px;padding-top:20px;padding-bottom:20px;padding-left:20px;justify-content: space-between; text-align:justify">
       Note
       <table style="width:95%; ">
       <tr style="background-color:#DCDCDC;  width:95%; height:40px; padding-top:8px;">
          <td><strict style="font-weight:bold;">Name : </td>
          <td></strict><c:out value="${todos.title}" /> </td>
       </tr>
       <tr style="background-color:white;  width:95%; height:40px; padding-top:8px;">
        <td> <strict style="font-weight:bold;">Start Date : </strict></td>
        <td><c:out value="${todos.startDate}" />  </td>
       </tr>
       <tr style="background-color:#DCDCDC;  width:95%; height:40px; padding-top:8px; ">
        <td><strict style="font-weight:bold;">End Date : </strict></td>
        <td><c:out value="${todos.endDate}" /></td> 
       </tr>
       <tr style="background-color:white;  width:95%; height:40px; padding-top:8px;">
        <td><strict style="font-weight:bold;">Remainder Date : </strict></td>
        <td><c:out value="${todos.remainderDate}" /> </td> 
       </tr>
       <tr style="background-color:#DCDCDC;  width:95%; height:40px; padding-top:8px;">
        <td><strict style="font-weight:bold;">Status : </strict></td>
        <td><c:out value="${todo.status ? 'Completed' : 'Started'}" /> </td>
       </tr> 
       <tr style="background-color:white;  width:95%; height:40px; padding-top:8px;">
        <td><strict style="font-weight:bold;">Description : </strict></td>
        <td><c:out value="${todos.description}" /> </td>
       </tr>
       </table>
       <a href="<%=request.getContextPath()%>/list">Back</a>
    </div>
    </c:forEach>
    
  </div>
  
 </div>
</body>
</html>
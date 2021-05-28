<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Notifier App</title>

<link rel="stylesheet"
 href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
 integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
 crossorigin="anonymous">
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>

</head>
<body style="background-color: #E8E8E8">
 <header>
  <nav class="navbar navbar-expand-md navbar-dark"
   style="background-color: #404040">
   <div>
    <a href="https://www.javaguides.net" class="navbar-brand"> Notifier App</a>
   </div>
   
   <ul class="navbar-nav">
    <li><a href="<%=request.getContextPath()%>/notebook"
     class="nav-link">Notebooks</a></li>
   </ul>

   <ul class="navbar-nav">
    <li><a href="<%=request.getContextPath()%>/list"
     class="nav-link">Notes</a></li>
   </ul>
   
   <ul class="navbar-nav">
    <li><a href="<%=request.getContextPath()%>/edituser"
     class="nav-link">Edit User</a></li>
   </ul>

   <ul class="navbar-nav navbar-collapse justify-content-end">
    <li><a href="<%=request.getContextPath()%>/logout"
     class="nav-link">Logout</a></li>
   </ul>
  </nav>
 </header>
 <div class="container col-md-5" style="background-color: #E8E8E8">
  <div class="card">
   <div class="card-body">
    <c:if test="${todo != null}">
     <form action="update" method="post">
    </c:if>
    <c:if test="${todo == null}">
     <form action="insert" method="post">
    </c:if>

    <caption>
     <h2>
      <c:if test="${todo != null}">
               Edit Todo
              </c:if>
      <c:if test="${todo == null}">
               Add New Todo
              </c:if>
     </h2>
    </caption>

    <c:if test="${todo != null}">
     <input type="hidden" name="id" value="<c:out value='${todo.id}' />" />
    </c:if>
    
    <fieldset class="form-group">
     <label>Username</label> <input type="text"
      value="<c:out value='${todo.username}' />" class="form-control"
      name="username" required="required" minlength="2">
    </fieldset>
    
    <fieldset class="form-group">
     <label>NoteBook Name</label> <input type="text"
      value="<c:out value='${todo.notebook_name}' />" class="form-control"
      name="notebook_name" required="required" minlength="5">
    </fieldset>

    <fieldset class="form-group">
     <label>Note Title</label> <input type="text"
      value="<c:out value='${todo.title}' />" class="form-control"
      name="title" required="required" minlength="5">
    </fieldset>

    <fieldset class="form-group">
     <label>Note Decription</label> <input type="text"
      value="<c:out value='${todo.description}' />" class="form-control"
      name="description" minlength="5">
    </fieldset>

    <fieldset class="form-group">
     <label>Note Status</label> <select class="form-control"
      name="isDone">
      <option value="false">In Progress</option>
      <option value="true">Complete</option>
     </select>
    </fieldset>

    <fieldset class="form-group">
     <label>Note Start Date</label> 
     <input type="text" value="<c:out value='${todo.startDate}' />" class="form-control" name="startDate" required="required">
    </fieldset>
    
    <fieldset class="form-group">
     <label>Note End Date</label>
     <input type="text" value="<c:out value='${todo.endDate}' />" class="form-control" name="endDate" required="required">
    </fieldset>
    
    <fieldset class="form-group">
     <label>Note Remainder Date</label> 
     <input type="text" value="<c:out value='${todo.remainderDate}' />" class="form-control" name="remainderDate" required="required">
    </fieldset>

    <button type="submit" class="btn btn-success">Save</button>
    </form>
   </div>
  </div>
 </div>
</body>
</html>
<%@ page import="in.sp.model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome Page</title>
</head>
<body>

<%
User user = (User) session.getAttribute("session_user");
%>

<h4>Welcome</h4>
 <h3>Name : <%=user.getName()%></h3>
  <h3>Email : <%=user.getEmail()%></h3>
   <h3>place : <%=user.getCity()%></h3>
<a href="logout">Logout</a>
 </body>
 </html>

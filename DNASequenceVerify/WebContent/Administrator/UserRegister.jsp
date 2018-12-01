<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Register</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/Normal.css">
</head>
<body>
<div class="main">
<%@include file="taglib.jsp" %>
			<div class="menu">
     			<ul>
     			<li>
     				<c:choose>
         				<c:when test="${empty sessionScope.userbean}">
         					<span><a href="${pageContext.request.contextPath}/common/Index.jsp" >Log in</a></span>
         				</c:when>
         
         				<c:otherwise>
         					<span>Welecome, ${sessionScope.userbean.getUsername()}</span>
         					<Span>|</Span>
         					<Span><a href="${pageContext.request.contextPath}/servlet/LogoutService">Log out</a></Span>
         				</c:otherwise>
      				</c:choose>
      			</li>
     				
          			<li><a href="${pageContext.request.contextPath}/servlet/ListFileService">File list</a></li>
          			<li><a href="${pageContext.request.contextPath}/servlet/ListExperimentService">Experiment list</a></li>
          			<li><a href="${pageContext.request.contextPath}/common/Profile.jsp">Profile</a></li>
          			<li><a href="${pageContext.request.contextPath}/servlet/ListUserService">User list</a></li>
     			</ul>
     	 	</div>
		    <div class="list">
		         <form action="${pageContext.request.contextPath}/servlet/CreateUserService"  method="post">
     			<ul>
         			<li>User name:<input type="text" name="username" ></li>
         			<li>Password:<input type="text" name="password"></li>
         			<li>Email:<input type="text" name="email"></li>
         			<li>Address:<input type="text" name="address"></li>
         			<li>Telephone:<input type="text" name="telephone"></li>
         			<li>user is administrator or not:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
         			<input type="radio" name="isadmin" value="yes"/>Yes<input type="radio" name="isadmin" value="no"/>No</li>
         			<li><input type="submit" value="Register"></li>
     			</ul>
     			</form>
     					<a href='${pageContext.request.contextPath}/servlet/ListUserService'>Return to user list</a>
			</div>
	</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Adding a new experiment</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/Normal.css">

</head>
<body>
<%@include file="taglib.jsp" %>

<div class="main">
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
          		<li><a href="${pageContext.request.contextPath}/Administrator/AdministerFile.jsp">File list</a></li>
          		<li><a href="${pageContext.request.contextPath}/servlet/ListExperimentService">Experiment list</a></li>
          		<li><a href="${pageContext.request.contextPath}/Administrator/AdministerProfile.jsp">Profile</a></li>
          		<li>${link}</li>
     		</ul>
     	 </div>
      	 <div class="list">
      	 	<form action="${pageContext.request.contextPath}/servlet/CreateExperimentService" enctype="multipart/form-data"  method="post">
      	 	<ul>
         		<li>Experiment name:<input type="text" name="expname"></li>
         		<li><c:out value="Input data:"/><input type="file" name="file1" value="Select file"></li>
         		<li><input type="submit" value="submit"></li>
         	</ul>
      	 	</form>
         	
      	 </div> 
</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" import="bean.User"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Administer Index</title>
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
     				
          			<li><a href="${pageContext.request.contextPath}/servlet/ListFileService">File list</a></li>
          			<li><a href="${pageContext.request.contextPath}/servlet/ListExperimentService">Experiment list</a></li>
          			<li><a href="${pageContext.request.contextPath}/common/Profile.jsp">Profile</a></li>
          			<li><a href="${pageContext.request.contextPath}/servlet/ListUserService">User list</a></li>
     			</ul>
     	 	</div>
    			<div class="list"> 
      		<ul>
      			<li><c:out value="User name"/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<c:out value="User id "/></li>
     			<c:forEach var="element" items="${usertable}">
     			<form action="${pageContext.request.contextPath}/servlet/ListProfileService"  method="post">
        				<input type="hidden" name="userid" value="${element.value}">
        				<li><input type="submit" name="userid" value="${element.key}">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<c:out value="${element.value}"/></li>
     			</form>
     			</c:forEach> 
     		</ul>
     		<a href="${pageContext.request.contextPath}/Administrator/UserRegister.jsp">Register a new user</a>
     		</div>
     </div>


</body>
</html>
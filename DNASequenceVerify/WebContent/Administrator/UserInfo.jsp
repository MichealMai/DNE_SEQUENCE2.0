<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Infomation</title>
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
		         <form action="${pageContext.request.contextPath}/servlet/EditUserInfoService"  method="post">
     			<ul>
         			<li>User id:<input type="hidden" name="userid" value="${userinfo.getUserid()}"><c:out value="${userinfo.getUserid()}"></c:out></li>
         			<li>User name:<input type="text" name="username" value="${userinfo.getUsername()}"></li>
         			<li>Password:<input type="text" name="password" value="${userinfo.getPassword()}"></li>
         			<li>Email:<input type="text" name="email" value="${userinfo.getEmail()}"></li>
         			<li>Address:<input type="text" name="address" value="${userbean.getAddress()}"></li>
         			<li>Telephone:<input type="text" name="telephone" value="${userinfo.getTelephone()}"></li>
         			<c:choose>
            				<c:when test="${userinfo.getIsAdmin().equals('yes')}">
            					<li>user is administrator or not:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="radio" name="isadmin" value="yes" checked/>Yes<input type="radio" name="isadmin" value="no"/>No</li>
            				</c:when>
         			<c:otherwise>
            					<li>user is administrator or not:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="radio" name="isadmin" value="yes"/>Yes<input type="radio" name="isadmin" value="no" checked/>No</li>
           			</c:otherwise>
        				</c:choose> 
         
         			<li><input type="submit" value="Update"></li>

     			</ul>
     			</form>
     					<a href='${pageContext.request.contextPath}/servlet/ListUserService'>Return to user list</a>
			</div>
	</div>


</body>
</html>
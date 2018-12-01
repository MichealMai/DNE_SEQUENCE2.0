<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/Normal.css">
</head>
<body>

<div class="main">
      <jsp:include page="/common/AdministratorMenu.jsp"></jsp:include>
      <div class="list">
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
         <li>User id:<c:out value="${userbean.getUserid()}"></c:out></li>
         <li>User name:<input type="text" name="username" value="${userbean.getUsername()}"></li>
         <li>Password:<input type="text" name="password" value="${userbean.getPassword()}"></li>
         <li>Email:<input type="text" name="email" value="${userbean.getEmail()}"></li>
         <li>Telephone:<input type="text" name="telephone" value="${userbean.getTelephone()}"></li>
         <c:choose>
            	<c:when test="${userbean.getIsAdmin().equals('yes')}">
            			<li>user is administrator or not:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="radio" name="isadmin" value="yes" checked/>Yes<input type="radio" name="isadmin" value="no"/>No</li>
            	</c:when>
         	<c:otherwise>
            			<li>user is administrator or not:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="radio" name="isadmin" value="yes"/>Yes<input type="radio" name="isadmin" value="no" checked/>No</li>
           	</c:otherwise>
        	</c:choose>
         
         </ul>
      </div>
</div>

</body>
</html>
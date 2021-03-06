<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Header</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/Normal.css">
</head>
<body>
<%@include file="taglib.jsp" %>
   <div class="header">
   <div class='last'>
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
   
   </div>
         <div class='first'><img src="${basePath}/static/image/logo.png"/></div>
         
    
    </div>
</body>
</html>
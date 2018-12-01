<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Administer File</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/Normal.css">
</head>
<body>
<div class="main">
      <jsp:include page="/common/AdministratorMenu.jsp"></jsp:include>
      <iframe src='${pageContext.request.contextPath}/servlet/ListFileService' style="width:60%;height:800px" frameborder=no></iframe>
</div>
</body>
</html>
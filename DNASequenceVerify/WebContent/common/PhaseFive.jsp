<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Phase five</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/Normal.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jQuery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/ajaxfileupload.js"></script>
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
          			<li>${link}</li>
     			</ul>
     	 	</div>
     	 	<div class="list" style="float:right;width:80%; background:#FF5688">
				<ul>
					<li><c:out value="Experiment name: ${expbean.getExpname()}"/></li>
					<li><c:out value="FP value: ${expbean.getFp()}"/></li>
					<li><c:out value="FN value: ${expbean.getFn()}"/></li>
					<li><c:out value="Input file: ${inputfilename}"/></li>
					<li><c:out value="Phase one result file name: "/></li>
					<li><c:out value="${phaseonefilename}"/></li>

					<form action="${pageContext.request.contextPath}/servlet/DownloadService" method="post">
						<input type="hidden" name="fileid" value="${expbean.getPhaseonefileid()}">
						<li><input type="submit" value="Download phase one result"></li>
					</form>
					
					<li><c:out value="Motifs number: ${expbean.getMotifsnumber()}"/></li>
					<li><c:out value="Feature selection: ${expbean.getFeatureselection()}"/></li>
					<li><c:out value="Extra feature: ${expbean.getExtrafeature()}"/></li>
					<li><c:out value="Phase two result file name: "/></li>
					<li><c:out value="${phasetwofilename}"/></li>
					
					<form action="${pageContext.request.contextPath}/servlet/DownloadService" method="post">
						<input type="hidden" name="fileid" value="${expbean.getPhasetwofileid()}">
						<li><input type="submit" value="Download phase two result"></li>
					</form>
					
					<li><c:out value="K fold: ${expbean.getKfold()}"/></li>
					<li><c:out value="The value of c(SVM parameter): ${expbean.getCvalue()}"/></li>
					
<%-- 					<form action="${pageContext.request.contextPath}/servlet/DownloadService" method="post">
						<input type="hidden" name="fileid" value="${expbean.getPhasethreefileid()}">
						<li><input type="submit" value="Download phase three result"></li>
					</form>	 --%>
					
					<li><c:out value="Number of sequences: ${expbean.getNumberofsequences()}"/></li>
					<li><c:out value="Desired class: ${expbean.getDesiredclass()}"/></li>
					
<%-- 					<form action="${pageContext.request.contextPath}/servlet/DownloadService" method="post">
						<input type="hidden" name="fileid" value="${expbean.getPhasefourfileid()}">
						<li><input type="submit" value="Download phase four result"></li>
					</form>	 --%>
					
				</ul>
			</div>
		</div>
</body>
</html>
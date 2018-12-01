<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Experiment list</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/Normal.css" type="text/css" charset="utf-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jQuery.js"></script>

<script type="text/javascript">
	function edit()
	{
		var eid=document.getElementsByName('expid');//get experiment id
		for(var i=0;i<eid.length;i++)
		{
			if(eid[i].checked==true)
			{
				var checkvalue=eid[i].value;
			}
		}
		
		$.ajax({
			type:"post",//请求方式
			url:"${pageContext.request.contextPath}/servlet/ListExperimentInfoService",//请求地址
			data:{"expid":checkvalue},
			error:function(){
				alert("The experiment is not exist");
			},
			success:function(data){
				alert(data.expid);
				window.location.href="${pageContext.request.contextPath}/common/experimentInfo.jsp?data="+data;
			}
	});
	}
	
	function deleted()
	{
		var eid=document.getElementsByName('expid');//get experiment id
		for(var i=0;i<eid.length;i++)
		{
			if(eid[i].checked==true)
			{
				var checkvalue=eid[i].value;
			}
		}
			
		$.ajax({
			type:"post",//请求方式
			url:"http://169.226.135.21:5000/processRequest",//请求地址
			data:{"expid":checkvalue},
			error:function(){
				alert("The experiment is not exist");
			},
			success:function(data){
				//alert(data.algname);
				window.location.href="{pageContext.request.contextPath}/servlet/ListExperimentService";
			}
	});
	}
	
	
</script>
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
      	 <div class="list">
         	<ul>
            		<li style="height:30px; float:center; width:900px;"><c:out value="Option"/>
            		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
            		<c:out value="Experiment name"/>
            		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
            		<c:out value="Phase"/>
            		</li>
					
            		<c:forEach var="element" items="${expNameMap}">
<%--             		 		<li style="width:75%; height:5px; float:right;"><c:out value="${element.key}"/></li>
            		 		<li style="width:63%; height:5px; float:right;"><c:out value="${expPhaseMap.get(element.value)}"/></li> --%>
            		 		
               		<li style="height:100px">
					<form action="${pageContext.request.contextPath}/servlet/ListExperimentInfoService"  method="post">
                  		<input type="hidden" name="expid" value="${element.value}">
               			<input type="submit" value="Continue">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
               			&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
               			<c:out value="${element.key}"/>&nbsp&nbsp&nbsp&nbsp&nbsp
               			&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
               			&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
               			<c:out value="${expPhaseMap.get(element.value)}"/>
              		</form>
              		<form action="${pageContext.request.contextPath}/servlet/DeleteExperimentService"  method="post">
                  		<input type="hidden" name="expid" value="${element.value}">
               			<input type="submit" value="Delete">
              		</form>
                		</li>
            		 </c:forEach>
            		<li><a href="${pageContext.request.contextPath}/servlet/ListExperimentCreatedForm">Add a new experiment</a></li>
            		<li><a href="${pageContext.request.contextPath}/common/ExampleExperiment.jsp">Add a new experiment with example input file</a></li>
         	</ul>
         	<div></div>
      	</div> 
      	
</div>

</body>
</html>

<%--               		 --%>
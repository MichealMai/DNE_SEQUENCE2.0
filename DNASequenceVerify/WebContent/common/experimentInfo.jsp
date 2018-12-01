<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Experiment info</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/Normal.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jQuery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/ajaxfileupload.js"></script>

<script type="text/javascript">
function testexp()
{
	var form=$('#parameter')
	var fp=form.find("input[name='fp']").val();
	var fn=form.find("input[name='fn']").val();
	$.ajax({
		url:"http://169.226.135.21:5000/DNASequenceVerify/servlet/RunPhaseOneService",
		type:"post",
		dataType:"json",
		contentType :"application/x-www-form-urlencoded; charset=utf-8",
        data:$('#parameter').serialize(),
        processData:false,
        beforeSend:function()
        {
        		$("#submit").attr({disabled:"disabled"});
        		$("#load-layout").show();
		},
 		complete:function()
		{
			
		},
        success:function(data)
		{
        		$("#load-layout").hide();
    			$("#submit").removeAttr("disabled");
        		var expid=data[0]["experimentid "];
        		var filename=data[0].filename;
        		var maximummotifs=data[0].totalMotifs;
			window.location.href="/DNASequenceVerify/servlet/UpdatePhaseOneService?expid="+expid+"&filename="+filename+"&fp="+fp+"&fn="+fn+"&maximummotifs="+maximummotifs;	
		},
		error:function(err)
		{	
			$("#load-layout").hide();
			$("#submit").removeAttr("disabled");
			alert("Test error");
		}
	});
}

</script>
</head>

<body>
<div id="load-layout" style="position:fixed;width:100%;height:100%;filter:alpha(opacity=20);opacity:0.1;background:#000;display:none">
	<div style="position:absolute;left:38%;top:200px;">
		<img src="${basePath}/DNASequenceVerify/static/image/timg.gif"/>
	</div>
	<c:out value="Please waitting the result"/>
</div>
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
         	<li>Phase 1: Motify Mining</li>
         	<form id="parameter" enctype="multipart/form-data">
         	<input type="hidden" name="expid" value="${expbean.getExpid()}"/>
         	<input type="hidden" name="userid" value="${userbean.getUserid()}"/>
         	<input type="hidden" name="fileName" id="fileName" value="${inputfilename}">
         	<li><c:out value="FP value"/><input type="text" name="fp" value="${expbean.getFp()}"/></li>
         	<li><c:out value="FN value"/><input type="text" name="fn" value="${expbean.getFn()}"/></li>
    			<li><c:out value="Input file :${inputfilename}"/></li>
    			<input type="button" value="Countinue" name="submit" id="submit" onclick="testexp();">
			</form>
         	</ul>
         	<a href="${pageContext.request.contextPath}/servlet/ListExperimentService" style="float:left">Return to experiment list</a>

     </div>
	</div>

</body>
</html>
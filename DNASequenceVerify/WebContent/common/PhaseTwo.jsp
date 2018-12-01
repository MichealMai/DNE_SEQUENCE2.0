<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Phase Two</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/Normal.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jQuery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/ajaxfileupload.js"></script>
<script type="text/javascript">
function testphasetwo()
{
	//parameter
	var form=$('#parameter');
	var motifsnumber=form.find("input[name='motifsnumber']").val();
	var featureselection=form.find("input[name='featureselection']").val();
	var ef=form.find("input[name='extrafeature']").is(":checked");
	var maxmotifs=form.find("input[name='getMaximummotifs']").val();
	var expid=form.find("input[name='expid']").val();
	var extrafeature
	//get all filename
	if (ef==true)
	{
		extrafeature="True"
	}
	else
	{
		extrafeature="False"
	}
	
	
	if(parseInt(motifsnumber)<parseInt(maxmotifs))
	{
		$.ajax({
			url:"http://169.226.135.21:5000/DNASequenceVerify/servlet/RunPhaseTwoService",
			type:"post",
			dataType:"json",
			contentType :"application/x-www-form-urlencoded; charset=utf-8",
	        data:{"featureselection":featureselection,"motifsnumber":motifsnumber,"extrafeature":extrafeature},
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
	        		var filename=data[0].stringOutput;
				window.location.href="/DNASequenceVerify/servlet/UpdatePhaseTwoService?expid="+expid+"&filename="+filename+"&motifsnumber="+motifsnumber+"&featureselection="+featureselection+"&extrafeature="+extrafeature;				
			},
			error:function(err)
			{
				$("#load-layout").hide();
				$("#submit").removeAttr("disabled");
				alert("Test error");
			}
		});
	}
	
	else
	{
		alert("Motifs number is over the maximum");
	}
	
}
</script>
</head>
<body>
<%@include file="taglib.jsp" %>
<div id="load-layout" style="position:fixed;width:100%;height:100%;filter:alpha(opacity=20);opacity:0.1;background:#000;display:none">
	<div style="position:absolute;left:38%;top:200px;">
		<img src="${basePath}/static/image/timg.gif"/>
	</div>
	<c:out value="Please waitting the result"/>
</div>

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
     			    <li>${link}</li>
          			<li><a href="${pageContext.request.contextPath}/servlet/ListFileService">File List</a></li>
          			<li><a href="${pageContext.request.contextPath}/servlet/ListExperimentService">Experiment List</a></li>
          			<li><a href="${pageContext.request.contextPath}/common/Profile.jsp">Profile</a></li>
     			</ul>
     	 	</div>
		    <div class="list" style="float:right;width:40%; background:#FF5688">
				<ul>
					<li><c:out value="Experiment name: ${expbean.getExpname()}"/></li>
					<li><c:out value="FP Value: ${expbean.getFp()}"/></li>
					<li><c:out value="FN Value: ${expbean.getFn()}"/></li>
					<li><c:out value="Input file: ${inputfilename}"/></li>
					<li><c:out value="Phase One Result file Name: "/></li>
					<li><c:out value="${phaseonefilename}"/></li>
					<form action="${pageContext.request.contextPath}/servlet/DownloadService" method="post">
						<input type="hidden" name="fileid" value="${expbean.getPhaseonefileid()}">
						<li><input type="submit" value="Download phase one result"></li>
					</form>
					</ul>
			</div>
			<div class="list">
					<ul>
					<li>Phase 2: Adding feature</li>
					<form id="parameter">
						<input type="hidden" name="expid" value="${expbean.getExpid()}"/>
         				<input type="hidden" name="userid" value="${userbean.getUserid()}"/>         				
         				<input type="hidden" name="getMaximummotifs" value="${expbean.getMaximummotifs()}"/>
         				<li><c:out value="Maximum motifs number: ${expbean.getMaximummotifs()}"/></li>
         				<li><c:out value="Motifs number"/></br><input type="text" name="motifsnumber" value="${expbean.getMotifsnumber()}"/></li>
         				<li><c:out value="Feature selection"/></br><input type="text" name="featureselection" value="${expbean.getFeatureselection()}"/></li>
         		
         				<c:choose>
         					<c:when test="${expbean.getExtrafeature().equals('True')}">
         						<li><input type="checkbox" name="extrafeature" checked><c:out value="Extra feature"/></li>
         					</c:when>
         					<c:otherwise>
         						<li><input type="checkbox" name="extrafeature"><c:out value="Extra feature"/></li>
         					</c:otherwise>
         				</c:choose>
						<li><input type="button" value="Countinue to phase three" name="submit" onclick="testphasetwo();"></li>
					</form>
					
					<li><input type="button" onclick="window.location.href='/DNASequenceVerify/servlet/ReturnPreviousPhaseService'" value="Go back to phase one"></li>
<%-- 					<form action="${pageContext.request.contextPath}/servlet/ListExperimentInfoService"  method="post">
						<input type="hidden" name="expid" value="${expid}">
						<li><input type="submit" value="Go back to phase one"></li> --%>
					<!-- </form>	 -->									
				</ul>
			</div>
		</div>
</body>
</html>
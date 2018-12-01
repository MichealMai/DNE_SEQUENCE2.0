<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>File List</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/Normal.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jQuery.js"></script>

<script type="text/javascript">
	function download()
	{
		var fid=document.getElementsByName('fileid');//document.getElementById("fileid").value;
		//alert(fid.length);
		for(var i=0;i<fid.length;i++)
		{
			if(fid[i].checked==true)
			{
				var checkfile=fid[i].value;
			}
		}
			
		$.ajax({
			type:"post",//请求方式
			url:"${pageContext.request.contextPath}/servlet/DownloadService",//请求地址
			data:{"fileid":checkfile},
			error:function(){
				alert("The file is not exist");
			},
			success:function(data){
				alert("success");
				window.location.href="${pageContext.request.contextPath}/servlet/ListFileService";
			}
	});
		//alert("${pageContext.request.contextPath}/servlet/DownloadService");
	}
	
	
	function Delete()
	{
		var fid=document.getElementsByName('fid');
		for(var i=0;i<fid.length;i++)
		{
			if(fid[i].checked==true)
			{
				var checkfile=fid[i].value;
			}
		}
		$.ajax({
			type:"post",//请求方式
			url:"${pageContext.request.contextPath}/servlet/DeleteFileService",//请求地址
			data:{"fileid":checkfile},
			
			error:function(){
				alert("the file is not exist");
			},
			success:function(data){
				var obj=$.parseJSON(data);
				if(obj['message']==1)
				{
					alert("success delete file");
					window.location.href="${pageContext.request.contextPath}/servlet/ListFileService";
				}
				else
				{
					alert("The file is still used in experiment, please delete the experiments which are using the current file or change experiment input file!!");
				}
				
			}
			
	});
		//alert("${pageContext.request.contextPath}/servlet/DownloadService");
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
     	 	
     	 	<form action="${pageContext.request.contextPath}/servlet/DownloadService" method="post">
     			<ul>
     				<li>Download file list</li>
     				<c:forEach var="element" items="${fileNameMap}">
         				<li><input type="radio" id="fileid" name="fileid" value="${element.value}">${element.key}</li>
     				</c:forEach>
<!--      					<li><input type="submit" id="btn" value="Download file" onclick="download()"></li> -->
     					<li><input type="submit" value="Download file"></li>
     			</ul>
     		</form>
     		
     			<ul>
     				<li>Delete file list</li>
     				<c:forEach var="element" items="${fileNameMap}">
         				<li><input type="radio" id="fid" name="fid" value="${element.value}">${element.key}</li>
     				</c:forEach>
      					<li><input type="submit" id="btn" value="Delete file" onclick="Delete()"></li>
     					<!-- <li><input type="submit" value="Delete file"></li> -->
     					
     				<li><a href="${pageContext.request.contextPath}/common/Upload.jsp"><input type ="button" value="Upload new file"></a></li>
     			</ul>
     			
     		</div>  
     </div>
     ${filepath}


</body>
</html>
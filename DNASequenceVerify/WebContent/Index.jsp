<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/Normal.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jQuery.js"></script>

<script type="text/javascript">
	function login()
	{
		var username=$('#username').val();//get username
		var password=$('#password').val();//get password
		$.ajax({
			type:"post",//请求方式
			url:"${pageContext.request.contextPath}/servlet/LoginService",//请求地址
			data:{"username":username,"password":password},
			error:function(e){
				alert("Invaild input");
			},
			success:function(data){
				var obj=$.parseJSON(data);
				if(obj['message']==0)
				{
					alert("user is not exist");
				}
				else {
					if(obj['message']==1)
					{
						alert("Username or password is not match");
					}
					else
					{
						if(obj['message']==2)
						{
							window.location.href="${pageContext.request.contextPath}/servlet/ListUserService";
						}
						else
						{
							window.location.href="${pageContext.request.contextPath}/servlet/ListExperimentService";
						}
					}
				}
			}
	});
	}
</script>
</head>
<body>
<jsp:include page="/common/Header.jsp"></jsp:include>

      <div class="login">
           <ul>
                 <li>User name:<input type="text" id="username"></li>
                 <li>Password:<input type="password" id="password"></li>
                 <li><input type="submit" value="sign in" onclick="login()"></li>
           </ul>
      </div>
</body>
</html>
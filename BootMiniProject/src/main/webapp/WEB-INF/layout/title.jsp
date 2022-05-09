<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>   
<link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&family=Jua&family=Lobster&family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<style type="text/css">
	h5.title{
		font-family: 'Lobster';
	}
	div.login {
		position: absolute;
		left: 400px;
		top: 30px;
		width: 300px;
	}
</style>
</head>
<c:set var="root" value="<%=request.getContextPath()%>"/>
<body>
	<a href="/">
	<img src="${root}/image/shop.jpg" height="80">
	<h5 class="title">SpringBoot+Tiles+Mybatis Project</h5></a>
	
	<div class="login">
		<c:if test="${sessionScope.loginok==null}">
			<button type="button" class="btn btn-success"
				style="width:100px;" 
				onclick="location.href='${root}/login/form'">Login</button>
		</c:if>
		
		<c:if test="${sessionScope.loginok!=null}">
			<b>${sessionScope.loginname}(${sessionScope.loginid}) 님</b>
			&nbsp;&nbsp;
			<button type="button" class="btn btn-info btn-sm" 
				style="width:80px;"
				onclick="logout()">Logout</button>
		</c:if>
		<script type="text/javascript">
			function logout() {
				$.ajax({
					type:"get",
					dataType:"text",
					url:"${root}/login/logout",
					success:function(){
						location.reload();
					}
				});
			};
		</script>
	</div>
</body>
</html>


<!-- 
로그인시 세션

  loginid  : 로그인한 아이디 저장
  loginname: 로그인한 사람의 이름
  saveid :  yes/no
  loginok: yes/null
 -->







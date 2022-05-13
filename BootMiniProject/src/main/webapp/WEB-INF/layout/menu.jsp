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
<link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&family=Jua&family=Lobster&family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script> 

<style type="text/css">
	body{
		font-size: 2rem;
		font-family: 'Jua';
	}
	
	ul.menu{
		list-style: none;		
	}
	
	ul.menu li{
		width: 130px;
		float: left;
		border: 1px solid black;
		height: 60px;
		line-height: 60px;
		margin-right: 10px;
		text-align: center;
		font-size: 24px;
		font-family: 'Jua';
		background-color: #ffc;
		cursor: pointer;
	}
	
	ul.menu li.select1{
		background-color: tomato;
		color: yellow;
	}
	
	ul.menu li a{
		color: black;
		text-decoration: none;
	}
</style>
<script type="text/javascript">
	$(function(){
		$("ul.menu li a").mouseover(function(e){
			$(this).parent().siblings().removeClass("select1");
			$(this).parent().addClass("select1");
		});
	});	
</script>
</head>

<!-- add menu -->
<c:set var="root" value="<%=request.getContextPath()%>"/>

<body>
	<ul class="menu">
		<li>
			<a href="${root}/">Home</a>
		</li>
		<li>
			<a href="${root}/shop/form">상품등록</a>
		</li>
		<li>
			<a href="${root}/shop/list">상품목록</a>
		</li>
		<li>
			<a href="${root}/board/list">게시판</a>
		</li>
		<li>
			<a href="${root}/member/list">회원가입</a>
		</li>
		<li>
			<a href="${root}/load/map">오시는길</a>
		</li>
	</ul> 
	
</body>
</html>












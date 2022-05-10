<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>   
<link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&family=Jua&family=Lobster&family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<style type="text/css">
	
	body{
		font-size: 1.3rem;
		font-family: 'Jua';
	}

	div.layout div{
		border: 0px solid black;
	}
	
	a:link {
		text-decoration: none;
		color:black;
	}
	
	div.layout div.title a{	
		text-decoration: none;
		color: black;		
	}
	
	div.layout div.title{
		position:absolute;
		top:10px;
		left:300px;	
		height: 100px;	
	}
	
	div.layout div.main{
		position: absolute;
		left: 400px;
		top: 230px;
		width: 1000px;
		height: 500px;	
		font-family: 'Jua';	
	}
</style>
</head>
<body>
	<div class="layout">
		<div class="title">
			<tiles:insertAttribute name="title2"/>
		</div>

		<div class="main">
			<tiles:insertAttribute name="main"/>
		</div>
	</div>
</body>
</html>














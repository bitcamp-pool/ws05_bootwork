<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- taglib 추가 -->
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

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
      font-size: 2rem;
      font-family: 'Jua';
   }
   div.layout div {
   	border: 1px solid black;
   }
</style>
</head>
<body>
   <div class="layout">
   
   	<div class="title">
   		<tiles:insertAttribute name="title"/>
   	</div>
   	<div class="menu">
   		<tiles:insertAttribute name="menu"/>
   	</div>
   	<div class="info">
   		<tiles:insertAttribute name="info"/>
   	</div>
   	<div class="main">
   		<tiles:insertAttribute name="main"/>
   	</div>
   	
   </div>
</body>
</html>


















































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
   body{
      font-size: 2rem;
      font-family: 'Jua';
   }
</style>
</head>
<body>
   <form action="update" method="post">
   
    <!-- 
    	가장 중요!!! 선택한 num을 폼에 넣어서 전달한다.
    	미전달 시 UPDATE가 아니라 INSERT 됨.
     -->
   	<input type="hidden" name="num" value="${dto.num}">
   	
   	
   	<table class="table table-bordered" style="width:400px;">
   		<caption><b>자동차정보입력</b></caption>
   		<tr>
   			<th bgcolor="pink">자동차 명</th>
   			<td>
   				<input type="text" name="carname" class="form-control" 
   					required="required" value="${dto.carname}">
   			</td>
   		</tr>
   		<tr>
   			<th bgcolor="pink">자동차 색상</th>
   			<td>
   				<input type="color" name="carcolor" class="form-control" 
   					value="${dto.carcolor}" style="width:120px;">
   			</td>
   		</tr>   		
   		<tr>
   			<th bgcolor="pink">자동차 가격</th>
   			<td>
   				<input type="text" name="carprice" class="form-control" 
   					value="${dto.carprice}" required="required">
   			</td>
   		</tr>   		
   		<tr>
   			<th bgcolor="pink">자동차 구입일</th>
   			<td>
   				<input type="date" name="carguip" class="form-control" 
   					value="${dto.carguip}" required="required">
   			</td>
   		</tr>
   		<tr>
   			<td colspan="2" align="center">
   			
   				<button type="submit" class="btn btn-info" 
   					style="width:120px">JPA수정</button>
   					
   				<button class="btn btn-success" style="width:120px" 
   						onclick="location.href='/'">자동차 목록</button>
   			</td>
   		</tr>   		
   	</table>
   </form>
</body>
</html>





































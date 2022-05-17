<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
   *{
      font-family: 'Jua';
   }
   span.day{
   	color: gray;
   	font-size: 14px;
   	float: right;
   }
   div.box{
   	width: 30px;
   	height: 30px;
   	border: 1px solid black;
   	border-radius: 100px;
   	float: right;
   }
</style>
<script type="text/javascript">
	$(function(){
		$(".del").click(function() {
			var ans = confirm("삭제하려면 [확인]을 눌러주세요");
			if(ans){
				location.href='delete?num=${dto.num}&currentPage=${currentPage}'
			}
		});
	});
</script>
</head>
<body>

   <div style="width: 600px;">
    <b style="font-size:30px;">${dto.sangpum}</b>
    <span class="day">입고일 : ${dto.ipgoday}</span>
   	<br><br>
   	<b style="font-size: 20px">단가 :
   		<fmt:formatNumber value="${dto.price}" type="currency" />
   	</b>
	<b style="float: right; font-size: 14px;">${dto.color}</b>
	<div class="box" style="background-color: ${dto.color}"></div>  		
   	<hr style="clear: both; height:3px; background-color:gray;">
   	${dto.content}
    <br><br>
    
    <button type="button" class="btn btn-default"
    	onclick="location.href='list?currentPage=${currentPage}'">
    	<span class="glyphicon glyphicon-pencil"></span>
    	상품목록</button>
    			
    <button type="button" class="btn btn-default del"
    	<%-- onclick="location.href='delete?num=${dto.num}&currentPage=${currentPage}'" --%>>
    	<span class="glyphicon glyphicon-trash"></span>
    	상품삭제</button>		
   </div>
   
</body>
</html>
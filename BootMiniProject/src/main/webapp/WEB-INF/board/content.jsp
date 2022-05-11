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
</style>
</head>
<body>
   <div class="content" style="width: 800px;">
   	<h1><b>${dto.subject}</b></h1>
   	<span class="glyphicon glyphicon-user"></span>&nbsp;<b>${dto.name}</b>
   	&nbsp;&nbsp;
   	<span style="color: gray;">
   		<fmt:formatDate value="${dto.writeday}" pattern="yyyy-MM-dd HH:mm"/>
   	</span>
   	<br><br>
   	<pre style="background-color: white; border:none;"><b>${dto.content}</b></pre>
   	<br><br>
   	<c:if test="${dto.photos != 'no'}">
   		<c:forTokens var="photo" items="${dto.photos}" delims="," varStatus="n">
   			<a href="../save/${photo}" target="_new" style="cursor: pointer;">
   				<img src = "../save/${photo}" style = "max-width: 300px; 
   					border: 1px solid black;">
   			</a>
   			<c:if test="${n.count % 2 == 0}"><br><br></c:if>
   		</c:forTokens>
   	</c:if>
   	<br><br>
   	<div class="buttons">
   		<button type="button" class= "btn btn-default"
   			onclick="location.href='list?currentPage=${currentPage}'">목록</button>
   			
   		<button type="button" class= "btn btn-default"
   			onclick="location.href='form?num=${dto.num}&reg=${dto.reg}&restep=${dto.restep}&relevel=${dto.relevel}&currentPage=${currentPage}'">
   			답글
   		</button>
   		<!-- and sessionScope.name == dto.name} -->
   		<c:if test="${sessionScope.loginok!=null and sessionScope.loginid == dto.id}">
   			<button type="button" class= "btn btn-default"
   				onclick="location.href=''">수정
   			</button>
   			<button type="button" class= "btn btn-default"
   				onclick="location.href=''">삭제
   			</button>
   		</c:if>
   	</div>
   </div>
</body>
</html>

















































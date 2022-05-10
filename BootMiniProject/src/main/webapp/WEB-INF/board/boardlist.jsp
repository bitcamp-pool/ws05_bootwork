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
	div.reboard {
		font-family: 'Jua';
	}
	
</style>
</head>
<body>
	<div class="reboard">
		<h3>총 ${totalCount}개의 글이 있습니다.</h3>
		
		<table class = "table table-bordered" style="width: 750px;">
			<caption><b>답변형게시판</b></caption>
			<tr style="background-color: #ddd">
				<th style="width: 50px">번호</th>
				<th style="width: 400px">제목</th>
				<th style="width: 70px">작성자</th>
				<th style="width: 100px">작성일</th>
				<th style="width: 50px">조회</th>
				<th style="width: 50px">추천</th>
			</tr>
			<c:if test="${totalCount==0}">
				<tr>
					<td colspan="6" align="center">
						<b>등록된 글이 없습니다.</b>
					</td>
				</tr>
			</c:if>
		</table>
		<!-- 글쓰기 버튼은 로그인을 해야만 보인다 -->
		<c:if test="${sessionScope.loginok!=null}">
			<button type="button" class= "btn btn-success"
				style="width: 100px;" onclick="location.href='form'">
					<span class="glyphicon glyphicon-pencil"></span>&nbsp;글쓰기
			</button>
		</c:if>		
	</div>
</body>
</html>











































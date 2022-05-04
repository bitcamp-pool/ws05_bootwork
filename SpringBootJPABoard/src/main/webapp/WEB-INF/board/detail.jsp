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
   <div style="margin: 50px 200px;">
   	<table class="table" style="width:600px;">
   		<caption><b>상세 글 확인</b></caption>
   		<tr>
   			<td>
   				작성자 : ${dto.writer}
   				<span style="float:right; color:gray;">
   					<fmt:formatDate value="${dto.writeday}" pattern="yyyy-MM-dd HH:mm"/>
   				</span>
   			</td>
   		</tr>
   			
   		<tr height="100">
   			<td>
   				<h1><b>${dto.subject}</b></h1>	
   				${dto.content}
   				
   				<c:if test="${dto.photo != 'no'}">
   					<br><br>
   					<img src="../save/${dto.photo}" style="max-width: 350px;">
   				</c:if>
   			</td>
   		</tr>
   		
   		<tr>
   			<td>
   				<button type="button" class="btn btn-default"
   					style="width:80px;"
   					onclick="location.href='writeform'">
   					<span class="glyphicon glyphicon-pencil"></span>
   					글쓰기
   				</button>
   					
   				<button type="button" class="btn btn-default"
   					style="width:80px;"
   					onclick="location.href='list'">
   					<span class="glyphicon glyphicon-th-list"></span>
   					목록
   				</button>
   				
   				<button type="button" class="btn btn-default"
   					style="width:80px;"
   					onclick="location.href='updateform?num=${dto.num}'">
   					<span class="glyphicon glyphicon-edit"></span>
   					수정
   				</button>
   				
   				<button type="button" class="btn btn-default"
   					style="width:80px;"
   					onclick="remove(${dto.num})">
   					<span class="glyphicon glyphicon-trash"></span>
   					삭제
   				</button>
   			</td>
   		</tr>
   	</table>
   </div>
   <script type="text/javascript">
	   	function remove(num) {
			// alert(num);
			var ans = confirm("삭제하려면 [확인]을 눌러주세요");
			if(ans){
				location.href = "delete?num=" + num;
			}
		}
   </script>
</body>
</html>































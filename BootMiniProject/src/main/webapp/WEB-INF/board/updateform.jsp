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
	table.boardform {
		font-family: 'Jua';
		font-size: 1.3em;
	}
</style>
</head>
<body>
   <c:if test="${sessionScope.loginok==null}">
   	<script type="text/javascript">
   		alert("다시 로그인 후 글수정을 시도해주세요");
   		location.href = '../login/updateform';
   	</script>
   </c:if>
   
   <form action="update" method="post" enctype="multipart/form-data">
   	<!-- hidden 2 개 -->
   	<input type="hidden" name="currentPage" value="${currentPage}">
   	<input type="hidden" name="num" value="${dto.num}">
   
    <table class="table table-bordered boardform" style="width:500px;" >
   	 <caption><b>글수정</b></caption>
   	 <tr>
   		<th style="width: 100px; background-color: #ddd">제목</th>
   		<td>
   			<input type="text" name="subject" class="form-control"
   				required="required" autofocus="autofocus"
   				value="${dto.subject}"
   				placeholder="제목을 써 주세요">
   		</td>
   	 </tr>
   	 <tr>
   		<th style="width: 100px; background-color: #ddd">사진들</th>
   		<td>
   			<b>사진을 업로드하지 않으면 기존 사진이 유지됩니다</b><br>
   			<input type="file" name="upload" class="form-control"
   				multiple="multiple">
   		</td>
   	 </tr>
   	 <tr>
   	 	<td colspan="2">
   	 		<textarea style="width: 100%; height: 100px" name="content"
   	 			class="form-control" required="required">${dto.content}</textarea>
   	 	</td>
   	 </tr>
   	 <tr>
   	 	<td colspan="2" align="center">
   	 		<button type="submit" class="btn btn-default"
   	 			style="width: 100px;">글수정</button>
   	 			
   	 		<button type="button" class="btn btn-default"
   	 			style="width:100px;" onclick="history.back()">이전</button>
   	 	</td>
   	 </tr>
    </table>
   </form>
</body>
</html>








































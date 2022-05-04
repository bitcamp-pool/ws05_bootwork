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
   #myimg {
   	position: absolute;
   	left: 700px;
   	top: 100px;
   	max-width: 200px
   }
   
</style>
<script type="text/javascript">

	$(function() {
		// 미리보기 코드	
		$("#upload").change(function(){
			// 정규표현식
			var reg = /(.*?)\/(jpg|jpeg|png|bmp)$/;
			
			var f = $(this)[0].files[0]; // 현재 선택한 파일
			if(!f.type.match(reg)){
				alert("확장자가 이미지 파일이 아닙니다.");
				return;
			}
			var reader = new FileReader();
			reader.onload = function(e){
				$("#myimg").attr("src", e.target.result); // 선택한 이미지가 배열로 리턴
			}
			
			reader.readAsDataURL(f);
		});
	});
</script>
</head>
<body>
	<div style="margin: 50px 200px;">
	    <!-- 파일 업로드(multipart/form-data)  -->
		<form action="update" method="post" enctype="multipart/form-data">
			<!-- num을 전달하기 위한 입력폼 -->
			<input type="hidden" name="num" value="${dto.num}">
			
			<table class="table table-bordered" style="width: 500px">
				<caption><b>글수정</b></caption>
				<tr>
					<!-- 전달하지 않는 항목 -->
					<th width="120" bgcolor="pink">작성자</th>
					<td>
						<b>${dto.writer}</b>
					</td>
				</tr>
				<tr>
					<th width="120" bgcolor="pink">제목</th>
					<td>
						<input type="text" name="subject" required="required"
							class="form-control" value="${dto.subject}">
					</td>
				</tr>
				<tr>
					<th width="120" bgcolor="pink">사진</th>
					<td>
					    <!-- 속성에 required="required"를 빼면 넣지 않아도 됨 -->
						<input type="file" name="upload" id="upload" 
							class="form-control">
					</td>
				</tr>
				<tr>
				 	<td colspan="2">
				 		<textarea style="width: 480px; height: 150px" name="content"
				 			required="required">${dto.content}</textarea>
				 	</td>
				 </tr>
				 <tr>
				 	<td colspan="2" align="center">
				 		<button type="submit" class="btn btn-info"
				 			style="width: 100px;">수정</button>
				 			
				 		<button type="reset" class="btn btn-info"
				 			style="width: 100px;">지우기</button>
				 			
				 		<button type="button" class="btn btn-info"
				 			style="width: 100px;"
				 			onclick="location.href='list'">목록</button>
				 	</td>
				 </tr>
			</table>
		</form>
		
		<!-- 미리보기 -->
		<img src="../save/${dto.photo}" id="myimg">
		
	</div>
</body>
</html>
















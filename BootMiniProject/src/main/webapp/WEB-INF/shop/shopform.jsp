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
	body{
		font-size: 2rem;
		font-family: 'Jua';
	}
</style>

<c:set var="root" value="<%=request.getContextPath()%>"/>

<script type="text/javascript" src="${root}/se2/js/HuskyEZCreator.js"
   charset="utf-8"></script>

<script type="text/javascript" src="${root}/se2/photo_uploader/plugin/hp_SE2M_AttachQuickPhoto.js"
   charset="utf-8"></script>   

</head>
<body>
	<form action="insert" method="post" enctype="multipart/form-data">
		<table class="table table-bordered" style="width:700px;">
			<caption><b>상품등록</b></caption>
			<tr>
				<th width="120" bgcolor="#ddd">상품명</th>
				<td colspan="3">
					<input type="text" name="sangpum" class="form-control"
						placeholder="상품명" required="required">
			   </td>
			</tr>
			<tr>
				<th width="120" bgcolor="#ddd">색상</th>
				<td>
					<input type="color" name="color" class="form-control"
						value="#ffddcc" style="width: 100px;">
			    </td>
				<th width="120" bgcolor="#ddd">단가</th>
				<td>
					<input type="text" name="price" class="form-control"
						required="required" style="width: 150px;"
						placeholder="가격입력">
			   </td>
			</tr>
			<tr>
				<th width="120" bgcolor="#ddd">입고일</th>
				<td colspan="3">
					<input type="date" name="ipgoday" class="form-control"
						value="2022-05-05" style="width:150px;">
			   </td>
			</tr>			
			<tr>
				<td colspan="4">
					<textarea name="content"  id="content" required="required"
						style="width: 100%; height:300px; display: none;"></textarea>
			   </td>
			</tr>			
			<tr>
				<td colspan="4" align="center">
					<button type="button" class="btn btn-success"
						style="width:140px;" onclick="submitContents(this)">상품등록</button>
			   </td>
			</tr>			
		</table>
	</form>

	<!-- 스마트게시판에 대한 스크립트 코드 넣기 -->
	<script type="text/javascript">
		var oEditors = [];
		
		nhn.husky.EZCreator.createInIFrame({
		    oAppRef: oEditors,
		    elPlaceHolder: "content",
		    sSkinURI: "${root}/se2/SmartEditor2Skin.html",
		    fCreator: "createSEditor2"
		}); 
		
		//‘저장’ 버튼을 누르는 등 저장을 위한 액션을 했을 때 submitContents가 호출된다고 가정한다.
		function submitContents(elClickedObj) {
		    // 에디터의 내용이 textarea에 적용된다.
		    oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", [ ]);
		
		    // 에디터의 내용에 대한 값 검증은 이곳에서
		    // document.getElementById("textAreaContent").value를 이용해서 처리한다.
		    try {
		        elClickedObj.form.submit();
		    } catch(e) { 
		
		    }
		}
		
		// textArea에 이미지 첨부
		function pasteHTML(filepath){
		    var sHTML = '<img src="${root}/save/'+filepath+'">';
		    oEditors.getById["content"].exec("PASTE_HTML", [sHTML]); 
		}
	</script>

</body>
</html>




























































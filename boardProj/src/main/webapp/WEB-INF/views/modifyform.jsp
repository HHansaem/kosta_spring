<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판글등록</title>
<style type="text/css">
h2 {
	text-align: center;
}
table {
	margin: auto;
	width: 450px;
}
.td_left {
	width: 150px;
	background: orange;
}
.td_right {
	width: 300px;
	background: skyblue;
}
#commandCell {
	text-align: center;
}
</style>
<script>
	window.onload = function() {
		const fileDom = document.querySelector('#file');
		const imageBox = document.querySelector('#image-box');
		
		//파일 수정했을 때 보여지는 이미지도 바뀌도록 이벤트 주기
		fileDom.addEventListener('change', function() {
			const imageSrc = URL.createObjectURL(fileDom.files[0]);
			imageBox.src = imageSrc;
		})
	}
</script>
</head>
<body>
<jsp:include page="main.jsp" />
	<section id="./writeForm">
		<h2>게시판글수정</h2>
<!-- 		file 업로드시 파일 정보를 넘기기 위해 enctype="multipart/form-data" 적어줘야함 -->
		<form action="boardmodify" method="post" enctype="multipart/form-data">
			<input type="hidden" name="num" value="${board.num }"/>
			<table>
				<tr>
					<td class="td_left"><label for="writer">글쓴이</label></td>
					<td class="td_right"><input type="text" name="writer" 
						id="writer" readonly="readonly" value="${board.writer }"/></td>
				</tr>
				<tr>
					<td class="td_left"><label for="subject">제목</label></td>
					<td class="td_right"><input name="subject" type="text"
						id="subject" required="required" value="${board.subject }" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="content">내용</label></td>
					<td><textarea id="content" name="content"
							cols="40" rows="15" required="required">${board.content }</textarea></td>
				</tr>
				<c:if test="${board.filenum ne null }">
					<tr>
						<td class="td_left"><label>이미지</label></td>
						<td class="td_right">
							<img src="image?num=${board.filenum }" width="100px" id="image-box"/>
						</td>
					</tr>
				</c:if>
				<tr>
					<td class="td_left"><label for="file">이미지</label></td>
					<td class="td_right"><input name="file" type="file" id="file" accept="image/*"/></td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="수정">&nbsp;&nbsp; 
				<a href="boardlist">목록</a>&nbsp;&nbsp;&nbsp;
			</section>
		</form>
	</section>
</body>
</html>
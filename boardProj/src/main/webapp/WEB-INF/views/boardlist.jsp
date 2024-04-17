<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<style type="text/css">
	h2 {
		text-align: center;
	}
	table {
		margin: auto;
		width: 800px;
	}
	#tr_top {
		background: orange;
		text-align: center;
	}
	#emptyArea {
		margin: auto;
		width: 800px;
		text-align: center;
	}
	#emptyArea a {
		display: inline-block;
		width: 20px;
		height: 20px;
		text-decoration: none;
	}
	#emptyArea .btn {
		background: lightgray;
	}
	#emptyArea .select {
		background: lightblue;
	}
</style>
</head>
<body>
<jsp:include page="main.jsp" />
<h2>글 목록&nbsp;&nbsp;&nbsp;&nbsp;<a href="boardwrite">글쓰기</a></h2>
<table>
	<tr id="tr_top">
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>날짜</th>
		<th>조회수</th>
		<c:forEach items="${boardList }" var="board">
			<tr>
				<td>${board.num }</td>
				<td><a href="boarddetail?num=${board.num}">${board.subject }</a></td>
				<td>${board.writer }</td>
				<td>${board.writedate }</td>
				<td>${board.viewcount }</td>
			</tr>
		</c:forEach>
	</tr>
</table>
<br>
<div id="emptyArea">
	<c:choose>
		<c:when test="${pageInfo.curPage == 1 }">
			<a>&lt;</a>  <!-- 현재페이지가 1이면 비활성화 -->
		</c:when>
		<c:otherwise>
			<a href="boardlist?page=${pageInfo.curPage-1 }">&lt;</a>  <!-- 아니면 링크 걸어서 활성화 -->
		</c:otherwise>
	</c:choose>
	<c:forEach begin="${pageInfo.startPage }" end="${pageInfo.endPage }" var="i">
		<c:choose>
			<c:when test="${i == pageInfo.curPage }">
				<a href="boardlist?page=${i }" class="select">${i }</a>
			</c:when>
			<c:otherwise>
				<a href="boardlist?page=${i }" class="btn">${i }</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${pageInfo.curPage == pageInfo.allPage }">
			<a>&gt;</a>  <!-- 시작페이지가 1이면 비활성화 -->
		</c:when>
		<c:otherwise>
			<a href="boardlist?page=${pageInfo.curPage+1 }">&gt;</a>  <!-- 아니면 링크 걸어서 활성화 -->
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>
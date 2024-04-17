<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 메인</title>
<style type="text/css">
	#header {
		height: 110px;
		display: flex;
	}
	.a,b{
		display: inline-block;
		line-height: 110px;
	}
</style>
</head>
<body>
<form action="main"></form>
<div id="header">
<img src="${path }/resources/image/catLogo.png" width="100px" height="100px">&nbsp;
<c:choose>
	<c:when test="${nickname ne Empty}">
		<b>${nickname }&nbsp;&nbsp;<img src="${thumbnail }" width="40px" height="40px" style="border-radius: 50%"/></b>&nbsp;&nbsp;
		<a href="logout" class="a">로그아웃</a>
	</c:when>
	<c:when test="${user ne Empty}">
		<b>${user }</b>&nbsp;&nbsp;<a href="logout" class="a">로그아웃</a>
	</c:when>
	<c:otherwise>
		<a href="login" class="a">로그인</a>
	</c:otherwise>
</c:choose>&nbsp;&nbsp;&nbsp;
<a href="join" class="a">회원가입</a>&nbsp;&nbsp;&nbsp;
<a href="boardlist" class="a">게시판목록</a><br><br>
</div>
</body>
</html>
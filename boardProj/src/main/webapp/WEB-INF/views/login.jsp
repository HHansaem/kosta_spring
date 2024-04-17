<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String cookieHeader = request.getHeader("Cookie");
	Boolean autologin = false;
	String id = "";
	String password = "";
	
	if(cookieHeader != null) {
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("autologin")) {
				if(cookie.getValue().equals("true")) {
					autologin = true;
				} else {
					autologin = false;
				}
			} else if(cookie.getName().equals("id")) {
				id = cookie.getValue();
			} else if(cookie.getName().equals("password")) {
				password = cookie.getValue();
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .header {
        text-align: center;
    } 
    .container {
        width: 280px;
        border: 1px solid black;
        padding: 10px;
        margin: 0 auto;
    }
    .row {
        height: 30px;
    }
    .title {
        float: left;
        width: 70px;
        text-align: center;
        font-weight: bold;
    }
    .input {
        float: left;
    }
    input[type='submit'] {
        font-weight: bold;
        width: 120px;
        background-color: lightgray;
        display: block;
        margin: 0 auto;
    }
    .login {
    	text-align: center;
    	margin-top: 10px;
    }
</style>
</head>
<body>
<jsp:include page="main.jsp" />
<form action="login" method="post">
	<div class="header"><h3>로그인</h3></div>
	<div class="container">
    	<div class="row">
			<div class="title">아이디</div>
			<div class="input">
				<input type="text" name="id" value='<%=id%>'/>
			</div>
		</div>
		<div class="row">
			<div class="title">비밀번호</div>
			<div class="input">
				<input type="text" name="password" value='<%=password%>'/>
			</div>
		</div>
		<div>
			<% if(autologin) { %>
				<input type="checkbox" value="true" name="autologin" checked="checked"/>자동로그인
			<% } else { %>
				<input type="checkbox" value="true" name="autologin"/>자동로그인
			<% } %>
		</div>
		<div class="login">
			<input type="submit" value="로그인"/>
		<!-- client_id는 내 애플리케이션의 REST API 키 -->
		<!-- redirect_uri는 카카오로그인 탭의 Redirect URI -->
		<!-- response_type는 코드로 받겠다는 거 -->
			<a href="https://kauth.kakao.com/oauth/authorize?client_id=64a71bf8086477eb48501855a911415f
					&redirect_uri=http://localhost:8080/test/kakao&response_type=code">
				<img src='<c:url value="/image/kakao_login_medium_narrow.png"/>' />
			</a>
		</div>
	</div>
</form>
</body>
</html>
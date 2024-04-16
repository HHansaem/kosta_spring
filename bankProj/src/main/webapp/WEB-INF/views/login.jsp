<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</style>
</head>
<body>
<%@ include file="header.jsp" %>
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
			<div>
				<input type="submit" value="로그인"/>
			</div>
		</div>
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        width: 320px;
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
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$(function() {
		$('#doubleId').click(function(e) {
			e.preventDefault();  //클릭 이벤트의 기본 행동 방지 (action으로 전송하는 거 방지)
			//backend에 비동기로 요청 (ajax)
			$.ajax({
				url:'memberDoubleId',  //form의 action같은 거 (memberDoubleId 서블릿에 요청)
				type:'post',  //post 방식으로 요청
				async:true,  //비동기 요청 여부 설정
				dataType:'text',  //서버로부터 text라는 타입으로 데이터를 받겠다는 것
				data:{id:$('#id').val()},  //서버로 전송할 데이터 (key:value 형식으로 값을 넘겨줘서 서블릿에서 getParameter로 받을 수 있음)
				success:function(result) {  //응답을 보낼 함수 (result: 서블릿에서 response로 받아온 데이터)
					if(result == 'true') {
						alert("아이디가 중복됩니다");
					} else {
						alert("사용 가능한 아이디 입니다");
					}
				},
				error:function(result) {  //에러 났을 때 실행될 함수 (안 써도 됨)
					
				}
			})
		})
	})
</script>
</head>
<body>
<%@ include file="header.jsp" %>
	<form action="join" method="post">	
		<div class="header"><h3>회원가입</h3></div>
		<div class="container">
			<div class="row">
				<div class="title">아이디</div>
				<div class="input"><input type="text" name="id" id="id"/></div>&nbsp;
				<button id="doubleId">중복</button>
			</div>
			<div class="row">
				<div class="title">이름</div>
				<div class="input"><input type="text" name="name"/></div>
			</div>
			<div class="row">
				<div class="title">비밀번호</div>
				<div class="input"><input type="password" name="password"/></div>
			</div>
			<div class="row">
				<div class="title">이메일</div>
				<div class="input"><input type="text" name="email"/></div>
			</div>
			<div class="row">
				<div class="title">주소</div>
				<div class="input"><input type="text" name="address"/></div>
			</div>
			<div class="button">
				<input type="submit" value="회원가입">
			</div>
		</div>
	</form>
</body>
</html>
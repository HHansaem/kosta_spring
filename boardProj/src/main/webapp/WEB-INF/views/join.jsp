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
    .button {
    	margin-top: 50px;
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
					alert("아이디 중복 체크 오류")
				}
			})
		})
	})
</script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
function daumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
        }
    }).open();
}
</script>
</head>
<body>
<jsp:include page="main.jsp" />
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
			<div class="input">
				<input type="button" onclick="daumPostcode()" value="주소 찾기"><br>
				<input type="text" id="address" name="address" placeholder="주소"><br>
				<input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소">	
			</div>
		</div>
		<div class="button">
			<input type="submit" value="회원가입">
		</div>
	</div>
</form>
</body>
</html>
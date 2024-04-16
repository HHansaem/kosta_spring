<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.kosta.bank.dto.Account"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <style>
        .header {
            text-align: center;
        } 
        .container {
            margin: 0 auto;
            border: 1px solid;
            width: 600px;
            /* padding: 10px; */
        }
        .row {
            height: 40px;
            border-top: 1px solid;
            border-bottom: 1px solid;
        }
        .title {
            font-weight: bold;
            background-color: lightgray;
        }
        .column {
            width: 98px;
            height: 100%;
            float: left;
            line-height: 40px;
            text-align: center;
            border-right: 1px solid;
            border-left: 1px solid;
        }
    </style>
    <script>
       /*  var accs = [
            {id:'1001', name:'홍길동', balance:100000, type:'일반', grade:''},
            {id:'1002', name:'김길동', balance:200000, type:'특수', grade:'VIP'},
            {id:'1003', name:'고길동', balance:300000, type:'특수', grade:'Gold'},
            {id:'1004', name:'송길동', balance:400000, type:'특수', grade:'Silver'},
            {id:'1005', name:'하길동', balance:500000, type:'특수', grade:'Normal'}
        ]

        //** jQuery 방식
        $(function(){
            accs.forEach(function(item, index){
                let row = $("<div></div>").addClass('row');
                // $(`<div class='column'>${index}</div>`).appendTo(row);  //이렇게 한번에도 가능
                $("<div></div>").addClass('column').text(index).appendTo(row);
                $("<div></div>").addClass('column').text(item.id).appendTo(row);
                $("<div></div>").addClass('column').text(item.name).appendTo(row);
                $("<div></div>").addClass('column').text(item.balance).appendTo(row);
                $("<div></div>").addClass('column').text(item.type).appendTo(row);
                $("<div></div>").addClass('column').text(item.grade).appendTo(row);
                $('#container').append(row);
            })
        }) */

        //** javascript 방식
        /* window.onload = function() {
            var container = document.getElementById("container");
            var elemStr = "";
            accs.forEach((item, index) => 
                elemStr += `<div class = 'row'>
                            <div class="column">${index}</div>
                            <div class="column">${item.id}</div>
                            <div class="column">${item.name}</div>
                            <div class="column">${item.balance}</div>
                            <div class="column">${item.type}</div>
                            <div class="column">${item.grade}</div>
                            </div>`)
                // elemStr += "</div>"
                container.innerHTML += elemStr;
        } */
    </script>
</head>
<body>
<%@ include file="header.jsp" %>
    <form action="">
        <div class="header"><h3>전체 계좌 조회</h3></div>
        <div class="container" id="container">
            <div class="row">
                <div class="title column">순서</div>
                <div class="title column">계좌번호</div>
                <div class="title column">이름</div>
                <div class="title column">입금액</div>
                <div class="title column">종류</div>
                <div class="title column">등급</div>
            </div>
            
            <c:forEach var="accs" items="${accs }" varStatus="status">
            	<div class="row">
	            	<div class="column">${status.count }</div>
	                <div class="column">${accs.id }</div>
	                <div class="column">${accs.name }</div>
	                <div class="column">${accs.balance }</div>
	                <div class="column">${accs.type }</div>
					<div class="column">
						<c:if test='${accs.grade ne Empty }'>
	                		${accs.grade }
	                	</c:if>
					</div>
<!-- 	             EL표기법은 null이면 안 찍음! 그래서 그냥 아래 코드로 써줘도 됨 -->
<%-- 	             <div class="column">${accs.grade }</div> --%>

<%-- 		EL표기법 (JSTL) 사용하지 않은 코드
			<% for(int i=0; i < accs.size(); i++) { %>
            	<div class="row">
	            	<div class="column"><%=i+1 %></div>
	                <div class="column"><%=accs.get(i).getId() %></div>
	                <div class="column"><%=accs.get(i).getName() %></div>
	                <div class="column"><%=accs.get(i).getBalance() %></div>
	                <div class="column"><%=accs.get(i).getType() %></div>
	                <div class="column"><%=accs.get(i).getGrade() == null ? "" : accs.get(i).getGrade() %></div>
                </div>
            <% } %> 
--%>
                </div>
            </c:forEach>
        </div>
    </form>
</body>
</html>
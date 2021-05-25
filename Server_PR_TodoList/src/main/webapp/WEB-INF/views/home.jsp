<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/static/home.css?ver=2021-05-25"
	rel="stylesheet"/>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<script>
	document.addEventListener(
			"DOMContentLoaded",
			function() {

				document.querySelector("button#v1insert").addEventListener(
						"click",
						function(ev) {
							let dom = document;
							let td_time = dom
									.querySelector("input[name='td_time']");
							let td_date = dom
									.querySelector("input[name='td_date']");
							let td_todo = dom
									.querySelector("input[name='td_todo']");
							let td_area = dom
									.querySelector("input[name='td_area']");

							if (td_todo.value == "") {
								alert("할일은 반드시 입력해야합니다");
								td_todo.focus();
								return false
							}//if end
							
							dom.querySelector("form#v0").submit();
						})//
			})
</script>


<body>
	<h1>TO DO List</h1>
	<div id="divV1">
		<form action="todo/insert" method="POST" id="v0">
			<input name="td_date" type="text" value="${TD.td_date}"
				placeholder="작성일자" id="inputauto"> <input name="td_time"
				type="text" value="${TD.td_time}" placeholder="작성시각" id="inputauto">

			<input name="td_todo" type="text" value="${TD.td_todo}"
				placeholder="할일" id="inputtext"> <input name="td_area"
				type="text" value="${TD.td_area}" placeholder="장소" id="inputtext">
			
			<button type="button" id="v1insert">insert&#128221;</button>
			
			<button form="v1" id="v3com">delete🤔</button>


		</form>
	</div>
	<table id="tdlist">
		<tr>
			<td>No.</td>
			<td>작성일자</td>
			<td>작성시각</td>
			<td>할 일</td>
			<td>장소</td>

			<td>완료여부</td>
		</tr>
		<form action="todo/delete" method="POST" id="v1">
			<c:forEach items="${TDLIST}" var="TD" varStatus="index">
				<tr data_seq="${TD.td_seq}">
					<td>${index.count}</td>
					<td>${TD.td_date}</td>
					<td>${TD.td_time}</td>
					<td>${TD.td_todo}</td>
					<td>${TD.td_area}</td>
					<td><input class="v1" type="checkbox" name="td_seq"
						type="checkbox" value="${TD.td_seq}"></td>
			</c:forEach>
		</form>
	</table>


</body>
</html>
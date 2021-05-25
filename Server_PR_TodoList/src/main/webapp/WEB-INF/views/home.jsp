<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
*{
background-color:#809478;
}
h1{
	background-color: #fff1ff;
	color:#809478;
	padding : 20px;
}
.todo_btn{
	background-color: #fff1ff;
}
div.todo_btn {

	width: 70%;
	margin: 10px auto;
}
</style>
<script type="text/javascript">
	var heading = document.querySelector("#heading");
	heading.onclick = function () {
 	 heading.style.color = "red";
}
</script>
<body>
	<h1 class="h1">TO DO List</h1>
	<form action="todo/insert" method="POST">
		<div>
			<input name="td_date" type="text" value="${TD.td_date}"
				placeholder="작성일자"> <input name="td_time" type="text"
				value="${TD.td_time}" placeholder="작성시각">
		</div>
		<div class="var1">
			<input name="td_todo" type="text" value="${TD.td_todo}"
				placeholder="할일">
		</div>
		<div class="var2">
			<input name="td_area" type="text" value="${TD.td_area}"
				placeholder="장소">
		</div>
		<button>추가</button>

	</form>
	<table id="tdlist">
		<tr>
			<td>No.</td>
			<td>작성일자</td>
			<td>작성시각</td>
			<td>할일</td>
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
			<button class="todo_btn">삭제</button>
		</form>
	</table>
</body>
</html>
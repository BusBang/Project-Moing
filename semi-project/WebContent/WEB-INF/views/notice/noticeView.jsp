<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="btn.css">
<link rel="stylesheet" href="formInput.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Moing</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<section>
	<div id="content" style="width: 1000px; margin: 0 auto;">
		<h1>공지사항</h1>
		<br>
		<hr>
		<table>
			<tr>
				<th style="width: 200px;">제목</th>
				<input type="hidden" value="${n.noticeNo }">
				<td id="noticeTitle" name="noticeTitle" style="font-size:25px; width: 600px;
				 border-bottom: 1px solid black;">${n.noticeTitle }</td>
			</tr>
			<tr>
			<br>
				<td id="noticeContent" name="noticeContent" colspan="2"
					style="width:1000px;height:600px; border: 1px solid black;">
					${n.noticeContent }
					</td>
			</tr>
		</table>
		<br><br>
		<c:if test="${sessionScope.member.memberId == 'admin' }">
		<div style="width: 200px; margin: 0 auto;">
			<a href="/noticeModifyFrm?noticeNo=${n.noticeNo }" id="modifyNotice"
				style="float: left; text-decoration: none;"class="btn btn-warning">글수정</a>
			<a href="/noticeDelete?noticeNo=${n.noticeNo }" type="button"
				id="deleteNotice"
				style="float: right; text-decoration: none;"class="btn btn-warning"
				onclick="/noticeDelete">글삭제</a>
		</div>
		</c:if>
		
	</div>
	<br><br><br><br><br>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	</section>
</body>
</html>
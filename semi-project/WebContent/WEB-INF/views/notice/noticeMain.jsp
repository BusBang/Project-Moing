<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Moing</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<style>
.content {
	width: 80%;
	height: 50px;
	text-align: center;
}
</style>

<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<section style="height:900px;"> 
	<div class="side1"></div>
	<div class="content" style="width: 800px; margin: 0 auto;">
		<form action="/noticeSearch" method="get">
			<div>
				<h2>공지사항</h2>
				<hr>
				<c:if test="${sessionScope.member.memberId == 'admin' }">
				<a href="/noticeInsertFrm" id="insertNotice"
					class="btn btn-outline-dark" style="float: right">글쓰기</a><br>
				</c:if>
				<br>
				<br>

			</div>
			<div class="con1">
				<table class="table" style="width: 800px; border: 1px solid black;">
					<tr>
						<th>제목</th>
					</tr>
					<c:forEach items="${list }" var="n">
						<tr>
							<input type="hidden" value="${n.noticeNo }">
							<td id="notice_Title" name="notice_Title" style="width: 600px"><a style="color:orange;
							"
								id="notice_Title" name="notice_Title"
								href="/noticeView?noticeNo=${n.noticeNo}">${n.noticeTitle }</a></td>
						</tr>
					</c:forEach>
				</table>
				<br> <br>
			</div>

			<div class="btn btn-success" id="pageNavi">${pageNavi }</div>
			<br>
			<br>
			<br>
			<div id="searchNoticeBox">
				<form action="/noticeSearch">
					<c:if test="${empty type }">
						<select name="type">
							<option name="notice_Title" value="notice_Title">제목</option>
							<option name="notice_Content" value="notice_Content">내용</option>
							<!-- 							<option value="1">제목</option> -->
						</select>
					</c:if>

					<c:if test="${type eq 'notice_Title' }">
						<select name="type">
							<option name="notice_Title" value="notice_Title" selected>제목</option>
							<option name="notice_Content" value="notice_Content">내용</option>
							<!-- 							<option value="1" selected>제목</option> -->

						</select>
					</c:if>

					<c:if test="${type eq 'notice_Content'}">
						<select name="type">
							<option name="notice_Title" value="notice_Title">제목</option>
							<option name="notice_Content" value="notice_Content" selected>내용</option>
							<!-- 							<option value="1">제목</option> -->
						</select>
					</c:if>
					<input type="text" id="search" name="keyword" value="${keyword}"
						style="width: 250px;" placeholder="검색하실 단어를 입력하세요."> <input
						type="hidden" name="reqPage" value=1>
					<button type="submit" id="searBtn" style="border-radius: 20px;">검색</button>
				</form>
			</div>
			<br><br>
	</section>
	<div style="width:1280px; margin:0 auto;">
			<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	</div>

</body>
<script>
///[\r\n\t\s]*/ smarteditor2.js 에서 주석처리함.
	function noticeModifyFrm() {
		location.href = "/noticeModifyFrm";
	}
	function noticeDelete() {
		location.href = "/noticeDelete";
	}
</script>

</html>

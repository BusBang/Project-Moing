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

</head>
<style>
.content {
	width: 80%;
	height: 50px;
	text-align: center;
}

ul {
	margin: 0 auto;
}
</style>

<body>
	<section> <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<div class="side1"></div>
	<div class="content" style="width: 800px; margin: 0 auto;">
	<input type="hidden" name="reqPage" value="">
 		<form action="/noticeSearch" method="get">
			<div >
				<h2>공지사항</h2>
				<hr>
				<c:if test="${not empty sessionScope.member }">
					<c:if test="${sessionScope.member.memberId == 'admin' }">
						<a href="/noticeInsertFrm" id="insertNotice" class="btn btn-warning" style="float: right">글쓰기</a>
					</c:if>
				</c:if>
				<br><br>
			</div>
			<div class="con1">
				<c:forEach items="${list }" var="n">
					<table class="table" style="width: 800px; border: 1px solid black;">

						<tr>
							<input type="hidden" value="${n.noticeNo }">
							<td id="noticeTitle" name="noticeTitle"
								style="width: 600px; border: 1px solid black;"><a
								id="noticeTitle" name="noticeTitle"
								href="/noticeView?noticeNo=${n.noticeNo}">${n.noticeTitle }</a></td>
						</tr>
						<tr>
							<td colspan="3"><p>${n.noticeContent}</p></td>
							
						</tr>
					</table>
				</c:forEach>
				<br>
				<br>
				
				<div class="btn btn-primary" id="pageNavi">${pageNavi }
			</div>
			<br><br>
			</div>
			<div id="searchNoticeBox">
				<form action="/noticeSearch">
					<c:if test="${empty type }">
						<select name="type">
							<option name="notice_Title" value="notice_Title">제목</option>
							<option name="notice_Content" value="notice_Content">내용</option>
						</select>
					</c:if>

					<c:if test="${type eq 'notice_Title' }">
						<select name="type">
							<option name="notice_Title" value="notice_Title" selected>제목</option>
							<option name="notice_Content" value="notice_Content">내용</option>
						</select>
					</c:if>

					<c:if test="${type eq 'notice_Content'}">
						<select name="type">
							<option name="notice_Title" value="notice_Title">제목</option>
							<option name="notice_Content" value="notice_Content" selected>내용</option>
						</select>
					</c:if>
					<input type="hidden" name = "reqPage" value=1>
					<input type="text" id="search" name="keyword" value="${keyword}"
						style="width: 250px;" placeholder="검색하실 단어를 입력하세요.">
					<button type="submit" id="searBtn" style="border-radius: 50px;"
						>검색</button>

				</form>
				<br><br>
			</div>
	</section>
</body>
<script>
	function noticeModifyFrm() {
		location.href = "/noticeModifyFrm";
	}
	function noticeDelete() {
		location.href = "/noticeDelete";
	}
</script>

</html>

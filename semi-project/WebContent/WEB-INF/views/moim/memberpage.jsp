<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/btn.css">
<link rel="stylesheet" href="/css/formInput.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.js">
	
</script>
<title>Moing</title>
<style>
.category {
	width: 150px;
	border: 1px solid #7d8084;
	height: 200px;
	float: left;
	overflow: scroll;
}

.category>ul>li>a {
	display: block;
	text-decoration: none;
}

.category>ul {
	padding: 0;
}

.category>ul>li {
	float: none;
}

.form {
	text-align: center;
	padding: 50px;
	width: 1000px;
}

th {
	display: block;
	padding: 5px;
	margin: 10px;
}

input {
	width: 100%;
	float: left;
}

#modifyBtn {
	float: right;
}

.adminTable {
	width: 500px;
	float: left;
}
</style>
</head>
<!--
c:forEacch
넘어온 id 가 admin 이면
list의 모든 정보를 표시

넘어온 id가 admin이 아니면
넘어온 id와 리스트중 getMemberId()가 같을떄만 표시
 
 -->
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<section class="login-page container">
	<form id="letterWriteFrm">
		<input type="hidden" name="letterGetMemberId">
		<input type="hidden" name="to">
	</form>
		<form class="formSubmit" action="/mypageModify" method="post">
			<input type="hidden" name="memberPw" value="${member.memberPw }">
			<div class="form form-origin">
				<!-- admin이 사용할 때 -->
					<h3>사용자 정보</h3>
							<div class="adminTable">
								<table style="margin: 0px; width: 100%;">
									<tr>
										<th><label for="memberId">아이디</label></th>
										<td><input type="text" name="memberId" id="memberId"
											class="input-origin" value="${member.memberId }" readonly>
										</td>
									</tr>
									<tr>
										<th><label for="memberName">이름</label></th>
										<td><input type="text" name="memberName" id="memberName"
											class="input-origin" value="${member.memberName }"></td>
									</tr>
									<tr>
										<th><label for="Email">이메일</label></th>
										<td><input type="text" name="memberEmail"
											id="memberEmail" class="input-origin"
											value="${member.memberEmail }"></td>
									</tr>
									<tr>
										<th><label for="휴대전화">휴대전화</label></th>
										<td><input type="text" name="memberPhone"
											id="memberPhone" class="input-origin"
											value="${member.memberPhone }"></td>
									</tr>
									<tr>
										<th><label for="memberAddr">주소</label></th>
										<td><input type="text" name="memberAddr" id="memberAddr"
											class="input-origin" value="${member.memberAddr }"></td>
									</tr>
								</table>
								<button class="btn-long-orange2 btn-long-orange1" type="submit"
									name="modifyBtn" style="margin: 10px;">수정하기</button>
							</div>
							<div class="form form-orange-line yool" style="border-top: none; margin: 0px; width: 300px; text-align:center; float:left;">
						
						<table style="margin: 0 auto;">
							<tr>
								<td>
								<button type="button" class="btn-long-orange2 btn-long-orange1" onclick="location.href = '/myMoim?reqPage=1&memberId=${member.memberId}'">가입한 모임</button>
								</td>
							</tr>
							
							<tr>
								<td>
								<button type="button" class="btn-long-orange2 btn-long-orange1"
									onclick="location.href = '/myPlaceList?reqPagePlace0=1&num=0&reqPagePlace1=1&reqPagePlace0Booking=1&reqPagePlace1Booking=1&memberId=${member.memberId }'">장소</button>
								</td>
							</tr>
							
							<tr>
								<td>
								<button type="button" class="btn-long-orange2 btn-long-orange1"
									onclick="funcSendLetter('${member.memberId}', '2');">쪽지 보내기</button>
									
									
								</td>
							</tr>
							
							<tr>
								<td>
								<button type="button" class="btn-long-orange2 btn-long-orange1"
									onclick="funcLogin('${sessionScope.member.memberId}')">1:1 문의함</button>
								</td>
							</tr>
						</table>
					</div>
		
			</div>
		
	</section>
	
	<script>
	
	function funcSendLetter(letterGetMemberId, to){
		var url="/letterWrite";
		var title="letterWrite";
		var status = "left=500px, top=100px, width=470px, height=550px, menubar=no, status=no, scrollbars=yes";
    	var popup = window.open("", title, status);
    	$("input[name=letterGetMemberId]").val(letterGetMemberId);
    	$("input[name=to]").val(to);
		$("#letterWriteFrm").attr("action",url);
		$("#letterWriteFrm").attr("method","post");
		$("#letterWriteFrm").attr("target",title);//새로 열린 popup창과 form태그를 연결
		$("#letterWriteFrm").submit();
	}
	
		
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>
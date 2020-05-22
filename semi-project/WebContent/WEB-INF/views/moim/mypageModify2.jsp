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

.container{
	width: 1280px;
    margin: auto;
    overflow:hidden;
}
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
	padding-bottom: 50px;
	width: 500px;
}

th {
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
.myMenu th{
	display: block;
}
.form button:hover,.form button:active{
            background: #f0e0c0;
        }
        
        .yool{
        	float: left;
        	margin-left: 50px;
        }
        .rara{
        	width: 90%;
        	margin: 0 auto;
        }
        .cc{
        	width:1000px;
        }
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<section class="login-page container">
	<form id="letterWriteFrm">
		<input type="hidden" name="letterGetMemberId">
		<input type="hidden" name="to">
	</form>
			<div class="form-origin">
			
			
				<!-- admin이 사용할 때 -->
				<c:if test="${id == 'admin'}">
					<table class="form cc" style="margin : 0 auto;">
					<tr>
						<th>ID</th>
						<th>이름</th>
						<th>주소</th>
						<th>Email</th>
						<th>전화번호</th>
					</tr>
						<c:forEach items="${list }" var="m">
							<tr>
								<td>${m.memberId }<input type="hidden" name="hiddenId" value="${m.memberId }"></td>
								<td>${m.memberName }</td>
								<td>${m.memberAddr }</td>
								<td>${m.memberEmail }</td>
								<td>${m.memberPhone }</td>
								<td class="showBtn">
									<button class="btn-xsm-orange1 btn-xsm-orange2" onclick="location.href='/memberpageModifyFrm?memberId=${m.memberId}'" }>정보
								</button>
								</td>
<%-- 								<td class="showBtn">
									<button class="btn-xsm-orange1 btn-xsm-orange2" onclick="location.href='/adminDelete?memberId=${m.memberId}'" }>제거
									</button>
								</td> --%>
							</tr>
						</c:forEach>
					</table>
				</c:if>

				<!-- 본인이 사용할 때 -->
				<form class="formSubmit" action="/mypageModify" method="post">
				<c:if test="${not empty sessionScope.member  && sessionScope.member.memberId == id}">
					<c:if test="${sessionScope.member.memberId ne 'admin' }">
						<h3>마이페이지</h3>
					</c:if>
					<c:if test="${sessionScope.member.memberId eq 'admin' }">
						<h3>Admin Page</h3>
					</c:if>
					<div class="rara" style="width : 100%; margin: 0 auto;">
					<div class="adminTable" style="margin: 0 auto;">
						<table style="margin: 0 auto; width: 100%;"  class="myMenu">
							<tr>
								<th><label for="memberId">아이디</label></th>
								<td><input type="text" name="memberId" id="memberId"
									class="input-origin" value="${member.memberId }" readonly>
								</td>
							</tr>
							<tr>
								<th><label for="memberPw">비밀번호</label></th>
								<td><input type="password" name="memberPw" id="memberPw"
									class="input-origin" value="${member.memberPw }"></td>
							</tr>
							<tr>
								<th><label for="memberName">이름</label></th>
								<td><input type="text" name="memberName" id="memberName"
									class="input-origin" value="${member.memberName }" ></td>
							</tr>
							<tr>
								<th><label for="Email">이메일</label></th>
								<td><input type="text" name="memberEmail" id="memberEmail"
									class="input-origin" value="${member.memberEmail }" readonly></td>
							</tr>
							<tr>
								<th><label for="휴대전화">휴대전화</label></th>
								<td><input type="text" name="memberPhone" id="memberPhone"
									class="input-origin" value="${member.memberPhone }"></td>
							</tr>
							<tr>
								<th><label for="memberAddr">주소</label></th>
								<td><input type="text" name="memberAddr" id="memberAddr"
									class="input-origin" value="${member.memberAddr }"></td>
							</tr>
						</table>
						<div style="text-align: center;">
						<button class="btn-xsm-orange2 btn-xsm-orange1" type="submit"
							name="modifyBtn" style="margin: 10px;">수정하기</button>
						<button class="btn-xsm-orange2 btn-xsm-orange1" type="button"
							name="withdrawBtn" id="withdrawBtn" style="margin: 10px;">탈퇴하기</button>
						</div>
					</div>
					
				</form>
				
				<c:if test="${sessionScope.member.memberId ne 'admin' }">
					<div class="form form-orange-line yool" style="border-top: none margin: 0 auto; width: 300px; text-align:center; float:left;">
						<br>
						<table>
							<tr>
								<td>
								<button type="button" class="btn-long-orange2 btn-long-orange1"
									onclick="location.href = 'myMoim?reqPage=1&memberId=${sessionScope.member.memberId }'">내모임</button>
								</td>
							</tr>
							<tr>
								<td>
								<button type="button" class="btn-long-orange2 btn-long-orange1"
									onclick="location.href = '/myPlaceList?reqPagePlace0=1&num=0&reqPagePlace1=1&reqPagePlace0Booking=1&reqPagePlace1Booking=1&memberId=${sessionScope.member.memberId }'">내장소</button>
								</td>
							</tr>
							
							<tr>
								<td>
								<button type="button" class="btn-long-orange2 btn-long-orange1"
									onclick="location.href = '/letterList?reqPageGetLetter=1&reqPageSendLetter=1&memberId=${sessionScope.member.memberId }&num=0'">쪽지함</button>
								</td>
							</tr>
							
							<tr>
								<td>
								<button type="button" class="btn-long-orange2 btn-long-orange1"
									onclick="funcLogin('${sessionScope.member.memberId}')">1:1 문의함</button>
								</td>
							</tr>
							<tr>
								<td>
									<button type="button" class="btn-long-orange2 btn-long-orange1"
									onclick="location.href = '/likeList?memberId=${sessionScope.member.memberId }'">좋아요 리스트</button>
								</td>
							</tr>
						</table>
					</div>
					</c:if>
					
					<c:if test="${sessionScope.member.memberId eq 'admin'}">
						<div class="form form-orange-line yool" style="border-top: none margin: 0 auto; width: 300px; text-align:center; float:left;">
						<br>
						<table>
							<tr>
								<td>
								<button type="button" class="btn-long-orange2 btn-long-orange1"
									onclick="location.href = '/adminMoim?reqPage=1'">모임 게시물 전체조회</button>
								</td>
							</tr>
							<tr>
								<td>
								<button type="button" class="btn-long-orange2 btn-long-orange1"
									onclick="location.href = '/adminPlaceList?reqPagePlace=1'">장소 게시물 전체 조회</button>
								</td>
							</tr>
							
							<tr>
								<td>
								<button type="button" class="btn-long-orange2 btn-long-orange1"
									onclick="location.href = '/letterList?reqPageGetLetter=1&reqPageSendLetter=1&memberId=${sessionScope.member.memberId }&num=0'">쪽지함</button>
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
					</c:if>
				</div>
				</c:if>


				<!-- 3자일때 -->
				<c:if test="${not empty sessionScope.member &&  sessionScope.member.memberId != id}">
				
					<h3>${member.memberId}님의정보</h3>
					<div class="adminTable">
						<table style="border-top: none; margin: 0px; width: 100%;">
							<tr>
								<th><label for="memberId">아이디</label></th>
								<td><input type="text" name="memberId" id="memberId"
									class="input-origin" value="${member.memberId }" readonly>
								</td>
							</tr>
							<tr>
								<th><label for="memberName">이름</label></th>
								<td><input type="text" name="memberName" id="memberName"
									class="input-origin" value="${member.memberName }"readonly></td>
							</tr>
							<tr>
								<th><label for="Email">이메일</label></th>
								<td><input type="text" name="memberEmail" id="memberEmail"
									class="input-origin" value="${member.memberEmail }" readonly></td>
							</tr>
						</table>
					</div>
					<div class="form form-orange-line yool" style="border-top: none; margin: 0px; width: 300px; text-align:center; float:left;">
						
						<table style="margin: 0 auto;">
							<tr>
								<td>
								<button type="button" class="btn-long-orange2 btn-long-orange1" onclick="location.href = '/myMoim?reqPage=1&memberId=${member.memberId }'">가입한 모임</button>
								</td>
							</tr>
							
							<tr>
								<td>
								<button type="button" class="btn-long-orange2 btn-long-orange1"
									onclick="location.href = '/myPlaceList?reqPagePlace0=1&num=0&reqPagePlace1=1&reqPagePlace0Booking=1&reqPagePlace1Booking=1&memberId=${member.memberId }'">가입한 장소</button>
								</td>
							</tr>
							
							<tr>
								<td>
								<button type="button" class="btn-long-orange2 btn-long-orange1"
									onclick="funcSendLetter('${member.memberId }', '2');">쪽지 보내기</button>
									
									
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
				</c:if>
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
	
	
	
		$("#withdrawBtn").click(function(){
			if(confirm("탈퇴하시겠습니까?")){
				location.href="/withdrawId"
			}
		});
		
		$(function(){
			
			$("input[name=hiddenId]").each(function(i,item){
				
				if($(item).val() == 'admin'){
					$(item).parent().parent().find('button').hide();
				}
			});
		});
		
		
		
		var tdValue = $("#checkId").html();
		if($("#chkID").html == 'admin'){
			$(".showBtn").hide();
		}
		
		
		
		
		function funcLogin(memberId){
			if(memberId=="admin"){
				location.href= "/askAdmin?reqPage=1";
			}else if(memberId==""){
				location.href = "/loginFrm";
			}else{
				location.href = "/askFrm?reqPage=1&memberId="+memberId;
			}
		}
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>
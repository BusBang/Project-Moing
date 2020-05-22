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
/*#F0C040
                #D0AEE8*/
.login-page {
	width: 450px;
	padding: 10%;
	margin: auto;
}

.form {
	position: relative;
	background: #f0e0c0;
	max-width: 360px;
	margin: 0 auto;
	padding: 45px;
	text-align: center;
	height: 250px;
}

.form input {
	/*
            font-family: 
            outline: 1;
            background: 
*/
	width: 100%;
	border: 0;
	margin: 0 0 15px;
	padding: 15px;
	box-sizing: border-box;
	font-size: 14px;
	text-align-last: left;
}

.form button {
	font-family:;
	text-transform: uppercase;
	outline: 0;
	color: black;
	font-size: 14px;
	cursor: pointer;
	width: 100%;
	float: right;
}

.form button:hover, .form button:active {
	background: #f0e0c0;
}

.form {
	font-size: 12px;
}

.form a {
	color: beige;
	text-decoration: none;
	float: left;
	padding-top: 15px;
}

#searchIdPw {
	color: beige;
	text-decoration: none;
	float: right;
	padding-top: 15px;
}

#confirmMsg {
	width: 35%;
	font-size: 10px;
}

#emailInput {
	float: left;
	width: 60%;
}

.addr {
	display: inline-block;
	width: 100%;
}

#memberEmail {
	width: 60%;
	float: left;
}

.pwBox {
	height: 180px;
}

</style>
</head>

<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<section class="login-page container">
		<div class="form form-origin form-orange-line">
			<h3>비밀번호 찾기</h3>
			<form action="/searchPw" method="post" class="formSubmit">
			<div>
				<input type="text" placeholder="이메일" name="memberEmail"
					id="memberEmail" required>
				<button id="confirmMsg" class="btn-sm-orange1 btn-sm-orange2"
					type="button">인증번호받기</button>
				<br> <span class="error error5"></span>
			</div>
			<div>
				<input type="text" placeholder="인증번호 입력" id="mailCode"
					style="margin-bottom: 15px;" required>
				<button id="mailResult" class="btn-sm-orange1 btn-sm-orange2"
					type="button" style="margin-bottom: 15px;">인증번호확인</button>
				<br> <span class="error error6"></span>
			</div>
			<span id="mailMsg"></span>
			
			<div class="laterBox">
				<input type="password" placeholder="새비밀번호" name="memberPw"
					id="memberPw" required> 
					<span class="error error3"></span>
					<input type="password"
					placeholder="새비밀번호 확인" id="memberPwc" required>
					<span class="error error4"></span>
				<button type="submit" class="btn-sm-orange1 btn-sm-orange2" id="submitBtn">새비밀번호
					변경</button>
			</div>
			</form>
			
			<a id="gotoJoin" href="/joinFrm">회원가입</a> <a id="searchIdPw"
				href="/login" type="button">로그인</a>
			<div>
				<br> <br>
				<div>
					<p class="idSearchId"></p>
					<p class="idSearchError"></p>
				</div>
			</div>
		</div>
	</section>


	<script>
	var pwChk=/^[a-zA-Z0-9]{4,15}$/;
   	$("#memberPw").focusout(function(){
    	if(pwChk.test($("#memberPw").val())){
       		$(".error3").hide();
        } else{
       		$(".error3").show();
       		$(".error3").addClass("errorRed");
       		$(".error3").text("비밀번호를 확인해 주세요")
       	}
	});
	$("#memberPwc").focusout(function(){
    	if($("#memberPwc").val() != $("#memberPw").val()){
    		$(".error4").show();
       		$(".error4").addClass("errorRed");
       		$(".error4").text("비밀번호확인을 확인해 주세요")
        } else{
        	$(".error4").hide();
       	}
	});
	
		$("#submitBtn").click(function(){
			$(".formSubmit").submit();
		});
		
		var mailCode = "";
		$("#confirmMsg").click(function() {
			var email = $("#memberEmail").val();
			$.ajax({
				url : "/sendMail",
				type : "post",
				data : {
					email : email
				},
				success : function(data) {
					mailCode = data;
					$("#mailCode").show();
					$("#mailResult").show();
				}
			});
		});
	$(".laterBox").hide();
		$("#mailResult").click(function(){
			if($("#mailCode").val()==mailCode && $("#mailCode").val() != ""){
				$(".error6").hide;
				var value = $("#memberEmail").val();
				$.ajax({
					url:"/mailCodeForPwAjax",
					type:"get",
					data:{memberEmail:value},
					success:function(data){
						if($.trim(data)=="Yes"){
							$(".form").animate({
								height : "420px"
							}, "slow");
							$(".laterBox").css("display", "");
						}						
					}
				});
			} else{
				$(".error6").html("이메일또는 인증번호를 확인하세요");
			}
		});

	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>













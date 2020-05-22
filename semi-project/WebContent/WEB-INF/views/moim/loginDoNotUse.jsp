<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js">
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
            background: #F0C040;
            max-width: 360px;
            margin: 0 auto;
            padding: 45px;
            text-align: center;
        }

       
        .form input{
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
            text-align-last: center;
        }

        .form button{
            font-family: ;
            text-transform: uppercase;
            outline: 0;
            background: #D0AEE8;
            width: 100%;
            border: 0;
            padding: 15px;
            color: black;
            font-size: 14px;
            cursor: pointer;
        }
        .form button:hover,.form button:active{
            background: #4e9c9b;
        }
        .form {
            color: aliceblue;
            font-size: 12px;
        }
        .form a{
            color: beige;
            text-decoration: none;
            float: left;
            padding-top: 15px;
        }
        #searchIdPw{
            color: beige;
            text-decoration: none;
            float: right;
            padding-top: 15px;
        }
        .form .join{
            display: none;
        }
        #confirmMsg{
            width: 35%;
            font-size: 10px;
        }
        #emailInput{
            float: left;
            width: 60%;
        }
        .addr{
        	display: inline-block;
        	width: 100%;
        }
    </style>
</head>

<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
    <section class="login-page">
        <div class="form">
            <form class="login">
                <h3>로그인</h3>
                <input type="text" placeholder="아이디를 입력하세요">
                <input type="password" placeholder="비밀번호를 입력하세요">
                <button type="submit" class="form">로그인</button>
                <a id="gotoJoin" href="#">회원가입</a>
                <a id="searchIdPw" href="searchIdPw">아이디찾기/비밀번호찾기</a>
            </form>
            <form class="join">
                <h3>회원가입</h3>
                <input type="text" placeholder="아이디" name="joinMemberId" id="joinMemberId">
                <input type="password" placeholder="비밀번호"name="joinMemberPw" id="joinMemberPw">
                <input type="password" placeholder="비밀번호 확인" name="joinMemberPwc" id="joinMemberPwc">
                <input type="text" placeholder="이름" name="joinMemberName" id="joinMemberName">
                <input type="text" placeholder="이메일"  id="emailInput" name="joinMemberEmail" id="joinMemberEmail">
                <button id="confirmMsg">인증번호받기</button>
                <input type="text" placeholder="인증번호 입력" id="mailCode" style="margin-bottom:0px;">
                <button id="mailResult" style="margin-bottom:15px;">인증번호확인</button>
                <span id="mailMsg"></span>
                <input type="text" placeholder="휴대전화 ('-'없이)" id="phoneInput" name="joinMemberId" id="joinMemberId">
                
				<input type="text" id="postCode" class="addr" placeholder="우편번호" readonly>
				<button id="addrSearchBtn" onclick="addrSearch();" class="addr">주소검색</button>
				<input id="roadAddr"  type="text" class="addr" placeholder="도로명주소">
				<input id="jibunAddr"  type="text" class="addr" placeholder="지번주소">
				<input id="detailAddr" type="text" class="addr" placeholder="상세주소">
                
                <button class="form" type="submit">회원가입</button>
                <a id="returnLogin" href="#">로그인하기</a>
            </form>
            
        </div>
    </section>
    <script>
	    function addrSearch(){
			new daum.Postcode({
				oncomplete:function(data){
					$("#postCode").val(data.zonecode);
					$("#roadAddr").val(data.roadAddress);
					$("#jibunAddr").val(data.jibunAddress);
	
				}
			}).open();
		}
    
    	var mailCode="";
    	$("#confirmMsg").click(function(){
    		var email = $("#emailInput").val();
    		$.ajax({
    			url : "/sendMail",
    			type : "post",
    			data : {email:email},
    			success : function(data){
    				mailCode = data;
    				$("#mailCode").show();
    	            $("#mailResult").show();
    			}
    		});
    	});
    	$("#mailResult").click(function(){
    		if($("#mailCode").val()==mailCode){
    			$("#mailMsg").html("인증성공");
    		}else{
    			$("#mailMsg").html("인증실패");
    		}
    	});
    	
    
        $("#gotoJoin").click(function() {
            $("form").animate({
                height: "toggle",
                opacity: "toggle"
            }, "slow");
        });
        $("#returnLogin").click(function() {
        $("form").animate({
                height: "toggle",
                opacity: "toggle"
            });
        });
    </script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body></html>
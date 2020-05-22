<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/btn.css">
    <link rel="stylesheet" href="/css/formInput.css">
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
            background: #f0e0c0;
            max-width: 360px;
            margin: 0 auto;
            padding: 45px;
            text-align: center;
            height: 250px;
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
            text-align-last: left;
        }

        .form button{
            font-family: ;
            text-transform: uppercase;
            outline: 0;
            color: black;
            font-size: 14px;
            cursor: pointer;
            width: 100%;
            float: right;
        }
        .form button:hover,.form button:active{
            background: #f0e0c0;
        }
        .form {
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
        #memberEmail{
        	width: 60%;
        	float: left;
        }

    </style>
</head>

<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
    <section class="login-page container">
        <div class="form form-origin form-orange-line">
            <form>
                <h3>아이디 찾기</h3>
                <div>
	                <input type="text" placeholder="이메일"  name="memberEmail" id="memberEmail" required>
	                <button id="confirmMsg" class="btn-sm-orange1 btn-sm-orange2" type="submit">인증번호받기</button><br><span class="error error5"></span>
                </div>
                <div>
	                <input type="text" placeholder="인증번호 입력" id="mailCode" style="margin-bottom:15px;" required>
	                <button id="mailResult" class="btn-sm-orange1 btn-sm-orange2" type="button" >인증번호확인</button><br><span class="error error6"></span>
                </div>
                <span id="mailMsg"></span>
                <a id="gotoJoin" href="/joinFrm">회원가입</a>
                <a id="searchIdPw" href="/login" type="button">로그인</a>
            </form>
        </div>
    </section>
    			
 
<script>
		$(".idSearchError").hide();

    	var mailCode="";
    	$("#confirmMsg").click(function(){
    		var email = $("#memberEmail").val();
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
    		if($("#mailCode").val()==mailCode && $("#mailCode").val()!=""){
    				var value = $("#memberEmail").val();
    				$.ajax({
    					url: "/mailCodeAjax",
    					type: "get",
    					data: {memberEmail:value},
    					success: function(data){
    						var idSearchId = decodeURIComponent(data.idSearchId);
    						$("#mailMsg").html(idSearchId+"입니다.");
    						},
    					error: function(){
    						$(".idSearchId").html("아이디 또는 인증번호를 확인해 주세요.");
    						console.log("실패");
    					}
    				});
    		}else{
    			$("#mailMsg").html("인증번호를 확인해 주세요");
    		}
    	});
    	
			
    </script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body></html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/btn.css">
    <link rel="stylesheet" href="/css/btn.css">
    <link rel="stylesheet" href="/css/formInput.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js">
    </script>
    <title>Moing</title>
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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
            color: black;
            font-size: 14px;
            cursor: pointer;
            width: 100%;
            margin: 0 0 15px;
        }
        .form button:hover,.form button:active{
            background: #f0e0c0;
        }
        .form {
            font-size: 12px;
        }
        .form a{
            color: grey;
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
        #confirmMsg, #addrSearchBtn, #mailResult, #confirmId{
            width: 35%;
            font-size: 10px;
        }
        #memberEmail, #postCode, #mailCode, #memberId{
            float: left;
            width: 60%;
        }
        .addr{
        	display: inline-block;
        	width: 100%;
        }
        .errorRed{
        	color: red;
        }
        .redBorder{
        	border-color: red;
        }
    </style>
</head>

<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
    <section class="login-page container">
        <div class="form-orange-line">
            <form class="join form form-origin" id="form" action="/join" method="post">
                
                <h3>회원가입</h3>
                <div>
                	<input type="text" placeholder="아이디" name="memberId" id="memberId" required>
                	<button id="confirmId" class="btn-sm-orange1 btn-sm-orange2" type="button">아이디중복체크</button>
                </div>
                <span class="error error1"></span>
                <input type="password" placeholder="비밀번호"name="memberPw" id="memberPw"required><br>
                <span class="error error2"></span>
                <input type="password" placeholder="비밀번호 확인" name="memberPwc" id="memberPwc"required><br>
                <span class="error error3"></span>
                <input type="text" placeholder="이름" name="memberName" id="memberName" required><br>
                <span class="error error4"></span>
                <div>
	                <input type="text" placeholder="이메일" name="memberEmail" id="memberEmail" required>
	                <button id="confirmMsg" class="btn-sm-orange1 btn-sm-orange2 confirmEmail" type="button">인증번호받기</button><br><span class="error error5"></span>
                </div>
                <div>
	                <input type="text" placeholder="인증번호 입력" id="mailCode" style="margin-bottom:0px;" required>
	                <button id="mailResult" class="btn-sm-orange1 btn-sm-orange2" type="button">인증번호확인</button><br><span class="error error6"></span>
                </div>
                <span id="mailMsg"></span>
                <input type="text" placeholder="휴대전화 ('-'없이)" name="memberPhone" id="memberPhone" required><br>
                <span class="error error7"></span>
                <div>
					<input type="text" id="postCode" class="addr" placeholder="우편번호" readonly required name="memberAddr1" >
					<button id="addrSearchBtn" onclick="addrSearch();" class="addr btn-sm-orange1 btn-sm-orange2" type="button">주소검색</button>
				</div>
				<input id="roadAddr"  type="text" class="addr" placeholder="도로명주소" name="memberAddr2">
				<input id="jibunAddr"  type="text" class="addr" placeholder="지번주소">
				<input id="detailAddr" type="text" class="addr" placeholder="상세주소" name="memberAddr3">
                <button class="btn-sm-orange2 btn-sm-orange1 submitBtn" type="submit">회원가입</button>
                <a id="returnLogin" href="/loginFrm">로그인하기</a>
            </form>
        </div> 
    </section>
    <script>
    //유효성검사
    $(function(){
    	$(".error").hide();
    	
    	var idChk= /^[a-z0-9]{4,15}$/;
    	var pwChk=/^[a-zA-Z0-9~!@#$%^&*]{4,15}$/;
    	var mailChk=/^[a-zA-Z0-9]+@[a-z]+.[a-z]/;
    	var nameChk=/(^[가-힣]{2,4})/;
    	var phoneChk=/^[0-9]{10,11}$/;
    	
    	$("#memberId").focusout(function(){
	    	if(idChk.test($("#memberId").val())){
	       		$(".error1").hide();
	        } else{
	       		$(".error1").show();
	       		$(".error1").addClass("errorRed");
	       		$(".error1").text("아이디는를 확인해 주세요")
	       	}
    	});

    	$("#memberPw").focusout(function(){
	    	if(pwChk.test($("#memberPw").val())){
	       		$(".error2").hide();
	        } else{
	       		$(".error2").show();
	       		$(".error2").addClass("errorRed");
	       		$(".error2").text("4자리 이상 15자리 이하로 입력해 주세요");
	       	}
    	});
    	$("#memberPwc").focusout(function(){
	    	if($("#memberPwc").val() != $("#memberPw").val()){
	    		$(".error3").show();
	       		$(".error3").addClass("errorRed");
	       		$(".error3").text("비밀번호확인을 확인해 주세요")
	        } else{
	        	$(".error3").hide();
	       	}
    	});
    	$("#memberName").focusout(function(){
	    	if(nameChk.test($("#memberName").val())){
	       		$(".error4").hide();
	        } else{
	       		$(".error4").show();
	       		$(".error4").addClass("errorRed");
	       		$(".error4").text("이름을 확인해 주세요")
	       	}
		});
    	
    	
    	$("#memberEmail").focusout(function(){
	    	if(mailChk.test($("#memberEmail").val()) && $("#memberEmail").val() != ""){
	       		$(".error5").hide();
	        } else{
	       		$(".error5").show();
	       		$(".error5").addClass("errorRed");
	       		$(".error5").text("이메일을 확인해 주세요")
	       	}
    	});
    	
    	
    	$("#mailCode").focusout(function(){
	    	if($("#mailCode").val()==mailCode && $("#mailCode").val()!=""){
       			$(".error6").hide();
	        } else{
	       		$(".error6").show();
	       		$(".error6").addClass("errorRed");
	       	}
    	});
    	
    	
    	$("#memberPhone").focusout(function(){
	    	if(phoneChk.test($("#memberPhone").val())){
	       		$(".error7").hide();
	        } else{
	       		$(".error7").show();
	       		$(".error7").addClass("errorRed");
	       		$(".error7").text("휴대폰 번호를 확인해 주세요")
	       	}
    	});

    	
    });
    // api 주소찾기
	function addrSearch(){
		new daum.Postcode({
			oncomplete:function(data){
				$("#postCode").val(data.zonecode);
				$("#roadAddr").val(data.roadAddress);
				$("#jibunAddr").val(data.jibunAddress);
			}
		}).open();
	}
    	$("#mailResult").click(function(){
    		if($("#mailCode").val()==mailCode){
    			$("#mailMsg").html("인증성공");
    		}else{
    			$("#mailMsg").html("인증번호를 확인해 주세요");
    		}
    	});
    	
    	
    	$(function(){
    		//아이디 중복체크
    		    $('#confirmId').click(function(){
    		        $.ajax({
    			     type:"POST",
    			     url:"/confirm",
    			     data:{
    			            "memberId":$('#memberId').val()
    			     },
    			     success:function(data){
    			            if($.trim(data)=="YES"){
    			               if($('#memberId').val()!='' && $('#memberId').val().length>3 && $('#memberId').val().length<16){ 
    			               	alert("사용가능한 아이디입니다");
    			               } else if($('#memberId').val()!='' &&( $('#memberId').val().length<4 || $('#memberId').val().length>15)){
    			            	   alert("아이디 길이를 확인해 주세요");
    			               }
    			           	}else{
    			               if($('#memberId').val()!=''){
    			                  alert("중복된 아이디입니다");
    			                  $('#memberId').val('');
    			                  $('#memberId').focus();
    			               }
    			            }
    			         },
    			      error : function(){
    			    	  alert("test");
    			      }
    			    }) 
    		     })

    		});
    	$(function(){
    		//이메일 중복체크
    		    $('.confirmEmail').click(function(){
    		        $.ajax({
    			     type:"POST",
    			     url:"/confirmEmail",
    			     data:{
    			            "memberEmail":$('#memberEmail').val()
    			     },
    			     success:function(data){
    			            if($.trim(data)=="YES"){
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
    			            }else{
    			            	if($('#memberEmail').val()!=''){
        			                  alert("중복된 아이디입니다");
        			                  $('#memberEmail').val('');
        			                  $('#memberEmail').focus();
    			            }
    			         }
    		     }
    		        })

    		});
    	});
 
    </script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body></html>
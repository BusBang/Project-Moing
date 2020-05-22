<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Moing</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <link href="/css/btn.css" rel="stylesheet" type="text/css">
    <link href="/css/formInput.css" rel="stylesheet" type="text/css">
    <link href="/css/letterList.css" rel="stylesheet" type="text/css">
    
    <style>
    
    .letterWrapper{
             width: 420px;
             padding: 20px;
         }
         .letterWrapper>div{
             font-size: 23px;
             font-weight: 700;
             color: #204000;
             margin-left: 30px;
             margin-bottom: 20px;
         }
        form{
            text-align: center;
        }
         .la{
             display: block;
             margin-top: 10px;
             margin-bottom: 6px;
             text-align: left;
             margin-left: 17px;
             font-size: 13px;
             font-weight: 700;
             color: #ff9742;
         }
        .letter{
            text-align: left;
            margin-left: 20px;
            font-size: 13px;
            width: 90%;
        }
        
        
         button{
             background-color: white;
             margin-top: 25px;
             margin-bottom: 10px;
             color: #808060;
             font-size: 16px;
             font-weight: 700;
         }
         button:hover{
             background-color: #ff9742;
        }
    </style>
</head>
<body>
	 <div class="letterWrapper">
		 	<form id="letterWriteFrm">
	 			<input type="hidden" name="letterGetMemberId"><!-- 답장/쪽지보내기 버튼 누를 때 작성하는 폼(현재 쪽지의 수신/발신자 아이디를 받아 쪽지를 보내기 때문에 수신자가 된다.) -->
	 			<input type="hidden" name="to"><!-- 답장/쪽지 보내기 버튼과 일반 쪽지보내기 버튼을 누를 때를 구분함 (답장/쪽지 (수신인 정해짐):2, 단체 쪽지(수신인 여러명, 수정 가능):1 뉴쪽지(수신인X):0 -->
	 		</form>
	 	<c:if test="${letter.letterGetMemberId eq sessionScope.member.memberId }"><!--해당 쪽지 받은 아이디가 로그인 한 아이디와 같을 때 (받은 쪽지) -->
	 		<div>받은 쪽지</div>
	 		
         <form action="/letterDeleteFrm" class="form-origin form-orange-line"><!-- 삭제 버튼을 누를 때 작동하는 폼 -->
            
            <lable class="la">From. </lable>
            <div id="letterSendMemberId" class="letter">${letter.letterSendMemberId }</div>
             <input type="hidden" name="letterNo" value="${letter.letterNo }"><!-- 삭제 시 현재 쪽지의 번호를 전달 -->
             <input type="hidden" name="num" value="0">
            <label class="la">Title. </label>
            <div id="letterTitle" class="letter">${letter.letterTitle }</div>
            <label class="la">Content. </label>
            <div id="letterContent" class="letter">${letter.contentBr }</div><!-- <br>로 바꿔서 -->
            <button type="button" class="btn-sm-orange1" onclick="funcSendLetter('${letter.letterSendMemberId}','2');">답장</button><!-- 답장을 받는 아이디는 현재 쪽지를 보낸 회원의 아이디 -->
            <button type="submit" class="btn-sm-orange1 delBtn">삭제</button><!-- 삭제시 바로 서블릿으로 이동 -->
         </form>
         </c:if>
         <c:if test="${letter.letterSendMemberId eq sessionScope.member.memberId}"><!--해당 쪽지 보낸 아이디가 로그인 한 아이디와 같을 때 (보낸 쪽지) -->
	 		<div>보낸 쪽지</div>
	 		<form action="letterDeleteFrm" class="form-origin form-orange-line"><!-- 삭제 버튼을 누를 때 작동하는 폼 -->
            
            <lable class="la">To. </lable>
            <div id="letterGetMemberId" class="letter">${letter.letterGetMemberId }</div>
             <input type="hidden" name="letterNo" value="${letter.letterNo }"><!-- 삭제 시 현재 쪽지의 번호를 전달 -->
             <input type="hidden" name="num" value="1">
            <label class="la">Title. </label>
            <div id="letterTitle" class="letter">${letter.letterTitle }</div>
            <label class="la">Content. </label>
            <div id="letterContent" class="letter">${letter.contentBr }</div>
            <button type="button" class="btn-sm-orange1" onclick="funcSendLetter('${letter.letterGetMemberId}','2');">쪽지보내기</button><!-- 현재 쪽지를 받는 회원에게 또 쪽지 보내기-->
            <button type="submit" class="btn-sm-orange1 delBtn">삭제</button>
         </form>
	 	</c:if>
            
        </div>
        
        
        
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
</body>
</html>
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
    <link href="/css/letterWriteCss.css" rel="stylesheet" type="text/css">
    
    <style>
    	#arrString{
             text-align: left;
             margin-left: 30px;
             font-size: 13px;
             color:#808060;
             font-weight: 700;
         }
    </style>
                
               
                
</head>
<body>
    
  
        <div class="letterWrapper">
            <div>쪽지 보내기</div>
         <form action="/letterSend" class="form-origin form-orange-line">
            <input type="hidden" name="letterSendMemberId" value="${sessionScope.member.memberId }">
            <lable for="letterGetMemberId" class="la">받는 회원 : </lable>
            <c:if test="${to==0 }"><!-- 쪽지 받는 사람 직접 설정 -->
            	<input name="letterGetMemberId" class="input-origin inputId" placeholder="쪽지를 받는 회원의 아이디를 입력하세요. (,로 구분)">
            </c:if>
            <c:if test="${to==1 }"><!-- 전체쪽지 (수정 가능) -->
            	<input name="letterGetMemberId" class="input-origin inputId" value="${arrString }">
            	
            </c:if>
            <c:if test="${to==2 }"><!-- 답장, 쪽지 이어보내기(받는 사람 수정 불가) -->
            	<div id="arrString">${arrString}</div>
            	<input type="hidden" name="letterGetMemberId" value="${arrString }">
            </c:if>
            <label for="letterTitle" class="la">제목 : </label>
            <input name="letterTitle" class="input-origin inputTitile">
            <label for="letterContent" class="la">내용 : </label>
            <textarea name="letterContent" ></textarea>
            <button type="submit" class="btn-sm-orange1">전송</button>
            <button type="reset" class="btn-sm-orange1">초기화</button>
         </form>
        </div>

</body>
</html>
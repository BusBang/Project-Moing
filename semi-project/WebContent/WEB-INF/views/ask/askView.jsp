<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <link href="/css/btn.css" rel="stylesheet" type="text/css">
    <link href="/css/formInput.css" rel="stylesheet" type="text/css">
    <link href="/css/letterList.css" rel="stylesheet" type="text/css">
    <link href="/css/letterWriteCss.css" rel="stylesheet" type="text/css">
    
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
        
        #photo{
        	width:350px; 
        	margin:5px auto; 
        	margin-bottom:10px;
        	overflow:hidden;
        	display: none;
        }
        
        
    </style>
</head>
<body>
	 <div class="letterWrapper">
		 <div>문의 보기</div>
	 		<form action="/sendReply" method="get" class="form-origin form-green-line"><!-- 삭제 버튼을 누를 때 작동하는 폼 -->
            <c:if test="${sessionScope.member.memberId ne 'admin' }">
	            <lable class="la">To. </lable>
	            <div id="letterGetMemberId" class="letter">Admin</div>
            </c:if>
            <c:if test="${sessionScope.member.memberId eq 'admin' }">
            	<lable class="la">From. </lable>
	            <div id="letterGetMemberId" class="letter">${ask.memberId }</div>
            </c:if>
             
            <label class="la">Title. </label>
            <div id="askTitle" class="letter">${ask.askTitle }</div>
            <label class="la">Content. </label>
            <div id="askContent" class="letter">${ask.contentBr }</div>
            
            <c:if test="${ask.askFilepath ne 'no' }">
	           	<button id="photoBtn" opened="0" class="btn-long-green1" onclick="photo();" type="button" href="javascript:void(0)">사진 보기</button>
	           	<div id="photo" class="letter">
	           		<img id="photoImg" src="/upload/photo/${ask.askFilepath }" width="100%">
	           	</div>
	        </c:if>
           	
            <label class="la">Reply.</label>
            <c:if test="${sessionScope.member.memberId ne 'admin' }">
            <div id="replyContent" class="letter">${ask.replyBr }</div>
            </c:if>
            <c:if test="${sessionScope.member.memberId=='admin' }">
	           
	            	<textarea id="rePaper" name="replyContent">${ask.replyBr }</textarea>
	            	<input type="hidden" name="askNo" value="${ask.askNo }">
	           
            </c:if>
            
            <c:if test="${sessionScope.member.memberId eq 'admin' }">
            <button type="submit" class="btn-sm-orange1 bb">전송</button>
            </c:if>
            <button type="button" href="javascript:void(0)" onclick="funcDel('${ask.askNo}');" id="replyBtn" class="btn-sm-orange1 delBtn bb">삭제</button>
            
         </form>
        </div>
        
        
        
	<script>
        function funcDel(askNo){
        	location.href="/askDeleteFrm?askNo="+askNo;
        }
        
        function photo(){
			if($("#photoBtn").attr("opened")==0){//사진 열려있을 때
				$("#photo").css("display","block");
				$("#photoBtn").html("사진 닫기");
				$("#photoBtn").attr("opened",1);
				
				
			}else{
				$("#photo").css("display","none");
				$("#photoBtn").html("사진 보기");
				$("#photoBtn").attr("opened",0);
			}
			
		}
        	function funcReply(askNo, memberId){
        		var url="/replyWriteFrm";
        		var title="replyWriteFrm";
        		var status = "left=500px, top=100px, width=470px, height=550px, menubar=no, status=no, scrollbars=yes";
            	var popup = window.open("", title, status);
            	$("input[name=askNo]").val(askNo);
            	$("input[name=memberId]").val(memberId);
    			$("#replyWriteFrm").attr("action",url);
    			$("#replyWriteFrm").attr("method","post");
    			$("#replyWriteFrm").attr("target",title);//새로 열린 popup창과 form태그를 연결
    			$("#replyWriteFrm").submit();
        	}
        	
        	
        
	</script>
</body>
</html>
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
    <link href="/css/letterListCss.css" rel="stylesheet" type="text/css">
	<style>
		
	</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>	
    
    <section class="container"><!-- 콘텐츠 영역 전체 감싸는 div -->
    <div class="letter-wrapper"><!-- 화면 전체 감싸는 div -->
        <div class="pageMainTitle">1:1 문의</div><!-- 마이페이지 타이틀 -->
        <div class="pageSubTitle">Ask</div><!-- 쪽지함 타이틀 -->
        <div class="letterList"><!-- 쪽지함 전체 감싸는 div -->
            <div class="letterBtn"><!-- 쪽지함 버튼(받은/보낸쪽지/쪽지보내기)영역 감싸는 div -->
            
                <c:if test="${sessionScope.member.memberId ne'admin' }">
                <div class="btn-long-orange1 or3"><a onclick="askWrite();" href="javascript:void(0)">문의하기</a></div>
                </c:if>
                
            </div>
            <form id="letterWriteFrm">
            	<input type="hidden" name="admin" value="admin">
	 			
	 		</form>
            <div class="letters"><!-- 쪽지리스트테이블 감싸는  div -->
            	<form id="askViewFrm"><!-- 선택한 쪽지의 쪽지번호를 매개변수를 전달하는 폼 -->
            		<input type="hidden" name="askNo"><!-- 제이쿼리 함수를 이용하여 submit할 것이므로 type을 hidden으로 한다. -->
            		<input type="hidden" name="memberId">
            	</form>
            	<c:if test="${sessionScope.member.memberId ne'admin' }">
                 <table id="getLetters" class="letterTbl"><!-- 문의 리스트테이블 -->
                    <tr>
                        <th>NO</th>
                        <th>TITLE</th>
                        <th>Date</th>
                        <th>Reply</th>
                    </tr>
                    <tr>
                        <td colspan="4" style="border-bottom: 3px solid #ff9742;"></td>
                    </tr>
                    
                    <c:forEach items="${list }" var="l"><!-- 문의 리스트 데이터를 foreach문 돌려서 나열  -->
                    	<tr>
                    		<td>${l.rnum }</td>
                    		<td>
                    			<a href="#" onclick="askView(${l.ask.askNo});" class="titleBlock blk">${l.ask.askTitle }</a><!-- 문의 타이틀 클릭시 해당 문의 번호를 매개변수로 전달하여, 문의 내용 가져오는 서블릿으로 이동 시켜주는 함수 실행-->
                    		</td>
                    		
                    		<td>${l.ask.askSendDate }</td>
                    		<td>${l.ask.askReply }</td>
                    	</tr>
                    	<tr>
                        	<td colspan="4" style="border-bottom: 0.5px solid lightgray;"></td>
                        </tr>
                    </c:forEach>
                    
                </table>
                
                <div id="PageNaviGet" class="letterNavi">${pageNavi }</div><!-- 페이지 네비 -->
                </c:if>
                <c:if test="${sessionScope.member.memberId eq'admin' }">
                 <table id="getLetters" class="letterTbl"><!-- 문의 리스트테이블 -->
                    <tr>
                        <th>NO</th>
                        <th>TITLE</th>
                        <th>FROM</th>
                        <th>Date</th>
                        <th>Reply</th>
                    </tr>
                    <tr>
                        <td colspan="5" style="border-bottom: 3px solid #ff9742;"></td>
                    </tr>
                    
                    <c:forEach items="${list }" var="l"><!-- 문의 리스트 데이터를 foreach문 돌려서 나열  -->
                    	<tr>
                    		<td>${l.rnum }</td>
                    		<td>
                    			<a href="#" onclick="askView(${l.ask.askNo});" class="titleBlock blk">${l.ask.askTitle }</a><!-- 문의 타이틀 클릭시 해당 문의 번호를 매개변수로 전달하여, 문의 내용 가져오는 서블릿으로 이동 시켜주는 함수 실행-->
                    		</td>
                    		<td>
                    			<a href="/mypageModifyFrm?id=${l.ask.memberId}" class="titleBlock blk">${l.ask.memberId }</a>
                    			
                    		</td>
                    		<td>${l.ask.askSendDate }</td>
                    		<td>${l.ask.askReply }</td>
                    	</tr>
                    	<tr>
                        	<td colspan="5" style="border-bottom: 0.5px solid lightgray;"></td>
                        </tr>
                    </c:forEach>
                    
                </table>
                
                <div id="PageNaviGet" class="letterNavi">${pageNavi }</div><!-- 페이지 네비 -->
                </c:if>
                
                
                
                <div class="searchGetDiv" class="letterSearch"><!-- 받는 쪽지 검색창 -->
                	<form action="/searchAskKeyword" id="searchGetDiv">
						<c:if test="${opt eq 'askTitle'}">
							<select name="opt">
		                        <option class="opt" value="askTitle" selected>제목</option>
		                        <option class="opt" value="askContent">문의 내용</option>
		                        <option class="opt" value="replyContent">답변내용</option>
		                   </select>
						</c:if>
						<c:if test="${opt eq 'askContent'}">
							<select name="opt">
		                        <option class="opt" value="askTitle">제목</option>
		                        <option class="opt" value="askContent" selected>문의 내용</option>
		                        <option class="opt" value="replyContent">답변내용</option>
		                   </select>
						</c:if>
						<c:if test="${opt eq 'replyContent'}">
							<select name="opt">
		                        <option class="opt" value="askTitle">제목</option>
		                        <option class="opt" value="askContent">문의 내용</option>
		                        <option class="opt" value="replyContent" selected>답변내용</option>
		                   </select>
						</c:if>
						<c:if test="${empty opt}">
							<select name="opt">
		                        <option class="opt" value="askTitle" selected>제목</option>
		                        <option class="opt" value="askContent">문의 내용</option>
		                        <option class="opt" value="replyContent">답변내용</option>
		                   </select>
						</c:if>
						<input type="text" name="keyword" class="input-origin" value="${keyword }">
	                   <input type="hidden" name="memberId" value="${sessionScope.member.memberId }">
	                   <input type="hidden" name="reqPage" value="1">
	                   <button class="btn-xsm-green2 searchBtn" type="submit">검색</button>
		                   
					</form>
				</div>
                
                
            </div>
            
        </div>
    
    </div>
    </section>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    <script>
        $(function(){
        
            $(".or3").mousedown(function(){
                $(this).css("background-color","#ff9742");
            });
            $(".or3").mouseup(function(){
                $(this).css("background-color","white");
            });
            
        });
        
       
        function askView(askNo){
        	var memberId = "${sessionScope.member.memberId}";
        	var url = "/askView";
        	var title = "askView";
        	var status = "left=500px, top=100px, width=470px, height=450px, menubar=no, status=no, scrollbars=yes";
        	var popup = window.open("", title, status);
        	$("input[name=askNo]").val(askNo);
        	$("input[name=memberId]").val(memberId);
			$("#askViewFrm").attr("action",url);
			$("#askViewFrm").attr("method","post");
			$("#askViewFrm").attr("target",title);//새로 열린 popup창과 form태그를 연결
			$("#askViewFrm").submit();
        }
        
        function askWrite(){
    		var url="/askWrite";
    		var title="askWrite";
    		var status = "left=500px, top=100px, width=470px, height=550px, menubar=no, status=no, scrollbars=yes";
        	var popup = window.open("", title, status);
        	
			$("#letterWriteFrm").attr("action",url);
			$("#letterWriteFrm").attr("method","post");
			$("#letterWriteFrm").attr("target",title);//새로 열린 popup창과 form태그를 연결
			$("#letterWriteFrm").submit();
    	}
    </script>
</body>
</html>
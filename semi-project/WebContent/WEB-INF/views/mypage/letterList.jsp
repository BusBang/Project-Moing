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
    <link href="/css/letterListCss.css" rel="stylesheet" type="text/css">
	<style>
		
	</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>	
    
    <section class="container"><!-- 콘텐츠 영역 전체 감싸는 div -->
    <div class="letter-wrapper"><!-- 쪽지화면 전체 감싸는 div -->
    <div id="vv">
        <div class="pageMainTitle">마이페이지</div><!-- 마이페이지 타이틀 -->
        <div class="pageSubTitle">쪽지함</div><!-- 쪽지함 타이틀 -->
        </div>
        <div class="letterList"><!-- 쪽지함 전체 감싸는 div -->
            <div class="letterBtn"><!-- 쪽지함 버튼(받은/보낸쪽지/쪽지보내기)영역 감싸는 div -->
                <div class="btn-long-orange1 or1 focusBtn"><a class="btn-a" onclick="listfunc('${sessionScope.member.memberId }','1','1','0');" href="javascript:void(0)">받은 쪽지함</a></div><!-- 쪽지함 버튼 -->
                <div class="btn-long-orange1 or2"><a class="btn-a" onclick="listfunc('${sessionScope.member.memberId }','1','1','1');"href="javascript:void(0)">보낸 쪽지함</a></div>
                <div class="btn-long-orange1 or3"><a onclick="funcSendLetter('0');" href="javascript:void(0)">쪽지 보내기</a></div>
            </div>
            <form id="letterWriteFrm">
	 			<input type="hidden" name="to"><!-- 답장/쪽지 보내기 버튼과 일반 쪽지보내기 버튼을 누를 때를 구분함 (답장/쪽지 (수신인 정해짐):2, 단체 쪽지(수신인 여러명, 수정 가능):1 뉴쪽지(수신인X):0 -->
	 		</form>
            <div class="letters"><!-- 쪽지리스트테이블 감싸는  div -->
            	<form id="letterViewFrm"><!-- 선택한 쪽지의 쪽지번호를 매개변수를 전달하는 폼 -->
            		<input type="hidden" name="letterNo"><!-- 제이쿼리 함수를 이용하여 submit할 것이므로 type을 hidden으로 한다. -->
            		<input type="hidden" name="memberId">
            	</form>
            	<c:if test="${num ==0 }">
                 <table id="getLetters" class="letterTbl"><!-- 받은쪽지 리스트테이블 -->
                    <tr>
                        <th width="10%">NO</th>
                        <th width="">TITLE</th>
                        <th width="15%">FROM</th>
                        <th width="20%">DATE</th>
                        <th width="10%">READ</th>
                    </tr>
                    <tr>
                        <td colspan="5" style="border-bottom: 3px solid #ff9742;"></td>
                    </tr>
                    
                    <c:forEach items="${listGet }" var="l"><!-- 받은쪽지 리스트 데이터를 foreach문 돌려서 나열  -->
                    	<tr>
                    		<td>${l.letterRNum }</td>
                    		<td>
                    			<a href="#" onclick="letterView(${l.letter.letterNo});" class="titleBlock blk">${l.letter.letterTitle }</a><!-- 쪽지 타이틀 클릭시 해당 쪽지 번호를 매개변수로 전달하여, 쪽지 내용 가져오는 서블릿으로 이동 시켜주는 함수 실행-->
                    		</td>
                    		
                    		<td><a href="/mypageModifyFrm?id=${l.letter.letterSendMemberId}" style="text-decoration:none; color=black;">${l.letter.letterSendMemberId }</a></td>
                    		<td>${l.letter.letterSendDate }</td>
                    		<td>${l.letter.letterRead }</td>
                    	</tr>
                    	<tr>
                        	<td colspan="5" style="border-bottom: 0.5px solid lightgray;"></td>
                        </tr>
                    </c:forEach>
                    
                </table>
                
                <div id="PageNaviGet" class="letterNavi">${pageNaviGet }</div><!-- 받는 쪽지 페이지 네비 -->
                
                  <div class="searchGetDiv" class="letterSearch"><!-- 받는 쪽지 검색창 -->
                	<form action="/searchLetterKeyword" id="searchGetDiv">
						<c:if test="${opt eq 'letterSendMemberId'}">
							<select name="opt">
		                        <option class="opt" value="letterSendMemberId" selected>발신아이디</option>
		                        <option class="opt" value="letterTitle">제목</option>
		                        <option class="opt" value="letterContent">내용</option>
		                   </select>
						</c:if>
						<c:if test="${opt eq 'letterTitle'}">
							<select name="opt">
		                        <option class="opt" value="letterSendMemberId">발신아이디</option>
		                        <option class="opt" value="letterTitle" selected>제목</option>
		                        <option class="opt" value="letterContent">내용</option>
		                   </select>
						</c:if>
						<c:if test="${opt eq 'letterContent'}">
							<select name="opt">
		                        <option class="opt" value="letterSendMemberId">발신아이디</option>
		                        <option class="opt" value="letterTitle">제목</option>
		                        <option class="opt" value="letterContent" selected>내용</option>
		                   </select>
						</c:if>
						<c:if test="${empty opt}">
							<select name="opt">
		                        <option class="opt" value="letterSendMemberId">발신아이디</option>
		                        <option class="opt" value="letterTitle">제목</option>
		                        <option class="opt" value="letterContent">내용</option>
		                   	</select>
						</c:if>
						<input type="text" name="keyword" class="input-origin" value="${keyword }">
						<input type="hidden" name="memberId" value="${sessionScope.member.memberId }">
	                   	<input type="hidden" name="reqPage" value="1">
	                   	<input type="hidden" name="num" value="0">
	                   	<button class="btn-xsm-green2 searchBtn" type="submit">검색</button>
					</form>
				</div>
                
                </c:if>
                
                <c:if test="${num==1 }">
                <table id="sendLetters" class="letterTbl"><!-- 보낸쪽지 리스트테이블 -->
                    <tr>
                        <th width="5%"> NO</th>
                        <th>TITLE</th>
                        <th width="15%">TO</th>
                        <th width="20%">DATE</th>
                        <th width="10%">READ</th>
                    </tr>
                    <tr>
                        <td colspan="5" style="border-bottom: 3px solid #ff9742;"></td>
                    </tr>
                    <c:forEach items="${listSend }" var="l">
                    	<tr>
                    		<td>${l.letterRNum }</td>
                    		<td>
                    			<a href="#" onclick="letterView(${l.letter.letterNo});" class="titleBlock">${l.letter.letterTitle }</a>
                    		</td>
                   			<td><a href="/mypageModifyFrm?id=${l.letter.letterGetMemberId}" style="text-decoration:none; color=black;">${l.letter.letterGetMemberId }</a></td>
                    		<td>${l.letter.letterSendDate }</td>
                    		<td>${l.letter.letterRead }</td>
                    	</tr>
                    	<tr>
                        	<td colspan="5" style="border-bottom: 0.5px solid lightgray;"></td>
                        </tr>
                    </c:forEach>
                </table>
                
                
                <div id="PageNaviSend" class="letterNavi">${pageNaviSend }</div>
                
                
                <div class="searchSendDiv" class="letterSearch">
                 <form action="/searchLetterKeyword" id="searchGetDiv">
						<c:if test="${opt eq 'letterGetMemberId'}">
							<select name="opt">
		                        <option class="opt" value="letterGetMemberId" selected>수신아이디</option>
		                        <option class="opt" value="letterTitle">제목</option>
		                        <option class="opt" value="letterContent">내용</option>
		                   </select>
						</c:if>
						<c:if test="${opt eq 'letterTitle'}">
							<select name="opt">
		                        <option class="opt" value="letterGetMemberId">수신아이디</option>
		                        <option class="opt" value="letterTitle"  selected>제목</option>
		                        <option class="opt" value="letterContent">내용</option>
		                   </select>
						</c:if>
						<c:if test="${opt eq 'letterContent'}">
							<select name="opt">
		                        <option class="opt" value="letterGetMemberId">수신아이디</option>
		                        <option class="opt" value="letterTitle">제목</option>
		                        <option class="opt" value="letterContent"  selected>내용</option>
		                   </select>
						</c:if>
						<c:if test="${empty opt}">
							<select name="opt">
		                        <option class="opt" value="letterGetMemberId" selected>수신아이디</option>
		                        <option class="opt" value="letterTitle">제목</option>
		                        <option class="opt" value="letterContent">내용</option>
		                   </select>
						</c:if>
						<input type="text" name="keyword" class="input-origin">
	                   <input type="hidden" name="memberId" value="${sessionScope.member.memberId }">
	                   <input type="hidden" name="reqPage" value="1">
	                   <input type="hidden" name="num" value="1">
	                   <button class="btn-xsm-green2 searchBtn" type="submit">검색</button>
					</form>
                </div>
                </c:if>
            </div>
            
        </div>
    
    </div>
    </section>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    <script>
        $(function(){
        	$(".blk").click(function(){
                if($(this).parent().parent().children().last().html()=="안읽음"){
                    $(this).parent().parent().children().last().html("읽음");
                }
            });
        	
            $(".or3").mousedown(function(){
                $(this).css("background-color","#ff9742");
            });
            $(".or3").mouseup(function(){
                $(this).css("background-color","white");
            });
            
            var num = "${num}";
            if(num==0){
            	$(".or1").addClass("focusBtn");
            	$(".or2").removeClass("focusBtn");
            }else{
            	$(".or2").addClass("focusBtn");
            	$(".or1").removeClass("focusBtn");
            }
        });
        
        function listfunc(memberId, reqPageGetLetter, reqPageSendLetter, num){
        	location.href="/letterList?reqPageGetLetter="+reqPageGetLetter+"&reqPageSendLetter="+reqPageSendLetter+"&memberId="+memberId+"&num="+num;
        	
        }
        
        
        function letterView(letterNo){
        	var memberId = "${sessionScope.member.memberId}";
        	var url = "/letterView";
        	var title = "letterView";
        	var status = "left=500px, top=100px, width=470px, height=450px, menubar=no, status=no, scrollbars=yes";
        	var popup = window.open("", title, status);
        	$("input[name=letterNo]").val(letterNo);
        	$("input[name=memberId]").val(memberId);
			$("#letterViewFrm").attr("action",url);
			$("#letterViewFrm").attr("method","post");
			$("#letterViewFrm").attr("target",title);//새로 열린 popup창과 form태그를 연결
			$("#letterViewFrm").submit();
        }
        
        function funcSendLetter(to){
    		var url="/letterWrite";
    		var title="letterWrite";
    		var status = "left=500px, top=100px, width=470px, height=550px, menubar=no, status=no, scrollbars=yes";
        	var popup = window.open("", title, status);
        	$("input[name=to]").val(to);
			$("#letterWriteFrm").attr("action",url);
			$("#letterWriteFrm").attr("method","post");
			$("#letterWriteFrm").attr("target",title);//새로 열린 popup창과 form태그를 연결
			$("#letterWriteFrm").submit();
    	}
    </script>
</body>
</html>